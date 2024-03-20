package optimizer.entity;

import entity.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class CurrentState {
    private Vehicle vehicle;
    private Trip tripSoFar;
    private int currentCapacity;
    private Node currentNode;
    private Map<String, Set<Candidate>> dropMap;
    private PickupPointsState pickupPointsState;
    private LocalTime currentTime;
    private float distance;

    public CurrentState(PickupPointsState pickupPointsState) {
        tripSoFar = new Trip();
        dropMap = new HashMap<>();
        this.pickupPointsState = pickupPointsState;
    }

    public Candidate pickupACandidate(PickupPoint pickUpPoint) {
        final Pair<Candidate, DropPoint> pair = pickupPointsState.peekCandidate(pickUpPoint);
        if(null == pair) return null;
        final Candidate candidate = pair.getKey();
        final DropPoint dropPoint = pair.getValue();
        final Set<Candidate> candidatesSet = dropMap.getOrDefault(dropPoint.getId(), new HashSet<>());
        if(canPickCandidate(candidate, dropPoint)) {
            pickupPointsState.removeCandidate(pickUpPoint);
            candidatesSet.add(candidate);
            dropMap.put(dropPoint.getId(), candidatesSet);
            record(ActionType.PICKUP, List.of(candidate));
            if(currentTime != null) currentTime = currentTime.plusSeconds(90);
            currentCapacity ++;
        } else {
            return null;
        }
        return candidate;
    }

    public int dropAllPossibleCandidates(DropPoint dropPoint) {
        Set<Candidate> probableCandidates = dropMap.getOrDefault(dropPoint.getId(), Set.of());
        List<Candidate> candidatesActionedOn = new ArrayList<>();
        for(Candidate candidate : probableCandidates) {
            if(canDropCandidate(candidate, dropPoint)) {
                candidatesActionedOn.add(candidate);
            }
        }
        if(candidatesActionedOn.size() > 0) {
            currentCapacity--;
            addEarnings(candidatesActionedOn);
            record(ActionType.DROP, candidatesActionedOn);
            Set<Candidate> candidatesToBeDropped = dropMap.getOrDefault(dropPoint.getId(), new HashSet<>());
            candidatesToBeDropped.removeAll(candidatesActionedOn);
            dropMap.put(dropPoint.getId(), candidatesToBeDropped);
            if(currentTime != null) currentTime = currentTime.plusSeconds(candidatesActionedOn.size() * 60);
        }
        return candidatesActionedOn.size();
    }

    private boolean canDropCandidate(Candidate candidate, DropPoint dropPoint) {
        if(currentTime.isAfter(candidate.getEarliestDropTime()) && currentTime.isBefore(candidate.getLatestDropTime())) return true;
        return false;
    }

    private boolean canPickCandidate(Candidate candidate, DropPoint dropPoint) {
        if(currentTime == null) currentTime = candidate.getEarliestPickupTime().plusSeconds(1);
        if(currentTime.isAfter(candidate.getEarliestPickupTime()) && currentTime.isBefore(candidate.getLatestPickupTime()) && currentCapacity < vehicle.getMaxCapacity()) return true;
        return false;
    }

    private void record(ActionType actionType, List<Candidate> candidates) {
        Stop stop = new Stop();
        stop.setArrivalTime(currentTime);
        Action action = new Action();
        action.setActionType(actionType);
        action.setCandidateList(List.copyOf(candidates));
        stop.setAction(action);

        switch (actionType) {
            case DROP:
                stop.setDepartTime(currentTime.plusSeconds(candidates.size() * 60));
                break;
            case PICKUP:
                stop.setDepartTime(currentTime.plusSeconds(candidates.size() * 90));
                break;
        }
        tripSoFar.addStop(stop);
        this.tripSoFar.setDistanceTravelled(distance);
    }

    public CurrentState clone() {
        CurrentState newState = new CurrentState(pickupPointsState.clone());
        newState.setVehicle(this.vehicle);
        newState.setTripSoFar(this.getTripSoFar().clone());
        newState.setCurrentCapacity(this.getCurrentCapacity());
        newState.setCurrentNode(this.getCurrentNode());
        newState.setDropMap(cloneMap(this.getDropMap()));
        newState.setCurrentTime(this.getCurrentTime());
        newState.setDistance(this.getDistance());
        return newState;
    }

    private Map<String, Set<Candidate>> cloneMap(Map<String, Set<Candidate>> mapToClone) {
        return mapToClone.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> new HashSet<>(e.getValue())));
    }

    private void addEarnings(List<Candidate> candidates) {
        for(Candidate candidate : candidates) {
            this.getTripSoFar().setEarnings(this.getTripSoFar().getEarnings() + (1000 * candidate.getSubscriptionTier().getProfitWeight()));
        }
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(other == null || other.getClass() != this.getClass()) return false;

        CurrentState otherState = (CurrentState) other;

        return this.tripSoFar.equals(otherState.tripSoFar)
                && this.currentNode.equals(otherState.currentNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripSoFar, currentNode);
    }

}

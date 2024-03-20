package entity;


import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PickupPointsState {
    private Map<PickupPoint, Map<Candidate, DropPoint>> candidatesMap;

    public PickupPointsState() {
        candidatesMap = new HashMap<>();
    }

    public void addCandidate(PickupPoint pickupPoint, Candidate candidate, DropPoint dropPoint){
        Map<Candidate, DropPoint> currentMap = candidatesMap.getOrDefault(pickupPoint, new HashMap<>());
        currentMap.put(candidate, dropPoint);
        candidatesMap.put(pickupPoint,currentMap);
    }

    public Pair<Candidate, DropPoint> peekCandidate(PickupPoint pickupPoint) {
        Map<Candidate, DropPoint> currentMap = candidatesMap.getOrDefault(pickupPoint, new HashMap<>());
        Candidate candidate = currentMap.keySet().stream().findFirst().orElse(null);
        if(null == candidate) return null;
        Pair<Candidate, DropPoint> returnValue = ImmutablePair.of(candidate, currentMap.get(candidate));
        return returnValue;
    }

    public Pair<Candidate, DropPoint> removeCandidate(PickupPoint pickupPoint) {
        Map<Candidate, DropPoint> currentMap = candidatesMap.getOrDefault(pickupPoint, new HashMap<>());
        Candidate candidate = currentMap.keySet().stream().findFirst().orElse(null);
        if(null == candidate) return null;
        Pair<Candidate, DropPoint> returnValue = ImmutablePair.of(candidate, currentMap.get(candidate));
        currentMap.remove(candidate);
        if(currentMap.isEmpty()) {
            candidatesMap.remove(pickupPoint);
        } else {
            candidatesMap.put(pickupPoint, currentMap);
        }
        return returnValue;
    }

    public PickupPointsState clone() {
        PickupPointsState newState = new PickupPointsState();
        newState.candidatesMap = candidatesMap.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> cloneMap(e.getValue())));
        return newState;
    }

    public List<Candidate> getAllCandidates(PickupPoint pickupPoint) {
        Map<Candidate, DropPoint> currentMap = candidatesMap.getOrDefault(pickupPoint, new HashMap<>());
        return currentMap.entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Candidate> getAllCandidates() {
        List<Candidate> allCandidates = new ArrayList<>();
        candidatesMap.keySet()
                .stream()
                .map(p -> getAllCandidates(p))
                .forEach(l -> allCandidates.addAll(l));
        return allCandidates;
    }

    private Map<Candidate, DropPoint> cloneMap(Map<Candidate, DropPoint> mapToClone) {
        return mapToClone.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }
}

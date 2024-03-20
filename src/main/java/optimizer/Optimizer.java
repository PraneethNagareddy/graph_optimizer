package optimizer;

import entity.*;
import optimizer.entity.CurrentState;
import optimizer.entity.Edge;
import optimizer.entity.Node;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Optimizer {

    public static Trip getOptimizedTour(Vehicle vehicle, Map<Node, Set<Edge>> graph, PickupPointsState initPickupPointsState) {
        Trip optimizedTrip = null;
        LocalTime cutOffTime = getMaxDropTime(initPickupPointsState);
        List<PickupPoint> pickupPoints = getPickupPoints(graph);
        for(PickupPoint pickupPoint : pickupPoints) {
            Trip trip = bfs(pickupPoint, vehicle, graph, initPickupPointsState, cutOffTime);
            if(optimizedTrip == null) optimizedTrip = trip;
            else {
                if(optimizedTrip.compareTo(trip) < 0) {
                    optimizedTrip = trip.clone();
                }
            }
        }
        return optimizedTrip;
    }

    private static Trip bfs(PickupPoint pickupPoint, Vehicle vehicle, Map<Node, Set<Edge>> graph, PickupPointsState initPickupPointsState, LocalTime cutOffTime) {
        Trip maxTrip = null;
        CurrentState currentState = initializeCurrentTrip(vehicle, pickupPoint, initPickupPointsState);
        Queue<CurrentState> queue = new LinkedList<>();
        queue.offer(currentState);
        Set<CurrentState> visited = new HashSet<>();
        while(isNotEnd(queue)) {

            currentState = queue.poll();

            if(visited.contains(currentState)) continue;
            if(currentState.getCurrentTime() != null && currentState.getCurrentTime().isAfter(cutOffTime))
                continue;

            CurrentState clonedCurrent = currentState.clone();
            Node node = currentState.getCurrentNode();

            if(node.isPickupPoint()) {
                List<CurrentState> states = new ArrayList<>();
                CurrentState noPick = currentState.clone();
                states.add(noPick);
                Candidate candidate = currentState.pickupACandidate(node.getPickupPoint());
                LocalTime prevTime = currentState.getCurrentTime();
                while(candidate != null) {
                    CurrentState pick = currentState.clone();
                    if(prevTime == null) {
                        pick.setCurrentTime(candidate.getEarliestPickupTime().plusSeconds(1));
                    } else {
                        pick.setCurrentTime(prevTime.plusSeconds(60));
                    }
                    candidate = currentState.pickupACandidate(node.getPickupPoint());
                    states.add(pick);
                    prevTime = pick.getCurrentTime();
                    //currentState = pick;
                }
                for(Edge edge : graph.getOrDefault(node, Set.of())) {
                    for(CurrentState state : states) {
                        CurrentState clonedState = state.clone();
                        if(clonedState.getCurrentTime() != null) clonedState.setCurrentTime(clonedState.getCurrentTime().plusMinutes(edge.getMinutes()));
                        clonedState.setDistance(clonedState.getDistance() + edge.getDistance());
                        clonedState.setCurrentNode(edge.getDestinationNode());
                        queue.offer(clonedState);
                    }
                }
            }  else {
                int droppedCount = currentState.dropAllPossibleCandidates(node.getDropPoint());
                if(maxTrip == null) {
                    maxTrip = currentState.getTripSoFar().clone();
                } else {
                    if(maxTrip.compareTo(currentState.getTripSoFar()) < 0) {
                        maxTrip = currentState.getTripSoFar().clone();
                    }
                }
                for(Edge edge : graph.getOrDefault(node, Set.of())) {
                    CurrentState newState = currentState.clone();
                    if(currentState.getCurrentTime() != null) newState.setCurrentTime(currentState.getCurrentTime().plusSeconds(droppedCount * 90));
                    if(currentState.getCurrentTime() != null) newState.setCurrentTime(currentState.getCurrentTime().plusMinutes(edge.getMinutes()));
                    newState.setDistance(currentState.getDistance() + edge.getDistance());
                    newState.setCurrentNode(edge.getDestinationNode());
                    queue.offer(newState);
                }
            }
            visited.add(clonedCurrent);
        }
        return maxTrip;
    }

    private static CurrentState initializeCurrentTrip(Vehicle vehicle, PickupPoint pickupPoint, PickupPointsState initPickupPointsState) {
        Node node = new Node();
        node.setPickupPoint(pickupPoint);
        CurrentState currentTrip = new CurrentState(initPickupPointsState);
        currentTrip.setVehicle(vehicle);
        currentTrip.setCurrentNode(node);
//        currentTrip.setCurrentTime(getEarliestTime(pickupPoint, initPickupPointsState));

        return currentTrip;
    }

    private static List<PickupPoint> getPickupPoints(Map<Node, Set<Edge>> graph) {
        return graph.keySet().stream()
                .map(Node::getPickupPoint)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static boolean isNotEnd(Queue queue) {
        return !queue.isEmpty();
    }

    private static LocalTime getEarliestTime(PickupPoint pickupPoint, PickupPointsState pickupPointsState) {
        LocalTime minLocalTime = LocalTime.MAX;
        List<Candidate> allCandidates = pickupPointsState.getAllCandidates(pickupPoint);
        for(Candidate c : allCandidates) {
            if(minLocalTime.isAfter(c.getEarliestPickupTime()))
                minLocalTime = c.getEarliestPickupTime();
        }
        return minLocalTime;
    }

    private static LocalTime getMaxDropTime(PickupPointsState initPickupPointsState) {
        LocalTime maxDropTime = LocalTime.MIN;
        final List<Candidate> candidateList = initPickupPointsState.getAllCandidates();
        for(Candidate candidate : candidateList) {
            if(candidate.getLatestDropTime() != null && maxDropTime.isBefore(candidate.getLatestDropTime()))
                maxDropTime = candidate.getLatestDropTime().plusSeconds(0);
        }
        return maxDropTime;
    }
}

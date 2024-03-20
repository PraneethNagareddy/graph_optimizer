package v2.state;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import v2.entity.Candidate;
import v2.entity.DropPoint;
import v2.entity.PickupPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestsState {
    private Map<PickupPoint, Map<Candidate, DropPoint>> requestsMap;

    public RequestsState() {
        requestsMap = new HashMap<>();
    }

    public void addCandidate(PickupPoint pickupPoint, Candidate candidate, DropPoint dropPoint){
        Map<Candidate, DropPoint> currentMap = requestsMap.getOrDefault(pickupPoint, new HashMap<>());
        currentMap.put(candidate, dropPoint);
        requestsMap.put(pickupPoint,currentMap);
    }

    public Pair<Candidate, DropPoint> peekCandidate(PickupPoint pickupPoint) {
        Map<Candidate, DropPoint> currentMap = requestsMap.getOrDefault(pickupPoint, new HashMap<>());
        Candidate candidate = currentMap.keySet().stream().findFirst().orElse(null);
        if(null == candidate) return null;
        Pair<Candidate, DropPoint> returnValue = ImmutablePair.of(candidate, currentMap.get(candidate));
        return returnValue;
    }

    public Pair<Candidate, DropPoint> removeCandidate(PickupPoint pickupPoint) {
        Map<Candidate, DropPoint> currentMap = requestsMap.getOrDefault(pickupPoint, new HashMap<>());
        Candidate candidate = currentMap.keySet().stream().findFirst().orElse(null);
        if(null == candidate) return null;
        Pair<Candidate, DropPoint> returnValue = ImmutablePair.of(candidate, currentMap.get(candidate));
        currentMap.remove(candidate);
        requestsMap.put(pickupPoint,currentMap);
        return returnValue;
    }

    public RequestsState clone() {
        RequestsState newState = new RequestsState();
        newState.requestsMap = requestsMap.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> cloneMap(e.getValue())));
        return newState;
    }

    public List<Candidate> getAllCandidates(PickupPoint pickupPoint) {
        Map<Candidate, DropPoint> currentMap = requestsMap.getOrDefault(pickupPoint, new HashMap<>());
        return currentMap.entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Candidate> getAllCandidates() {
        List<Candidate> allCandidates = new ArrayList<>();
        requestsMap.keySet()
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

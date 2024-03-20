import entity.*;
import optimizer.Optimizer;
import optimizer.entity.Edge;
import optimizer.entity.Node;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static Map<String, Candidate> candidatesMap = new HashMap<>();
    public static Map<String, PickupPoint> pickupPointMap = new HashMap<>();
    public static Map<String, DropPoint> dropPointMap = new HashMap<>();


    public static void main(String args[]) {

        PickupPointsState pickupPointsState = buildTestState();
        Map<Node, Set<Edge>> graph = buildTestGraph();
        Vehicle vehicle = getTestVehicle();
        Trip optimizedTrip = Optimizer.getOptimizedTour(vehicle, graph, pickupPointsState);
        printTrip(optimizedTrip);
    }

    private static Map<Node, Set<Edge>> buildTestGraph(){
        Map<Node, Set<Edge>> graph = new HashMap<>();
        Set<Edge> p1Edges = new HashSet<>();
        p1Edges.add(new Edge(3, 3, new Node(pickupPointMap.get("P2"), null)));
        p1Edges.add(new Edge(5, 5, new Node(null, dropPointMap.get("D1"))));
        p1Edges.add(new Edge(8, 8, new Node(null, dropPointMap.get("D2"))));
        p1Edges.add(new Edge(9, 9, new Node(pickupPointMap.get("P3"), null)));
        graph.put(new Node(pickupPointMap.get("P1"), null), p1Edges);

        Set<Edge> p2Edges = new HashSet<>();
        p2Edges.add(new Edge(3, 3, new Node(pickupPointMap.get("P1"), null)));
        p2Edges.add(new Edge(15, 15, new Node(null, dropPointMap.get("D1"))));
        p2Edges.add(new Edge(30, 30, new Node(null, dropPointMap.get("D2"))));
        p2Edges.add(new Edge(4, 4, new Node(null, dropPointMap.get("D3"))));
        p2Edges.add(new Edge(10, 10, new Node(pickupPointMap.get("P3"), null)));
        graph.put(new Node(pickupPointMap.get("P2"), null), p2Edges);

        Set<Edge> p3Edges = new HashSet<>();
        p3Edges.add(new Edge(10, 10, new Node(pickupPointMap.get("P2"), null)));
        p3Edges.add(new Edge(9, 9, new Node(pickupPointMap.get("P1"), null)));
        p3Edges.add(new Edge(5, 5, new Node(null, dropPointMap.get("D3"))));
        graph.put(new Node(pickupPointMap.get("P3"), null), p3Edges);



        Set<Edge> d1Edges = new HashSet<>();
        d1Edges.add(new Edge(5, 5, new Node(pickupPointMap.get("P1"), null)));
        d1Edges.add(new Edge(15, 15, new Node(pickupPointMap.get("P2"), null)));
        graph.put(new Node(null, dropPointMap.get("D1")), d1Edges);

        Set<Edge> d2Edges = new HashSet<>();
        d2Edges.add(new Edge(8, 8, new Node(pickupPointMap.get("P1"), null)));
        d2Edges.add(new Edge(30, 30, new Node(pickupPointMap.get("P2"), null)));
        graph.put(new Node(null, dropPointMap.get("D2")), d2Edges);

        Set<Edge> d3Edges = new HashSet<>();
        d3Edges.add(new Edge(4, 4, new Node(pickupPointMap.get("P2"), null)));
        d3Edges.add(new Edge(5, 5, new Node(pickupPointMap.get("P3"), null)));
        graph.put(new Node(null, dropPointMap.get("D3")), d3Edges);


        return graph;
    }

    private static PickupPointsState buildTestState() {
        buildTestCandidates();
        buildTestPickupPoints();
        buildTestDropPoints();
        PickupPointsState state = new PickupPointsState();
        state.addCandidate(pickupPointMap.get("P1"), candidatesMap.get("C1"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P1"), candidatesMap.get("C2"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P1"), candidatesMap.get("C3"), dropPointMap.get("D3"));
        state.addCandidate(pickupPointMap.get("P1"), candidatesMap.get("C4"), dropPointMap.get("D1"));

        state.addCandidate(pickupPointMap.get("P2"), candidatesMap.get("C5"), dropPointMap.get("D3"));
        state.addCandidate(pickupPointMap.get("P2"), candidatesMap.get("C6"), dropPointMap.get("D3"));

        state.addCandidate(pickupPointMap.get("P3"), candidatesMap.get("C7"), dropPointMap.get("D2"));

        return state;
    }

    private static Vehicle getTestVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Maruti");
        vehicle.setModel("Ertiga");
        vehicle.setMaxCapacity(7);
        vehicle.setPricePerKm(10);
        return vehicle;
    }

    private static void printTrip(Trip trip) {
        System.out.println("*_*_*_*_*_*_*_*_*_Printing Trip_*_*_*_*_*_*_*_*_*");
        System.out.println("Total Earnings:" + trip.getEarnings());
        System.out.println("Total distance Travelled:" + trip.getDistanceTravelled());
        System.out.println("Total time elapsed(in Minutes):" + trip.timeElapsedInMinutes());
        System.out.println("Stops:");

        for(Stop stop : trip.getStops()) {
            System.out.println("Action:" + stop.getAction().getActionType());
            System.out.println("\r"+ stop.getAction().getCandidateList());
            System.out.println("\rArrival Time:" + stop.getArrivalTime());
            System.out.println("\rDepart Time:" + stop.getDepartTime());
        }
    }

    private static void buildTestDropPoints() {
        DropPoint d1 = new DropPoint();
        d1.setId("D1");
        d1.setName("D1");

        DropPoint d2 = new DropPoint();
        d2.setId("D2");
        d2.setName("D2");

        DropPoint d3 = new DropPoint();
        d3.setId("D3");
        d3.setName("D3");

        dropPointMap.put("D1", d1);
        dropPointMap.put("D2", d2);
        dropPointMap.put("D3", d3);
    }

    private static void buildTestPickupPoints() {
        PickupPoint p1 = new PickupPoint();
        p1.setId("P1");
        p1.setName("P1");

        PickupPoint p2 = new PickupPoint();
        p2.setId("P2");
        p2.setName("P2");

        PickupPoint p3 = new PickupPoint();
        p3.setId("P3");
        p3.setName("P3");

        pickupPointMap.put("P1", p1);
        pickupPointMap.put("P2", p2);
        pickupPointMap.put("P3", p3);
    }

    private static void buildTestCandidates() {
        Candidate c1 = new Candidate();
        c1.setId("C1");
        c1.setName("C1");
        c1.setEarliestPickupTime(LocalTime.of(7,30));
        c1.setEarliestDropTime(LocalTime.of(8,30));
        c1.setLatestDropTime(LocalTime.of(9,30));
        c1.setLatestPickupTime(LocalTime.of(8,00));
        c1.setSubscriptionTier(SubscriptionTier.TIER1);


        Candidate c2 = new Candidate();
        c2.setId("C2");
        c2.setName("C2");
        c2.setEarliestPickupTime(LocalTime.of(7,00));
        c2.setEarliestDropTime(LocalTime.of(8,00));
        c2.setLatestDropTime(LocalTime.of(9,30));
        c2.setLatestPickupTime(LocalTime.of(8,00));
        c2.setSubscriptionTier(SubscriptionTier.TIER2);


        Candidate c3 = new Candidate();
        c3.setId("C3");
        c3.setName("C3");
        c3.setEarliestPickupTime(LocalTime.of(7,30));
        c3.setEarliestDropTime(LocalTime.of(8,30));
        c3.setLatestDropTime(LocalTime.of(9,30));
        c3.setLatestPickupTime(LocalTime.of(8,00));
        c3.setSubscriptionTier(SubscriptionTier.TIER1);


        Candidate c4 = new Candidate();
        c4.setId("C4");
        c4.setName("C4");
        c4.setEarliestPickupTime(LocalTime.of(7,00));
        c4.setEarliestDropTime(LocalTime.of(8,00));
        c4.setLatestDropTime(LocalTime.of(9,30));
        c4.setLatestPickupTime(LocalTime.of(8,00));
        c4.setSubscriptionTier(SubscriptionTier.TIER2);


        Candidate c5 = new Candidate();
        c5.setId("C5");
        c5.setName("C5");
        c5.setEarliestPickupTime(LocalTime.of(7,45));
        c5.setEarliestDropTime(LocalTime.of(8,45));
        c5.setLatestDropTime(LocalTime.of(9,45));
        c5.setLatestPickupTime(LocalTime.of(8,15));
        c5.setSubscriptionTier(SubscriptionTier.TIER1);


        Candidate c6 = new Candidate();
        c6.setId("C6");
        c6.setName("C6");
        c6.setEarliestPickupTime(LocalTime.of(7,30));
        c6.setEarliestDropTime(LocalTime.of(8,30));
        c6.setLatestDropTime(LocalTime.of(9,30));
        c6.setLatestPickupTime(LocalTime.of(8,00));
        c6.setSubscriptionTier(SubscriptionTier.TIER1);


        Candidate c7 = new Candidate();
        c7.setId("C7");
        c7.setName("C7");
        c7.setEarliestPickupTime(LocalTime.of(7,30));
        c7.setEarliestDropTime(LocalTime.of(8,30));
        c7.setLatestDropTime(LocalTime.of(9,30));
        c7.setLatestPickupTime(LocalTime.of(8,00));
        c7.setSubscriptionTier(SubscriptionTier.TIER1);

        candidatesMap.put("C1", c1);
        candidatesMap.put("C2", c2);
        candidatesMap.put("C3", c3);
        candidatesMap.put("C4", c4);
        candidatesMap.put("C5", c5);
        candidatesMap.put("C6", c6);
        candidatesMap.put("C7", c7);
    }
}

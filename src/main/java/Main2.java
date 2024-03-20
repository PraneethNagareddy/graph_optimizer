import entity.*;
import optimizer.Optimizer;
import optimizer.entity.Edge;
import optimizer.entity.Node;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main2 {

    public static Map<String, Candidate> candidatesMap = new HashMap<>();
    public static Map<String, PickupPoint> pickupPointMap = new HashMap<>();
    public static Map<String, DropPoint> dropPointMap = new HashMap<>();


    public static void main(String args[]) {

        long startTime = System.currentTimeMillis();
        PickupPointsState pickupPointsState = buildTestState();
        Map<Node, Set<Edge>> graph = buildTestGraph();
        Vehicle vehicle = getTestVehicle();
        Trip optimizedTrip = Optimizer.getOptimizedTour(vehicle, graph, pickupPointsState);
        long endTime = System.currentTimeMillis();
        printTrip(optimizedTrip);
        System.out.println("Time taken to compute (in milli seconds): " + (endTime - startTime));
    }

    private static Map<Node, Set<Edge>> buildTestGraph(){
        Map<Node, Set<Edge>> graph = new HashMap<>();
        Set<Edge> p1Edges = new HashSet<>();
        p1Edges.add(new Edge(29,12, new Node(pickupPointMap.get("P2"), null)));
        p1Edges.add(new Edge(1,0.5f, new Node(pickupPointMap.get("P3"), null)));
        p1Edges.add(new Edge(8,3, new Node(pickupPointMap.get("P4"), null)));
        p1Edges.add(new Edge(29,10, new Node(pickupPointMap.get("P5"), null)));
        p1Edges.add(new Edge(29,12, new Node(pickupPointMap.get("P6"), null)));
        p1Edges.add(new Edge(14,5.7f, new Node(pickupPointMap.get("P7"), null)));
        p1Edges.add(new Edge(33,15, new Node(pickupPointMap.get("P8"), null)));
        p1Edges.add(new Edge(31,11, new Node(pickupPointMap.get("P9"), null)));
        p1Edges.add(new Edge(34,21, new Node(pickupPointMap.get("P10"), null)));
        p1Edges.add(new Edge(22,10, new Node(pickupPointMap.get("P11"), null)));
        p1Edges.add(new Edge(31,14, new Node(pickupPointMap.get("P12"), null)));
        p1Edges.add(new Edge(25,13, new Node(pickupPointMap.get("P13"), null)));
        p1Edges.add(new Edge(29,12, new Node(pickupPointMap.get("P14"), null)));
        p1Edges.add(new Edge(60,25, new Node(pickupPointMap.get("P15"), null)));
        p1Edges.add(new Edge(32,14, new Node(pickupPointMap.get("P16"), null)));
        p1Edges.add(new Edge(47,21, new Node(pickupPointMap.get("P17"), null)));
        p1Edges.add(new Edge(20,8.4f, new Node(null, dropPointMap.get("D1"))));

        Set<Edge> p2Edges = new HashSet<>();
        p2Edges.add(new Edge(29,12, new Node(pickupPointMap.get("P1"), null)));
        p2Edges.add(new Edge(26,11, new Node(pickupPointMap.get("P3"), null)));
        p2Edges.add(new Edge(27,12, new Node(pickupPointMap.get("P4"), null)));
        p2Edges.add(new Edge(40,17, new Node(pickupPointMap.get("P5"), null)));
        p2Edges.add(new Edge(1,0.1f, new Node(pickupPointMap.get("P6"), null)));
        p2Edges.add(new Edge(25,8.9f, new Node(pickupPointMap.get("P7"), null)));
        p2Edges.add(new Edge(13,6, new Node(pickupPointMap.get("P8"), null)));
        p2Edges.add(new Edge(32,12, new Node(pickupPointMap.get("P9"), null)));
        p2Edges.add(new Edge(47,30, new Node(pickupPointMap.get("P10"), null)));
        p2Edges.add(new Edge(35,14, new Node(pickupPointMap.get("P11"), null)));
        p2Edges.add(new Edge(31,12, new Node(pickupPointMap.get("P12"), null)));
        p2Edges.add(new Edge(35,16, new Node(pickupPointMap.get("P13"), null)));
        p2Edges.add(new Edge(1,0.1f, new Node(pickupPointMap.get("P14"), null)));
        p2Edges.add(new Edge(59,23, new Node(pickupPointMap.get("P15"), null)));
        p2Edges.add(new Edge(43,22, new Node(pickupPointMap.get("P16"), null)));
        p2Edges.add(new Edge(48,17, new Node(pickupPointMap.get("P17"), null)));
        p2Edges.add(new Edge(28,11, new Node(null, dropPointMap.get("D1"))));


        Set<Edge> p3Edges = new HashSet<>();
        p3Edges.add(new Edge(1,0.5f, new Node(pickupPointMap.get("P1"), null)));
        p3Edges.add(new Edge(29,11, new Node(pickupPointMap.get("P2"), null)));
        p3Edges.add(new Edge(9,4.1f, new Node(pickupPointMap.get("P4"), null)));
        p3Edges.add(new Edge(22,11, new Node(pickupPointMap.get("P5"), null)));
        p3Edges.add(new Edge(29,11, new Node(pickupPointMap.get("P6"), null)));
        p3Edges.add(new Edge(13,5.2f, new Node(pickupPointMap.get("P7"), null)));
        p3Edges.add(new Edge(32,14, new Node(pickupPointMap.get("P8"), null)));
        p3Edges.add(new Edge(30,11, new Node(pickupPointMap.get("P9"), null)));
        p3Edges.add(new Edge(35,22, new Node(pickupPointMap.get("P10"), null)));
        p3Edges.add(new Edge(24,10, new Node(pickupPointMap.get("P11"), null)));
        p3Edges.add(new Edge(30,14, new Node(pickupPointMap.get("P12"), null)));
        p3Edges.add(new Edge(26,13, new Node(pickupPointMap.get("P13"), null)));
        p3Edges.add(new Edge(29,11, new Node(pickupPointMap.get("P14"), null)));
        p3Edges.add(new Edge(60,25, new Node(pickupPointMap.get("P15"), null)));
        p3Edges.add(new Edge(31,16, new Node(pickupPointMap.get("P16"), null)));
        p3Edges.add(new Edge(48,21, new Node(pickupPointMap.get("P17"), null)));
        p3Edges.add(new Edge(21,8.3f, new Node(null, dropPointMap.get("D1"))));



        Set<Edge> p4Edges = new HashSet<>();
        p4Edges.add(new Edge(7,3.2f, new Node(pickupPointMap.get("P1"), null)));
        p4Edges.add(new Edge(28,12, new Node(pickupPointMap.get("P2"), null)));
        p4Edges.add(new Edge(8,3.7f, new Node(pickupPointMap.get("P3"), null)));
        p4Edges.add(new Edge(20,10, new Node(pickupPointMap.get("P5"), null)));
        p4Edges.add(new Edge(28,12, new Node(pickupPointMap.get("P6"), null)));
        p4Edges.add(new Edge(16,7.9f, new Node(pickupPointMap.get("P7"), null)));
        p4Edges.add(new Edge(35,17, new Node(pickupPointMap.get("P8"), null)));
        p4Edges.add(new Edge(27,10, new Node(pickupPointMap.get("P9"), null)));
        p4Edges.add(new Edge(31,20, new Node(pickupPointMap.get("P10"), null)));
        p4Edges.add(new Edge(22,11, new Node(pickupPointMap.get("P11"), null)));
        p4Edges.add(new Edge(32,16, new Node(pickupPointMap.get("P12"), null)));
        p4Edges.add(new Edge(24,11, new Node(pickupPointMap.get("P13"), null)));
        p4Edges.add(new Edge(28,12, new Node(pickupPointMap.get("P14"), null)));
        p4Edges.add(new Edge(54,24, new Node(pickupPointMap.get("P15"), null)));
        p4Edges.add(new Edge(29,13, new Node(pickupPointMap.get("P16"), null)));
        p4Edges.add(new Edge(54,22, new Node(pickupPointMap.get("P17"), null)));
        p4Edges.add(new Edge(16,7.5f, new Node(null, dropPointMap.get("D1"))));



        Set<Edge> p5Edges = new HashSet<>();
        p5Edges.add(new Edge(21,8.8f, new Node(pickupPointMap.get("P1"), null)));
        p5Edges.add(new Edge(43,16, new Node(pickupPointMap.get("P2"), null)));
        p5Edges.add(new Edge(22,9.4f, new Node(pickupPointMap.get("P3"), null)));
        p5Edges.add(new Edge(24,10, new Node(pickupPointMap.get("P4"), null)));
        p5Edges.add(new Edge(43,16, new Node(pickupPointMap.get("P6"), null)));
        p5Edges.add(new Edge(25,9.1f, new Node(pickupPointMap.get("P7"), null)));
        p5Edges.add(new Edge(45,19, new Node(pickupPointMap.get("P8"), null)));
        p5Edges.add(new Edge(47,18, new Node(pickupPointMap.get("P9"), null)));
        p5Edges.add(new Edge(33,24, new Node(pickupPointMap.get("P10"), null)));
        p5Edges.add(new Edge(18,7.4f, new Node(pickupPointMap.get("P11"), null)));
        p5Edges.add(new Edge(57,23, new Node(pickupPointMap.get("P12"), null)));
        p5Edges.add(new Edge(33,16, new Node(pickupPointMap.get("P13"), null)));
        p5Edges.add(new Edge(43,16, new Node(pickupPointMap.get("P14"), null)));
        p5Edges.add(new Edge(68,31, new Node(pickupPointMap.get("P15"), null)));
        p5Edges.add(new Edge(31,16, new Node(pickupPointMap.get("P16"), null)));
        p5Edges.add(new Edge(74,28, new Node(pickupPointMap.get("P17"), null)));
        p5Edges.add(new Edge(38,16, new Node(null, dropPointMap.get("D1"))));



        Set<Edge> p6Edges = new HashSet<>();
        p6Edges.add(new Edge(29,12, new Node(pickupPointMap.get("P1"), null)));
        p6Edges.add(new Edge(26,11, new Node(pickupPointMap.get("P3"), null)));
        p6Edges.add(new Edge(27,12, new Node(pickupPointMap.get("P4"), null)));
        p6Edges.add(new Edge(40,17, new Node(pickupPointMap.get("P5"), null)));
        p6Edges.add(new Edge(1,0.1f, new Node(pickupPointMap.get("P2"), null)));
        p6Edges.add(new Edge(25,8.9f, new Node(pickupPointMap.get("P7"), null)));
        p6Edges.add(new Edge(13,6, new Node(pickupPointMap.get("P8"), null)));
        p6Edges.add(new Edge(32,12, new Node(pickupPointMap.get("P9"), null)));
        p6Edges.add(new Edge(47,30, new Node(pickupPointMap.get("P10"), null)));
        p6Edges.add(new Edge(35,14, new Node(pickupPointMap.get("P11"), null)));
        p6Edges.add(new Edge(31,12, new Node(pickupPointMap.get("P12"), null)));
        p6Edges.add(new Edge(35,16, new Node(pickupPointMap.get("P13"), null)));
        p6Edges.add(new Edge(1,0.1f, new Node(pickupPointMap.get("P14"), null)));
        p6Edges.add(new Edge(59,23, new Node(pickupPointMap.get("P15"), null)));
        p6Edges.add(new Edge(43,22, new Node(pickupPointMap.get("P16"), null)));
        p6Edges.add(new Edge(48,17, new Node(pickupPointMap.get("P17"), null)));
        p6Edges.add(new Edge(29,11, new Node(null, dropPointMap.get("D1"))));



        Set<Edge> p7Edges = new HashSet<>();
        p7Edges.add(new Edge(15,6, new Node(pickupPointMap.get("P1"), null)));
        p7Edges.add(new Edge(24,8.9f, new Node(pickupPointMap.get("P2"), null)));
        p7Edges.add(new Edge(16,6.5f, new Node(pickupPointMap.get("P3"), null)));
        p7Edges.add(new Edge(17,7.2f, new Node(pickupPointMap.get("P4"), null)));
        p7Edges.add(new Edge(20,8.8f, new Node(pickupPointMap.get("P5"), null)));
        p7Edges.add(new Edge(24,8.9f, new Node(pickupPointMap.get("P6"), null)));
        p7Edges.add(new Edge(26,12, new Node(pickupPointMap.get("P8"), null)));
        p7Edges.add(new Edge(37,16, new Node(pickupPointMap.get("P9"), null)));
        p7Edges.add(new Edge(44,26, new Node(pickupPointMap.get("P10"), null)));
        p7Edges.add(new Edge(14,5.6f, new Node(pickupPointMap.get("P11"), null)));
        p7Edges.add(new Edge(36,16, new Node(pickupPointMap.get("P12"), null)));
        p7Edges.add(new Edge(36,16, new Node(pickupPointMap.get("P13"), null)));
        p7Edges.add(new Edge(24,8.9f, new Node(pickupPointMap.get("P14"), null)));
        p7Edges.add(new Edge(67,27, new Node(pickupPointMap.get("P15"), null)));
        p7Edges.add(new Edge(42,18, new Node(pickupPointMap.get("P16"), null)));
        p7Edges.add(new Edge(53,21, new Node(pickupPointMap.get("P17"), null)));
        p7Edges.add(new Edge(30,12, new Node(null, dropPointMap.get("D1"))));



        Set<Edge> p8Edges = new HashSet<>();
        p8Edges.add(new Edge(39,16, new Node(pickupPointMap.get("P1"), null)));
        p8Edges.add(new Edge(13,5.3f, new Node(pickupPointMap.get("P2"), null)));
        p8Edges.add(new Edge(37,16, new Node(pickupPointMap.get("P3"), null)));
        p8Edges.add(new Edge(38,16, new Node(pickupPointMap.get("P4"), null)));
        p8Edges.add(new Edge(45,37, new Node(pickupPointMap.get("P5"), null)));
        p8Edges.add(new Edge(13,5.3f, new Node(pickupPointMap.get("P6"), null)));
        p8Edges.add(new Edge(37,14, new Node(pickupPointMap.get("P7"), null)));
        p8Edges.add(new Edge(43,17, new Node(pickupPointMap.get("P9"), null)));
        p8Edges.add(new Edge(51,54, new Node(pickupPointMap.get("P10"), null)));
        p8Edges.add(new Edge(48,19, new Node(pickupPointMap.get("P11"), null)));
        p8Edges.add(new Edge(43,17, new Node(pickupPointMap.get("P12"), null)));
        p8Edges.add(new Edge(46,21, new Node(pickupPointMap.get("P13"), null)));
        p8Edges.add(new Edge(13,5.3f, new Node(pickupPointMap.get("P14"), null)));
        p8Edges.add(new Edge(73,28, new Node(pickupPointMap.get("P15"), null)));
        p8Edges.add(new Edge(54,27, new Node(pickupPointMap.get("P16"), null)));
        p8Edges.add(new Edge(58,22, new Node(pickupPointMap.get("P17"), null)));
        p8Edges.add(new Edge(41,16, new Node(null, dropPointMap.get("D1"))));



        Set<Edge> p9Edges = new HashSet<>();
        p9Edges.add(new Edge(32,13, new Node(pickupPointMap.get("P1"), null)));
        p9Edges.add(new Edge(33,11, new Node(pickupPointMap.get("P2"), null)));
        p9Edges.add(new Edge(31,13, new Node(pickupPointMap.get("P3"), null)));
        p9Edges.add(new Edge(30,8.8f, new Node(pickupPointMap.get("P4"), null)));
        p9Edges.add(new Edge(50,22, new Node(pickupPointMap.get("P5"), null)));
        p9Edges.add(new Edge(33,11, new Node(pickupPointMap.get("P6"), null)));
        p9Edges.add(new Edge(37,14, new Node(pickupPointMap.get("P7"), null)));
        p9Edges.add(new Edge(47,17, new Node(pickupPointMap.get("P8"), null)));
        p9Edges.add(new Edge(45,19, new Node(pickupPointMap.get("P10"), null)));
        p9Edges.add(new Edge(48,19, new Node(pickupPointMap.get("P11"), null)));
        p9Edges.add(new Edge(9,3.2f, new Node(pickupPointMap.get("P12"), null)));
        p9Edges.add(new Edge(25,10, new Node(pickupPointMap.get("P13"), null)));
        p9Edges.add(new Edge(32,11, new Node(pickupPointMap.get("P14"), null)));
        p9Edges.add(new Edge(40,14, new Node(pickupPointMap.get("P15"), null)));
        p9Edges.add(new Edge(33,13, new Node(pickupPointMap.get("P16"), null)));
        p9Edges.add(new Edge(29,11, new Node(pickupPointMap.get("P17"), null)));
        p9Edges.add(new Edge(18,4.8f, new Node(null, dropPointMap.get("D1"))));



        Set<Edge> p10Edges = new HashSet<>();
        p10Edges.add(new Edge(34,22, new Node(pickupPointMap.get("P1"), null)));
        p10Edges.add(new Edge(54,29, new Node(pickupPointMap.get("P2"), null)));
        p10Edges.add(new Edge(33,22, new Node(pickupPointMap.get("P3"), null)));
        p10Edges.add(new Edge(27,19, new Node(pickupPointMap.get("P4"), null)));
        p10Edges.add(new Edge(31,23, new Node(pickupPointMap.get("P5"), null)));
        p10Edges.add(new Edge(54,29, new Node(pickupPointMap.get("P6"), null)));
        p10Edges.add(new Edge(41,25, new Node(pickupPointMap.get("P7"), null)));
        p10Edges.add(new Edge(50,54, new Node(pickupPointMap.get("P8"), null)));
        p10Edges.add(new Edge(48,20, new Node(pickupPointMap.get("P9"), null)));
        p10Edges.add(new Edge(39,35, new Node(pickupPointMap.get("P11"), null)));
        p10Edges.add(new Edge(40,18, new Node(pickupPointMap.get("P12"), null)));
        p10Edges.add(new Edge(30,15, new Node(pickupPointMap.get("P13"), null)));
        p10Edges.add(new Edge(53,29, new Node(pickupPointMap.get("P14"), null)));
        p10Edges.add(new Edge(44,21, new Node(pickupPointMap.get("P15"), null)));
        p10Edges.add(new Edge(21,15, new Node(pickupPointMap.get("P16"), null)));
        p10Edges.add(new Edge(52,22, new Node(pickupPointMap.get("P17"), null)));
        p10Edges.add(new Edge(40,23, new Node(null, dropPointMap.get("D1"))));


        Set<Edge> p11Edges = new HashSet<>();
        p11Edges.add(new Edge(23,11, new Node(pickupPointMap.get("P1"), null)));
        p11Edges.add(new Edge(39,16, new Node(pickupPointMap.get("P2"), null)));
        p11Edges.add(new Edge(24,11, new Node(pickupPointMap.get("P3"), null)));
        p11Edges.add(new Edge(26,12, new Node(pickupPointMap.get("P4"), null)));
        p11Edges.add(new Edge(17,7.7f, new Node(pickupPointMap.get("P5"), null)));
        p11Edges.add(new Edge(39,16, new Node(pickupPointMap.get("P6"), null)));
        p11Edges.add(new Edge(22,8.8f, new Node(pickupPointMap.get("P7"), null)));
        p11Edges.add(new Edge(42,19, new Node(pickupPointMap.get("P8"), null)));
        p11Edges.add(new Edge(48,20, new Node(pickupPointMap.get("P9"), null)));
        p11Edges.add(new Edge(41,36, new Node(pickupPointMap.get("P10"), null)));
        p11Edges.add(new Edge(54,23, new Node(pickupPointMap.get("P12"), null)));
        p11Edges.add(new Edge(38,19, new Node(pickupPointMap.get("P13"), null)));
        p11Edges.add(new Edge(39,16, new Node(pickupPointMap.get("P14"), null)));
        p11Edges.add(new Edge(78,34, new Node(pickupPointMap.get("P15"), null)));
        p11Edges.add(new Edge(40,29, new Node(pickupPointMap.get("P16"), null)));
        p11Edges.add(new Edge(72, 28, new Node(pickupPointMap.get("P17"), null)));
        p11Edges.add(new Edge(41,17, new Node(null, dropPointMap.get("D1"))));


        Set<Edge> p12Edges = new HashSet<>();
        p12Edges.add(new Edge(35,14, new Node(pickupPointMap.get("P1"), null)));
        p12Edges.add(new Edge(35,12, new Node(pickupPointMap.get("P2"), null)));
        p12Edges.add(new Edge(34,14, new Node(pickupPointMap.get("P3"), null)));
        p12Edges.add(new Edge(36,14, new Node(pickupPointMap.get("P4"), null)));
        p12Edges.add(new Edge(56,22, new Node(pickupPointMap.get("P5"), null)));
        p12Edges.add(new Edge(35,12, new Node(pickupPointMap.get("P6"), null)));
        p12Edges.add(new Edge(39,15, new Node(pickupPointMap.get("P7"), null)));
        p12Edges.add(new Edge(49,18, new Node(pickupPointMap.get("P8"), null)));
        p12Edges.add(new Edge(9,2.9f, new Node(pickupPointMap.get("P9"), null)));
        p12Edges.add(new Edge(42,19, new Node(pickupPointMap.get("P10"), null)));
        p12Edges.add(new Edge(51,20, new Node(pickupPointMap.get("P11"), null)));
        p12Edges.add(new Edge(27,10, new Node(pickupPointMap.get("P13"), null)));
        p12Edges.add(new Edge(35,12, new Node(pickupPointMap.get("P14"), null)));
        p12Edges.add(new Edge(34,12, new Node(pickupPointMap.get("P15"), null)));
        p12Edges.add(new Edge(36,13, new Node(pickupPointMap.get("P16"), null)));
        p12Edges.add(new Edge(25, 8.9f, new Node(pickupPointMap.get("P17"), null)));
        p12Edges.add(new Edge(27,8.5f, new Node(null, dropPointMap.get("D1"))));


        Set<Edge> p13Edges = new HashSet<>();
        p13Edges.add(new Edge(30,11, new Node(pickupPointMap.get("P1"), null)));
        p13Edges.add(new Edge(48,15, new Node(pickupPointMap.get("P2"), null)));
        p13Edges.add(new Edge(29,11, new Node(pickupPointMap.get("P3"), null)));
        p13Edges.add(new Edge(24,7.7f, new Node(pickupPointMap.get("P4"), null)));
        p13Edges.add(new Edge(34,15, new Node(pickupPointMap.get("P5"), null)));
        p13Edges.add(new Edge(48,15, new Node(pickupPointMap.get("P6"), null)));
        p13Edges.add(new Edge(39,14, new Node(pickupPointMap.get("P7"), null)));
        p13Edges.add(new Edge(58,23, new Node(pickupPointMap.get("P8"), null)));
        p13Edges.add(new Edge(32,10, new Node(pickupPointMap.get("P9"), null)));
        p13Edges.add(new Edge(32,21, new Node(pickupPointMap.get("P10"), null)));
        p13Edges.add(new Edge(45,17, new Node(pickupPointMap.get("P11"), null)));
        p13Edges.add(new Edge(34,12, new Node(pickupPointMap.get("P12"), null)));
        p13Edges.add(new Edge(50,15, new Node(pickupPointMap.get("P14"), null)));
        p13Edges.add(new Edge(52,19, new Node(pickupPointMap.get("P15"), null)));
        p13Edges.add(new Edge(12,3.8f, new Node(pickupPointMap.get("P16"), null)));
        p13Edges.add(new Edge(57, 18, new Node(pickupPointMap.get("P17"), null)));
        p13Edges.add(new Edge(20,7.1f, new Node(null, dropPointMap.get("D1"))));


        Set<Edge> p14Edges = new HashSet<>();
        p14Edges.add(new Edge(29,12, new Node(pickupPointMap.get("P1"), null)));
        p14Edges.add(new Edge(1,0.1f, new Node(pickupPointMap.get("P2"), null)));
        p14Edges.add(new Edge(26,11, new Node(pickupPointMap.get("P3"), null)));
        p14Edges.add(new Edge(27,12, new Node(pickupPointMap.get("P4"), null)));
        p14Edges.add(new Edge(40,17, new Node(pickupPointMap.get("P5"), null)));
        p14Edges.add(new Edge(1,0.1f, new Node(pickupPointMap.get("P6"), null)));
        p14Edges.add(new Edge(25,8.9f, new Node(pickupPointMap.get("P7"), null)));
        p14Edges.add(new Edge(13,6, new Node(pickupPointMap.get("P8"), null)));
        p14Edges.add(new Edge(32,12, new Node(pickupPointMap.get("P9"), null)));
        p14Edges.add(new Edge(47,30, new Node(pickupPointMap.get("P10"), null)));
        p14Edges.add(new Edge(35,14, new Node(pickupPointMap.get("P11"), null)));
        p14Edges.add(new Edge(31,12, new Node(pickupPointMap.get("P12"), null)));
        p14Edges.add(new Edge(35,16, new Node(pickupPointMap.get("P13"), null)));
        p14Edges.add(new Edge(59,23, new Node(pickupPointMap.get("P15"), null)));
        p14Edges.add(new Edge(43,22, new Node(pickupPointMap.get("P16"), null)));
        p14Edges.add(new Edge(48,17, new Node(pickupPointMap.get("P17"), null)));
        p14Edges.add(new Edge(29,11, new Node(null, dropPointMap.get("D1"))));


        Set<Edge> p15Edges = new HashSet<>();
        p15Edges.add(new Edge(69,25, new Node(pickupPointMap.get("P1"), null)));
        p15Edges.add(new Edge(69,24, new Node(pickupPointMap.get("P2"), null)));
        p15Edges.add(new Edge(68,28, new Node(pickupPointMap.get("P3"), null)));
        p15Edges.add(new Edge(64,24, new Node(pickupPointMap.get("P4"), null)));
        p15Edges.add(new Edge(79,31, new Node(pickupPointMap.get("P5"), null)));
        p15Edges.add(new Edge(69,24, new Node(pickupPointMap.get("P6"), null)));
        p15Edges.add(new Edge(75,27, new Node(pickupPointMap.get("P7"), null)));
        p15Edges.add(new Edge(82,30, new Node(pickupPointMap.get("P8"), null)));
        p15Edges.add(new Edge(45,15, new Node(pickupPointMap.get("P9"), null)));
        p15Edges.add(new Edge(46,22, new Node(pickupPointMap.get("P10"), null)));
        p15Edges.add(new Edge(82,34, new Node(pickupPointMap.get("P11"), null)));
        p15Edges.add(new Edge(34,14, new Node(pickupPointMap.get("P12"), null)));
        p15Edges.add(new Edge(56,17, new Node(pickupPointMap.get("P13"), null)));
        p15Edges.add(new Edge(69,24, new Node(pickupPointMap.get("P14"), null)));
        p15Edges.add(new Edge(57,21, new Node(pickupPointMap.get("P16"), null)));
        p15Edges.add(new Edge(27,9.9f, new Node(pickupPointMap.get("P17"), null)));
        p15Edges.add(new Edge(56,19, new Node(null, dropPointMap.get("D1"))));


        Set<Edge> p16Edges = new HashSet<>();
        p16Edges.add(new Edge(35,15, new Node(pickupPointMap.get("P1"), null)));
        p16Edges.add(new Edge(55,23, new Node(pickupPointMap.get("P2"), null)));
        p16Edges.add(new Edge(34,15, new Node(pickupPointMap.get("P3"), null)));
        p16Edges.add(new Edge(27,12, new Node(pickupPointMap.get("P4"), null)));
        p16Edges.add(new Edge(31,17, new Node(pickupPointMap.get("P5"), null)));
        p16Edges.add(new Edge(55,23, new Node(pickupPointMap.get("P6"), null)));
        p16Edges.add(new Edge(44,18, new Node(pickupPointMap.get("P7"), null)));
        p16Edges.add(new Edge(52,48, new Node(pickupPointMap.get("P8"), null)));
        p16Edges.add(new Edge(43,14, new Node(pickupPointMap.get("P9"), null)));
        p16Edges.add(new Edge(21,15, new Node(pickupPointMap.get("P10"), null)));
        p16Edges.add(new Edge(42,29, new Node(pickupPointMap.get("P11"), null)));
        p16Edges.add(new Edge(49,19, new Node(pickupPointMap.get("P12"), null)));
        p16Edges.add(new Edge(12,3.9f, new Node(pickupPointMap.get("P13"), null)));
        p16Edges.add(new Edge(57,22, new Node(pickupPointMap.get("P14"), null)));
        p16Edges.add(new Edge(54,21, new Node(pickupPointMap.get("P15"), null)));
        p16Edges.add(new Edge(62,22, new Node(pickupPointMap.get("P17"), null)));
        p16Edges.add(new Edge(32,11, new Node(null, dropPointMap.get("D1"))));



        Set<Edge> p17Edges = new HashSet<>();
        p17Edges.add(new Edge(51,22, new Node(pickupPointMap.get("P1"), null)));
        p17Edges.add(new Edge(53,18, new Node(pickupPointMap.get("P2"), null)));
        p17Edges.add(new Edge(50,21, new Node(pickupPointMap.get("P3"), null)));
        p17Edges.add(new Edge(51,22, new Node(pickupPointMap.get("P4"), null)));
        p17Edges.add(new Edge(73,29, new Node(pickupPointMap.get("P5"), null)));
        p17Edges.add(new Edge(53,81, new Node(pickupPointMap.get("P6"), null)));
        p17Edges.add(new Edge(58,21, new Node(pickupPointMap.get("P7"), null)));
        p17Edges.add(new Edge(65,26, new Node(pickupPointMap.get("P8"), null)));
        p17Edges.add(new Edge(31,11, new Node(pickupPointMap.get("P9"), null)));
        p17Edges.add(new Edge(55,23, new Node(pickupPointMap.get("P10"), null)));
        p17Edges.add(new Edge(68,26, new Node(pickupPointMap.get("P11"), null)));
        p17Edges.add(new Edge(21,7.9f, new Node(pickupPointMap.get("P12"), null)));
        p17Edges.add(new Edge(47,18, new Node(pickupPointMap.get("P13"), null)));
        p17Edges.add(new Edge(53,18, new Node(pickupPointMap.get("P14"), null)));
        p17Edges.add(new Edge(27,9.6f, new Node(pickupPointMap.get("P15"), null)));
        p17Edges.add(new Edge(56,21, new Node(pickupPointMap.get("P16"), null)));
        p17Edges.add(new Edge(45,16, new Node(null, dropPointMap.get("D1"))));




        Set<Edge> d1Edges = new HashSet<>();
        d1Edges.add(new Edge(23,7.2f, new Node(pickupPointMap.get("P1"), null)));
        d1Edges.add(new Edge(34,11, new Node(pickupPointMap.get("P2"), null)));
        d1Edges.add(new Edge(22,6.7f, new Node(pickupPointMap.get("P3"), null)));
        d1Edges.add(new Edge(18,5.3f, new Node(pickupPointMap.get("P4"), null)));
        d1Edges.add(new Edge(44,16, new Node(pickupPointMap.get("P5"), null)));
        d1Edges.add(new Edge(34,11, new Node(pickupPointMap.get("P6"), null)));
        d1Edges.add(new Edge(31,10, new Node(pickupPointMap.get("P7"), null)));
        d1Edges.add(new Edge(47,17, new Node(pickupPointMap.get("P8"), null)));
        d1Edges.add(new Edge(21,7.4f, new Node(pickupPointMap.get("P9"), null)));
        d1Edges.add(new Edge(43,24, new Node(pickupPointMap.get("P10"), null)));
        d1Edges.add(new Edge(41,16, new Node(pickupPointMap.get("P11"), null)));
        d1Edges.add(new Edge(25,7.9f, new Node(pickupPointMap.get("P12"), null)));
        d1Edges.add(new Edge(23,8.7f, new Node(pickupPointMap.get("P13"), null)));
        d1Edges.add(new Edge(34,11, new Node(pickupPointMap.get("P14"), null)));
        d1Edges.add(new Edge(54,18, new Node(pickupPointMap.get("P15"), null)));
        d1Edges.add(new Edge(33,13, new Node(pickupPointMap.get("P16"), null)));
        d1Edges.add(new Edge(48,16, new Node(pickupPointMap.get("P17"), null)));





        graph.put(new Node(pickupPointMap.get("P1"), null), p1Edges);
        graph.put(new Node(pickupPointMap.get("P2"), null), p2Edges);
        graph.put(new Node(pickupPointMap.get("P3"), null), p3Edges);
        graph.put(new Node(pickupPointMap.get("P4"), null), p4Edges);
        graph.put(new Node(pickupPointMap.get("P5"), null), p5Edges);
        graph.put(new Node(pickupPointMap.get("P6"), null), p6Edges);
        graph.put(new Node(pickupPointMap.get("P7"), null), p7Edges);
        graph.put(new Node(pickupPointMap.get("P8"), null), p8Edges);
        graph.put(new Node(pickupPointMap.get("P9"), null), p9Edges);
        graph.put(new Node(pickupPointMap.get("P10"), null), p10Edges);
        graph.put(new Node(pickupPointMap.get("P11"), null), p11Edges);
        graph.put(new Node(pickupPointMap.get("P12"), null), p12Edges);
        graph.put(new Node(pickupPointMap.get("P13"), null), p13Edges);
        graph.put(new Node(pickupPointMap.get("P14"), null), p14Edges);
        graph.put(new Node(pickupPointMap.get("P15"), null), p15Edges);
        graph.put(new Node(pickupPointMap.get("P16"), null), p16Edges);
        graph.put(new Node(pickupPointMap.get("P17"), null), p17Edges);
        graph.put(new Node(null, dropPointMap.get("D1")), d1Edges);

        return graph;
    }

    private static PickupPointsState buildTestState() {
        buildTestCandidates();
        buildTestPickupPoints();
        buildTestDropPoints();
        PickupPointsState state = new PickupPointsState();

        state.addCandidate(pickupPointMap.get("P1"), candidatesMap.get("C1"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P2"), candidatesMap.get("C2"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P3"), candidatesMap.get("C3"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P4"), candidatesMap.get("C4"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P5"), candidatesMap.get("C5"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P6"), candidatesMap.get("C6"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P7"), candidatesMap.get("C7"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P8"), candidatesMap.get("C8"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P9"), candidatesMap.get("C9"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P10"), candidatesMap.get("C10"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P11"), candidatesMap.get("C11"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P12"), candidatesMap.get("C12"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P13"), candidatesMap.get("C13"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P14"), candidatesMap.get("C14"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P15"), candidatesMap.get("C15"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P16"), candidatesMap.get("C16"), dropPointMap.get("D1"));
        state.addCandidate(pickupPointMap.get("P17"), candidatesMap.get("C17"), dropPointMap.get("D1"));

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

        dropPointMap.put("D1", d1);
    }

    private static void buildTestPickupPoints() {
        pickupPointMap.put("P1", getTestPickupPoint("P1", "P1"));
        pickupPointMap.put("P2", getTestPickupPoint("P2", "P2"));
        pickupPointMap.put("P3", getTestPickupPoint("P3", "P3"));
        pickupPointMap.put("P4", getTestPickupPoint("P4", "P4"));
        pickupPointMap.put("P5", getTestPickupPoint("P5", "P5"));
        pickupPointMap.put("P6", getTestPickupPoint("P6", "P6"));
        pickupPointMap.put("P7", getTestPickupPoint("P7", "P7"));
        pickupPointMap.put("P8", getTestPickupPoint("P8", "P8"));
        pickupPointMap.put("P9", getTestPickupPoint("P9", "P9"));
        pickupPointMap.put("P10", getTestPickupPoint("P10", "P10"));
        pickupPointMap.put("P11", getTestPickupPoint("P11", "P11"));
        pickupPointMap.put("P12", getTestPickupPoint("P12", "P12"));
        pickupPointMap.put("P13", getTestPickupPoint("P13", "P13"));
        pickupPointMap.put("P14", getTestPickupPoint("P14", "P14"));
        pickupPointMap.put("P15", getTestPickupPoint("P15", "P15"));
        pickupPointMap.put("P16", getTestPickupPoint("P16", "P16"));
        pickupPointMap.put("P17", getTestPickupPoint("P17", "P17"));

    }

    private static void buildTestCandidates() {
        candidatesMap.put("C1", getTestCandidate("C1", "C1"));
        candidatesMap.put("C2", getTestCandidate("C2", "C2"));
        candidatesMap.put("C3", getTestCandidate("C3", "C3"));
        candidatesMap.put("C4", getTestCandidate("C4", "C4"));
        candidatesMap.put("C5", getTestCandidate("C5", "C5"));
        candidatesMap.put("C6", getTestCandidate("C6", "C6"));
        candidatesMap.put("C7", getTestCandidate("C7", "C7"));
        candidatesMap.put("C8", getTestCandidate("C8", "C8"));
        candidatesMap.put("C9", getTestCandidate("C9", "C9"));
        candidatesMap.put("C10", getTestCandidate("C10", "C10"));
        candidatesMap.put("C11", getTestCandidate("C11", "C11"));
        candidatesMap.put("C12", getTestCandidate("C12", "C12"));
        candidatesMap.put("C13", getTestCandidate("C13", "C13"));
        candidatesMap.put("C14", getTestCandidate("C14", "C14"));
        candidatesMap.put("C15", getTestCandidate("C15", "C15"));
        candidatesMap.put("C16", getTestCandidate("C16", "C16"));
        candidatesMap.put("C17", getTestCandidate("C17", "C17"));
    }

    private static Candidate getTestCandidate(String id, String name) {
        Candidate c = new Candidate();
        c.setId(id);
        c.setName(name);
        c.setEarliestPickupTime(LocalTime.of(7,15));
        c.setEarliestDropTime(LocalTime.of(7,00));
        c.setLatestDropTime(LocalTime.of(8,00));
        c.setLatestPickupTime(LocalTime.of(7,45));
        c.setSubscriptionTier(SubscriptionTier.TIER1);
        return c;
    }

    private static PickupPoint getTestPickupPoint(String id, String name) {
        PickupPoint p1 = new PickupPoint();
        p1.setId(id);
        p1.setName(name);
        return p1;
    }
}

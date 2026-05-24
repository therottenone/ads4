public class Main {

    public static void main(String[] args) {

        System.out.println("      Graph Traversal and Representation System        ");
        System.out.println("    Section 1: Small Graph (10 vertices, unweighted)    \n");

        Graph smallGraph = new Graph();
        for (int i = 0; i < 10; i++) smallGraph.addVertex(new Vertex(i));
        for (int i = 0; i < 10; i++) {
            smallGraph.addEdge(i, (i + 1) % 10);
            smallGraph.addEdge(i, (i + 2) % 10);
        }

        smallGraph.printGraph();
        System.out.println();
        smallGraph.bfs(0);
        smallGraph.dfs(0);

        System.out.println("\n    Section 2: Dijkstra's Algorithm (weighted graph)    ");

        Graph wg = new Graph();
        for (int i = 0; i < 6; i++) wg.addVertex(new Vertex(i));

        wg.addEdge(0, 1, 2);   // V0 – V1 : 2
        wg.addEdge(0, 3, 6);   // V0 – V3 : 6
        wg.addEdge(1, 2, 3);   // V1 – V2 : 3
        wg.addEdge(1, 4, 5);   // V1 – V4 : 5
        wg.addEdge(2, 3, 4);   // V2 – V3 : 4
        wg.addEdge(2, 5, 2);   // V2 – V5 : 2
        wg.addEdge(4, 5, 1);   // V4 – V5 : 1

        System.out.println();
        wg.printGraph();
        wg.dijkstra(0);

        System.out.println("\n    Section 3: Performance Experiments    ");
        Experiment experiment = new Experiment();
        experiment.runMultipleTests();
        experiment.printResults();

        System.out.println("\nDone.");
    }
}
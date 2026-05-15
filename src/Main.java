public class Main {

    public static void main(String[] args) {

        System.out.println("       Graph Traversal and Representation System         ");

        System.out.println("     Section 1: Small Graph (10 vertices)     ");

        Graph smallGraph = new Graph();

        for (int i = 0; i < 10; i++) {
            smallGraph.addVertex(new Vertex(i));
        }

        for (int i = 0; i < 10; i++) {
            smallGraph.addEdge(i, (i + 1) % 10);
            smallGraph.addEdge(i, (i + 2) % 10);
        }

        smallGraph.printGraph();
        System.out.println();

        System.out.println("   Section 2: Traversal Output    ");

        Experiment demo = new Experiment();
        demo.runTraversals(smallGraph);

        System.out.println("    Section 3: Performance Experiments     ");

        Experiment experiment = new Experiment();
        experiment.runMultipleTests();


        System.out.println("   Section 4: Results Summary    ");
        experiment.printResults();

        System.out.println("Done.");
    }
}
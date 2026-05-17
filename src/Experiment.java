 public class Experiment {

        private static final int SMALL  = 10;
        private static final int MEDIUM = 30;
        private static final int LARGE  = 100;

        private long[][] results = new long[3][2];
        private int[]    sizes   = { SMALL, MEDIUM, LARGE };


        private Graph buildGraph(int n) {
            Graph g = new Graph();

            for (int i = 0; i < n; i++) {
                g.addVertex(new Vertex(i));
            }

            for (int i = 0; i < n; i++) {
                g.addEdge(i, (i + 1) % n);
                g.addEdge(i, (i + 2) % n);
            }

            return g;
        }

        public void runTraversals(Graph g) {
            boolean isSmall = g.vertexCount() <= SMALL;

            if (isSmall) {
                g.bfs(0);
                g.dfs(0);
            } else {
                System.out.println("  (traversal output suppressed for graphs > " + SMALL + " vertices)");
            }
        }


        public void runMultipleTests() {
            String[] labels = { "Small  (" + SMALL + " vertices)",
                    "Medium (" + MEDIUM + " vertices)",
                    "Large  (" + LARGE + " vertices)" };

            for (int i = 0; i < sizes.length; i++) {
                int n = sizes[i];
                System.out.println("\n    " + labels[i] + "    ");
                Graph g = buildGraph(n);

                System.out.println("  Graph: " + g.vertexCount() + " vertices, "
                        + g.edgeCount() + " edges");

                long bfsStart = System.nanoTime();
                if (n <= SMALL) g.bfs(0);
                else            silentBfs(g, 0);
                long bfsEnd = System.nanoTime();
                results[i][0] = bfsEnd - bfsStart;

                long dfsStart = System.nanoTime();
                if (n <= SMALL) g.dfs(0);
                else            silentDfs(g, 0);
                long dfsEnd = System.nanoTime();
                results[i][1] = dfsEnd - dfsStart;

                System.out.printf("  BFS time: %,d ns%n", results[i][0]);
                System.out.printf("  DFS time: %,d ns%n", results[i][1]);
            }
        }


        public void printResults() {
            System.out.println("          Performance Comparison: BFS vs DFS            ");
            System.out.println("                                                        ");
            System.out.printf( "  %-16s   %-15s   %-15s  %n",
                    "Graph Size", "BFS Time (ns)", "DFS Time (ns)");

            String[] sizeLabels = { "10  vertices", "30  vertices", "100 vertices" };
            for (int i = 0; i < sizes.length; i++) {
                System.out.printf("  %-16s   %,15d   %,15d  %n",
                        sizeLabels[i], results[i][0], results[i][1]);
            }

            String faster = results[2][0] < results[2][1] ? "BFS" : "DFS";
            System.out.println("\nObservation: " + faster
                    + " was faster on the large graph in this run.");
            System.out.println("Both algorithms run in O(V + E) time; differences reflect");
            System.out.println("JVM warm-up, cache effects, and Queue vs Stack overhead.");
        }

        private void silentBfs(Graph g, int start) {
            java.io.PrintStream original = System.out;
            System.setOut(new java.io.PrintStream(java.io.OutputStream.nullOutputStream()));
            g.bfs(start);
            System.setOut(original);
        }


        private void silentDfs(Graph g, int start) {
            java.io.PrintStream original = System.out;
            System.setOut(new java.io.PrintStream(java.io.OutputStream.nullOutputStream()));
            g.dfs(start);
            System.setOut(original);
        }
    }

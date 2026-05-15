import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

    public class Graph {

        private Map<Integer, Vertex> vertices;

        private Map<Integer, List<Integer>> adjacencyList;

        public Graph() {
            vertices = new HashMap<>();
            adjacencyList = new HashMap<>();
        }

        public void addVertex(Vertex v) {
            if (!vertices.containsKey(v.getId())) {
                vertices.put(v.getId(), v);
                adjacencyList.put(v.getId(), new ArrayList<>());
            }
        }

        public void addEdge(int from, int to) {
            if (!vertices.containsKey(from) || !vertices.containsKey(to)) {
                System.out.println("Cannot add edge: vertex " + from + " or " + to + " does not exist.");
                return;
            }
            adjacencyList.get(from).add(to);
            adjacencyList.get(to).add(from);
        }


        public void printGraph() {
            System.out.println("Graph Adjacency List:");
            for (int id : adjacencyList.keySet()) {
                System.out.print("  " + vertices.get(id) + " -> [");
                List<Integer> neighbors = adjacencyList.get(id);
                for (int i = 0; i < neighbors.size(); i++) {
                    System.out.print(vertices.get(neighbors.get(i)));
                    if (i < neighbors.size() - 1) System.out.print(", ");
                }
                System.out.println("]");
            }
        }

        public void bfs(int start) {
            if (!vertices.containsKey(start)) {
                System.out.println("BFS: start vertex " + start + " not found.");
                return;
            }

            boolean[] visited = new boolean[getMaxId() + 1];
            Queue<Integer> queue = new LinkedList<>();

            visited[start] = true;
            queue.add(start);

            System.out.print("BFS traversal from V" + start + ": ");

            while (!queue.isEmpty()) {
                int current = queue.poll();
                System.out.print(vertices.get(current) + " ");

                for (int neighbor : adjacencyList.get(current)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
            System.out.println();
        }


        public void dfs(int start) {
            if (!vertices.containsKey(start)) {
                System.out.println("DFS: start vertex " + start + " not found.");
                return;
            }

            boolean[] visited = new boolean[getMaxId() + 1];
            Stack<Integer> stack = new Stack<>();

            stack.push(start);

            System.out.print("DFS traversal from V" + start + ": ");

            while (!stack.isEmpty()) {
                int current = stack.pop();

                if (visited[current]) continue;

                visited[current] = true;
                System.out.print(vertices.get(current) + " ");

                for (int neighbor : adjacencyList.get(current)) {
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
            System.out.println();
        }


        public int vertexCount() {
            return vertices.size();
        }

        public int edgeCount() {
            int total = 0;
            for (List<Integer> neighbors : adjacencyList.values()) {
                total += neighbors.size();
            }
            return total / 2;
        }

        private int getMaxId() {
            int max = 0;
            for (int id : vertices.keySet()) {
                if (id > max) max = id;
            }
            return max;
        }
    }

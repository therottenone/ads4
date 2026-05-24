import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Graph {

    // Maps each vertex id to its Vertex object
    private Map<Integer, Vertex> vertices;

    // Maps each vertex id to a list of int[]{neighborId, weight}
    private Map<Integer, List<int[]>> adjacencyList;

    public Graph() {
        vertices      = new HashMap<>();
        adjacencyList = new HashMap<>();
    }


    public void addVertex(Vertex v) {
        if (!vertices.containsKey(v.getId())) {
            vertices.put(v.getId(), v);
            adjacencyList.put(v.getId(), new ArrayList<>());
        }
    }


    public void addEdge(int from, int to) {
        addEdge(from, to, 1);
    }

    public void addEdge(int from, int to, int weight) {
        if (!vertices.containsKey(from) || !vertices.containsKey(to)) {
            System.out.println("Cannot add edge: vertex " + from + " or " + to + " does not exist.");
            return;
        }
        adjacencyList.get(from).add(new int[]{to,   weight});
        adjacencyList.get(to  ).add(new int[]{from, weight});
    }

    public void printGraph() {
        System.out.println("Graph Adjacency List (weighted):");
        for (int id : adjacencyList.keySet()) {
            System.out.print("  " + vertices.get(id) + " -> [");
            List<int[]> neighbors = adjacencyList.get(id);
            for (int i = 0; i < neighbors.size(); i++) {
                int[] e = neighbors.get(i);
                System.out.print(vertices.get(e[0]) + "(w=" + e[1] + ")");
                if (i < neighbors.size() - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }

    public void bfs(int start) {
        if (!vertices.containsKey(start)) { System.out.println("BFS: vertex not found."); return; }

        boolean[] visited = new boolean[getMaxId() + 1];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        System.out.print("BFS from V" + start + ": ");
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            System.out.print(vertices.get(cur) + " ");
            for (int[] e : adjacencyList.get(cur)) {
                if (!visited[e[0]]) { visited[e[0]] = true; queue.add(e[0]); }
            }
        }
        System.out.println();
    }

    public void dfs(int start) {
        if (!vertices.containsKey(start)) { System.out.println("DFS: vertex not found."); return; }

        boolean[] visited = new boolean[getMaxId() + 1];
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        System.out.print("DFS from V" + start + ": ");
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            if (visited[cur]) continue;
            visited[cur] = true;
            System.out.print(vertices.get(cur) + " ");
            for (int[] e : adjacencyList.get(cur)) {
                if (!visited[e[0]]) stack.push(e[0]);
            }
        }
        System.out.println();
    }

    // Dijkstra's Algorithm

    public void dijkstra(int start) {
        if (!vertices.containsKey(start)) {
            System.out.println("Dijkstra: start vertex " + start + " not found.");
            return;
        }

        int size = getMaxId() + 1;
        int[]     dist    = new int[size];
        boolean[] visited = new boolean[size];
        int[]     prev    = new int[size];   // tracks the previous vertex for path reconstruction

        // Step 1: Initialise all distances to "infinity"
        Arrays.fill(dist,  Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        int V = vertices.size();

        // Step 2: Relax edges V times
        for (int iter = 0; iter < V; iter++) {

            // Pick the unvisited vertex with the smallest tentative distance
            int u = -1;
            for (int id : vertices.keySet()) {
                if (!visited[id] && (u == -1 || dist[id] < dist[u])) {
                    u = id;
                }
            }

            if (u == -1 || dist[u] == Integer.MAX_VALUE) break; // remaining vertices unreachable

            // Mark as visited
            visited[u] = true;

            // Relax all edges from u
            for (int[] edge : adjacencyList.get(u)) {
                int v      = edge[0];
                int weight = edge[1];
                if (!visited[v] && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    prev[v] = u;
                }
            }
        }

        // Step 3: Print results
        System.out.println("\nDijkstra shortest paths from V" + start + ":");
        System.out.println("  " + String.format("%-8s %-10s %s", "Vertex", "Distance", "Path"));
        System.out.println("  " + "-".repeat(40));
        for (int id : vertices.keySet()) {
            String distStr = (dist[id] == Integer.MAX_VALUE) ? "∞" : String.valueOf(dist[id]);
            System.out.println("  " + String.format("%-8s %-10s %s",
                    vertices.get(id), distStr, buildPath(prev, start, id)));
        }
    }

    private String buildPath(int[] prev, int start, int target) {
        if (target == start) return "V" + start;
        if (prev[target] == -1) return "no path";

        StringBuilder sb = new StringBuilder();
        for (int cur = target; cur != -1; cur = prev[cur]) {
            sb.insert(0, "V" + cur);
            if (prev[cur] != -1) sb.insert(0, " -> ");
        }
        return sb.toString();
    }

    public int vertexCount() { return vertices.size(); }

    public int edgeCount() {
        int total = 0;
        for (List<int[]> n : adjacencyList.values()) total += n.size();
        return total / 2;
    }

    private int getMaxId() {
        int max = 0;
        for (int id : vertices.keySet()) if (id > max) max = id;
        return max;
    }
}
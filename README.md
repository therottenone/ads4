Overview
A graph system built in Java using an adjacency list. Implements BFS, DFS, and Dijkstra's shortest path on weighted graphs.
Classes

Vertex — a node with a unique id
Edge — a directed, weighted connection between two vertices
Graph — weighted adjacency list with addVertex, addEdge, printGraph, bfs, dfs, dijkstra
Experiment — builds test graphs, times traversals, prints results
Main — entry point

How to Run
bashjavac *.java
java Main

BFS vs DFS
AlgorithmStrategyBest ForBFSQueue, level by levelShortest path (unweighted)DFSStack, deep firstCycle detection, topology sort
Both run in O(V + E) time.
BFS/DFS Results
Graph SizeBFS (ns)DFS (ns)10 vertices274,727251,81630 vertices1,698,913604,364100 vertices5,419,7251,665,912
DFS was faster across all sizes in practice. Both are O(V + E), but BFS uses a Queue (LinkedList) which carries more overhead per operation than DFS's Stack.

Dijkstra's Algorithm
BFS finds the shortest path in terms of number of hops, but fails when edges have different costs. Dijkstra's algorithm finds the shortest weighted path from a single source vertex to all other vertices.

Pick the unvisited vertex u with the smallest dist[u].
Mark u as visited (its distance is now final).
For each neighbor v of u: if dist[u] + weight(u,v) < dist[v], update dist[v].
Print all shortest distances and paths.

Why it is correct: All edge weights are non-negative, so once a vertex is visited, no future path through another vertex can be shorter.
Time complexity: O(V²) with arrays. Acceptable for small/medium graphs; a priority queue would reduce this to O((V + E) log V).


Edge.java — added weight field, updated constructor and toString.
Graph.java — adjacency list changed from List<Integer> to List<int[]> storing {neighborId, weight}; addEdge(from, to) still works (defaults to weight 1); dijkstra(int start) added.
Main.java — added a Dijkstra demo section with a hand-crafted 6-vertex weighted graph.

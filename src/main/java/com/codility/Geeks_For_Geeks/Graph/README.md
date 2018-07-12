
GRAPH 
-----

Graph is a data structure that consists of following two components:
1. A finite set of vertices also called as nodes.
2. A finite set of ordered pair of the form (u, v) called as edge. The pair is ordered because (u, v) 
is not same as (v, u) in case of a directed graph(di-graph). The pair of the form (u, v) indicates 
that there is an edge from vertex u to vertex v. The edges may contain weight/value/cost.

Graphs are used to represent many real-life applications: Graphs are used to represent networks. The 
networks may include paths in a city or telephone network or circuit network. Graphs are also used in 
social networks like linkedIn, Facebook. For example, in Facebook, each person is represented with a 
vertex(or node). Each node is a structure and contains information like person id, name, gender and 
locale. See this for more applications of graph.

Following two are the most commonly used representations of a graph.

1. Adjacency Matrix
2. Adjacency List

There are other representations also like, Incidence Matrix and Incidence List. The choice of the graph 
representation is situation specific. It totally depends on the type of operations to be performed and 
ease of use.

Adjacency Matrix
----------------

Adjacency Matrix is a 2D array of size V x V where V is the number of vertices in a graph. Let the 2D 
array be adj[][], a slot adj[i][j] = 1 indicates that there is an edge from vertex i to vertex j. 
Adjacency matrix for undirected graph is always symmetric. Adjacency Matrix is also used to represent 
weighted graphs. If adj[i][j] = w, then there is an edge from vertex i to vertex j with weight w.

Pros: Representation is easier to implement and follow. Removing an edge takes O(1) time. Queries like whether there is an edge from vertex ‘u’ to vertex ‘v’ are efficient and can be done O(1).

Cons: Consumes more space O(V^2). Even if the graph is sparse(contains less number of edges), it consumes the same space. Adding a vertex is O(V^2) time.



Adjacency List
--------------

An array of linked lists is used. Size of the array is equal to the number of vertices. Let the array 
be array[]. An entry array[i] represents the linked list of vertices adjacent to the ith vertex. This 
representation can also be used to represent a weighted graph. The weights of edges can be stored in 
nodes of linked lists.


Pros: Saves space O(|V|+|E|) . In the worst case, there can be C(V, 2) number of edges in a graph thus consuming O(V^2) space. Adding a vertex is easier.

Cons: Queries like whether there is an edge from vertex u to vertex v are not efficient and can be done O(V)


Breadth first search
--------------------

Breadth first search (BFS) is an algorithm for traversing or searching tree or graph data structures. 
It starts at the tree root (or some arbitrary node of a graph, sometimes referred to as a 'search key'), 
and explores all of the neighbor nodes at the present depth prior to moving on to the nodes at the next 
depth level.
 
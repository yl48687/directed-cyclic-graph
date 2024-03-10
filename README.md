# Directed Cyclic Graph
This project implements a directed cyclic graph (DAG) in Java using a linked list representation. It supports various path queries within the graph using `GraphDriver`, `Graph`, and `Node` classes. The user interacts with the program through the command line interface, providing input files containing triples and querying paths between specified nodes.

## Design Overview
The project is structured around a hierarchical relationship between classes, facilitating modularity and code reuse. At the core of the design is the Graph class, which serves as the main entity for managing the directed cyclic graph. This class encapsulates various operations related to graph manipulation and path queries.

## Functionality
`GraphDriver`:
- Serves as the entry point of the program.
- Reads input from the user, including file paths and query commands.
- Instantiates and interacts with the Graph class to perform graph operations and display results.
- Provides a user-friendly command-line interface for easy interaction.

`Graph`:
- Manages the directed cyclic graph structure.
- Provides methods for adding edges between nodes, querying paths between nodes, finding shortest paths, etc.
- Utilizes `Map` data structures to store vertices and edge labels efficiently.

`Node`:
- Represents individual nodes within the directed cyclic graph.
- Contains attributes such as data and a set of neighbors to maintain connections to other nodes.
- Utilizes a `Set` to store neighbor nodes for efficient neighbor management.

## File Structure
```
directed-cyclic-graph/
├── compile.sh
├── README.md
├── resources/
│   └── Sample_Input.txt
└── src/
    ├── GraphDriver.java
    ├── Graph.java
    └── Node.java
```

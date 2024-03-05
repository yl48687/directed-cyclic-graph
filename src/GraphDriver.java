import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Constructs a driver, {@code GraphDriver}, for {@code Graph}.
 */
public class GraphDriver {
    /**
     * Runs the driver by reading the file given by {@code args}. The program will
     * read commands given by the user and give appropriate outputs.
     *
     * @param args The path of the file.
     */
    public static void main(String[] args) {
        String filePath;
        // Add directed edges to the graph

        // Initializes graph.
        Graph graph = new Graph();

        // Stores the first command-line argument as the path to the file. If no command-line
        // argument is given, exits.
        if (args.length > 0) {
            filePath = args[0];
        } else {
            System.out.println("Please provide a path to the file as a command-line argument.");
            return;
        } // if

        // Finds the file through the given path. If file is not found, throws
        // FileNotFoundException and exits.
        try {
            File inputFile = new File(filePath);
            Scanner scanner2 = new Scanner(inputFile);
            while (scanner2.hasNextLine()) {
                String line = scanner2.nextLine();
                String[] triple = line.split(" ");
                graph.addEdge(triple[0], triple[2], triple[1]);
            } // while
            scanner2.close();
        } catch (FileNotFoundException e) {
            System.out.println("[Error] File not found: " + filePath);
            return;
        } // try

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your starting node label: ");
        String start = scanner.nextLine();

        System.out.print("Enter your ending node label: ");
        String end = scanner.nextLine();

        // If the graph does not contain the user's desired starting and ending nodes,
        // prints an error message.
        if (!graph.contains(start)) {
            System.out.println("[Error] Starting node label not found in the graph.");
        } else if (!graph.contains(end)) {
            System.out.println("[Error] Ending node label not found in the graph.");
        } else {
            System.out.println("\nQueries:\n");
            System.out.println("(1) - Find all directed paths between A and B");
            System.out.println("(2) - Find directed paths of a given length (edge count) between A and B");
            System.out.println("(3) - Find shortest directed path(s) with minimum number of edges");
            // System.out.println("(4) - Find paths that match a pattern between A and B");
            System.out.println("(q) - Quit program\n");
            String command;
            do {
                System.out.print("Enter a command: ");
                command = scanner.next();
                scanner.nextLine();
                switch (command) {
                case "1":
                    // Query 1: Find all directed paths between A and B.
                    List<List<String>> paths = graph.findPaths(start, end);
                    boolean pathsFound = printPaths(paths, graph);
                    if (!pathsFound) {
                        System.out.println("Exiting the program...");
                        return;
                    } // if
                    break;
                case "2":
                    // Query 2: Find directed paths of a given length (edge count) between A and B.
                    System.out.print("Enter your edge count: ");
                    int length = scanner.nextInt();
                    scanner.nextLine();
                    List<List<String>> pathsLength = graph.findPathsLength(start, end, length);
                    printPaths(pathsLength, graph);
                    break;
                case "3":
                    // Query 3: Find shortest directed path(s) with minimum number of edges.
                    List<List<String>> shortestPaths = graph.findShortestPaths(start, end);
                    printPaths(shortestPaths, graph);
                    break;
                    // case "4":
                    // Query 4: Find paths that match a pattern between A and B.
                    // System.out.print("Enter the pattern for edge labels: ");
                    // String pattern = scanner.next();
                    // break;
                case "q":
                    // Quit: exits the program and prints the exiting message.
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid command. Try again.");
                } // switch
            } while (!command.equals("q")); // do-while
            scanner.close();
        } // if
        System.out.println("Exiting the program...!");
    } // main

    /**
     * Prints all directed {@code paths} stored in the list.
     *
     * @param paths The list of all directed paths.
     * @param graph The graph containing all nodes.
     * @return true if there exist at least one possible path; otherwise, false.
     */
    private static boolean printPaths(List<List<String>> paths, Graph graph) {
        if (paths.isEmpty()) {
            System.out.println("[Error] No paths found.");
            return false;
        } // if
        for (List<String> path : paths) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                String node1 = path.get(i);
                String node2 = path.get(i + 1);
                String edgeLabel = graph.getEdgeLabel(node1, node2);
                sb.append(node1).append(" --- ").append(edgeLabel).append(" ---> ");
            } // for
            sb.append(path.get(path.size() - 1));
            System.out.println(sb.toString());
        } // for
        return true;
    } // printPaths
} // GraphDriver

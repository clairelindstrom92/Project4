import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * This class represents the main class of the project.
 * It reads a file selected by the user using a JFileChooser and creates a directed graph based on the contents of the file.
 * It then uses the graph to create a hierarchy and a parenthesized list representation of the graph.
 * Finally, it displays the hierarchy and parenthesized list representations and provides a TODO for displaying unreachable classes.
 */
public class MainClass {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                List<String> lines = Files.readAllLines(selectedFile.toPath());
                DirectedGraph<String> graph = new DirectedGraph<>();
                for (String line : lines) {
                    String[] parts = line.split(" ");
                    for (int i = 1; i < parts.length; i++) {
                        graph.addEdge(parts[0], parts[i]);
                    }
                }

                Hierarchy<String> hierarchy = new Hierarchy<>();
                graph.DFS(lines.get(0).split(" ")[0], hierarchy);
                System.out.println("Hierarchy Representation:");
                System.out.println(hierarchy);

                ParenthesizedList<String> parenthesizedList = new ParenthesizedList<>();
                graph.DFS(lines.get(0).split(" ")[0], parenthesizedList);
                System.out.println("Parenthesized Representation:");
                System.out.println(parenthesizedList);

                // Display unreachable classes
                List<String> unreachableClasses = graph.getUnreachableClasses();
                if (!unreachableClasses.isEmpty()) {
                    System.out.println("Unreachable Classes:");
                    for (String className : unreachableClasses) {
                        System.out.println(className + " is unreachable");
                    }
                } else {
                    System.out.println("All classes are reachable.");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

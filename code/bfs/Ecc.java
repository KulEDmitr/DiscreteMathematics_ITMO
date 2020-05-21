import java.io.*;
import java.util.*;

public class Ecc {
    private static int[] ecc;

    private void bfs(List<List<Integer>> graph, int vert, int[] depth) {
        Deque<Integer> currentSet = new ArrayDeque<>();
        currentSet.addLast(vert);

        while (!currentSet.isEmpty()) {
            int parent = currentSet.pop();
            for (int el : graph.get(parent)) {
                if (depth[el] == 0) {
                    currentSet.addLast(el);
                    depth[el] = depth[parent] + 1;

                    ecc[vert] = Math.max(ecc[vert], depth[el]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("graph.in"));
        PrintWriter out = new PrintWriter("graph.out");
        int n = in.nextInt();
        int edgesCount = in.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edgesCount; ++i) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        ecc = new int[n];
        for (int i = 0; i < n; ++i) {
            int[] depth = new int[n];
            new Ecc().bfs(graph, i, depth);
        }

        for (int element : ecc) {
            int MAX = 1000;
            out.println((element == 0) ? MAX : element);
        }
        out.close();
    }
}

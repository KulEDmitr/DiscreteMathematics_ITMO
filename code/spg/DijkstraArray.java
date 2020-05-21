import java.io.*;
import java.util.*;

public class DijkstraQueue {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("pathmgep.in"));
        PrintWriter out = new PrintWriter("pathmgep.out");

        int n = in.nextInt();
        int edge = in.nextInt();

        int[][] graph = new int[n][n];
        int[] ways = new int[n];
        int[] vertx = {5, 6, 7, 8, 9, 10, 12, 13, 15, 19, 22, 24, 25, 26, 27, 28, 29, 31, 33, 35, 37, 40};

        int INF = 2_000_000_000;
        for (int i = 0; i < n; ++i) {
            ways[i] = INF;
            for (int j = 0; j < n; ++j) {
                if (i != j) {
                    graph[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < edge; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            graph[a][b] = 1;
            graph[b][a] = 1;
        }
        in.close();

        for (int i : vertx) {
            boolean[] calc = new boolean[n];
            for (int j = 0; j < n; ++j) {
                ways[j] = INF;
            }
            ways[i - 1] = 0;
            for (int j = 0; j < n; ++j) {
                int vert = -1;
                for (int k = 0; k < n; ++k) {
                    if (!calc[k] && (vert == -1 || ways[k] < ways[vert])) {
                        vert = k;
                    }
                }
                if (ways[vert] == INF) {
                    break;
                }
                calc[vert] = true;

                for (int k = 0; k < n; ++k) {
                    ways[k] = Math.min(ways[vert] + graph[vert][k], ways[k]);
                }
            }

            for (int j : vertx) {
                out.print((ways[j - 1] != INF) ? ways[j - 1] : -1);
                out.print(" ");
            }
            out.println();
        }

        out.close();
    }
}

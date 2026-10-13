import java.time.Clock;
import java.util.Arrays;

public class TAssignment3{
    
    public static double[] times = new double[7];
    public static int repeats = 100;

    public static int approxDiameter(int[][] graph){
        boolean temp = false;
        while (temp){Arrays.toString(graph[0]);}
        Clock clock = Clock.systemUTC();
        int nodes = graph.length;
        int edges = 0;
        for (int[] graph1 : graph) {edges += graph1.length;}
        edges /= 2;
        System.out.println("Nodes: " + nodes + " Edges: " + edges);
        for (int i=0;i<repeats;i++){
            String msg = "|";
            for(int j=0;j<10;j++){msg += (i >=(j/10.0)*repeats) ? "=":" ";}
            msg += "|\r";
            System.out.print(msg);
            double start = clock.millis();
            int out = 0;
            // times[0] += clock.millis() - start;
            // start = clock.millis();
            // out = third(graph);
            // times[1] += clock.millis() - start;
            // start = clock.millis();
            // out = fourth(graph);
            // times[2] += clock.millis() - start;
            // start = clock.millis();
            // out = sixth(graph);
            // times[3] += clock.millis() - start;
            start = clock.millis();
            out = seventh(graph);
            times[4] += clock.millis() - start;
            start = clock.millis();
            out = eight(graph);
            times[5] += clock.millis() - start;
            start = clock.millis();
            out = nine(graph);
            times[6] += clock.millis() - start;
        }
        System.out.println("Initial: " + times[0]/repeats + "ms\nImprovedBFS: " + times[1]/repeats + "ms\nRecursive DFS: " + times[2]/repeats + 
        "ms\nQueueBFS: " + times[3]/repeats + "ms\nNoDoubleCheck: " + times[4]/repeats + "ms\nNoFunctionCall: " + times[5]/repeats + 
        "ms\nStar Check: " + times[6]/repeats + "ms");
        return nine(graph);
    }

    public static void result(int testNo){
        int totalRepeats = testNo * repeats;
        System.out.println("Initial: " + times[0]/totalRepeats + "ms\nImprovedBFS: " + times[1]/totalRepeats + "ms\nRecursive DFS: " + times[2]/totalRepeats + 
        "ms\nQueueBFS: " + times[3]/totalRepeats + "ms\nNoDoubleCheck: " + times[4]/totalRepeats + "ms\nNoFunctionCall: " + times[5]/totalRepeats + 
        "ms\nStar Check: " + times[6]/totalRepeats + "ms");
    }

    public static int first(int[][] graph){
        if (graph.length <= 1){return 0;}
        int maxLen = 0;
        for (int start=0;start<graph.length; start++){
            int[] distance = new int[graph.length];
            distance[start] = -1;
            int[][] toVisit = new int[graph.length][graph.length + 2];
            toVisit[0][0] = 1;
            toVisit[0][1] = start;
            for (int depth = 0; depth < toVisit.length; depth++){
                for (int i=1;i<=toVisit[depth][0];i++){
                    int current = toVisit[depth][i];
                    for (int x=0;x<graph[current].length;x++){
                        if (distance[graph[current][x]] == 0){
                            toVisit[depth+1][toVisit[depth+1][0]+1] = graph[current][x];
                            toVisit[depth+1][0]++;
                            distance[graph[current][x]] = depth+1;
                        }
                    }
                }
            }
            for (int i=0;i<distance.length;i++){maxLen = Math.max(maxLen, distance[i]);}
        }
        return maxLen;
    }

    public static int second(int[][] graph) {
        if (graph.length <= 1){return 0;}
        int[] longest = new int[]{-1, -1};
        for (int i=0;i<graph.length;i++){
            if (graph[i].length > longest[1]){
                longest[0] = i;
                longest[1] = graph[i].length;
            }
        }
        int[] distance = BFS(graph, longest[0]);
        int[] maxes = new int[]{0, 0};
        for (int i=0;i<distance.length;i++){
            if (distance[i] > maxes[1]){
                if (distance[i] > maxes[0]){
                    maxes[1] = maxes[0];
                    maxes[0] = distance[i];
                }
                else{maxes[0] = distance[i];}
            }
        }
        int result = maxes[0]+maxes[1];
        return result;
    }

    public static int third(int[][] graph){
        if (graph.length <= 1){return 0;}
        int[] distance = BFS(graph, 0);
        int[] longest = new int[]{0, 0};
        for (int i=0;i<distance.length;i++){
            if (distance[i] > longest[1]){
                longest = new int[]{i, distance[i]};
            }
        }
        distance = BFS(graph, longest[0]);
        int result = 0;
        for (int i=0;i<distance.length;i++){
            if (distance[i] > result){result = distance[i];}
        }
        return result;
    }

    public static int fourth(int[][] graph){
        if (graph.length <= 1){return 0;}
        int[] distance = new int[graph.length];
        distance[0] = -1;
        distance = recursDFS(graph, distance, 0, 0);
        int[] longest = new int[]{0, 0};
        for (int i=0;i<distance.length;i++){
            if (distance[i] > longest[1]){
                longest = new int[]{i, distance[i]};
            }
        }
        distance = new int[graph.length];
        distance = recursDFS(graph, distance, longest[0], 0);
        int result = 0;
        for (int i=0;i<distance.length;i++){
            if (distance[i] > result){result = distance[i];}
        }
        return result;
    }

    public static int fifth(int[][] graph){
        if (graph.length <= 1){return 0;}
        int[] distance = iterDFS(graph, 0);
        int[] longest = new int[]{0, 0};
        for (int i=0;i<distance.length;i++){
            if (distance[i] > longest[1]){
                longest = new int[]{i, distance[i]};
            }
        }
        distance = iterDFS(graph, longest[0]);
        int result = 0;
        for (int i=0;i<distance.length;i++){
            if (distance[i] > result){result = distance[i];}
        }
        return result;
    }

    public static int sixth(int[][] graph){
        if (graph.length <= 1){return 0;}
        int[] distance = queueBFS(graph, 0);
        int[] longest = new int[]{0, 0};
        for (int i=0;i<distance.length;i++){
            if (distance[i] > longest[1]){
                longest = new int[]{i, distance[i]};
            }
        }
        distance = queueBFS(graph, longest[0]);
        int result = 0;
        for (int i=0;i<distance.length;i++){
            if (distance[i] > result){result = distance[i];}
        }
        return result;
    }

    public static int seventh(int[][] graph){
        if (graph.length <= 1){return 0;}
        int[] distance = queueBFS(graph, 0);
        int longest = 0;
        for (int i=0;i<distance.length;i++){
            if (distance[i] > longest){
                longest = distance[i];
            }
        }
        return 2*longest;
    }

    public static int eight(int[][] graph){
        if (graph.length <= 1){return 0;}
        int[] distance = new int[graph.length];
        for (int i=0;i<graph.length;i++){distance[i]=-1;}
        int[] queue = new int[graph.length];
        int end = 1;
        int index = 0;
        for (distance[0] = 0; index < end; index++){
            int current = queue[index];
            for (int i=0;i<graph[current].length;i++){
                if (distance[graph[current][i]] == -1){
                    distance[graph[current][i]] = distance[current]+1;
                    queue[end] = graph[current][i];
                    end++;
                }
            }
        }
        return 2*distance[queue[index-1]];
    }

    public static int nine(int[][] graph){
        if (graph.length <= 1){return 0;}
        int[] distance = new int[graph.length];
        for (int i=0;i<graph.length;i++){distance[i]=-1;}
        int[] queue = new int[graph.length];
        int end = 1;
        int index = 0;
        for (distance[0] = 0; index < end; index++){
            int current = queue[index];
            if (graph[current].length == graph.length - 1){return 2;}
            for (int i=0;i<graph[current].length;i++){
                if (distance[graph[current][i]] == -1){
                    distance[graph[current][i]] = distance[current]+1;
                    queue[end] = graph[current][i];
                    end++;
                }
            }
        }
        return 2*distance[queue[index-1]];
    }

    public static int[] BFS(int[][] graph, int startNode){
        int[] distance = new int[graph.length];
        distance[startNode] = -1;
        int[][] toVisit = new int[graph.length][graph.length];
        toVisit[0] = new int[]{1, startNode};
        for (int depth=0;depth<toVisit.length && toVisit[depth][0] != 0;depth++){
            for (int i=1;i<=toVisit[depth][0];i++){
                int current = toVisit[depth][i];
                for (int x=0;x<graph[current].length;x++){
                    if (distance[graph[current][x]] == 0){
                        toVisit[depth+1][0]++;
                        toVisit[depth+1][toVisit[depth+1][0]] = graph[current][x];
                        distance[graph[current][x]] = depth+1;
                    }
                }
            }
        }
        return distance;
    }

    public static int[] queueBFS(int[][] graph, int startnode){
        int[] distance = new int[graph.length];
        for (int i=0;i<graph.length;i++){distance[i]=-1;}
        int[] queue = new int[graph.length];
        int end = 1;
        int index = 0;
        queue[0] = startnode;
        distance[startnode] = 0;
        while (index < end){
            int current = queue[index];
            for (int i=0;i<graph[current].length;i++){
                if (distance[graph[current][i]] == -1){
                    distance[graph[current][i]] = distance[current]+1;
                    queue[end] = graph[current][i];
                    end++;
                }
            }
            index++;
        }
        return distance;
    }

    public static int[] recursDFS(int[][] graph, int[] distance, int startNode, int depth){
        if (distance[startNode] == 0){distance[startNode] = depth;}
        else{
            if (distance[startNode] > depth){distance[startNode] = depth;}
            else{return distance;}
        }
        int[] connections = graph[startNode];
        for (int i=0;i<connections.length;i++){
            distance = recursDFS(graph, distance, graph[startNode][i], depth+1);
        }
        return distance;
    }

    public static int[] iterDFS(int[][] graph, int startNode){
        int[] distance = new int[graph.length];
        int nodes = graph.length;
        int edges = 0;
        for (int i=0;i<nodes;i++){edges+=graph[i].length;}
        edges/=2;
        int[][] stack = new int[nodes*edges][2];
        int index = 0;
        distance[startNode] = -1;
        for (int i=0;i<graph[startNode].length;i++){
            stack[index] = new int[]{graph[startNode][i], startNode};
            index++;
        }
        while (index > 0){
            index--;
            int[] current = stack[index];
            int depth = distance[current[1]];
            boolean skip = false;
            if (distance[current[0]] == 0){distance[current[0]] = depth;}
            else{
                if (distance[current[0]] > depth){distance[current[0]] = depth;}
                else{skip = true;}
            }
            if (!skip){
                for (int i=0;i<graph[current[0]].length;i++){
                    stack[index] = new int[]{graph[startNode][i], current[0]};
                    index++;
                }
            }
        }
        return distance;
    }
}
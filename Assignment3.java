public class Assignment3 {
    public static int approxDiameter(int[][] graph){
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
}
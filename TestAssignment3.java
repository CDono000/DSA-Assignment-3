import java.util.Arrays;
import java.util.Random;

public class TestAssignment3 {
  int testNo;
  int passed;
  int maxTests;

  TestAssignment3() {
    this.testNo = 0;
    this.passed = 0;
    this.maxTests = 100;
  }

  void testGraph(int[][] graph, int diameter) {
    this.testNo++;
    System.out.println("Test " + testNo + ": ");

    try {
      int out = Assignment3.approxDiameter(graph);
      if (out <= 2*diameter && out >= diameter) {
        System.out.println("Passed!");
        System.out.println("Got " + out + " Min " + diameter);
        if (out != diameter){System.out.println("DIFFERENT");}
        passed++;
      } else {
        System.out.println("Failed!");
        System.out.println("Expected a value between " + diameter + " and " + (2*diameter) + ", but got: " + out);
      }
    } catch (Exception e) {
      System.out.println("Failed with Exception:");
      System.out.println(e);
    }

    // System.out.println("\n\n\n");
    boolean temp = false;
    while (temp){Arrays.toString(graph[0]);}
  }

  void conclude() {
    System.out.println();
    System.out.println("Passed "+this.passed+"/"+this.testNo+" tests.");
    if (this.passed == this.testNo) {
      System.out.println("WELL DONE!");
      System.out.println("Note that your code will be tested on more cases. " +
                         "You may want to add\nadditional test cases.");
    }
  }

  public static void main(String[] args) {
    TestAssignment3 t = new TestAssignment3();

    // Test 1
    int[][] exampleGraph = {
        {1, 2},
        {0, 2},
        {0, 1, 3},
        {2, 4},
        {3}};
    t.testGraph(exampleGraph, 3);

    // Test 2
    int[][] testGraph1 = {
        {1, 2},
        {0, 2},
        {0, 1, 3},
        {2, 4},
        {3}};
    t.testGraph(testGraph1, 3);

    // Test 3
    int[][] lineGraph = {
        {1},
        {0, 2},
        {1, 3},
        {2, 4},
        {3}
    };
    t.testGraph(lineGraph, 4);

    // Test 4
    int[][] starGraph = {
        {1, 2, 3, 4, 5},
        {0},
        {0},
        {0},
        {0},
        {0}
    };
    t.testGraph(starGraph, 2);

    // Test 5
    int[][] completeGraph = {
        {1, 2, 3},
        {0, 2, 3},
        {0, 1, 3},
        {0, 1, 2}
    };
    t.testGraph(completeGraph, 1);

    // Test 6
    int[][] cycleGraph = {
        {1, 4},
        {0, 2},
        {1, 3},
        {2, 4},
        {3, 0}
    };
    t.testGraph(cycleGraph, 2);

    // Test 7
    int[][] gridGraph = {
        {1, 2},
        {0, 3},
        {0, 3},
        {1, 2}
    };
    t.testGraph(gridGraph, 2);

    // Test 8
    int[][] bridgeGraph = {
        {1, 2},
        {0, 2},
        {0, 1, 3},
        {2, 4, 5},
        {3, 5},
        {3, 4}
    };
    t.testGraph(bridgeGraph, 3);

    // Test 9
    int[][] broomGraph = {
        {1},
        {0, 2},
        {1, 3},
        {2, 4, 5, 6, 7},
        {3},
        {3},
        {3},
        {3}
    };
    t.testGraph(broomGraph, 4);

    // Test 10
    int[][] cycle6 = {
        {1, 5},
        {0, 2},
        {1, 3},
        {2, 4},
        {3, 5},
        {4, 0}
    };
    t.testGraph(cycle6, 3);

    // Test 11
    int[][] complete6 = {
        {1, 2, 3, 4, 5},
        {0, 2, 3, 4, 5},
        {0, 1, 3, 4, 5},
        {0, 1, 2, 4, 5},
        {0, 1, 2, 3, 5},
        {0, 1, 2, 3, 4}
    };
    t.testGraph(complete6, 1);

    // Test 12
    int[][] singleEdge = {
        {1},
        {0}
    };
    t.testGraph(singleEdge, 1);

    // Test 13
    int[][] isolatedNode = {
        {}
    };
    t.testGraph(isolatedNode, 0);

    // Test 14
    int[][] emptyGraph = {};
    t.testGraph(emptyGraph, 0);

    int n = 1000;

    // Test 15
    int[][] LongAFlineGraph = new int[n][];

    for (int i = 0; i < n; i++) {
        if (i == 0) {
            LongAFlineGraph[i] = new int[]{1};
        } else if (i == n - 1) {
            LongAFlineGraph[i] = new int[]{n - 2};
        } else {
            LongAFlineGraph[i] = new int[]{i - 1, i + 1};
        }
    }
    t.testGraph(LongAFlineGraph, n-1);

    // Test 16
    int[][] fullyConnectedGraph = new int[n][];
    for (int i = 0; i < n; i++) {
        fullyConnectedGraph[i] = new int[n - 1];
        int index = 0;

        for (int j = 0; j < n; j++) {
            if (j != i) {
                fullyConnectedGraph[i][index++] = j;
            }
        }
    }
    t.testGraph(fullyConnectedGraph, 1);

    // Test 17
    int[][] LongAFCircleGraph = new int[n][2];
    for (int i = 0; i < n; i++) {
        if (i == 0) {
            LongAFCircleGraph[i] = new int[]{1, n-1};
        } else if (i == n - 1) {
            LongAFCircleGraph[i] = new int[]{0, n-2};
        } else {
            LongAFCircleGraph[i] = new int[]{i - 1, i + 1};
        }
    }
    t.testGraph(LongAFCircleGraph, (n)/2);

    // Test 18
    int[][] longAFStarGraph = new int[n][1];
    int[] origin = new int[n-1];
    for (int i=1;i<n;i++){
        origin[i-1] = i;
        longAFStarGraph[i][0] = 0;
    }
    longAFStarGraph[0] = origin;
    t.testGraph(longAFStarGraph, 2);

    int[][] shortCircleGraph;
    for (int x=2;x<=1;x++){
        shortCircleGraph = new int[x][2];
        for (int i=0;i<x;i++){
            if (i == 0) {
                shortCircleGraph[i] = new int[]{1, x-1};
            } else if (i == x - 1) {
                shortCircleGraph[i] = new int[]{0, x-2};
            } else {
                shortCircleGraph[i] = new int[]{i - 1, i + 1};
            }
        }
        t.testGraph(shortCircleGraph, x/2);
    }

    // Randomised Graphs
    Random r = new Random();
    while (t.testNo < t.maxTests){
        int nodes = (r.nextInt() & Integer.MAX_VALUE) % 1000 + 10;
        int edges = nodes * 5;
        int[][] graph = new int[nodes][0];
        for (int i = 0; i < nodes; i++) {
            if (i == 0) {
                graph[i] = new int[]{1};
            } else if (i+1 == nodes) {
                graph[i] = new int[]{nodes - 2};
            } else {
                graph[i] = new int[]{i - 1, i + 1};
            }
        }
        while (edges > 0){
            int n1 = r.nextInt(nodes);
            int n2 = r.nextInt(nodes);
            while (n1 == n2){
                n1 = r.nextInt(nodes);
                n2 = r.nextInt(nodes);
            }
            int[] connections = graph[n1];
            int[] newConnections = new int[connections.length + 1];
            System.arraycopy(connections, 0, newConnections, 0, connections.length);
            newConnections[connections.length] = n2;
            connections = graph[n2];
            newConnections = new int[connections.length + 1];
            System.arraycopy(connections, 0, newConnections, 0, connections.length);
            newConnections[connections.length] = n1;
            edges--;
        }
        t.testGraph(graph, TAssignment3.first(graph));
    }

    t.conclude();
  }
}

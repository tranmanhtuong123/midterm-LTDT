import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

public abstract class Graph {
    public int[][] arr;
    public int[] visit;
    public ArrayList<Integer> listVisit = new ArrayList<>();

    public Graph(int[][] arr) {
        this.arr = arr;
    }

    public Graph() {
    }

    public int topNum() {
        return this.arr.length;
    }

    // thêm cạnh cho đồ thị
    public abstract boolean addEdge(int x, int y);

    // xóa cạnh cho đồ thị
    public abstract boolean removeEdge(int x, int y);

    // tổng bậc của tất cả các đỉnh
    public abstract int allTopDegree();

    // tính bậc tại đỉnh bất kì
    public abstract int topDegree(int i);

    // tính tổng các cạnh
    public abstract int numbDegree();

    // kiểm tra tính liên thông của đồ thị
    public abstract boolean isConnect();

    // thêm đỉnh cho đồ thị
    public boolean addTop() {
        try {
            int arrSample[][] = new int[topNum() + 1][topNum() + 1];
            for (int i = 0; i < this.arr.length; i++) {
                for (int j = 0; j < this.arr[i].length; j++) {
                    arrSample[i][j] = this.arr[i][j];
                }
            }
            this.arr = arrSample;

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // xóa đỉnh cho đồ thị
    public boolean removeTop() {
        try {
            int arrSample[][] = new int[topNum() - 1][topNum() - 1];
            for (int i = 0; i < arrSample.length; i++) {
                for (int j = 0; j < arrSample[i].length; j++) {
                    arrSample[i][j] = this.arr[i][j];
                }
            }
            this.arr = arrSample;

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // Duyệt Đồ Thị Theo Chiều Sâu DFS - dùng đệ quy
    public void DFS(int i) {
        visit[i] = 1;
        listVisit.add(i);
        for (int j = 0; j < this.arr.length; j++) {
            if (this.arr[i][j] > 0 && this.visit[j] != 1) {
                DFS(j);
            }
        }
        // printArr(listVisit);
    }

    // Duyệt Đồ Thị Theo Chiều Sâu DFS - k dùng đệ quy
    public void DFSNotRecursive(int i) {
        int numVertex = topNum();
        int vs[] = new int[numVertex];
        ArrayList<Integer> listVS = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        listVS.add(i);
        vs[i] = 1;
        stack.push(i);
        while (!stack.empty()) {
            i = stack.peek();
            int count = 0;
            for (int j = 0; j < vs.length; j++) {
                if (this.arr[i][j] > 0 && vs[j] != 1) {
                    vs[j] = 1;
                    listVS.add(j);
                    stack.push(j);
                    break;
                } else {
                    count++;
                }
            }
            if (count == visit.length) {
                stack.pop();
            }
        }
        printArr(listVS);
    }

    // Duyệt Đồ Thị Theo Chiều Rộng BFS
    public void BFS(int i) {
        int numVertex = topNum();
        int vs[] = new int[numVertex];
        vs[i] = 1;
        ArrayList<Integer> queue = new ArrayList<>();
        ArrayList<Integer> listVS = new ArrayList<>();
        // thêm vào đầu
        queue.add(0, i);
        while (queue.size() > 0) {
            // lấy ra ở cuối => theo nguyên tắc FIFO
            i = queue.get(queue.size() - 1);
            queue.remove(queue.size() - 1);
            listVS.add(i);
            for (int j = 0; j < vs.length; j++) {
                if (this.arr[i][j] > 0 && vs[j] != 1) {
                    vs[j] = 1;
                    queue.add(0, j);
                }
            }
        }
        printArr(listVS);
    }

    // in DFS - BFS
    public void printArr(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ==> ");
        }
        System.out.println();
    }

    // load đồ thị từ file .txt
    public boolean loadData(String filePath) {
        Path path = Paths.get(filePath);
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            String line = null;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                String k[] = line.split(" ");
                if (k.length == 1) {
                    this.arr = new int[Integer.parseInt(k[0])][Integer.parseInt(k[0])];
                    this.visit = new int[Integer.parseInt(k[0])];
                } else {
                    for (int i = 0; i < k.length; i++) {
                        this.arr[count][i] = Integer.parseInt(k[i]);
                    }
                    count++;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.getMessage();
            return false;
        }
        return true;
    }

    // in đồ thị
    public void viewMatrix(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Dijkstra: shortest Distance from source 0th node to all other nodes
    int minimumDistance(int distance[], Boolean spSet[]) {
        int totalVertex = topNum();
        // Initialize min value
        int m = Integer.MAX_VALUE, m_index = -1;
        // arr[x][y] = - 1 means, there is no any edge that connects the nodes x and y
        // directly
        for (int vx = 0; vx < totalVertex; vx++) {
            if (spSet[vx] == false && distance[vx] <= m) {
                m = distance[vx];
                m_index = vx;
            }
        }
        return m_index;
    }

    // method that does the implementation of Dijkstra's shortest path algorithm
    // for a graph that is being represented using the adjacency matrix
    // representation
    void dijkstra(int graph[][], int s) {
        int totalVertex = topNum();
        int distance[] = new int[totalVertex]; // The output array distance[i] holds the shortest distance from source s
                                               // to j

        // spSet[j] will be true if vertex j is included in the shortest
        // path tree or the shortest distance from the source s to j is finalized
        Boolean spSet[] = new Boolean[totalVertex];

        // Initializing all of the distances as INFINITE
        // and spSet[] as false
        for (int j = 0; j < totalVertex; j++) {
            distance[j] = Integer.MAX_VALUE;
            spSet[j] = false;
        }

        // Distance from the source vertex to itself is always 0
        distance[s] = 0;

        // compute the shortest path for all the given vertices
        for (int cnt = 0; cnt < totalVertex - 1; cnt++) {
            // choose the minimum distance vertex from the set of vertices
            // not yet processed. ux is always equal to source s in the first
            // iteration.
            int ux = minimumDistance(distance, spSet);

            // the choosed vertex is marked as true
            // it means it is processed
            spSet[ux] = true;

            // Updating the distance value of the neighboring vertices
            // of the choosed vertex.
            for (int vx = 0; vx < totalVertex; vx++)

                // Update distance[vx] if and only if it is not in the spSet, there is an
                // edge from ux to vx, and the total weight of path from source s to
                // vx through ux is lesser than the current value of distance[vx]
                if (!spSet[vx] && graph[ux][vx] != -1 && distance[ux] != Integer.MAX_VALUE
                        && distance[ux] + graph[ux][vx] < distance[vx]) {
                    distance[vx] = distance[ux] + graph[ux][vx];
                }
        }

        // display the build distance array
        printSolution(distance, totalVertex);
    }

    // A utility method to display the built distance array
    void printSolution(int distance[], int n) {
        System.out.println("Node# \t\t\t Distance");
        for (int j = 0; j < n; j++)
            System.out.println(j + " \t\t\t " + distance[j]);
    }
}

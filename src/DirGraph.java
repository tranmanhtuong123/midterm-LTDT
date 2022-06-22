import java.util.ArrayList;
import java.util.Stack;

public class DirGraph extends Graph {
    // đồ thị có hướng

    @Override
    public boolean addEdge(int x, int y) {
        int vertexCount = topNum();
        if (x >= 0 && x < vertexCount && x < y && y < vertexCount) {
            this.arr[x][y] = 1;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEdge(int x, int y) {
        int vertexCount = topNum();
        if (x >= 0 && x < vertexCount && x < y && y < vertexCount) {
            this.arr[x][y] = 0;
            return true;
        }
        return false;
    }

    @Override
    public int allTopDegree() {
        return allTopDegreeX() + allTopDegreeY();
    }

    // theo cột
    public int topDegreeX(int x) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += this.arr[i][x];
        }
        return sum;
    }

    // theo hàng
    public int topDegreeY(int x) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += this.arr[x][i];
        }
        return sum;
    }

    public int allTopDegreeX() {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += topDegreeX(i);
        }
        return sum;
    }

    public int allTopDegreeY() {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += topDegreeY(i);
        }
        return sum;
    }

    @Override
    public int topDegree(int i) {
        return topDegreeX(i) + topDegreeY(i);
    }

    @Override
    public int numbDegree() {
        return allTopDegree();
    }

    public void translateGraph(DirGraph g) {
        int[][] test = g.arr;
        for (int i = 0; i < test.length; i++) {
            for (int j = 0; j < test[0].length; j++) {
                if (test[i][j] != 0) {
                    this.arr[i][j] = test[i][j];
                    this.arr[j][i] = test[i][j];
                }
            }
        }
    }

    @Override
    public boolean isConnect() {
        translateGraph(this);
        int i = 0;
        boolean connectyStrongly = true;
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
        for (int k = 0; k < vs.length; k++) {
            if (vs[k] != 1)
                return false;
            if (topDegreeX(k) == 0 || topDegreeY(k) == 0)
                connectyStrongly = false;
        }
        if (connectyStrongly) {
            System.out.println("Connecty Strongly");
        } else {
            System.out.println("Connecty Weakly");
        }
        return true;
    }

}

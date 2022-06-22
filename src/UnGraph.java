import java.util.ArrayList;
import java.util.Stack;

public class UnGraph extends Graph {
    // đồ thị vô hướng

    @Override
    public boolean addEdge(int x, int y) {
        int vertexCount = topNum();
        if (x >= 0 && x < vertexCount && x < y && y < vertexCount) {
            this.arr[x][y] = 1;
            this.arr[y][x] = 1;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEdge(int x, int y) {
        int vertexCount = topNum();
        if (x >= 0 && x < vertexCount && x < y && y < vertexCount) {
            this.arr[x][y] = 0;
            this.arr[y][x] = 0;
            return true;
        }
        return false;
    }

    @Override
    public int allTopDegree() {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                sum += this.arr[i][j];
            }
        }
        return sum;
    }

    @Override
    public int topDegree(int i) {
        int sum = 0;
        if (i < 0 || i > topNum())
            return -1;
        for (int j = 0; j < arr.length; j++) {
            sum += this.arr[j][i];
        }
        return sum;
    }

    @Override
    public int numbDegree() {
        return allTopDegree() / 2;
    }

    @Override
    public boolean isConnect() {
        int i = 0;
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
        }
        return true;
    }

}

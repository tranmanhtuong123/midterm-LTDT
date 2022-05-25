public class DirGraph extends Graph {
    // có vô hướng

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

}

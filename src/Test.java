public class Test {

    public static void main(String[] args) {
        String filePath = "src\\data\\test2.txt";
        Graph ug = new UnGraph(); // đồ thị vô hướng
        Graph du = new DirGraph();// đồ thị có hướng
        ug.loadData(filePath);
        du.loadData(filePath);

        System.out.println("loadData from file .txt:");
        ug.viewMatrix(ug.arr);

        // DFS - BFS
        // ug.DFS(0);
        // ug.printArr(ug.listVisit);
        // ug.DFSNotRecursive(0);
        // ug.BFS(0);

        // Tính liên thông của đồ thị
        // System.out.println(ug.isConnect());
        // System.out.println(du.isConnect());

        // Thêm Và Xóa Cạnh Cho Đồ Thị
        // System.out.println("add Edge:");
        // ug.addEdge(0, 1);
        // ug.viewMatrix(ug.arr);
        // System.out.println("remove Edge:");
        // ug.removeEdge(0, 1);
        // ug.viewMatrix(ug.arr);

        // Thêm Và Xóa Đỉnh Cho Đồ Thị
        // System.out.println("add Top:");
        // ug.addTop();
        // ug.viewMatrix(ug.arr);
        // System.out.println("remove Top:");
        // ug.removeTop();
        // ug.viewMatrix(ug.arr);

        // Tính Bậc Của Đỉnh -Tính Cạnh Của Đồ Thị
        // đồ thị có hướng
        // System.out.println(ug.topDegree(4));
        // System.out.println(ug.allTopDegree());
        // System.out.println(ug.numbDegree());
        // đồ thị vô hướng
        // System.out.println(du.topDegree(4));
        // System.out.println(du.numbDegree());
        // System.out.println(du.allTopDegree());

        // Dijkstra algo
        // ug.dijkstra(ug.arr, 0);
    }
}

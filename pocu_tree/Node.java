package pocu_tree;

public class Node {

    private int data;

    private Node left; // 왼쪽 자식노드
    private Node right; // 오른쪽 자식노드

    private Node parent; // 부모 노드

    public Node(int data, Node parent) {
        this.data = data;
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void printStatus() {
        int l = -1; int r = -1;
        if(left != null) {
            l = left.getData();
        }
        if(right != null) {
            r = right.getData();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("data: " + data).append("|left:" + l).append("|right:" + r)
                .append(System.lineSeparator());
        System.out.println(sb);
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}

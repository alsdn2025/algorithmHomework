package pocu_tree.bst;

import pocu_tree.Node;
import pocu_tree.Orders;

import java.util.Stack;


public class Main {

    public static Node searchBST(Node node, int data) {
        if(node == null)
            return null;

        if(data == node.getData())
            return node;

        if(data < node.getData()){
            return searchBST(node.getLeft(), data);
        }else
            return searchBST(node.getRight(), data);
    }

    public static void insertBST(Node node, int data) {
        if(data <= node.getData()) {
            if(node.getLeft() == null)
                node.setLeft(new Node(data, node));
            else
                insertBST(node.getLeft(), data);
        }else {
            if(node.getRight() == null)
                node.setRight(new Node(data, node));
            else
                insertBST(node.getRight(), data);
        }
    }

    /**
     * 트리 출력
     */
    public static void printBST(Node node, Orders order) {
        StringBuilder sb = new StringBuilder();

        switch (order) {
            case IN_ORDER:
                printBSTRecursive(node, sb);
                break;
            case PRE_ORDER:
                printBSTRecursivePreOrder(node, sb);
//                printBSTPreOrder(node, sb); // 재귀 X 버전
                break;
            case POST_ORDER:
                printBSTRecursivePostOrder(node, sb);
                break;
            default:
                System.out.println("WARN: Main.printBST(): NO_ORDER_PARAM");
        }

        System.out.println(sb);
    }

    /**
     * 중위 순회(in-order)
     */
    private static void printBSTRecursive(Node node, StringBuilder sb) {
        if(node == null)
            return;
        printBSTRecursive(node.getLeft(), sb);
        sb.append("[").append(node.getData()).append("]");
        printBSTRecursive(node.getRight(), sb);
    }

    /**
     * 전위 순회
     */
    private static void printBSTRecursivePreOrder(Node node, StringBuilder sb) {
        if(node == null)
            return;
        sb.append("[").append(node.getData()).append("]");
        printBSTRecursivePreOrder(node.getLeft(), sb);
        printBSTRecursivePreOrder(node.getRight(), sb);
    }

    /**
     * 전위 순회 - 재귀 X
     */
    public static void printBSTPreOrder(Node root, StringBuilder sb) {
        if(root == null)
            return;

        Stack<Node> stack = new Stack<>();

        stack.push(root);
        while(!stack.empty()) {
            Node node = stack.pop();

            sb.append("[").append(node.getData()).append("]");

            // 스택은 선입후출, 따라서 먼저 방문할 요소를 나중에 넣는다.
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            if(node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    /**
     * 후위 순회
     */
    private static void printBSTRecursivePostOrder(Node node, StringBuilder sb) {
        if(node == null)
            return;
        printBSTRecursivePostOrder(node.getLeft(), sb);
        printBSTRecursivePostOrder(node.getRight(), sb);
        sb.append("[").append(node.getData()).append("]");
    }

    /**
     * 트리 복사, 전위 순회 사용
     */
    public static Node copyTreeRecursivePreOrder(Node nodeParam) {

        if (nodeParam == null)
            return null;

        Node node = new Node(nodeParam.getData(), nodeParam.getParent());
        node.setLeft(copyTreeRecursivePreOrder(nodeParam.getLeft()));
        node.setRight(copyTreeRecursivePreOrder(nodeParam.getRight()));
        return node;
    }

    public static boolean deleteBST(Node node, int targetValue) {

        if(node == null) {
            System.out.println("ERROR: PARAM NODE IS NULL");
            return false;
        }

        Node target = searchBST(node, targetValue);
        if(target == null){
            System.out.println("ERROR: TARGET NOT EXISTS");
            return false;
        }

        Node leafNode;
        if(target.getLeft() != null){
            leafNode = findRightmostNode(target.getLeft());
        }else if(target.getRight() != null) {
            leafNode = findLeftmostNode(target.getRight());
        }else {
            // target is leaf
            makeLeafToOrphaned(target);
            return true;
        }

        target.setData(leafNode.getData());
        makeLeafToOrphaned(leafNode);

        return true;
    }

    public static void makeLeafToOrphaned(Node orphan) {

        Node parent = orphan.getParent();

        if(parent == null){
            System.out.println("ERROR: TARGET IS THE ONLY NODE IN THE TREE");
        }

        if(parent.getLeft() == orphan) {
            parent.setLeft(null);
        }else {
            parent.setRight(null);
        }
    }

    // BST 에서 가장 왼쪽 노드를 찾아서 리턴
    public static Node findLeftmostNode(Node node) {
        if(node == null) {
            System.out.println("ERROR: findLeftmostNode- PARAM IS NULL");
            return null;
        }

        if(node.getLeft() != null) {
            return findLeftmostNode(node.getLeft());
        }else if (node.getRight() != null) {
            return findLeftmostNode(node.getRight());
        }else
            return node;
    }

    // BST 에서 가장 오른쪽 노드를 찾아서 리턴
    public static Node findRightmostNode(Node node) {
        if(node == null) {
            System.out.println("ERROR: findRightmostNode- PARAM IS NULL");
            return null;
        }

        if(node.getRight() != null) {
            return findRightmostNode(node.getRight());
        }else if (node.getLeft() != null) {
            return findRightmostNode(node.getLeft());
        }else
            return node;
    }

    public static void main(String[] args) {
        Node root = new Node(6, null);
        insertBST(root, 4);
        insertBST(root, 5);
        insertBST(root, 2);
        insertBST(root, 1);
        insertBST(root, 3);
        insertBST(root, 10);
        insertBST(root, 8);
        insertBST(root, 15);
        insertBST(root, 7);
        insertBST(root, 9);
        insertBST(root, 12);

        System.out.println("중위 순회");
        printBST(root, Orders.IN_ORDER);

//        deleteBST(root, 10);
//        printBST(root);

        System.out.println("복사 트리 - 중위 순회 출력:");
        Node copyRoot = copyTreeRecursivePreOrder(root);
        printBST(copyRoot, Orders.IN_ORDER);

    }
}

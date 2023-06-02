package pocu_dfs_bfs;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 간단한 형태의 트리용 깊이 우선 탐색 / 너비 우선 탐색 코드
 */
public class DFS_BFS_Program {

    // 트리를 예제 형태로 초기화
    public static Node initTree() {
        Node root = new Node('A');
        Node nodeB = new Node('B');
        Node nodeC = new Node('C');
        Node nodeD = new Node('D');
        Node nodeE = new Node('E');
        Node nodeF = new Node('F');
        Node nodeG = new Node('G');
        Node nodeH = new Node('H');
        Node nodeI = new Node('I');
        Node nodeJ = new Node('J');
        Node nodeK = new Node('K');
        Node nodeL = new Node('L');
        Node nodeM = new Node('M');
        Node nodeN = new Node('N');

        root.children.add(nodeB);
        root.children.add(nodeC);
        root.children.add(nodeD);
        nodeB.children.add(nodeE);
        nodeB.children.add(nodeF);
        nodeB.children.add(nodeG);
        nodeC.children.add(nodeH);
        nodeD.children.add(nodeI);

        nodeE.children.add(nodeJ);
        nodeE.children.add(nodeK);
        nodeF.children.add(nodeL);
        nodeH.children.add(nodeM);
        nodeH.children.add(nodeN);

        return root;
    }

    // 재귀를 이용한 트리 dfs
    public static void dfs_recursive(Node node) {
        if(node == null)
            return;

        System.out.print( "[" + node.data + "]");

        for(Node c : node.children) {
            dfs_recursive(c);
        }
    }

    // 스택을 이용한 트리 dfs
    public static void dfs_stack(Node node) {
        Stack<Node> stack = new Stack<>();

        stack.push(node); // 루트 노드를 스택에 먼저 넣는다

        /**
         !! 스택이 비면 루프가 끝난다. 따라서 출력을 위해선 스택을 계속 채워줘야 한다.
          */
        while(!stack.isEmpty()) {
            Node next = stack.pop(); // 스택의 가장 위의 노드를 꺼내와 출력한다.

            System.out.print( "[" + next.data + "]"); //

            // 꺼내온 노드의 자식 노드를 스택에 넣어준다.
            // 스택은 선입후출, 왼쪽부터 출력하고 싶으면 오른쪽부터 넣어야 한다.
            for(int i = next.children.size() - 1; i >= 0; --i) {
                stack.push(next.children.get(i));
            }
        }
    }

    // 큐를 이용한 트리 bfs
    public static void bfs_stack(Node node) {
        NodeQueue queue = new NodeQueue();

        queue.push(node);

        while(!queue.isEmpty()) {
            Node next = queue.pop();

            System.out.print( "[" + next.data + "]");

            for(Node c : next.children) {
                queue.push(c);
            }
        }
    }

    // bfs 에 사용하기 위한 큐 클래스
    static class NodeQueue {
        ArrayList<Node> queue = new ArrayList<>();

        void push(Node node) {
            queue.add(node);
        }

        Node pop() {
            return queue.remove(0);
        }

        int size() {
            return this.queue.size();
        }

        boolean isEmpty() {
            return this.queue.size() == 0;
        }
    }

    public static void main(String[] args) {
        Node root = initTree(); // 트리를 예제 형태로 초기화

//        dfs_recursive(root);
//        dfs_stack(root);
        bfs_stack(root);

    }
}

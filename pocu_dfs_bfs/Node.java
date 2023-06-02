package pocu_dfs_bfs;

import java.util.ArrayList;

/**
 * DFS/BFS 를 테스트하기 위한 트리를 구성하는 노드
  */
public class Node {
    public char data;
    public Node parent;
    public ArrayList<Node> children = new ArrayList<>();

    public Node(char data) {
        this.data = data;
    }

    public Node(char data, Node parent) {
        this.data = data;
        this.parent = parent;
    }
}

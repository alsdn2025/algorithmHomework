package pocu_datastructure;

public class LinkedListEx {
    public static void main(String[] args) {
        LinkedList list = new LinkedList(1);
        list.add(2);
        list.add(3);
        list.addRight(4,2);
        list.add(5);
        list.add(6);
        list.addRight(7,1);
        list.printList();
    }
}



/**
 * 단일 연결 리스트를 만들자
 */
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value,Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
    public Node(int value, Node left) {
        this.value = value;
        this.left = left;
        this.right = null;
    }
    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public void setLeft(Node left){
        this.left = left;
    }
    public void setRight(Node right){
        this.right = right;
    }

    public void setValue(){
        this.value = value;
    }
}

class LinkedList {
    Node root;
    public LinkedList(int rootValue) {
         root = new Node(rootValue);
    }

    public void printList(){
        Node temp = root;
        do{
            System.out.printf("[%3d] ", temp.value);
            temp = temp.right;
        }while (temp != null);

    }

    public void add(int value) {
        Node node = new Node(value);
        Node temp = root;
        while(temp.right != null){
            temp = temp.right;
        }
        temp.right = node;
    }

    public void addRight(int value, int leftValue){
        Node node = new Node(value);
        Node leftNode = search(leftValue);
        if(leftNode == null)
            return;

        node.right = leftNode.right;
        leftNode.right = node;
    }

    public void del(int index){

    }

    public Node search(int value){
        Node temp = root;
        int n = 0;
        while(temp != null){
            if(temp.value == value){
//                System.out.printf("%d노드는 %d 인덱스에 있습니다.", value, n);
                return temp;
            }
            temp = temp.right;
            n++;
        }
        System.out.println("노드를 찾지 못했습니다.");
        return null;
    }

}
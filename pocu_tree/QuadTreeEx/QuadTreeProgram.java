package pocu_tree.QuadTreeEx;

import java.awt.*;
import java.util.ArrayList;

public class QuadTreeProgram {
    public static void main(String[] args) {
        final Point p1 = new Point(1, 4);
        final GameObject gameObject1 = new GameObject(p1, 1);
        final Point p2 = new Point(7,9);
        final GameObject gameObject2 = new GameObject(p2, 2);
        final Point p3 = new Point(3,7);
        final GameObject gameObject3 = new GameObject(p3, 3);
        final Point p4 = new Point(4, 8);
        final GameObject gameObject4 = new GameObject(p4, 4);
        // ...

        Point topLeft = new Point(0, 10);

        BoundingRect rect = new BoundingRect(topLeft, 10, 10);
        final Quadrant root = new Quadrant(rect);

        root.insert(gameObject1);
        root.insert(gameObject2);
        root.insert(gameObject3);
        root.insert(gameObject4);

        topLeft = new Point(2, 9);
        rect = new BoundingRect(topLeft, 3, 3);

        ArrayList<GameObject> gameObjects = root.getGameObjects(rect);
        System.out.println("해당 사분면 안에 있는 오브젝트들: ");
        for (GameObject o : gameObjects) {
            System.out.print("gameObj-" + o.id + " ");
        }

        System.out.println();
    }
}

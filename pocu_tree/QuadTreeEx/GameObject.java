package pocu_tree.QuadTreeEx;

import java.awt.*;

public class GameObject {
    Point position;
    final int id;

    public GameObject(int id) {
        this.position = new Point(0,0);
        this.id = id;
    }

    public GameObject(Point p, int id) {
        this.position = p;
        this.id = id;
    }

    public GameObject(int x, int y, int id) {
        this.position = new Point(x,y);
        this.id = id;
    }
}

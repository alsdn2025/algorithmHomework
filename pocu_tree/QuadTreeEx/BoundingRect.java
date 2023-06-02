package pocu_tree.QuadTreeEx;

import java.awt.*;

// 2d 직사각형 영역을 표시하는 클래스
public class BoundingRect {
    Point topLeft;
    Point bottomRight;
    int width;
    int height;

    public BoundingRect(int x1, int y1, int x2, int y2) {
        assert x1 < x2 || y1 > y2;

        this.topLeft = new Point(x1, y1);
        this.bottomRight = new Point(x2, y2);
        this.width = Math.abs(x2 - x1);
        this.height = Math.abs(y2 - y1);
    }

    public BoundingRect(Point p, int width, int height) {

        this.topLeft = new Point(p.x, p.y);
        this.bottomRight = new Point(p.x + width, p.y - height);
        this.width = width;
        this.height = height;
    }

    public boolean contains(Point p) {
        if(p.x >= this.topLeft.x && p.y <= this.topLeft.y) {
            return p.x <= this.bottomRight.x && p.y >= this.bottomRight.y;
        }
        return false;
    }

    public boolean contains(BoundingRect rect) {
        if(rect.topLeft.x >= this.topLeft.x && rect.bottomRight.x <= this.bottomRight.x) {
            if(rect.topLeft.y <= this.topLeft.y && rect.bottomRight.y >= this.bottomRight.y) {
                return true;
            }
        }

        return false;
    }
}

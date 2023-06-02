package pocu_tree.QuadTreeEx;

import java.awt.*;
import java.util.ArrayList;

/**
 * 2D 영역(사분면)을 나타내는 클래스
 * 일반적인 트리에서 사용하는 Node 와 대응
 */
public class Quadrant {
    // 너비나 높이가 해당 값 이하가 되면 더 이상 하위 사분면을 만들지 않는다.
    private static final int MIN_QUAD_DIMENSION = 2;

    private final BoundingRect boundingRect; // 해당 사분면이 다루는 2d 직사각형 영역

    private Quadrant topLeft;
    private Quadrant topRight;
    private Quadrant bottomLeft;
    private Quadrant bottomRight;

    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    public Quadrant(BoundingRect boundingRect) {
        this.boundingRect = boundingRect;

        createChildren(); // 하위 사분면 생성
    }

    // 하위 사분면 객체들을 생성하는 메서드
    private void createChildren() {
        final int width = this.boundingRect.width;
        final int height = this.boundingRect.height;

        if (width < 2 * MIN_QUAD_DIMENSION || height < 2 * MIN_QUAD_DIMENSION) {
            return;
        }

        int x1 = this.boundingRect.topLeft.x;
        int y1 = this.boundingRect.topLeft.y;
        int x2 = this.boundingRect.bottomRight.x;
        int y2 = this.boundingRect.bottomRight.y;

        int midX = (x1 + x2) / 2;
        int midY = (y1 + y2) / 2;

        BoundingRect rect = new BoundingRect(x1, y1, midX, midY);
        this.topLeft = new Quadrant(rect);
        rect = new BoundingRect(midX, y1, x2, midY);
        this.topRight = new Quadrant(rect);
        rect = new BoundingRect(x1, midY, midX, y2);
        this.bottomLeft = new Quadrant(rect);
        rect = new BoundingRect(midX, midY, x2, y2);
        this.bottomRight = new Quadrant(rect);
    }

    // 게임 오브젝트를 해당 사분면에 추가하는 메서드
    public boolean insert(final GameObject gameObject) {
        final Point position = gameObject.position;

        // 게임 오브젝트가 해당 사분면 안에 존재하지 않으면 추가하지 않는다
        if(!this.boundingRect.contains(position)) {
            return false;
        }

        this.gameObjects.add(gameObject);

        // 하위 사분면에도 해당 게임오브젝트에 대한 insert 를 호출
        if(this.topLeft != null) {
            this.topLeft.insert(gameObject);
            this.topRight.insert(gameObject);
            this.bottomLeft.insert(gameObject);
            this.bottomRight.insert(gameObject);
        }

        return true;
    }

    // 사용자가 요청하는 영역에 해당하는 게임 오브젝트 목록을 반환하는 메서드
    public ArrayList<GameObject> getGameObjects(final BoundingRect rect) {
        if(!this.boundingRect.contains(rect)) {
            return new ArrayList<>();
        }

        // 요청 영역을 커버하는 가장 작은 사분면을 찾는다
        // 하위 사분면이 없을 경우 this 가 가장 작은 영역이므로 게임 오브젝트를 반환한다
        if(this.topLeft == null) {
            return this.gameObjects;
        }

        // 하위 사분면에 요청 영역이 포함될 경우 하위 사분면에게 재귀 요청한다
        if(this.topLeft.boundingRect.contains(rect)) {
            return this.topLeft.getGameObjects(rect);
        }
        if (this.topRight.boundingRect.contains(rect)) {
            return this.topRight.getGameObjects(rect);
        }
        if (this.bottomLeft.boundingRect.contains(rect)) {
            return this.bottomLeft.getGameObjects(rect);
        }
        if (this.bottomRight.boundingRect.contains(rect)) {
            return this.bottomRight.getGameObjects(rect);
        }

        // 어떤 자식 사분면도 요청 영역을 커버하지 못할 경우, this 가 가장 작은 사분면이므로 게임 오브젝트를 반환한다
        return this.gameObjects;
    }
}

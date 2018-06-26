package logic;

import javafx.scene.paint.Color;
import java.util.Random;

public class Grid {
    public static final int SIZE = 10;
    private final int cols;
    private final int rows;
    private Snake snake;
    private Food food;

    public Grid(final double width, final double height) {
        rows = (int) width / SIZE;
        cols = (int) height / SIZE;
        snake = new Snake(this, new Point(rows / 2, cols / 2));
        food = new Food(getRandomPoint());
    }

    public Point wrap(Point point) {
        int x = point.getX();
        int y = point.getY();
        if (x >= rows || x < 0) Snake.safe = false;
        if (y >= cols || y < 0) Snake.safe = false;
        return new Point(x, y);
    }

    private Point getRandomPoint() {
        Random random = new Random();
        Point point;
        do {
            point = new Point(random.nextInt(rows), random.nextInt(cols));
        } while (point.equals(snake.getHead()));
        return point;
    }

    public void update() {
        if (food.getPoint().equals(snake.getHead())) {
            snake.extend();
            food.setPoint(getRandomPoint());
        } else {
            snake.move();
        }
    }

    public double getWidth() {
        return rows * SIZE;
    }

    public double getHeight() {
        return cols * SIZE;
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }
}

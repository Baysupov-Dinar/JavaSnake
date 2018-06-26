package gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Grid;
import logic.Point;
import logic.Snake;

import static logic.Grid.SIZE;

public class Painter {
    private static final Color COLOR = Color.FIREBRICK;
    private static final Color DEAD = Color.RED;
    private static final Color FIELD = new Color(0.1, 0.1, 0.1, 1);
    private static final Color FOOD = Color.DEEPPINK;

    public static void paint(Grid grid, GraphicsContext gc) {
        gc.setFill(FIELD);
        gc.fillRect(0, 0, grid.getWidth(), grid.getHeight());
        gc.setFill(FOOD);
        paintPoint(grid.getFood().getPoint(), gc);
        Snake snake = grid.getSnake();
        gc.setFill(COLOR);
        snake.getPoints().forEach(point -> paintPoint(point, gc));
        if (!snake.isSafe()) {
            gc.setFill(DEAD);
            paintPoint(snake.getHead(), gc);
        }

        gc.setFill(Color.valueOf("FFE400"));
        gc.fillText("Score : " + 100 * snake.getPoints().size(), 10, 490);
    }

    private static void paintPoint(Point point, GraphicsContext gc) {
        gc.fillRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
    }

    public static void paintResetMessage(GraphicsContext gc) {
        gc.setFill(Color.AQUAMARINE);
        gc.fillText("Press RETURN to reset.", 10, 10);
    }
}

package logic;

import gui.Painter;
import javafx.scene.canvas.GraphicsContext;

public class Game implements Runnable {

    private final Grid grid;
    private final GraphicsContext context;
    private float interval;
    private boolean running;
    private boolean paused;
    private boolean keyIsPressed;

    public Game(final Grid grid, final GraphicsContext context) {
        this.grid = grid;
        this.context = context;
        int frameRate = 20;
        interval = 1500.0f / frameRate;
        running = true;
        paused = false;
        keyIsPressed = false;
    }

    @Override
    public void run() {
        while (running && !paused) {
            float time = System.currentTimeMillis();
            keyIsPressed = false;
            grid.update();
            Painter.paint(grid, context);
            if (!grid.getSnake().isSafe()) {
                pause();
                Painter.paintResetMessage(context);
                break;
            }
            time = System.currentTimeMillis() - time;
            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException ignore) {
                }
            }
        }
    }

    public boolean isKeyPressed() {
        return keyIsPressed;
    }

    private void pause() {
        paused = true;
    }

    public boolean isPaused() {
        return paused;
    }
}

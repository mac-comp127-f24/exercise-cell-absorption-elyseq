package cellabsorption;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.awt.List;
import java.util.Random;
import java.util.ArrayList;

@SuppressWarnings("SameParameterValue")
public class CellSimulation {
    private CanvasWindow canvas;
    private Random rand = new Random();
    private ArrayList<Cell> cells;

    public static void main(String[] args) {
        new CellSimulation();
    }

    public CellSimulation() {
        canvas = new CanvasWindow("Cell Absorption", 800, 800);
        populateCells();

        //noinspection InfiniteLoopStatement
        while (true) {
            Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
            for(int i = 0; i < cells.size(); i++) {
                cells.get(i).moveAround(canvasCenter);
                cells.get(i).grow(0.02);
            }
            canvas.draw();
            canvas.pause(10);
        }
    }

    private void populateCells() {
        double size = rand.nextInt(5) + 2;
        cells = new ArrayList<>();
        for(int i = 0; i <= 200; i++) {
            Cell cell = new Cell(rand.nextDouble() * (canvas.getWidth() - size), rand.nextDouble() * (canvas.getWidth() - size), size, Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
            cells.add(cell);
            canvas.add(cells.get(i).getShape());
        }
    }

    private static double sqr(double x) {
        return x * x;
    }

}
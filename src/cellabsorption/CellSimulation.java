package cellabsorption;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;

import java.awt.Color;
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
                handleCellInteraction();
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

    private void handleCellInteraction() {
        for(int i = 0; i < cells.size(); i++) { // for i from 0 up to max cell index
            Cell cell1 = cells.get(i);// get cell at index i
            for(int j = i + 1; j < cells.size(); j++) { // for j from i+1 up to max cell index
                Cell cell2 = cells.get(j);// get cell at index j
                cell1.interactWith(cell2);// make the two cells interact
            }
        }
    }

}
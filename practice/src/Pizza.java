import java.util.*;

/**
 * Created by Thomas on 2017. 02. 21..
 */
public class Pizza {
    private int height, width;
    private int[][] pizza;
    private int[][] scores;
    private int[][] cells;

    private Map<int[],Cell> nextCells = new HashMap<>();
    private List<Integer[]> slices = new ArrayList<>();
    private Map<int[],List<int[]>> triedSlices = new HashMap<>();

    public Pizza(int[][] pizza) {
        this.pizza = pizza;
        height = pizza.length;
        width = pizza[0].length;
        cells = new int[pizza.length][pizza[0].length];

        updateScores();
    }

    public int[] getNextCell() {
        List<Cell> possibleCells = new ArrayList(nextCells.values());
        Collections.sort(possibleCells);
        return possibleCells.get(0).getCoordinate();
    }

//    TODO
    private boolean isCellCovered(int[] coordinate) {
        // covered or out of range
        if (coordinate[0] < 0 || coordinate[1] < 0 || coordinate[0] > height || coordinate[1] > width) {
            return true;
        }
        return false;
    }

//    TODO
    public int getSliceScore(int[] slice) {
        return -1;
    }

//    TODO
    public void addSlice(int[] slice) {
        // update cells
        // update nextCells
        // update scores
        // add triedSlices
    }

//    TODO
    public void removeSlice(int[] slice) {
        // remove from triedSlices
        // update cells
        // update nextCells
        // update scores
    }



    public void updateScores() {}
}

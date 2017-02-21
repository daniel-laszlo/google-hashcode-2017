/**
 * Created by Thomas on 2017. 02. 21..
 */
public class Cell implements Comparable<Cell> {
    private Integer score;
    private int[] coordinate;
    private Integer neighbours;

    public Cell(int[] coordinate) {
        this.coordinate = coordinate;
    }

    public Cell(int score, int[] coordinate, int neighbours) {
        this.score = score;
        this.coordinate = coordinate;
        this.neighbours = neighbours;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int[] coordinate) {
        this.coordinate = coordinate;
    }

    public int getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(int neighbours) {
        this.neighbours = neighbours;
    }

    @Override
    public int compareTo(Cell o) {
        return neighbours == o.getNeighbours() ? score.compareTo(o.getScore()) : neighbours.compareTo(neighbours);
    }
}

/**
 * Created by Daniel on 2017. 02. 21..
 */
public interface IFileHandler {

	public void setFile(int i);

	public int[][] readMap();

	public void printSolutionToFile(int[][] i) throws IndexOutOfBoundsException;

	public int getL();

	public int getH();
}

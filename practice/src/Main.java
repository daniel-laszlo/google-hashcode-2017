/**
 * Created by Daniel on 2017. 02. 21..
 */
public class Main {

	public static void main(String[] args){

		FileHandlerImpl fileHandler = new FileHandlerImpl();
		fileHandler.setFile(1);
		int[][] map = fileHandler.readMap();
		int i = map[0][0];
		Pizza pizza = new Pizza(map);

	}
}

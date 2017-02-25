/**
 * Created by Daniel on 2017. 02. 21..
 */
public class Main {

	public static void main(String[] args) {
		FileHandlerImpl fileHandler = new FileHandlerImpl();
		fileHandler.setFile(5);
		fileHandler.initModel();
		Model model = new Model();
		model.init();
		model.run();

		fileHandler.printSolutionToFile();
		System.out.println("ok");
	}
}

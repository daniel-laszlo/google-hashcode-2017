import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Daniel on 2017. 02. 21..
 */
public class FileHandlerImpl implements IFileHandler {

	/**
	 * USAGE:
	 *
	 FileHandlerImpl fileHandler = new FileHandlerImpl();
	 fileHandler.setFile(1);
	 int[][] map = fileHandler.readMap();
	 int i = map[0][0];
	 */

	private static final String INPUT_FILE_NAME_1 = "kittens.in";
	private static final String INPUT_FILE_NAME_2 = "me_at_the_zoo.in";
	private static final String INPUT_FILE_NAME_3 = "trending_today.in";
	private static final String INPUT_FILE_NAME_4 = "videos_worth_spreading.in";
	private static String chosenInputFile = "small.in";
	private static String chosenOutPutFile = "small.out";

	private int L;
	private int H;

	@Override
	public void setFile(int i) {
		if (i > 4 || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (i == 1) {
			chosenInputFile = INPUT_FILE_NAME_1;
			chosenOutPutFile = "kittens.out";
		} else if (i == 2) {
			chosenInputFile = INPUT_FILE_NAME_2;
			chosenOutPutFile = "me_at_the_zoo.out";
		} else if (i == 3) {
			chosenInputFile = INPUT_FILE_NAME_3;
			chosenOutPutFile = "trending_today.out";
		} else if (i == 4) {
			chosenInputFile = INPUT_FILE_NAME_4;
			chosenOutPutFile = "videos_worth_spreading.out";
		}
	}

	@Override
	public int[][] readMap() {
		File file = new File(new File("practice/resource/" + chosenInputFile).getAbsolutePath());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String[] params = scanner.nextLine().split(" ");
		int[] intParams = new int[4];
		for (int i1 = 0; i1 < intParams.length; i1++) {
			intParams[i1] = Integer.parseInt(params[i1]);
		}
		L = intParams[2];
		H = intParams[3];

		int[][] ret = new int[intParams[0]][intParams[1]];

		for (int i = 0; i < intParams[0]; ++i) {
			char[] vegetablesLine = scanner.next().toCharArray();
			for (int j = 0; j < vegetablesLine.length; ++j) {
				if (vegetablesLine[j] == 'T') {
					ret[i][j] = -1;
				} else if (vegetablesLine[j] == 'M') {
					ret[i][j] = 1;
				}
			}
		}

		return ret;
	}

	@Override
	public int getL() {
		return L;
	}

	@Override
	public int getH() {
		return H;
	}

	@Override
	public void printSolutionToFile(int[][] solution) throws IndexOutOfBoundsException {
		if (solution[0].length != 4) {
			throw new IndexOutOfBoundsException();
		}
		StringBuilder stringBuilder = new StringBuilder();
		System.out.println(solution.length);
		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution.length - 1; j++) {
				stringBuilder.append(solution[i][j] + " ");
			}
			stringBuilder.append(solution[i][solution.length - 1] + "\n");
		}
		try {
			Files.write(Paths.get("./" + chosenOutPutFile), stringBuilder.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

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


	private static final String INPUT_FILE_NAME_1 = "kittens.in";
	private static final String INPUT_FILE_NAME_2 = "me_at_the_zoo.in";
	private static final String INPUT_FILE_NAME_3 = "trending_today.in";
	private static final String INPUT_FILE_NAME_4 = "videos_worth_spreading.in";
	private static String chosenInputFile = "kittens.in";
	private static String chosenOutPutFile = "kittens.out";

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

	public Model initModel() {
		File file = new File(new File("live/resource/" + chosenInputFile).getAbsolutePath());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String[] params = scanner.nextLine().split(" ");
		int[] intParams = new int[5];
		for (int i1 = 0; i1 < intParams.length; i1++) {
			intParams[i1] = Integer.parseInt(params[i1]);
		}

		int V = intParams[0];
		int E = intParams[1];
		int R = intParams[2];
		int C = intParams[3];
		int X = intParams[4];

		Request[] requests = new Request[R];
		int[] videoSizes = new int[V];
		Endpoint[] endpoints = new Endpoint[E];
		CacheServer[] cacheServers = new CacheServer[C];

		// videoSizes
		String[] videoSizesArray = scanner.nextLine().split(" ");
		for (int i1 = 0; i1 < videoSizes.length; i1++) {
			videoSizes[i1] = Integer.parseInt(videoSizesArray[i1]);
		}

		// Endpoints
		for (int e = 0; e < E; ++e) {
			Endpoint endpoint = new Endpoint();
			params = scanner.nextLine().split(" ");
			int latency = Integer.parseInt(params[0]);
			int connectedToCachesNumber = Integer.parseInt(params[1]);
			for (int c = 0; c < connectedToCachesNumber; ++c) {
				String[] params1 = scanner.nextLine().split(" ");
				endpoint.getCacheServerIds().add(Integer.parseInt(params1[0]));
				endpoint.getTimeToReachCacheServers().add(Integer.parseInt(params1[1]));
			}
			endpoint.setLatencyToDataCenter(latency);
			endpoints[e] = endpoint;
		}

		// Request description
		int requestId = 0;
		for (int r = 0; r < R; ++r) {
			Request request = new Request();
			params = scanner.nextLine().split(" ");

			request.setRequestId(requestId++);
			request.setEndPointId(Integer.parseInt(params[1]));
			request.setRequestDarab(Integer.parseInt(params[2]));
			requests[r] = request;
		}

		// Cache servers
		for (int c = 0; c < C; ++c) {
			CacheServer cacheServer = new CacheServer();
			cacheServer.setSize(X);

			cacheServers[c] = cacheServer;
			
		}

		Model model = new Model(requests, videoSizes, endpoints, cacheServers);
		return model;
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

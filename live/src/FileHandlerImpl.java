import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Daniel on 2017. 02. 21..
 */
public class FileHandlerImpl {

	private static final String INPUT_FILE_NAME_1 = "kittens.in";
	private static final String INPUT_FILE_NAME_2 = "me_at_the_zoo.in";
	private static final String INPUT_FILE_NAME_3 = "trending_today.in";
	private static final String INPUT_FILE_NAME_4 = "videos_worth_spreading.in";
	private static final String INPUT_FILE_NAME_5 = "proba.in";
	private static String chosenInputFile = "proba.in";
	private static String chosenOutPutFile = "proba.out";

	public void setFile(int i) {
		if (i > 5 || i < 0) {
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
		} else if (i == 5) {
			chosenInputFile = INPUT_FILE_NAME_5;
			chosenOutPutFile = "proba.out";
		}
	}

	public void initModel() {
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

		Model.videoSizes = videoSizes;

		// Request description
		int requestId = 0;
		for (int r = 0; r < R; ++r) {
			Request request = new Request();
			params = scanner.nextLine().split(" ");

			request.setRequestId(requestId++);
			request.setEndPointId(Integer.parseInt(params[1]));
			request.setRequestCount(Integer.parseInt(params[2]));
			request.setVideoId(Integer.parseInt(params[0]));
			requests[r] = request;
		}

		// Cache servers
		for (int c = 0; c < C; ++c) {
			CacheServer cacheServer = new CacheServer();
			cacheServer.setSize(X);

			cacheServers[c] = cacheServer;
		}

		Model.cacheServers = cacheServers;
		Model.requests = requests;
		Model.endpoints = endpoints;
	}

	public void printSolutionToFile() throws IndexOutOfBoundsException {
		if (Model.getActiveCacheServers().size() > Model.cacheServers.length) {
			throw new IndexOutOfBoundsException();
		}

		List<Integer> activeServerIds = Model.getActiveCacheServers();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(activeServerIds.size() + "\n");
		for (int i = 0; i < activeServerIds.size(); i++) {
			CacheServer cacheServerToBePrinted = Model.cacheServers[activeServerIds.get(i)];
			stringBuilder.append(activeServerIds.get(i) + " ");
			for (int j = 0; j < cacheServerToBePrinted.getVideoIds().size() - 1; j++) {
				stringBuilder.append(cacheServerToBePrinted.getVideoIds().get(j) + " ");
			}
			stringBuilder.append(cacheServerToBePrinted.getVideoIds().get(cacheServerToBePrinted.getVideoIds().size() - 1) + "\n");
		}
		try {
			Files.write(Paths.get("./" + chosenOutPutFile), stringBuilder.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

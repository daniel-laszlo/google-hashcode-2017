/**
 * Created by Daniel on 2017. 02. 23..
 */
public class Model {

	public static Request[] requests;
	public static int[] videoSizes;
	public static Endpoint[] endpoints;
	public static CacheServer[] cacheServers;


	public Model(Request[] requests1, int[] videoSizes1, Endpoint[] endpoints1, CacheServer[] cacheServers1) {
		requests = requests1;
		videoSizes = videoSizes1;
		endpoints = endpoints1;
		cacheServers = cacheServers1;

	}

}

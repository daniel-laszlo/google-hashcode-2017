import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Daniel on 2017. 02. 23..
 */
public class Model {

	public static Request[] requests;
	public static int[] videoSizes;
	public static Endpoint[] endpoints;
	public static CacheServer[] cacheServers;


    public Model() {}

	public Model(Request[] requests1, int[] videoSizes1, Endpoint[] endpoints1, CacheServer[] cacheServers1) {
		requests = requests1;
		videoSizes = videoSizes1;
		endpoints = endpoints1;
		cacheServers = cacheServers1;


	}

    public void init() {
        for (int i = 0; i < requests.length; i++) {
            for (int cacheServerId = 0; cacheServerId < endpoints[requests[i].getEndPointId()].getCacheServerIds().size(); cacheServerId++) {
                cacheServers[cacheServerId].put(requests[i]);
            }
        }
        for (CacheServer cacheServer : cacheServers) {
            cacheServer.updateAll(); 
        }
    }

    public void run() {
        Set<Integer> availableCacheServers = new HashSet<>();
        for (int i = 0; i < cacheServers.length; i++) {
            availableCacheServers.add(i);
        }
        while (!availableCacheServers.isEmpty()) {
            List<Integer> oldIds = new ArrayList<Integer>();
            for (int csId: availableCacheServers) {
                Integer[] requestIds = cacheServers[csId].pop();
                for (int i : requestIds) {
                    for (int cacheServerId : endpoints[requests[i].getEndPointId()].getCacheServerIds()) {
                        cacheServers[cacheServerId].remove(requests[i]);
                    }
                }
                if (requestIds.length == 0) {
                    oldIds.add(csId);
                }
            }
            for (Integer id: oldIds) {
                availableCacheServers.remove(id);
            }
        }
    }
}

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2017. 02. 23..
 */
public class Endpoint {

	private List<Integer> cacheServerIds;
	private List<Integer> timeToReachCacheServers;

	public Endpoint() {
		this.cacheServerIds = new ArrayList<>();
		this.timeToReachCacheServers = new ArrayList<>();
	}

	public List<Integer> getCacheServerIds() {
		return cacheServerIds;
	}

	public void setCacheServerIds(List<Integer> cacheServerIds) {
		this.cacheServerIds = cacheServerIds;
	}

	public List<Integer> getTimeToReachCacheServers() {
		return timeToReachCacheServers;
	}

	public void setTimeToReachCacheServers(List<Integer> timeToReachCacheServers) {
		this.timeToReachCacheServers = timeToReachCacheServers;
	}
}

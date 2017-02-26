import java.util.*;

/**
 * Created by Daniel on 2017. 02. 23..
 */
public class Endpoint {

	private Map<Integer, Integer> cacheServerLatencies;
	private List<Integer> cacheServerIds;
	private List<Integer> timeToReachCacheServers;
	private int latencyToDataCenter;

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

	public int getLatencyToDataCenter() {
		return latencyToDataCenter;
	}

	public void setLatencyToDataCenter(int latencyToDataCenter) {
		this.latencyToDataCenter = latencyToDataCenter;
	}

	public void sortCacheServers() {
		List<Integer> mapKeys = new ArrayList<>(cacheServerLatencies.keySet());
		List<Integer> mapValues = new ArrayList<>(cacheServerLatencies.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);

		LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();

		Iterator<Integer> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			Integer val = valueIt.next();
			Iterator<Integer> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				Integer key = keyIt.next();
				Integer comp1 = cacheServerLatencies.get(key);
				Integer comp2 = val;

				if (comp1.equals(comp2)) {
					keyIt.remove();
					sortedMap.put(key, val);
					break;
				}
			}
		}
		cacheServerLatencies = sortedMap;
	}
}

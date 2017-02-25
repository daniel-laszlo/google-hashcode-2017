import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Daniel on 2017. 02. 23..
 */
public class CacheServerEntry implements Comparable<CacheServerEntry> {

	private int videoId;
	private int requestNumberSum;
	private Set<Integer> requestIds;
	private int priority = 0;
	private int weight = 1;

	public CacheServerEntry() {
		requestIds = new HashSet<>();
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public int getRequestNumberSum() {
		return requestNumberSum;
	}

	public void setRequestNumberSum(int requestNumberSum) {
		this.requestNumberSum = requestNumberSum;
	}

	public List<Integer> getRequestIds() {
		return new ArrayList<>(requestIds);
	}

	public void addRequestId(Integer id) {
		requestIds.add(id);
	}

	public int getPriority() {
		return priority;
	}

	public void updatePriority() {
		priority = requestNumberSum / (weight * Model.videoSizes[videoId]);
	}

	@Override
	public int compareTo(CacheServerEntry other) {
		if (priority < other.getPriority()) {
			return 1;
		} else if (priority > other.getPriority()) {
			return -1;
		} else {
			return 0;
		}
	}

	public void removeRequestId(int requestId) {
		requestIds.remove(requestId);
	}
}

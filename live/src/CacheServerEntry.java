import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2017. 02. 23..
 */
public class CacheServerEntry implements Comparable<CacheServerEntry> {

	private int videoId;
	private int requestNumberSum;
	private List<Integer> requestIds;
	private int priority = 0;
	private int weight = 1;
	
	public CacheServerEntry() {
		requestIds = new ArrayList<>();
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
		return requestIds;
	}

	public void setRequestIds(List<Integer> requestIds) {
		this.requestIds = requestIds;
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
		if(priority < other.getPriority()) {
			return 1;
		} else if(priority > other.getPriority()){
			return -1;
		} else {
			return 0;
		}
	} 
}

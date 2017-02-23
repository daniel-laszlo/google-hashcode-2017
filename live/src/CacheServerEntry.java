import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2017. 02. 23..
 */
public class CacheServerEntry {

	private int videoId;
	private int requestNumberSum;
	private List<Integer> requestIds;
	private int maxSize;
	private int priority = 0;
	
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

	public int getMax() {
		return maxSize;
	}

	public void setMax(int max) {
		this.maxSize = max;
	}
	
	public void updatePriority() {
		priority = requestNumberSum / Model.videoSizes[videoId];
	}
}

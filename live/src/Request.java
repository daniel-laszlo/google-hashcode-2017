/**
 * Created by Daniel on 2017. 02. 23..
 */

// Request objektum minden requestre
public class Request {

	private int requestId;
	private int videoId;
	private int endPointId;
	private int requestCount; // 1 endpoint - 1 videora

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public int getEndPointId() {
		return endPointId;
	}

	public void setEndPointId(int endPointId) {
		this.endPointId = endPointId;
	}

	public int getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
}

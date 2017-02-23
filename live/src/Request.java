/**
 * Created by Daniel on 2017. 02. 23..
 */

// Request objektum minden requestre
public class Request {

	private int videoId;
	private int endPointId;
	private int requestDarab; // 1 endpoint - 1 videora

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

	public int getRequestDarab() {
		return requestDarab;
	}

	public void setRequestDarab(int requestDarab) {
		this.requestDarab = requestDarab;
	}
}

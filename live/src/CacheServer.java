import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Daniel on 2017. 02. 23..
 */
public class CacheServer {

	private int size;
	private CacheServerEntry[] cacheServerEntries;
	private List<Integer> videoIds;

	public CacheServer() {
		videoIds = new ArrayList<>();
		cacheServerEntries = new CacheServerEntry[Model.videoSizes.length];
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public CacheServerEntry[] getCacheServerEntries() {
		return cacheServerEntries;
	}

	public void setCacheServerEntries(CacheServerEntry[] cacheServerEntries) {
		this.cacheServerEntries = cacheServerEntries;
	}

	public List<Integer> getVideoIds() {
		return videoIds;
	}

	public void setVideoIds(List<Integer> videoIds) {
		this.videoIds = videoIds;
	}

	//
	// FUNCTIONS
	//

	// MIKE
	// Végigmegy a request tömbön, csak a legelején
	public void put(Request request) {
		int videoId = request.getVideoId();
		cacheServerEntries[videoId].setVideoId(videoId);
		cacheServerEntries[videoId] = new CacheServerEntry();
		cacheServerEntries[videoId].setRequestNumberSum(
				cacheServerEntries[videoId].getRequestNumberSum() + request.getRequestDarab());
		cacheServerEntries[videoId].getRequestIds().add(request.getRequestId());
		cacheServerEntries[videoId].updatePriority();
	}

	// MARK
	// sorba teszi a cache tömböt. Amelyik a legjobb annak az R.idjét adja vissza
	// üres tömb ha már nem bírt kivenni
	public Integer[] pop() {
		Arrays.sort(cacheServerEntries);
		CacheServerEntry best = cacheServerEntries[0];
		if(best.getRequestNumberSum() == 0) {
			return new Integer[0];
		} else {
			best.setRequestNumberSum(0);
			return best.getRequestIds().toArray(new Integer[best.getRequestIds().size()]);
		}
	}

	//MIKE
	// request számát kiveszi és törli a megfelelő elemet a tömbből
	public void remove(Request request) {
		int videoId = request.getVideoId();
		cacheServerEntries[videoId].setRequestNumberSum(
				cacheServerEntries[videoId].getRequestNumberSum() - request.getRequestDarab());
		cacheServerEntries[videoId].getRequestIds().remove(
				cacheServerEntries[videoId].getRequestIds().indexOf(request.getRequestId()));
		cacheServerEntries[videoId].updatePriority();
	}
}

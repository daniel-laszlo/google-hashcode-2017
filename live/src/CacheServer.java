import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Daniel on 2017. 02. 23..
 */
public class CacheServer {

	private int size;
	private List<CacheServerEntry> cacheServerEntries;
	private List<Integer> videoIds;

	public CacheServer() {
		videoIds = new ArrayList<>();
		cacheServerEntries = new ArrayList<>();
//				new CacheServerEntry[Model.videoSizes.length];
//		for (int i = 0; i < cacheServerEntries.length; i++) {
//			cacheServerEntries[i] = new CacheServerEntry();
//		}
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Integer> getVideoIds() {
		return videoIds;
	}

	//
	// FUNCTIONS
	//

	public void put(Request request) {
		int videoId = request.getVideoId();
		CacheServerEntry entry = getEntryByVideoId(videoId);
		if (entry != null) {
			entry.increaseRequestCountSum(request.getRequestCount());
			entry.addRequestId(request.getRequestId());
			return;
		}
		CacheServerEntry newEntry = new CacheServerEntry();
		newEntry.increaseRequestCountSum(request.getRequestCount());
		newEntry.addRequestId(request.getRequestId());
		newEntry.setVideoId(videoId);
		cacheServerEntries.add(newEntry);
	}

	public Integer[] pop() {
		Collections.sort(cacheServerEntries);
		for (CacheServerEntry entry : cacheServerEntries) {
			if (entry.getRequestCountSum() == 0) {
				return new Integer[0];
			} else {
				int leftSize = size;
				for (int v : videoIds) {
					leftSize = leftSize - Model.videoSizes[v];
				}
				if (leftSize < Model.videoSizes[entry.getVideoId()]) {
					entry.setRequestCountSum(0);
				} else {
					videoIds.add(entry.getVideoId());
					entry.setRequestCountSum(0);
					return entry.getRequestIds().toArray(new Integer[entry.getRequestIds().size()]);
				}
			}
		}
		return new Integer[0];
	}

	public void remove(Request request) {
		int videoId = request.getVideoId();
		CacheServerEntry entry = getEntryByVideoId(videoId);
		if (entry != null) {
			entry.increaseRequestCountSum((-1) * request.getRequestCount());
			entry.removeRequestId(request.getRequestId());
			entry.updatePriority();
		}
		//throw new NullPointerException();
	}

	public void updateAll() {
		for (CacheServerEntry cacheServerEntry : cacheServerEntries) {
			cacheServerEntry.updatePriority();
		}
	}

	private CacheServerEntry getEntryByVideoId(int videoId) {
		for (CacheServerEntry cacheServerEntry : cacheServerEntries) {
			if (cacheServerEntry.getVideoId() == videoId) {
				return cacheServerEntry;
			}
		}
		return null;
	}
}

import java.util.ArrayList;
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
}

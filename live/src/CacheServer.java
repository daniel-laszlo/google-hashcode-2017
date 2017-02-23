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

	}

	// MARK
	// sorba teszi a cache tömböt. Amelyik a legjobb annak az R.idjét adja vissza
	// üres tömb ha már nem bírt kivenni
	public int[] pop() {
		Arrays.sort(cacheServerEntries);
		CacheServerEntry best = cacheServerEntries[0];
		if(best.getRequestNumberSum() == 0) {
			return new int[0];
		} else {
			best.setRequestNumberSum(0);
			return new int[0];
			
		}
	}

	//MIKE
	// request számát kiveszi és törli a megfelelő elemet a tömbből
	public void remove(Request request) {

	}
}

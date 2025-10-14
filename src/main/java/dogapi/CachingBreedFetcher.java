package dogapi;

import java.util.*;

/**
 * This BreedFetcher caches fetch request results to improve performance and
 * lessen the load on the underlying data source. An implementation of BreedFetcher
 * must be provided. The number of calls to the underlying fetcher are recorded.
 * <p>
 * If a call to getSubBreeds produces a BreedNotFoundException, then it is NOT cached
 * in this implementation. The provided tests check for this behaviour.
 * <p>
 * The cache maps the name of a breed to its list of sub breed names.
 */
public class CachingBreedFetcher implements BreedFetcher {
    // TODO Task 2: Complete this class
    private int callsMade = 0;
    private Map<String, List<String>> breedCach;
    private BreedFetcher fetcher;

    public CachingBreedFetcher(BreedFetcher fetcher) {
        this.breedCach = new HashMap<>();
        this.fetcher = fetcher;
    }

    @Override
    public List<String> getSubBreeds(String breed) {
        if (this.breedCach.containsKey(breed)) {
            return this.breedCach.get(breed);
        } else {
            this.callsMade += 1;
            List<String> result = this.fetcher.getSubBreeds(breed);
            this.breedCach.put(breed, result);
            return result;
        }

        // return statement included so that the starter code can compile and run.
    }

    public int getCallsMade() {
        return callsMade;
    }
}
package twitterconcurrent;

import twitter4j.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Management {
    List<Runnable> arrayList = new ArrayList<>();
    ExecutorService executorSearch;
    Twitter twitter = TwitterFactory.getSingleton();

    // Listener
    TwitterStreamListener listener = new TwitterStreamListener(executorSearch);
    TwitterStream twitterStream = new TwitterStreamFactory().getInstance();

    Management() {
        executorSearch = Executors.newFixedThreadPool(4);
        twitterStream.addListener(listener);
    }

    void search(String query) {
        executorSearch.submit(new TwitterSearch(query, executorSearch));
    }

    void watch(String[] keywords) {
        FilterQuery filterQuery = new FilterQuery(keywords);
        twitterStream.filter(filterQuery);
    }
}

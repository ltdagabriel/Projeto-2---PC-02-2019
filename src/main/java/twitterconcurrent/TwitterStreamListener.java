package twitterconcurrent;

import twitter4j.*;

import java.util.concurrent.ExecutorService;

/**
 * @author rodrigo
 */
public class TwitterStreamListener implements StatusListener {

    TwitterStream twitterStream = new TwitterStreamFactory().getInstance();


    @Override
    public void onStatus(Status status) {
        // Não funciona com executor, Problema de sincronismo
        new TwitterProcess(status).run();
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId) {
        System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
    }

    @Override
    public void onException(Exception ex) {
        ex.printStackTrace();
    }


    @Override
    public void onStallWarning(StallWarning stallWarning) {
        System.out.println(stallWarning);
    }

}

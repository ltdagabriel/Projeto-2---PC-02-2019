package twitterconcurrent;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import java.util.concurrent.ExecutorService;

/**
 *
 * @author rodrigo
 */
public class TwitterStreamListener implements StatusListener {

    private ExecutorService executorService;

    TwitterStreamListener(ExecutorService executorService){

        this.executorService = executorService;
    }

    @Override
    public void onStatus(Status status) {
        executorService.submit(new TwitterProcess(status));
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice sdn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onTrackLimitationNotice(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onScrubGeo(long l, long l1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onStallWarning(StallWarning sw) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onException(Exception excptn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

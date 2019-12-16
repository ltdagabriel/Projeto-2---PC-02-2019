package twitterconcurrent;/*
 *  Learning Project.
 */

import twitter4j.*;

/**
 * Get access twitter data
 *
 * @author rodrigo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException {
        Management management = new Management();


//        management.search("security malware");

        String[] keywords = {"infosec", "malware"};

        management.watch(keywords);
    }
}


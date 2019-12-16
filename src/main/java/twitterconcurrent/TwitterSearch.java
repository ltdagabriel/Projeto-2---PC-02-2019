package twitterconcurrent;

import twitter4j.*;

import java.util.concurrent.ExecutorService;

class TwitterSearch implements Runnable {
    private String query;
    final ExecutorService executor;
    /*
     * Como é singleton não preciso passar por parametro, so tera uma instancia em execução
     */
    Twitter twitter = TwitterFactory.getSingleton();

    TwitterSearch(String query, ExecutorService executor) {
        this.query = query;
        this.executor = executor;
    }

    @Override
    public void run() {
        Query query = new Query(this.query);
        try {
            // Faz a busca
            QueryResult result = twitter.search(query);
            result.getTweets().forEach(notice -> executor.submit(new TwitterProcess(notice)));

            // Verifica se existem + paginas
            while (result.hasNext()){
                // Adiciona resultados da busca para processamento a parte

                query = result.nextQuery();
                result = twitter.search(query);
                result.getTweets().forEach(notice -> executor.submit(new TwitterProcess(notice)));
            }

        } catch (TwitterException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}

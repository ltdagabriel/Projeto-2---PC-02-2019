package twitterconcurrent;

import twitter4j.JSONArray;
import twitter4j.JSONObject;
import twitter4j.Status;
import twitter4j.URLEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

class TwitterProcess implements Runnable {

    private Status notice;


    TwitterProcess(Status notice) {
        this.notice = notice;
    }

    @Override
    public void run() {
        JSONObject object = new JSONObject();
        object.put("postedBy", this.notice.getUser().getScreenName());
        object.put("createdAt", this.notice.getCreatedAt());
        object.put("id", this.notice.getId());
        object.put("text", this.notice.getText());

        List<URLEntity> l = Arrays.asList(this.notice.getURLEntities());

        // Lista de urls citadas no texto
        JSONArray urls = new JSONArray();
        l.forEach(e -> {
            JSONObject url = new JSONObject();
            String exp_url = e.getExpandedURL();
            url.put("url", exp_url);
            url.put("saveAs",FileManager.downloadUrl(exp_url));

            urls.put(url);
        });
        object.put("urls", urls);

        object.put("url", this.notice.getURLEntities());

        // Lista de tokens
        JSONArray text_token = new JSONArray();
        Tokenizer.getTokens(object.get("text").toString()).forEach(text_token::put);
        object.put("tokens", text_token);

        object.put("source", this.notice.getSource());
        try {
            object.put("url", new URL(String.format("https://twitter.com/%s/status/%s", object.get("postedBy"), object.get("id"))));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(object.toString(2));

        FileManager.writeJson(String.format("twitter_%s.json", object.get("id").toString()), object);
    }
}

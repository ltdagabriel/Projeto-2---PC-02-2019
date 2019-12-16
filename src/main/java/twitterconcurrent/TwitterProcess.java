package twitterconcurrent;

import twitter4j.JSONObject;
import twitter4j.Status;

import java.net.MalformedURLException;
import java.net.URL;

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
//        object.put("tokens", Tokenizer.getTokens(object.get("text").toString()).tokens());
        object.put("source", this.notice.getSource());
        try {
            object.put("url", new URL(String.format("https://twitter.com/%s/status/%s", object.get("postedBy"), object.get("id"))));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(object.toString(2));

        JsonData.writeJson(String.format("twitter_%s.json", object.get("id").toString()), object);
    }
}

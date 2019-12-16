package twitterconcurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Tokenizer {

    static List<String> getTokens(String text) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(text, " \n");
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}

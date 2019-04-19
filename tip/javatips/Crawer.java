
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Crawer {

    //爬虫
    static void crawer() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("http://photo.bitauto.com/serial/4117/");
        try {
            HttpResponse resp = httpClient.execute(get);
            HttpEntity entity = resp.getEntity();
            String html = convertStreamToString(entity.getContent());
            if (html != null && !html.isEmpty()) {
                Document doc = Jsoup.parse(html);
                if (null != doc) {
                    Elements elements = doc.getElementsByClass("row block-4col-180");
                    if (null != elements && elements.size() > 0) {
                        for (Element element : elements) {
                            System.out.println(element);
                        }
                    }
                }
            } else {
                System.out.println("html str is blank !!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    public static void main(String[] args){
        Crawer.crawer();
    }

}

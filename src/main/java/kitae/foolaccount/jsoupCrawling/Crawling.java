package kitae.foolaccount.jsoupCrawling;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

public class Crawling {

    public void naverStockPriceCrawling() throws IOException{
//        Document doc = Jsoup.connect("https://finance.naver.com/sise/").get();
//        Elements name = doc.select("ul[class=lst_pop]").select("a");
//        Elements price = doc.select("ul[class=lst_pop").select("span");
//
//        for (int i=0; i< name.size(); i++)
//        {
//            System.out.println(name.get(i).text());
//            System.out.println(price.get(i).text());
//        }
    }
}

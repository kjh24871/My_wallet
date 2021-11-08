package kitae.foolaccount.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Crawling{
    public Elements nameCrawling() throws IOException {
        Document doc = Jsoup.connect("https://kr.investing.com/equities/south-korea").get();
        Elements name = doc.select("#marketInnerContent").select("tbody").select("tr").select("a");
        return name;
    }

    public Elements priceCrawling() throws IOException {
        Document doc = Jsoup.connect("https://kr.investing.com/equities/south-korea").get();
        Elements price = doc.select("tbody").select("td[class*=last]");
        return price;
    }

    public String priceCrawlingByIdStock(String id) throws IOException{
        long beforetime = System.currentTimeMillis();
        Document doc = Jsoup.connect("https://kr.investing.com/equities/south-korea").get();
        Elements price = doc.select("tbody").select("span[data-name="+id+"]");
        String name = price.attr("data-id");
        long aftertime = System.currentTimeMillis();
        System.out.println(aftertime-beforetime);
        return doc.select("td").select(".pid-"+name+"-last").text();
    }
}

package kitae.foolaccount.controller;

import kitae.foolaccount.domain.Member;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController{

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/list")
    public String list(Model model) throws IOException
    {

//        Document doc = Jsoup.connect("https://finance.naver.com/sise/").get();
//        Elements name = doc.select("ul[class=lst_pop]").select("a");
//        Elements price = doc.select("#popularItemList > li:nth-child > span");
//        Elements statement = doc.select("#popularItemList > li:nth-child > img");
//        ArrayList<String> stock = new ArrayList<>();
//
//        for (int i=0; i< name.size(); i++){
//            System.out.println(name);
//            System.out.println(price);
//            System.out.println(statement);
//        }

//    public void info(){
//        String name;
//        String price;
//        String statement;
        return "list";
    }
}

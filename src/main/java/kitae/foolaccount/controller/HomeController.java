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
    public String list(Model model) throws IOException {

        Document doc = Jsoup.connect("https://finance.naver.com/sise/").get();
        Elements name = doc.select("ul[class=lst_pop]").select("a");
        Elements price = doc.select("ul[class=lst_pop]").select("span");

        ArrayList<String> stock = new ArrayList<>();
        ArrayList<String> stock_price = new ArrayList<>();
        ArrayList<String> stock_statement = new ArrayList<>();

        for (int i = 0; i < price.size(); i++) {
            stock.add(price.get(i).text());
        }

        ArrayList<ListForm> Infolist = new ArrayList<>();

        for(int i=0; i<stock.size(); i++)
        {
            if(i%2==0)
                stock_price.add(stock.get(i));
            else
                stock_statement.add(stock.get(i));

        }

        for (int i=0; i<10; i++)
        {
            Infolist.add(new ListForm(name.get(i).text(), stock_price.get(i), stock_statement.get(i)));
        }

        System.out.println(name.text());
        System.out.println(stock_price);
        System.out.println(stock_statement);
        model.addAttribute("Infolist",Infolist);


        //코인정보 크롤링


        return "list";
    }
}

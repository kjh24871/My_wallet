package kitae.foolaccount.controller;

import kitae.foolaccount.FoolAccountApplication;
import kitae.foolaccount.domain.Member;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController{

    @GetMapping("/")
    public String main(){
        return "main";
    }


    @GetMapping("/logout")
    public String logoutMainGet(HttpSession session){
        System.out.println("세션종료");
        session.invalidate();
        return "redirect:/list";
    }
    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request) throws IOException {

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

        model.addAttribute("Infolist",Infolist);


        //코인정보 크롤링

        ArrayList<ListForm> coin_info = new ArrayList<>();

        Document coin_page = Jsoup.connect("https://kr.investing.com/crypto/").get();
        Elements coin_price = coin_page.select("td[class=price js-currency-price]");
        Elements coin_name = coin_page.select("td[class=left bold elp name cryptoName first js-currency-name]");
        Elements coin_statement = coin_page.select(".js-currency-change-24h ");

        for (int i = 0; i < coin_price.size(); i++) {
            coin_info.add(new ListForm(coin_name.get(i).text(), coin_price.get(i).text(), Character.toString(coin_statement.get(i).text().charAt(0))));
        }
        model.addAttribute("coin_information",coin_info);

        // 세션
        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute("member");
        if(member !=null) {
            model.addAttribute("flag", "1");  // 로그인 성공
            model.addAttribute("name", member.getName());
            model.addAttribute("id", member.getId());
        }
        else
            model.addAttribute("flag","0");

        return "list";
    }
}

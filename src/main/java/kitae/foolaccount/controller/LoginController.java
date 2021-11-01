package kitae.foolaccount.controller;

import kitae.foolaccount.domain.Member;
import kitae.foolaccount.repository.MemberRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class LoginController {

    private final MemberRepository memberRepository;

    @Autowired
    public LoginController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/login")
    public String login(){              // DB에 있는 값을 토대로 로그인 할 예정
        return "login";
    }

    @PostMapping("/login")
    public String memberLogin(MemberForm form, Model model) throws IOException
    {
        Member member = new Member();
        member.setId(form.getId());
        member.setPassword(form.getPassword()); //현재 화면에서 입력한 ID, PASSWORD

        Optional<Member> find_member1 = memberRepository.findById(member.getId());


        if(find_member1.isPresent()){  // 아이디 값이 DB에 있을 때
            if(find_member1.get().getPassword().equals(member.getPassword()))
            {
                System.out.println("login complete");
                model.addAttribute("name",find_member1.get().getName());
                model.addAttribute("id",find_member1.get().getId());

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
                return "login_complete";

            }
            else
            {
                model.addAttribute("flag","1");
                System.out.println("not password");
                return "login";
            }
        }
        else{  // 아이디 값이 DB에 없을 때 NULL 관련된 에러를 잡기 위함
            model.addAttribute("flag","1");
            System.out.println("no found");
            return "login";
        }
    }



}
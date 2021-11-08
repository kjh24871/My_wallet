package kitae.foolaccount.controller;

import kitae.foolaccount.domain.Stock_add_member;
import kitae.foolaccount.service.MemberService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.List;


@Controller
@Transactional
public class MypageController {
    private Logger log = LoggerFactory.getLogger(getClass());

    public MypageController(MemberService memberService) {
        this.memberService = memberService;
    }

    private final MemberService memberService;

    @GetMapping("/{id}")
    public String myPage(@PathVariable("id") String id, Model model) throws IOException {

        log.info("mappingPath id={}", id);
        List<Stock_add_member> stockAllListById = memberService.findAllStockMemberById(id);
        double originPrice = 0;  //원금
        double nowPrice = 0;
        double long_price = 0;
        double percent = 0.0;
        // db값 평균 단가 내서 stocks 리스트에 추가가
        ArrayList<sortStock> sortStocks = new ArrayList<>();
        ArrayList<sortStock> Stocks = new ArrayList<>();
        if (stockAllListById.size() != 0) {
            for (int i = 0; i < stockAllListById.size(); i++) {

                String kind = stockAllListById.get(i).getKind();
                long nowAveragePrice = stockAllListById.get(i).getAverage_price();
                long nowQuantity = stockAllListById.get(i).getQuantity();
                String nowCompany = stockAllListById.get(i).getCompany();
                for (int j = i + 1; j < stockAllListById.size(); j++) {
                    if (stockAllListById.get(j).getCompany().equals(nowCompany) ) {
                        if(stockAllListById.get(j).getBuy().equals("buy")) {
                            nowAveragePrice = ((nowAveragePrice * nowQuantity) + (stockAllListById.get(j).getAverage_price() * stockAllListById.get(j).getQuantity())) / (nowQuantity + stockAllListById.get(j).getQuantity());
                            nowQuantity += stockAllListById.get(j).getQuantity();
                        }
                        else {
                            nowAveragePrice = ((nowAveragePrice * nowQuantity) - (stockAllListById.get(j).getAverage_price() * stockAllListById.get(j).getQuantity())) / (nowQuantity - stockAllListById.get(j).getQuantity());
                            nowQuantity -= stockAllListById.get(j).getQuantity();
                        }
                    }
                }

                sortStocks.add(new sortStock(nowCompany, kind, nowQuantity, nowAveragePrice));
            }

            sortStocks.sort(Comparator.comparing((sortStock a) -> a.company));

            Stocks.add(sortStocks.get(0));
            String companyName = sortStocks.get(0).company;

            for (int i = 0; i < sortStocks.size(); i++) {
                if (!sortStocks.get(i).company.equals(companyName)) {
                    Stocks.add(sortStocks.get(i));
                    companyName = sortStocks.get(i).company;
                }
            }

//          크롤링 한번으로 현재 금액 파악악
            Crawling crawling = new Crawling();
           for (int i = 0; i < Stocks.size(); i++) {
//           String Url = "www.google.com/search?q=";
                nowPrice += Integer.parseInt(crawling.priceCrawlingByIdStock(Stocks.get(i).company).replace(",","")) * Stocks.get(i).quantity;
                originPrice += Stocks.get(i).average_price * Stocks.get(i).quantity; //원금

            }


           if (originPrice >= nowPrice)  //손해
                percent = (1 - nowPrice / originPrice);
           else                          //이득
                percent = (nowPrice/originPrice - 1);

        }
        model.addAttribute("stockList", Stocks);
        model.addAttribute("originalPrice", (int)originPrice);
        model.addAttribute("nowPrice", (int)nowPrice);
        model.addAttribute("profit", (int)nowPrice-originPrice);
        model.addAttribute("percent", percent*100);


        return "mypage";
    }

    @GetMapping("/stock_add")
    public String registerAssetView(Model model) throws  IOException{
        Crawling crawling = new Crawling();
        Elements nameCrawling = crawling.nameCrawling();
        ArrayList<String> name = new ArrayList<>();
        for ( int i=0; i<nameCrawling.size(); i++)
        {
            name.add(nameCrawling.get(i).text());
        }
        model.addAttribute("stockNameCrawling",name);

        System.out.println(name);

        return "stock_add";
    }

    @RequestMapping(value = "register_enter",method = {RequestMethod.POST})
    public String registerAsset(stock_addForm assetForm){
        Stock_add_member asset = new Stock_add_member();
        asset.setBuy(assetForm.getBuy());
        asset.setId(assetForm.getId());
        asset.setKind(assetForm.getKind());
        asset.setCompany(assetForm.getCompany());
        asset.setQuantity(assetForm.getQuantity());
        asset.setAverage_price(assetForm.getAverage_price());
        memberService.join_stock(asset);

        return "register_enter";
    }

    @GetMapping("/register_enter")
    public String continueOrStop(){
        return "register_enter";
    }

}


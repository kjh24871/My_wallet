package kitae.foolaccount.controller;

import kitae.foolaccount.domain.Stock_add_member;
import kitae.foolaccount.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
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
    public String myPage(@PathVariable("id") String id, Model model){
        log.info("mappingPath id={}",id);
        List<Stock_add_member> stockAllListById = memberService.findAllStockMemberById(id);

        ArrayList<sortStock> sortStocks = new ArrayList<>();
        ArrayList<sortStock> Stocks = new ArrayList<>();
        if (stockAllListById.size()!=0) {
            for (int i = 0; i < stockAllListById.size(); i++) {

                String kind = stockAllListById.get(i).getKind();
                long nowAveragePrice = stockAllListById.get(i).getAverage_price();
                long nowQuantity = stockAllListById.get(i).getQuantity();
                String nowCompany = stockAllListById.get(i).getCompany();
                for (int j = i + 1; j < stockAllListById.size(); j++) {
                    if (stockAllListById.get(j).getCompany().equals(nowCompany)) {
                        nowAveragePrice = ((nowAveragePrice * nowQuantity) + (stockAllListById.get(j).getAverage_price() * stockAllListById.get(j).getQuantity())) / (nowQuantity + stockAllListById.get(j).getQuantity());
                        nowQuantity += stockAllListById.get(j).getQuantity();
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
        }
        model.addAttribute("stockList",Stocks);
        return "mypage";
    }

    @GetMapping("/stock_add")
    public String registerAssetView(){
        return "stock_add";
    }

    @RequestMapping(value = "register_enter",method = {RequestMethod.POST})
    public String registerAsset(stock_addForm assetForm){
        Stock_add_member asset = new Stock_add_member();
        asset.setId(assetForm.getId());
        asset.setKind(assetForm.getKind());
        asset.setCompany(assetForm.getCompany());
        asset.setQuantity(assetForm.getQuantity());
        asset.setAverage_price(assetForm.getAverage_price());
        memberService.join_stock(asset);
        System.out.println(asset.getSequence());
        System.out.println(asset.getId());
        System.out.println(asset.getKind());
        System.out.println(asset.getQuantity());
        System.out.println(asset.getCompany());

        return "register_enter";
    }

    @GetMapping("/register_enter")
    public String continueOrStop(){
        return "register_enter";
    }

}


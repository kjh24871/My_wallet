package kitae.foolaccount;

import kitae.foolaccount.controller.ListForm;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class FoolAccountApplication {

	public static void main(String[] args)throws IOException {
		SpringApplication.run(FoolAccountApplication.class, args);

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

		}
}


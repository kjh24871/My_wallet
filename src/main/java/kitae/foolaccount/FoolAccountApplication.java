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

	public static void main(String[] args) throws IOException {
		SpringApplication.run(FoolAccountApplication.class, args);

		ArrayList<String> coin_info = new ArrayList<>();

		Document coin_page = Jsoup.connect("https://kr.investing.com/crypto/").get();
		Elements coin_price = coin_page.select("td[class=price js-currency-price]");
		Elements coin_name = coin_page.select("td[class=left bold elp name cryptoName first js-currency-name]");
		Elements coin_statement = coin_page.select(".js-currency-change-24h ");

		for (int i = 0; i < coin_price.size(); i++) {
			System.out.println(coin_price.get(i).text());
			System.out.println(coin_name.get(i).text());
			System.out.println(coin_statement.get(i).text());
		}
	}
}


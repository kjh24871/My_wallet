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

	}
}


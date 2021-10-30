package kitae.foolaccount.controller;


import org.jsoup.nodes.Element;

public class ListForm {
    public  String name;
    public  String price;
    public  String statement;

    public ListForm(String name, String price, String statement) {
        this.name = name;
        this.price = price;
        this.statement = statement;
    }
}

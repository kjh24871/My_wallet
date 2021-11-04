package kitae.foolaccount.controller;

public class sortStock {
    public String company;
    public String kind;
    public long average_price;
    public long quantity;

    public sortStock(String company, String kind, long quantity, long average_price) {
        this.company = company;
        this.kind = kind;
        this.quantity = quantity;
        this.average_price = average_price;
    }
}

package kitae.foolaccount.controller;

public class stock_addForm {
    private Long sequence;
    private String id;
    private String buy;
    private String kind;
    private String company;
    private Long quantity;
    private Long average_price;


    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }
    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getAverage_price() {
        return average_price;
    }

    public void setAverage_price(Long average_price) {
        this.average_price = average_price;
    }
}

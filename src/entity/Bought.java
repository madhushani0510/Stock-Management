package entity;

public class Bought {

    private String boughtID;
    private String comIndex;
    private int boughtQty;
    private double boughtPrice;
    private String date;
    private double exSellingPrice;
    private double boughtTax;

    public Bought() {
    }

    public Bought(String boughtID, String comIndex, int boughtQty, double boughtPrice, String date, double exSellingPrice, double boughtTax) {
        this.boughtID = boughtID;
        this.comIndex = comIndex;
        this.boughtQty = boughtQty;
        this.boughtPrice = boughtPrice;
        this.date = date;
        this.exSellingPrice = exSellingPrice;
        this.boughtTax = boughtTax;
    }

    public String getBoughtID() {
        return boughtID;
    }

    public void setBoughtID(String boughtID) {
        this.boughtID = boughtID;
    }

    public String getComIndex() {
        return comIndex;
    }

    public void setComIndex(String comIndex) {
        this.comIndex = comIndex;
    }

    public int getBoughtQty() {
        return boughtQty;
    }

    public void setBoughtQty(int boughtQty) {
        this.boughtQty = boughtQty;
    }

    public double getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getExSellingPrice() {
        return exSellingPrice;
    }

    public void setExSellingPrice(double exSellingPrice) {
        this.exSellingPrice = exSellingPrice;
    }

    public double getBoughtTax() {
        return boughtTax;
    }

    public void setBoughtTax(double boughtTax) {
        this.boughtTax = boughtTax;
    }
}
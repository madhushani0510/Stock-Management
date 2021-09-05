package dto;

public class SoldDTO {

    private String soldId;
    private String soldCIndex;
    private int soldQty;
    private double soldPrice;
    private double actualProfitMargin;
    private String date;
    private double soldTax;

    public SoldDTO() {
    }

    public SoldDTO(String soldId, String soldCIndex, int soldQty, double soldPrice, double actualProfitMargin, String date, double soldTax) {
        this.soldId = soldId;
        this.soldCIndex = soldCIndex;
        this.soldQty = soldQty;
        this.soldPrice = soldPrice;
        this.actualProfitMargin = actualProfitMargin;
        this.date = date;
        this.soldTax = soldTax;
    }

    public String getSoldId() {
        return soldId;
    }

    public void setSoldId(String soldId) {
        this.soldId = soldId;
    }

    public String getSoldCIndex() {
        return soldCIndex;
    }

    public void setSoldCIndex(String soldCIndex) {
        this.soldCIndex = soldCIndex;
    }

    public int getSoldQty() {
        return soldQty;
    }

    public void setSoldQty(int soldQty) {
        this.soldQty = soldQty;
    }

    public double getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    public double getActualProfitMargin() {
        return actualProfitMargin;
    }

    public void setActualProfitMargin(double actualProfitMargin) {
        this.actualProfitMargin = actualProfitMargin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getSoldTax() {
        return soldTax;
    }

    public void setSoldTax(double soldTax) {
        this.soldTax = soldTax;
    }
}
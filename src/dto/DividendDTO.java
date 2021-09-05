package dto;

public class DividendDTO {

    private String dividendID;
    private String dCompanyIndex;
    private String stockType;
    private String dividendType;
    private String approvalDate;
    private String xdDate;
    private String paymentDate;

    public DividendDTO() {
    }

    public DividendDTO(String dividendID, String dCompanyIndex, String stockType, String dividendType, String approvalDate, String xdDate, String paymentDate) {
        this.dividendID = dividendID;
        this.dCompanyIndex = dCompanyIndex;
        this.stockType = stockType;
        this.dividendType = dividendType;
        this.approvalDate = approvalDate;
        this.xdDate = xdDate;
        this.paymentDate = paymentDate;
    }

    public String getDividendID() {
        return dividendID;
    }

    public void setDividendID(String dividendID) {
        this.dividendID = dividendID;
    }

    public String getCompanyIndex() {
        return dCompanyIndex;
    }

    public void setdCompanyIndex(String dCompanyIndex) {
        this.dCompanyIndex = dCompanyIndex;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getDividendType() {
        return dividendType;
    }

    public void setDividendType(String dividendType) {
        this.dividendType = dividendType;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getXdDate() {
        return xdDate;
    }

    public void setXdDate(String xdDate) {
        this.xdDate = xdDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
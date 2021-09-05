package entity;

public class Company {
    private String cIndex;
    private String companyName;
    private String industryGroup;
    private String financialMonth;

    public Company() {
    }

    public Company(String cIndex, String companyName, String industryGroup, String financialMonth) {
        this.cIndex = cIndex;
        this.companyName = companyName;
        this.industryGroup = industryGroup;
        this.financialMonth = financialMonth;
    }

    public String getcIndex() {
        return cIndex;
    }

    public void setcIndex(String cIndex) {
        this.cIndex = cIndex;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustryGroup() {
        return industryGroup;
    }

    public void setIndustryGroup(String industryGroup) {
        this.industryGroup = industryGroup;
    }

    public String getFinancialMonth() {
        return financialMonth;
    }

    public void setFinancialMonth(String financialMonth) {
        this.financialMonth = financialMonth;
    }
}

package dto;

public class CompanyDTO {
    private String comnyIndex;
    private String comnyName;
    private String industryGroup;
    private String financialMonth;

    public CompanyDTO() {
    }

    public CompanyDTO(String comnyIndex, String comnyName, String industryGroup, String financialMonth) {
        this.comnyIndex = comnyIndex;
        this.comnyName = comnyName;
        this.industryGroup = industryGroup;
        this.financialMonth = financialMonth;
    }

    public String getComnyIndex() {
        return comnyIndex;
    }

    public void setComnyIndex(String comnyIndex) {
        this.comnyIndex = comnyIndex;
    }

    public String getComnyName() {
        return comnyName;
    }

    public void setComnyName(String comnyName) {
        this.comnyName = comnyName;
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

package org.sdgas.VO;

import org.sdgas.model.ContractProperty;

/**
 * Created by 120378 on 2015-03-06.
 */
public class ContractVO extends BaseVO {

    private String contractId;
    private String contractName;
    private double contractMoney = 0.0d;
    private int contractType;
    private String signContractDate;
    private String contractBeginDate;
    private String contractEndDate;
    private String contractOperator;
    private int count;
    private String contractSignCompany;
    private int budget;
    private String budgetType;
    private double budgetMoney = 0.0d;
    private String attachment;
    private String contractCloseDate;
    private String contractFilingDate;
    private String department;
    private int contractProperty;
    private String mainContent;
    private String remark;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public double getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(double contractMoney) {
        this.contractMoney = contractMoney;
    }

    public int getContractType() {
        return contractType;
    }

    public void setContractType(int contractType) {
        this.contractType = contractType;
    }

    public String getSignContractDate() {
        return signContractDate;
    }

    public void setSignContractDate(String signContractDate) {
        this.signContractDate = signContractDate;
    }

    public String getContractBeginDate() {
        return contractBeginDate;
    }

    public void setContractBeginDate(String contractBeginDate) {
        this.contractBeginDate = contractBeginDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getContractOperator() {
        return contractOperator;
    }

    public void setContractOperator(String contractOperator) {
        this.contractOperator = contractOperator;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getContractSignCompany() {
        return contractSignCompany;
    }

    public void setContractSignCompany(String contractSignCompany) {
        this.contractSignCompany = contractSignCompany;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(String budgetType) {
        this.budgetType = budgetType;
    }

    public double getBudgetMoney() {
        return budgetMoney;
    }

    public void setBudgetMoney(double budgetMoney) {
        this.budgetMoney = budgetMoney;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getContractCloseDate() {
        return contractCloseDate;
    }

    public void setContractCloseDate(String contractCloseDate) {
        this.contractCloseDate = contractCloseDate;
    }

    public String getContractFilingDate() {
        return contractFilingDate;
    }

    public void setContractFilingDate(String contractFilingDate) {
        this.contractFilingDate = contractFilingDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getContractProperty() {
        return contractProperty;
    }

    public void setContractProperty(int contractProperty) {
        this.contractProperty = contractProperty;
    }

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

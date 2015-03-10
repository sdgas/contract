package org.sdgas.VO;

import org.sdgas.model.ContractProperty;

import java.io.File;

/**
 * Created by 120378 on 2015-03-06.
 */
public class ContractVO extends BaseVO {

    private String contractId;
    private String contractName;
    private String contractMoney;
    private String performanceBond;
    private String contractType;
    private String signContractDate;
    private String contractBeginDate;
    private String contractEndDate;
    private String contractOperator;
    private String count;
    private String contractSignCompany;
    private String budget;
    private String budgetType;
    private String budgetMoney;

    private String contractCloseDate;
    private String contractFilingDate;
    private String department;
    private String contractProperty;
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

    public String getContractSignCompany() {
        return contractSignCompany;
    }

    public void setContractSignCompany(String contractSignCompany) {
        this.contractSignCompany = contractSignCompany;
    }

    public String getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(String budgetType) {
        this.budgetType = budgetType;
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

    public String getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(String contractMoney) {
        this.contractMoney = contractMoney;
    }

    public String getPerformanceBond() {
        return performanceBond;
    }

    public void setPerformanceBond(String performanceBond) {
        this.performanceBond = performanceBond;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getBudgetMoney() {
        return budgetMoney;
    }

    public void setBudgetMoney(String budgetMoney) {
        this.budgetMoney = budgetMoney;
    }

    public String getContractProperty() {
        return contractProperty;
    }

    public void setContractProperty(String contractProperty) {
        this.contractProperty = contractProperty;
    }
}


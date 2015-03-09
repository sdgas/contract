package org.sdgas.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 120378 on 2015-03-03.
 */

@Entity
public class Contract {

    /**
     * 合同编号
     */
    private String contractId;

    /**
     * 合同名称
     */
    private ContractName contractName;

    /**
     * 合同金额
     */
    private double contractMoney = 0.0;

    /**
     * 履约保证金
     */
    private double performanceBond = 0.0d;

    /**
     * 合同类别
     */
    private ContractType contractType;

    /**
     * 签订合同日期
     */
    private Date signContractDate;

    /**
     * 合同生效日期
     */
    private Date contractBeginDate;

    /**
     * 合同到期日期
     */
    private Date contractEndDate;

    /**
     * 合同经办人
     */
    private String contractOperator;

    /**
     * 合同原件份数
     */
    private int count;

    /**
     * 签约公司
     */
    private String contractSignCompany;

    /**
     * 预算  0预算内，1预算外
     */
    private Budget budget;

    /**
     * 预算类别
     */
    private String budgetType;

    /**
     * 预算剩下金额
     */
    private double budgetMoney = 0.0d;

    /**
     * 审结日期
     */
    private Date contractCloseDate;

    /**
     * 归档日期
     */
    private Date contractFilingDate;

    /**
     * 经办部门
     */
    private Department department;

    /**
     * 合同属性
     * 0新签，1续签，2改签，3注销
     */
    private ContractProperty contractProperty;

    /**
     * 合同主要内容
     */
    private String mainContent;

    /**
     * 备注
     */
    private String remark;

    @Id
    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @ManyToOne
    @JoinColumn(name = "contractName")
    public ContractName getContractName() {
        return contractName;
    }

    public void setContractName(ContractName contractName) {
        this.contractName = contractName;
    }

    @ManyToOne
    @JoinColumn(name = "department")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    //precision表示数值的总长度，scale表示小数点所占的位数
    @Column(precision = 12, scale = 2, nullable = false)
    public double getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(double contractMoney) {
        this.contractMoney = contractMoney;
    }

    @Column(precision = 8, scale = 2, nullable = false)
    public double getPerformanceBond() {
        return performanceBond;
    }

    public void setPerformanceBond(double performanceBond) {
        this.performanceBond = performanceBond;
    }

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    public Date getSignContractDate() {
        return signContractDate;
    }

    public void setSignContractDate(Date signContractDate) {
        this.signContractDate = signContractDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    public Date getContractBeginDate() {
        return contractBeginDate;
    }

    public void setContractBeginDate(Date contractBeginDate) {
        this.contractBeginDate = contractBeginDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    @Column(nullable = false, length = 10)
    public String getContractOperator() {
        return contractOperator;
    }

    public void setContractOperator(String contractOperator) {
        this.contractOperator = contractOperator;
    }

    @Column(length = 150)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(length = 300, nullable = false)
    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    @ManyToOne
    @JoinColumn(name = "type")
    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    @Enumerated(EnumType.ORDINAL)
    public ContractProperty getContractProperty() {
        return contractProperty;
    }

    public void setContractProperty(ContractProperty contractProperty) {
        this.contractProperty = contractProperty;
    }

    @Enumerated(EnumType.ORDINAL)
    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    @Column(length = 4, nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Temporal(TemporalType.TIME)
    public Date getContractCloseDate() {
        return contractCloseDate;
    }

    public void setContractCloseDate(Date contractCloseDate) {
        this.contractCloseDate = contractCloseDate;
    }

    @Temporal(TemporalType.TIME)
    public Date getContractFilingDate() {
        return contractFilingDate;
    }

    public void setContractFilingDate(Date contractFilingDate) {
        this.contractFilingDate = contractFilingDate;
    }

    @Column(length = 15)
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

    @Column(length = 30)
    public String getContractSignCompany() {
        return contractSignCompany;
    }

    public void setContractSignCompany(String contractSignCompany) {
        this.contractSignCompany = contractSignCompany;
    }
}

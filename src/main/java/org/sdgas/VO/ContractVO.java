package org.sdgas.VO;

import org.sdgas.model.Contract;
import org.sdgas.model.ContractProperty;

import java.io.File;

/**
 * Created by 120378 on 2015-03-06.
 */
public class ContractVO extends BaseVO {

    private String contractId;//合同编号
    private String contractName;//合同名称
    private String contractMoney;// 合同金额
    private String unit_priced;//单价金额
    private String version;//合同版本 0：非格式合同  1：格式合同
    private String contractProvide;//合同版本提供  0我司  1外单位
    private String performanceBond;//履约保证金/质保金
    private String duePerformanceBond;//质保金到期日期
    private String guaranteePeriod;//质保期
    private String contractType;//合同类别
    private String acceptance;//验收日期
    private String lawer;//咨询律师  1是 0否
    private String invoice; //发票
    private String biddingType;//供应商的确定方式 0公开招标  1依法邀请招标  2内部邀请招标  3直接发包  4询价比价  5其他

    private String contractBeginDate;//合同生效日期
    private String contractEndDate;//合同到期日期
    private String contractOperator;//合同经办人
    private String count;//合同原件份数
    private String contractSignCompany; //签约公司

    private String budget;//预算  0预算内，1预算外
    private String budgetType;//预算类别
    private String budgetMoney;//预算剩下金额
    private String contractCloseDate; //审结日期（提交到招采部时间）
    private String stampTax;//印花税 0未购买 1已购买 2无须购买
    private String supportFile;//支持文件
    private String department;//经办部门
    private String contractProperty;//合同属性 0新签，1续签，2改签，3注销
    private String mainContent;//合同主要内容
    private String receivableOrPayMoney;//应收（付）金额
    private String receivableOrPay;//收、付标识   0付款  1收款
    private String settleAccount;//是否超结算  0否 1是，已审核  2是，未审核
    private String paymentType;//款项类型   0工程费  1履约保证金  2履约保函  3咨询费  4容量气价  5款货  6租赁费  7培训费  -1其他
    private String stamp;//是否盖章  0未盖章   1已盖章
    private String remark;//备注

    private Contract contract;
    private String project;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getSupportFile() {
        return supportFile;
    }

    public void setSupportFile(String supportFile) {
        this.supportFile = supportFile;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

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

    public String getBiddingType() {
        return biddingType;
    }

    public void setBiddingType(String biddingType) {
        this.biddingType = biddingType;
    }

    public String getGuaranteePeriod() {
        return guaranteePeriod;
    }

    public void setGuaranteePeriod(String guaranteePeriod) {
        this.guaranteePeriod = guaranteePeriod;
    }

    public String getReceivableOrPayMoney() {
        return receivableOrPayMoney;
    }

    public void setReceivableOrPayMoney(String receivableOrPayMoney) {
        this.receivableOrPayMoney = receivableOrPayMoney;
    }

    public String getStampTax() {
        return stampTax;
    }

    public void setStampTax(String stampTax) {
        this.stampTax = stampTax;
    }

    public String getSettleAccount() {
        return settleAccount;
    }

    public void setSettleAccount(String settleAccount) {
        this.settleAccount = settleAccount;
    }

    public String getUnit_priced() {
        return unit_priced;
    }

    public void setUnit_priced(String unit_priced) {
        this.unit_priced = unit_priced;
    }

    public String getContractProvide() {
        return contractProvide;
    }

    public void setContractProvide(String contractProvide) {
        this.contractProvide = contractProvide;
    }

    public String getDuePerformanceBond() {
        return duePerformanceBond;
    }

    public void setDuePerformanceBond(String duePerformanceBond) {
        this.duePerformanceBond = duePerformanceBond;
    }

    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
    }

    public String getLawer() {
        return lawer;
    }

    public void setLawer(String lawer) {
        this.lawer = lawer;
    }

    public String getReceivableOrPay() {
        return receivableOrPay;
    }

    public void setReceivableOrPay(String receivableOrPay) {
        this.receivableOrPay = receivableOrPay;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getContractProperty() {
        return contractProperty;
    }

    public void setContractProperty(String contractProperty) {
        this.contractProperty = contractProperty;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }
}


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
     * 单价金额
     */
    private double unit_price = 0.0d;

    /**
     * 合同版本
     * 0：非格式合同  1：格式合同
     */
    private int version;

    /**
     * 合同版本提供
     * 0我司  1外单位
     */
    private int contractProvide;

    /**
     * 履约保证金/质保金
     */
    private double performanceBond = 0.0d;

    /**
     * 质保金到期日期
     */
    private Date duePerformanceBond;

    /**
     * 质保期
     */
    private GuaranteePeriod guaranteePeriod;

    /**
     * 合同类别
     */
    private ContractType contractType;

    /**
     * 会签日期
     */
    private Date signContractDate;

    /**
     * 验收日期
     */
    private Date acceptance;

    /**
     * 咨询律师
     * 1是 0否
     */
    private int lawer;

    /**
     * 发票
     */
    private int invoice;

    /**
     * 供应商的确定方式
     * 0公开招标  1依法邀请招标  2内部邀请招标  3直接发包  4询价比价  5其他
     */
    private BiddingType biddingType;

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
     * 审结日期（提交到招采部时间）
     */
    private Date contractCloseDate;

    /**
     * 印花税 0未购买 1已购买 2无须购买
     */
    private int stampTax;

    /**
     * 支持文件
     */
    private String supportFile;

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
     * 应收（付）金额
     */
    private Double receivableOrPayMoney = 0.0d;

    /**
     * 收、付标识   0付款  1收款
     */
    private ReceiveOrPay receivableOrPay;

    /**
     * 应收(付)时间
     */
    private String paymentDate;

    /**
     * 是否超结算  0否 1是，已审核  2是，未审核
     */
    private SettleAccount settleAccount;

    /**
     * 款项类型   0工程费  1履约保证金  2履约保函  3咨询费  4容量气价  5款货  6租赁费  7培训费  -1其他
     */
    private String paymentType;

    /**
     * 结算金额
     */
    // private double closingMoney;

    /**
     * 是否盖章
     * 0未盖章   1已盖章
     */
    private int stamp;

    /**
     * 备注
     */
    private String remark;

    /**
     * 合同状态
     */
    private ContractState state;

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

    @Temporal(TemporalType.TIMESTAMP)
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

    @Temporal(TemporalType.TIMESTAMP)
    public Date getContractCloseDate() {
        return contractCloseDate;
    }

    public void setContractCloseDate(Date contractCloseDate) {
        this.contractCloseDate = contractCloseDate;
    }

    @Column(length = 15)
    public String getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(String budgetType) {
        this.budgetType = budgetType;
    }

    @Column(precision = 12, scale = 2)
    public double getBudgetMoney() {
        return budgetMoney;
    }

    public void setBudgetMoney(double budgetMoney) {
        this.budgetMoney = budgetMoney;
    }

    @Column(length = 255)
    public String getContractSignCompany() {
        return contractSignCompany;
    }

    public void setContractSignCompany(String contractSignCompany) {
        this.contractSignCompany = contractSignCompany;
    }

    @Enumerated(EnumType.ORDINAL)
    public BiddingType getBiddingType() {
        return biddingType;
    }

    public void setBiddingType(BiddingType biddingType) {
        this.biddingType = biddingType;
    }

    @Enumerated(EnumType.ORDINAL)
    public GuaranteePeriod getGuaranteePeriod() {
        return guaranteePeriod;
    }

    public void setGuaranteePeriod(GuaranteePeriod guaranteePeriod) {
        this.guaranteePeriod = guaranteePeriod;
    }

    public int getStampTax() {
        return stampTax;
    }

    public void setStampTax(int stampTax) {
        this.stampTax = stampTax;
    }

    @Column(precision = 12, scale = 2, nullable = false)
    public Double getReceivableOrPayMoney() {
        return receivableOrPayMoney;
    }

    public void setReceivableOrPayMoney(Double receivableOrPayMoney) {
        this.receivableOrPayMoney = receivableOrPayMoney;
    }

    @Enumerated(EnumType.ORDINAL)
    public ReceiveOrPay getReceivableOrPay() {
        return receivableOrPay;
    }

    public void setReceivableOrPay(ReceiveOrPay receivableOrPay) {
        this.receivableOrPay = receivableOrPay;
    }

    @Column(precision = 12, scale = 2, nullable = false)
    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    @Enumerated(EnumType.ORDINAL)
    public SettleAccount getSettleAccount() {
        return settleAccount;
    }

    public void setSettleAccount(SettleAccount settleAccount) {
        this.settleAccount = settleAccount;
    }

    @Column(length = 4)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(length = 4)
    public int getStamp() {
        return stamp;
    }

    public void setStamp(int stamp) {
        this.stamp = stamp;
    }

    @Column(length = 10)
    public String getSupportFile() {
        return supportFile;
    }

    public void setSupportFile(String supportFile) {
        this.supportFile = supportFile;
    }

    @Column(length = 4)
    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getContractProvide() {
        return contractProvide;
    }

    public void setContractProvide(int contractProvide) {
        this.contractProvide = contractProvide;
    }

    @Temporal(TemporalType.DATE)
    public Date getDuePerformanceBond() {
        return duePerformanceBond;
    }

    public void setDuePerformanceBond(Date duePerformanceBond) {
        this.duePerformanceBond = duePerformanceBond;
    }

    @Temporal(TemporalType.DATE)
    public Date getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(Date acceptance) {
        this.acceptance = acceptance;
    }

    public int getLawer() {
        return lawer;
    }

    public void setLawer(int lawer) {
        this.lawer = lawer;
    }

    @Enumerated(EnumType.ORDINAL)
    public ContractState getState() {
        return state;
    }

    public void setState(ContractState state) {
        this.state = state;
    }

    @Column(length = 100, nullable = false)
    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}

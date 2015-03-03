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
    private String contractName;

    /**
     * 合同金额
     */
    private double contractMoney = 0.0d;

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
     * 附件
     */
    private String attachment;

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

    @Column(length = 50, nullable = false)
    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    @Column(length = 100)
    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
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
    @Column(precision = 12, scale = 2)
    public double getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(double contractMoney) {
        this.contractMoney = contractMoney;
    }

    @Temporal(TemporalType.DATE)
    public Date getSignContractDate() {
        return signContractDate;
    }

    public void setSignContractDate(Date signContractDate) {
        this.signContractDate = signContractDate;
    }

    @Temporal(TemporalType.DATE)
    public Date getContractBeginDate() {
        return contractBeginDate;
    }

    public void setContractBeginDate(Date contractBeginDate) {
        this.contractBeginDate = contractBeginDate;
    }

    @Temporal(TemporalType.DATE)
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
}

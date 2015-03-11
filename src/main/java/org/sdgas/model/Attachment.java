package org.sdgas.model;

import javax.persistence.*;

/**
 * Created by 120378 on 2015-03-09.
 */
@Entity
public class Attachment {

    /**
     * 标识列（时间字符串）
     */
    private String id;

    /**
     * 对应的合同编号
     */
    private String contract;

    /**
     * 附件名称
     */
    private String attachmentName;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(length = 15, nullable = false)
    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}

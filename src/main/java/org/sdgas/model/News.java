package org.sdgas.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wills_000 on 14-3-22.
 */
@Entity
public class News {

    /**
     * 流水号
     */
    private int id;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 发送人
     */
    private User sender;
    /**
     * 收件人
     */
    private User receiver;
    /**
     * 内容
     */
    private String content;
    /**
     * 连接
     */
    private String link;
    /**
     * 状态
     */
    private NewsState newsState;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @ManyToOne
    @JoinColumn(name = "sender", nullable = false)
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @ManyToOne
    @JoinColumn(name = "receiver", nullable = false)
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Column(length = 255, nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Enumerated
    public NewsState getNewsState() {
        return newsState;
    }

    public void setNewsState(NewsState newsState) {
        this.newsState = newsState;
    }
}
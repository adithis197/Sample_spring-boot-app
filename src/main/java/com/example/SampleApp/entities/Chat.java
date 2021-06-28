package com.example.SampleApp.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;
//@Entity
public class Chat implements Serializable {



    @Override
    public String toString() {
        return "Chat{" +
                "customerId=" + customerId + '\'' +
                ", agentId=" + agentId +
                ", emailId='" + emailId + '\'' +
                ", productId='" + productId + '\'' +
                ", chatId=" + chatId +
                ", productName='" + productName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", description='" + description + '\'' +
                ", queueId=" + queueId +
                '}';
    }
    private String customerId;
    private long agentId;
    private String emailId; // caching once for each domain
    private String productId;
    private long chatId;
    private String productName;
    private String companyName;
    private String description;
    private long queueId;

    public Chat(String customerId, long agentId, String emailId, String productId, long chatId, String productName, String companyName, String description, long queueId) {
        this.customerId = customerId;
        this.agentId = agentId;
        this.emailId = emailId;
        this.productId = productId;
        this.chatId = chatId;
        this.productName = productName;
        this.companyName = companyName;
        this.description = description;
        this.queueId = getQueueId();
    }

    public Chat(){
        super();
    }
    public long getQueueId() {
        return queueId;
    }

    public void setQueueId(long queueId) {
        this.queueId = queueId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}


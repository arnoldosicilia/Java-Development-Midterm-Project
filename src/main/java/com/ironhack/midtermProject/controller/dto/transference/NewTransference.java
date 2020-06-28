package com.ironhack.midtermProject.controller.dto.transference;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NewTransference {

    private Long originId;
    private String receiverName;
    private Long destinationId;
    private BigDecimal amount;

    /** Constructors **/
    public NewTransference(Long originId, String receiverName, Long destinationId, BigDecimal amount) {
        this.originId = originId;
        this.receiverName = receiverName;
        this.destinationId = destinationId;
        this.amount = amount;
    }


    /** Getters & Setters **/
    public Long getOriginId() {return originId;}
    public void setOriginId(Long originId) {this.originId = originId;}
    public String getReceiverName() {return receiverName;}
    public void setReceiverName(String receiverName) {this.receiverName = receiverName;}
    public Long getDestinationId() {return destinationId;}
    public void setDestinationId(Long destinationId) {this.destinationId = destinationId;}
    public BigDecimal getAmount() {return amount;}
    public void setAmount(BigDecimal amount) {this.amount = amount;}
}

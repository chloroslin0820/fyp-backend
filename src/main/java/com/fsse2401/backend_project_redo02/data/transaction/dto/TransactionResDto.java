package com.fsse2401.backend_project_redo02.data.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fsse2401.backend_project_redo02.data.transaction.domainObject.TransactionResData;
import com.fsse2401.backend_project_redo02.data.transaction.status.TransactionStatus;
import com.fsse2401.backend_project_redo02.data.transactionProduct.domainObject.TransactionProductResData;
import com.fsse2401.backend_project_redo02.data.transactionProduct.dto.TransactionProductResDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"tid", "buyer_uid", "date_time", "status", "total", "items"})
public class TransactionResDto {
    private Integer tid;
    @JsonProperty("buyer_uid")
    private Integer buyerUid;
    @JsonProperty("date_time")
    @JsonFormat(pattern="yyyyMMdd'T'HH:mm:ss")
    private LocalDateTime datetime;
    private TransactionStatus status;
    private BigDecimal total;
    private List<TransactionProductResDto> items;

    public TransactionResDto(TransactionResData data) {
        this.tid = data.getTid();
        this.buyerUid = data.getBuyerUid();
        this.datetime = LocalDateTime.now();
        this.status = data.getStatus();
        this.total = data.getTotal();
        setItems(data);
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(Integer buyerUid) {
        this.buyerUid = buyerUid;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }


    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductResDto> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductResDto> items) {
        this.items = items;
    }

    public void setItems(TransactionResData transactionResData){
        List<TransactionProductResDto> transactionProductResDtoList = new ArrayList<>();
        for(TransactionProductResData transactionProductResData : transactionResData.getItems()){
            transactionProductResDtoList.add(
                    new TransactionProductResDto(transactionProductResData));
            this.items = transactionProductResDtoList;
        }
    }

}
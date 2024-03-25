package com.fsse2401.backend_project_redo02.data.transaction.domainObject;

import com.fsse2401.backend_project_redo02.data.transaction.entity.TransactionEntity;
import com.fsse2401.backend_project_redo02.data.transaction.status.TransactionStatus;
import com.fsse2401.backend_project_redo02.data.transactionProduct.domainObject.TransactionProductResData;
import com.fsse2401.backend_project_redo02.data.transactionProduct.entity.TransactionProductEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionResData {
    private Integer tid;
    private Integer buyerUid;
    private TransactionStatus status;
    private List<TransactionProductResData> items;
    private BigDecimal total;

    public TransactionResData(TransactionEntity entity) {
        this.tid = entity.getTid();
        this.buyerUid = entity.getUser().getUid();
        this.status = entity.getStatus();
        setItems(entity);
        this.total = entity.getTotal();
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

    public List<TransactionProductResData> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductResData> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setItems(TransactionEntity transactionEntity){
        List<TransactionProductResData> transactionProductResDataList = new ArrayList<>();
        for(TransactionProductEntity transactionProductEntity : transactionEntity.getItems()){
            transactionProductResDataList.add(new TransactionProductResData(transactionProductEntity));
            this.items = transactionProductResDataList;
        }
    }
}

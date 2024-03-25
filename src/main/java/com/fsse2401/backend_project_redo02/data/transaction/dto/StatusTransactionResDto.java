package com.fsse2401.backend_project_redo02.data.transaction.dto;

import com.fsse2401.backend_project_redo02.data.transaction.domainObject.StatusTransactionResData;
import com.fsse2401.backend_project_redo02.data.transaction.status.TransactionStatus;

public class StatusTransactionResDto {
    private TransactionStatus status;

    public StatusTransactionResDto(StatusTransactionResData data) {
        setStatus(TransactionStatus.PROCESSING);
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}

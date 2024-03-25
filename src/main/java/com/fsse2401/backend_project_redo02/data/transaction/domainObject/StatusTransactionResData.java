package com.fsse2401.backend_project_redo02.data.transaction.domainObject;

public class StatusTransactionResData {
    private boolean status;

    public StatusTransactionResData(boolean entity) {
        this.status = entity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

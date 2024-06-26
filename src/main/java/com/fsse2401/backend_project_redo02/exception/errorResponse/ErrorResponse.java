package com.fsse2401.backend_project_redo02.exception.errorResponse;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private String timestamps;
    private int status;
    private HttpStatus error;
    private String path;

    public String getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(String timestamps) {
        this.timestamps = timestamps;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public HttpStatus getError() {
        return error;
    }

    public void setError(HttpStatus error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

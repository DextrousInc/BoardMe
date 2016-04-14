package com.dextrous.hack.boardme.response;


public class GenericBeanResponse<T> {
    private Boolean success;
    private String error;
    private T item;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "GenericBeanResponse{" +
                "success=" + success +
                ", error='" + error + '\'' +
                ", item=" + item +
                '}';
    }
}

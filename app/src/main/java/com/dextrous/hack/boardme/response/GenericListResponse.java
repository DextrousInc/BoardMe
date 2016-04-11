package com.dextrous.hack.boardme.response;

import java.util.List;

public class GenericListResponse<T> {
    private Boolean success;
    private List<T> items;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "GenericListResponse{" +
                "success=" + success +
                ", items=" + items +
                '}';
    }
}

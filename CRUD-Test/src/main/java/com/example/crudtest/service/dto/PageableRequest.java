package com.example.crudtest.service.dto;

public class PageableRequest {
    private String search;

    public PageableRequest() {
    }

    public PageableRequest(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}

package com.example.tugasakhir.response;

import com.example.tugasakhir.models.Hadith;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HadithResponse {
    private int currentPage;
    private List<Hadith> data;
    @SerializedName("next_page_url")
    private String nextPageUrl;
    private String previousPageUrl;
    private int perPage;
    private int to;
    private int total;

    // Getters and Setters
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Hadith> getData() {
        return data;
    }

    public void setData(List<Hadith> data) {
        this.data = data;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPreviousPageUrl() {
        return previousPageUrl;
    }

    public void setPreviousPageUrl(String previousPageUrl) {
        this.previousPageUrl = previousPageUrl;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

package com.example.khabar;

import java.util.List;

public class APIResponse {
    String status ="";
    int totalResults=0;
    List<HeadLine> articles=null;
    public List<HeadLine> getArticles() {
        return articles;
    }

    public void setArticles(List<HeadLine> article) {
        this.articles = article;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalresult) {
        this.totalResults = totalresult;
    }
}

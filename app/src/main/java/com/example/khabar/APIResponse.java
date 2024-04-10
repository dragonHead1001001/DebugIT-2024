package com.example.khabar;

import java.util.List;

public class APIResponse {
    String status ="";
    int totalresult;
    List<HeadLine> article;
    public List<HeadLine> getArticle() {
        return article;
    }

    public void setArticle(List<HeadLine> article) {
        this.article = article;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalresult() {
        return totalresult;
    }

    public void setTotalresult(int totalresult) {
        this.totalresult = totalresult;
    }
}

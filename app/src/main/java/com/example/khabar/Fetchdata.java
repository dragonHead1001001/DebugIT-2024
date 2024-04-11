package com.example.khabar;

import java.util.List;

public interface Fetchdata<APIResponse>{
    void onFetchData(List<HeadLine> l , String message,int position);
    void onError(String message);
}

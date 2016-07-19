package com.example.kb_shirlman.adscenddemo;

import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Created by KB-Shirlman on 7/19/2016.
 */
public interface ApiInterface {
    @GET(RetrofitHelper.ADSCEND_MEDIA_API_URL)
    Call<OfferAPIResult> adscendMediaOfferAPI(@QueryMap Map<String, String> filters);
}

package com.appstreettaskapplication.networking;


import com.appstreettaskapplication.model.ListResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("developers?")
    Call<List<ListResponseModel>> getRepositoryList(@Query("language") String newsSource,
                                                    @Query("since") String apiKey);
}

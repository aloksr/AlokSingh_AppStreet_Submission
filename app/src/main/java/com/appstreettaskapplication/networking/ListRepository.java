package com.appstreettaskapplication.networking;


import androidx.lifecycle.MutableLiveData;

import com.appstreettaskapplication.model.ListResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRepository {

    private static ListRepository listRepository;

    public static ListRepository getInstance(){
        if (listRepository == null){
            listRepository = new ListRepository();
        }
        return listRepository;
    }

    private NewsApi newsApi;

    public ListRepository(){
        newsApi = RetrofitService.cteateService(NewsApi.class);
    }

    public MutableLiveData<List<ListResponseModel>> getList(String source, String key){
        final MutableLiveData<List<ListResponseModel>> newsData = new MutableLiveData<>();
        newsApi.getRepositoryList(source,key).enqueue(new Callback<List<ListResponseModel>>() {
            @Override
            public void onResponse(Call<List<ListResponseModel>> call, Response<List<ListResponseModel>> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ListResponseModel>> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}

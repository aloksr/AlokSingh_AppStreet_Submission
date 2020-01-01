package com.appstreettaskapplication.networking;


import androidx.lifecycle.MutableLiveData;

import com.appstreettaskapplication.model.ListResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRepository {
    private static ListRepository listRepository;
    private ApiService apiInterface;

    public static synchronized ListRepository getInstance(){
        if (listRepository == null){
            listRepository = new ListRepository();
        }
        return listRepository;
    }


    public ListRepository(){
        apiInterface = RetrofitService.cteateService(ApiService.class);
    }

    public MutableLiveData<List<ListResponseModel>> getList(String source, String key){
        final MutableLiveData<List<ListResponseModel>> newsData = new MutableLiveData<>();
        apiInterface.getRepositoryList(source,key).enqueue(new Callback<List<ListResponseModel>>() {
            @Override
            public void onResponse(Call<List<ListResponseModel>> call, Response<List<ListResponseModel>> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ListResponseModel>> call, Throwable t) {
                newsData.setValue(null);
                t.printStackTrace();
            }
        });
        return newsData;
    }
}

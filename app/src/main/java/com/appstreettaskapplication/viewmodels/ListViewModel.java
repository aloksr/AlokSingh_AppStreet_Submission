package com.appstreettaskapplication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.appstreettaskapplication.constants.AppConstants;
import com.appstreettaskapplication.model.ListResponseModel;
import com.appstreettaskapplication.networking.ListRepository;

import java.util.List;

public class ListViewModel extends ViewModel {

    private MutableLiveData<List<ListResponseModel>> mutableLiveData;
    private ListRepository newsRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        newsRepository = ListRepository.getInstance();
        mutableLiveData = newsRepository.getList(AppConstants.LANGUAGE, AppConstants.DURATION);

    }

    public LiveData<List<ListResponseModel>> getListRepository() {
        return mutableLiveData;
    }

}

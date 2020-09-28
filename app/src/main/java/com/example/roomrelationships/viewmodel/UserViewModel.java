package com.example.roomrelationships.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.roomrelationships.database.UserAccountDatabase;
import com.example.roomrelationships.models.UserAccount;
import com.example.roomrelationships.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    private LiveData<UserAccount> getAllData;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        getAllData = repository.getAllData();
    }

    public void insert(UserAccount data) {
        repository.insertData(data);
    }

    public LiveData<UserAccount> getGetAllData() {
        return getAllData;
    }

    public boolean checkValidLogin(String username, String password) {

        return repository.isValidAccount(username, password);
    }

}


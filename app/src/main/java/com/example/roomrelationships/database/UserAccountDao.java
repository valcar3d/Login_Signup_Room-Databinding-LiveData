package com.example.roomrelationships.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomrelationships.models.UserAccount;

@Dao
public interface UserAccountDao {

    @Insert
    void insert(UserAccount account);

    @Query("SELECT * FROM useraccounts WHERE userName =:username")
    UserAccount getAccount(String username);

    @Query("SELECT * FROM useraccounts")
    LiveData<UserAccount> getDetails();

}


package com.example.roomrelationships.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomrelationships.database.UserAccountDao;
import com.example.roomrelationships.database.UserAccountDatabase;
import com.example.roomrelationships.models.UserAccount;

public class UserRepository {


    private UserAccountDao userAccountDao;
    private LiveData<UserAccount> allData;

    public UserRepository(Application application) {

        UserAccountDatabase db = UserAccountDatabase.getAppDatabase(application);
        userAccountDao = db.userAccountDao();
        allData = userAccountDao.getDetails();

    }


    public LiveData<UserAccount> getAllData() {
        return allData;
    }

    public void insertData(UserAccount data) {
        new LoginInsertion(userAccountDao).execute(data);
    }

    public boolean isValidAccount(String username, String password) {

        UserAccount userAccount = userAccountDao.getAccount(username);

        if (userAccount == null) {
            return false;
        } else {
            return userAccount.getPassword().equals(password);
        }

    }

    private static class LoginInsertion extends AsyncTask<UserAccount, Void, Void> {

        private UserAccountDao userDao;

        private LoginInsertion(UserAccountDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserAccount... userAccounts) {
            userDao.insert(userAccounts[0]);
            return null;
        }
    }


}

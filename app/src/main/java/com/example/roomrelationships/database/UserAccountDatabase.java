package com.example.roomrelationships.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomrelationships.models.UserAccount;

@Database(entities = {UserAccount.class}, version = 1)
public abstract class UserAccountDatabase extends RoomDatabase{

    public abstract UserAccountDao userAccountDao();
    public static UserAccountDatabase INSTANCE;

    public static UserAccountDatabase getAppDatabase(Context context)
    {
        if(INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserAccountDatabase.class, "user-database").allowMainThreadQueries().build();

        }

        return INSTANCE;

    }

    public static void destroyInstance()
    {
        INSTANCE = null;
    }

}

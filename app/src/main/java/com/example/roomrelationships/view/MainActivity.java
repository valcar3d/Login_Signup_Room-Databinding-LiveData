package com.example.roomrelationships.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.roomrelationships.R;
import com.example.roomrelationships.databinding.ActivityMainBinding;
import com.example.roomrelationships.databinding.Listener;
import com.example.roomrelationships.models.UserAccount;
import com.example.roomrelationships.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity implements Listener {

    private UserViewModel userViewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.setClickListener(this);
        userViewModel = ViewModelProviders.of(MainActivity.this).get(UserViewModel.class);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userViewModel.getGetAllData().observe(MainActivity.this, new Observer<UserAccount>() {
                    @Override
                    public void onChanged(UserAccount userAccount) {


                    }
                });
            }
        });

    }

    @Override
    public void onClickSignUp(View view) {
        String strEmail = binding.username.getText().toString().trim();
        String strPassword = binding.password.getText().toString().trim();

        UserAccount data = new UserAccount();

        if (TextUtils.isEmpty(strEmail)) {
            binding.username.setError("Please a valid E-mail Address");
        } else if (TextUtils.isEmpty(strPassword)) {
            binding.password.setError("Please Enter a Password");
        } else {

            data.setUserName(strEmail);
            data.setPassword(strPassword);
            userViewModel.insert(data);
            Toast.makeText(getApplicationContext(), "User added", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onClickLogin(View view) {

        String strEmail = binding.username.getText().toString().trim();
        String strPassword = binding.password.getText().toString().trim();

        if (TextUtils.isEmpty(strEmail)) {
            binding.username.setError("Please Enter Your E-mail Address");
        } else if (TextUtils.isEmpty(strPassword)) {
            binding.password.setError("Please Enter Your Password");
        } else {

            boolean isValid = userViewModel.checkValidLogin(strEmail, strPassword);

            if (isValid) {
                Toast.makeText(getBaseContext(), "Successfully Logged In!", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getBaseContext(), "Invalid Login!", Toast.LENGTH_SHORT).show();
            }
        }

    }
}

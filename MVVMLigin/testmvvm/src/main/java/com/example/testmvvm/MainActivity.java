package com.example.testmvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.testmvvm.databinding.ActivityMainBinding;
import com.example.testmvvm.model.UserModel;
import com.example.testmvvm.model.UserViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        UserModel userModel = new UserModel();
        userModel.setName("yjh");
        UserViewModel viewModel = new UserViewModel(this, userModel);
        binding.setVariable(1, userModel);
        binding.setVariable(2, viewModel);
    }

}

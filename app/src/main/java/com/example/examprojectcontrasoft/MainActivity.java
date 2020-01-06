package com.example.examprojectcontrasoft;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examprojectcontrasoft.Instances.RetrofitClientAPI;
import com.example.examprojectcontrasoft.Interfaces.RetrofitAPIInterface;
import com.example.examprojectcontrasoft.Models.LoggedInUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private Button loginBtn;
    private String sessionCookie;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setupSharedPreferences();
        System.out.println("Starting");
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLoginCookieAPI();
            }
        });

    }

    private void init(){
        loginEmail = findViewById(R.id.emailLogin);
        loginPassword = findViewById(R.id.passwordLogin);
        loginBtn = findViewById(R.id.loginBtn);

        loginEmail.setText("admin@test.dk");
        loginPassword.setText("test");
    }

    private void setupSharedPreferences() {
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();
    }

    private void getLoginCookieAPI() {
        System.out.println("GETLOGINCOOKIE ------- START");
        final String email = loginEmail.getText().toString();
        final String password = loginPassword.getText().toString();

        RetrofitAPIInterface apiServiceInterface = RetrofitClientAPI.getRetrofitInstance().create(RetrofitAPIInterface.class);

        Call<Void> call = apiServiceInterface.login(email, password);

        System.out.println(call.request().url());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("RESPONSE");
                System.out.println(response.code());

                sessionCookie = response.headers().get("Set-Cookie");
                editor.putString("cookieTest", sessionCookie);
                editor.commit();

                System.out.println(response.headers().get("Set-Cookie"));
                System.out.println("GETLOGINCOOKIE ------- END");

                loginPostApi();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Could not login...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginPostApi() {
        System.out.println("LOGIN REAL ONE ------- START");

        RetrofitAPIInterface apiServiceInterface = RetrofitClientAPI.getRetrofitInstance().create(RetrofitAPIInterface.class);

        String cookieTest = pref.getString("cookieTest", "");

        Call<LoggedInUser> call = apiServiceInterface.loginUser(cookieTest);

        call.enqueue(new Callback<LoggedInUser>() {
            @Override
            public void onResponse(Call<LoggedInUser> call, Response<LoggedInUser> response) {
                System.out.println("RESPONSE2: " + response.body());

            }

            @Override
            public void onFailure(Call<LoggedInUser> call, Throwable t) {
                System.out.println("FEJL2");
                t.printStackTrace();
            }
        });


    }


}

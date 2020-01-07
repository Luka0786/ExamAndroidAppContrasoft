package com.example.examprojectcontrasoft.Activities;

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
import com.example.examprojectcontrasoft.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private Button loginBtn;
    private String sessionCookie;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setupSharedPreferences();
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
        pref = getApplicationContext().getSharedPreferences(getString(R.string.shared_pref_name), MODE_PRIVATE);
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

                if (response.code() == 200) {
                    sessionCookie = response.headers().get("Set-Cookie");
                    editor.putString(getString(R.string.shared_pref_cookie), sessionCookie);
                    editor.commit();

                    Intent loginIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(loginIntent);
                    finish();
                }

                if (response.code() == 401) {
                    Toast.makeText(MainActivity.this, getString(R.string.bad_credentials), Toast.LENGTH_SHORT).show();

                }
                }

                @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Could not login...", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
package com.example.examprojectcontrasoft.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.examprojectcontrasoft.Activities.TimeRecord.TimeRecordActivity;
import com.example.examprojectcontrasoft.Adapters.HomeAdapter;
import com.example.examprojectcontrasoft.Instances.RetrofitClientAPI;
import com.example.examprojectcontrasoft.Interfaces.OnNoteListener;
import com.example.examprojectcontrasoft.Interfaces.RetrofitAPIInterface;
import com.example.examprojectcontrasoft.Models.Function;
import com.example.examprojectcontrasoft.Models.LoggedInUser;
import com.example.examprojectcontrasoft.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements OnNoteListener {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private HomeAdapter homeAdapter;
    private RecyclerView recyclerViewHome;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Function> functionsList;
    private Button logoutBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        setupSharedPreferences();
        fetchCompanyData();

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser();
            }
        });
    }

    private void fetchCompanyData() {
        RetrofitAPIInterface apiServiceInterface = RetrofitClientAPI.getRetrofitInstance().create(RetrofitAPIInterface.class);

        String cookieTest = pref.getString(getString(R.string.shared_pref_cookie), "");

        Call<LoggedInUser> call = apiServiceInterface.fetchData(cookieTest);

        call.enqueue(new Callback<LoggedInUser>() {
            @Override
            public void onResponse(Call<LoggedInUser> call, Response<LoggedInUser> response) {
                System.out.println(response.body());
                editor.putLong(getString(R.string.shared_pref_staff_id),response.body().getStaff().getStaffId());
                editor.commit();

                functionsList = response.body().getCompanyFunctions();
                genereateFunctionsRecyclerView(functionsList);
            }

            @Override
            public void onFailure(Call<LoggedInUser> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void genereateFunctionsRecyclerView(ArrayList<Function> functionsList) {
        homeAdapter = new HomeAdapter(functionsList, this, this);
        recyclerViewHome.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
    }

    private void logoutUser() {
        editor.remove(getString(R.string.shared_pref_cookie));
        editor.apply();

        Intent logoutIntent = new Intent(HomeActivity.this, MainActivity.class);
        logoutIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logoutIntent);
        finish();
    }

    private void setupSharedPreferences() {
        pref = getApplicationContext().getSharedPreferences(getString(R.string.shared_pref_name), MODE_PRIVATE);
        editor = pref.edit();
    }

    private void init(){
        functionsList = new ArrayList<>();
        recyclerViewHome = findViewById(R.id.recyclerViewHome);
        logoutBtn = findViewById(R.id.logoutBtn);

        layoutManager = new LinearLayoutManager(HomeActivity.this);
        recyclerViewHome.setLayoutManager(layoutManager);
    }

    @Override
    public void onNoteClick(int position) {
        if (functionsList.size() > 0) {
            switch (functionsList.get(position).getFunctionName()) {
                case "Time record" :
                    Intent timeRecordIntent = new Intent(HomeActivity.this, TimeRecordActivity.class);
                    startActivity(timeRecordIntent);
                    break;

                case "Picture upload damage" :
                    System.out.println("hej damage");
                    break;

                case "Picture upload receipt" :
                    System.out.println("hej receipt");
                    break;

                default:
                    System.out.println("hej default");
                    break;
                }
            }
    }
}

package com.example.examprojectcontrasoft.Activities.TimeRecord;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examprojectcontrasoft.Activities.HomeActivity;
import com.example.examprojectcontrasoft.Adapters.MyWorkedAdapter;
import com.example.examprojectcontrasoft.Instances.RetrofitClientAPI;
import com.example.examprojectcontrasoft.Interfaces.OnNoteListener;
import com.example.examprojectcontrasoft.Interfaces.RetrofitAPIInterface;
import com.example.examprojectcontrasoft.Models.WorkedDay;
import com.example.examprojectcontrasoft.Models.WorkedDaysTwoDates;
import com.example.examprojectcontrasoft.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyWorkedActivity extends AppCompatActivity implements OnNoteListener {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private MyWorkedAdapter myWorkedAdapter;
    private RecyclerView recyclerViewHome;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<WorkedDay> workedDaysList;
    private BottomNavigationView bottomNavigationViewTimeRecord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_worked);

        setupSharedPreferences();
        init();

        fetchMyWorkedDaysTwoWeeks();
        navItemSelected();
    }

    private void fetchMyWorkedDaysTwoWeeks() {

        LocalDate todaysDate = LocalDate.now();

        LocalDate twoWeeksBehindDate = todaysDate.minusDays(14);

        RetrofitAPIInterface apiServiceInterface = RetrofitClientAPI.getRetrofitInstance().create(RetrofitAPIInterface.class);

        String cookieTest = pref.getString(getString(R.string.shared_pref_cookie), "");
        Long staffId = pref.getLong(getString(R.string.shared_pref_staff_id), 0);

        Call<WorkedDaysTwoDates> call = apiServiceInterface.getWorkedDaysBetweenTwoDates(cookieTest,
                staffId,
                twoWeeksBehindDate.getDayOfMonth(),
                twoWeeksBehindDate.getMonth().getValue(),
                twoWeeksBehindDate.getYear(),
                todaysDate.getDayOfMonth(),
                todaysDate.getMonth().getValue(),
                todaysDate.getYear());

        call.enqueue(new Callback<WorkedDaysTwoDates>() {
            @Override
            public void onResponse(Call<WorkedDaysTwoDates> call, Response<WorkedDaysTwoDates> response) {
                System.out.println(response);
                workedDaysList = response.body().getWorkedDayDTOList();
                genereateMyWorkedDaysRecyclerView(workedDaysList);
            }

            @Override
            public void onFailure(Call<WorkedDaysTwoDates> call, Throwable t) {

            }
        });
    }

    private void genereateMyWorkedDaysRecyclerView(ArrayList<WorkedDay> myWorkedList) {
        myWorkedAdapter = new MyWorkedAdapter(myWorkedList, this, this);
        recyclerViewHome.setAdapter(myWorkedAdapter);
        myWorkedAdapter.notifyDataSetChanged();
    }

    private void navItemSelected() {
        bottomNavigationViewTimeRecord.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuNavHome:
                        Intent navHomeIntent = new Intent(MyWorkedActivity.this, HomeActivity.class);
                        startActivity(navHomeIntent);
                        return true;

                    case R.id.menuNavRegisterTime:
                        Intent navRegisterTime = new Intent(MyWorkedActivity.this, TimeRecordActivity.class);
                        startActivity(navRegisterTime);
                        return true;

                    case R.id.menuNavMyWorked:
                        return true;

                    default:
                        return true;
                }
            }
        });
    }

    private void setupSharedPreferences() {
        pref = getApplicationContext().getSharedPreferences(getString(R.string.shared_pref_name), MODE_PRIVATE);
        editor = pref.edit();
    }

    private void init() {
        recyclerViewHome = findViewById(R.id.recyclerViewMyWorked);
        bottomNavigationViewTimeRecord = findViewById(R.id.timeRecordBottomNav);
        bottomNavigationViewTimeRecord.setSelectedItemId(R.id.menuNavMyWorked);
        workedDaysList = new ArrayList<>();

        layoutManager = new LinearLayoutManager(MyWorkedActivity.this);
        recyclerViewHome.setLayoutManager(layoutManager);
    }

    @Override
    public void onNoteClick(int position) {

    }
}

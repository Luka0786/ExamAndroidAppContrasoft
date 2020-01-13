package com.example.examprojectcontrasoft.Activities.TimeRecord;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.examprojectcontrasoft.Activities.HomeActivity;
import com.example.examprojectcontrasoft.Instances.RetrofitClientAPI;
import com.example.examprojectcontrasoft.Interfaces.RetrofitAPIInterface;
import com.example.examprojectcontrasoft.Models.CheckIn;
import com.example.examprojectcontrasoft.Models.CheckOut;
import com.example.examprojectcontrasoft.Models.PauseEnd;
import com.example.examprojectcontrasoft.Models.PauseStart;
import com.example.examprojectcontrasoft.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private ImageButton checkInBtn, checkOutBtn, pauseStartBtn, pauseEndBtn;
    private TextView checkTextLeftSide, checkTextRightSide;
    private boolean isCheckedOut, isPauseStarted, isPauseEnded;
    private String sessionCookie;
    private Long staffId;
    private AlertDialog.Builder alertDialogBuilder;
    private BottomNavigationView bottomNavigationViewTimeRecord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_record);

        setupSharedPreferences();
        init();
        navItemSelected();
    }

    private void setButtonVisibility() {
        isCheckedOut = pref.getBoolean(getString(R.string.shared_pref_checked_out), false);
        isPauseStarted = pref.getBoolean(getString(R.string.shared_pref_pause_start), false);
        isPauseEnded = pref.getBoolean(getString(R.string.shared_pref_pause_end), false);

        if (!isCheckedOut) {
            checkOutBtn.setVisibility(View.INVISIBLE);

        } else {
            checkTextRightSide.setText(R.string.check_out_time);
            checkOutBtn.setVisibility(View.VISIBLE);
            checkInBtn.setVisibility(View.INVISIBLE);
        }

        if (!isPauseEnded && !isCheckedOut && !isPauseStarted) {
            checkTextLeftSide.setText(R.string.check_in_time);
            checkTextRightSide.setText("");
            checkInBtn.setVisibility(View.VISIBLE);
        }

        if (!isPauseStarted) {
            pauseStartBtn.setVisibility(View.INVISIBLE);
        } else {
            checkTextLeftSide.setText(R.string.check_pause_start);
            pauseStartBtn.setVisibility(View.VISIBLE);
        }

        if (!isPauseEnded) {
            pauseEndBtn.setVisibility(View.INVISIBLE);
        } else {
            checkTextLeftSide.setText("");
            checkTextRightSide.setText(R.string.check_pause_end);
            pauseEndBtn.setVisibility(View.VISIBLE);
        }
    }

    private void checkInAPI() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getString(R.string.check_in_message))
                .setTitle(getString(R.string.check_in_title))
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RetrofitAPIInterface apiServiceInterface = RetrofitClientAPI.getRetrofitInstance().create(RetrofitAPIInterface.class);

                        Call<CheckIn> call = apiServiceInterface.checkIn(sessionCookie, staffId);

                        call.enqueue(new Callback<CheckIn>() {
                            @Override
                            public void onResponse(Call<CheckIn> call, Response<CheckIn> response) {
                                Toast.makeText(TimeRecordActivity.this, getString(R.string.check_in_success), Toast.LENGTH_LONG).show();

                                isCheckedOut = true;
                                isPauseStarted = true;
                                editor.putBoolean(getString(R.string.shared_pref_checked_out), isCheckedOut);
                                editor.putBoolean(getString(R.string.shared_pref_pause_start), isPauseStarted);
                                editor.commit();
                                setButtonVisibility();
                            }

                            @Override
                            public void onFailure(Call<CheckIn> call, Throwable t) {
                                Toast.makeText(TimeRecordActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                t.printStackTrace();
                            }
                        });
                    }
                });

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }

    private void checkOutAPI() {

        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getString(R.string.check_out_message))
                .setTitle(getString(R.string.check_out_title))
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RetrofitAPIInterface apiServiceInterface = RetrofitClientAPI.getRetrofitInstance().create(RetrofitAPIInterface.class);

                        Call<CheckOut> call = apiServiceInterface.checkOut(sessionCookie, staffId);

                        call.enqueue(new Callback<CheckOut>() {
                            @Override
                            public void onResponse(Call<CheckOut> call, Response<CheckOut> response) {
                                Toast.makeText(TimeRecordActivity.this, getString(R.string.check_out_success), Toast.LENGTH_LONG).show();

                                isCheckedOut = false;
                                isPauseStarted = false;
                                editor.putBoolean(getString(R.string.shared_pref_checked_out), isCheckedOut);
                                editor.putBoolean(getString(R.string.shared_pref_pause_start), isPauseStarted);
                                editor.commit();
                                setButtonVisibility();
                            }

                            @Override
                            public void onFailure(Call<CheckOut> call, Throwable t) {
                                Toast.makeText(TimeRecordActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                t.printStackTrace();
                            }
                        });
                    }
                });

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();

    }

    private void pauseStartAPI() {

        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getString(R.string.pause_start_message))
                .setTitle(getString(R.string.start_pause_title))
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RetrofitAPIInterface apiServiceInterface = RetrofitClientAPI.getRetrofitInstance().create(RetrofitAPIInterface.class);

                        Call<PauseStart> call = apiServiceInterface.pauseStart(sessionCookie, staffId);

                        call.enqueue(new Callback<PauseStart>() {
                            @Override
                            public void onResponse(Call<PauseStart> call, Response<PauseStart> response) {
                                Toast.makeText(TimeRecordActivity.this, getString(R.string.pause_start_success), Toast.LENGTH_LONG).show();

                                isCheckedOut = false;
                                isPauseStarted = false;
                                isPauseEnded = true;
                                editor.putBoolean(getString(R.string.shared_pref_checked_out), isCheckedOut);
                                editor.putBoolean(getString(R.string.shared_pref_pause_start), isPauseStarted);
                                editor.putBoolean(getString(R.string.shared_pref_pause_end), isPauseEnded);
                                editor.commit();
                                setButtonVisibility();
                            }

                            @Override
                            public void onFailure(Call<PauseStart> call, Throwable t) {
                                Toast.makeText(TimeRecordActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                t.printStackTrace();
                            }
                        });
                    }
                });

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();


    }

    private void pauseEndAPI() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getString(R.string.pause_end_message))
                .setTitle(getString(R.string.pause_end_title))
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RetrofitAPIInterface apiServiceInterface = RetrofitClientAPI.getRetrofitInstance().create(RetrofitAPIInterface.class);

                        Call<PauseEnd> call = apiServiceInterface.pauseEnd(sessionCookie, staffId);

                        call.enqueue(new Callback<PauseEnd>() {
                            @Override
                            public void onResponse(Call<PauseEnd> call, Response<PauseEnd> response) {
                                Toast.makeText(TimeRecordActivity.this, getString(R.string.pause_end_success), Toast.LENGTH_LONG).show();

                                isCheckedOut = true;
                                isPauseStarted = true;
                                isPauseEnded = false;
                                editor.putBoolean(getString(R.string.shared_pref_checked_out), isCheckedOut);
                                editor.putBoolean(getString(R.string.shared_pref_pause_start), isPauseStarted);
                                editor.putBoolean(getString(R.string.shared_pref_pause_end), isPauseEnded);
                                editor.commit();
                                setButtonVisibility();
                            }

                            @Override
                            public void onFailure(Call<PauseEnd> call, Throwable t) {
                                Toast.makeText(TimeRecordActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                t.printStackTrace();
                            }
                        });
                    }
                });

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }

    private void navItemSelected() {
        bottomNavigationViewTimeRecord.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuNavHome:
                        Intent navHomeIntent = new Intent(TimeRecordActivity.this, HomeActivity.class);
                        startActivity(navHomeIntent);
                        return true;

                    case R.id.menuNavRegisterTime:
                        return true;

                    case R.id.menuNavMyWorked:
                        Intent navMyWorkedIntent = new Intent(TimeRecordActivity.this, MyWorkedActivity.class);
                        startActivity(navMyWorkedIntent);
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
        checkInBtn = findViewById(R.id.checkInBtn);
        checkOutBtn = findViewById(R.id.checkOutBtn);
        pauseStartBtn = findViewById(R.id.checkPauseStartBtn);
        pauseEndBtn = findViewById(R.id.checkPauseEndBtn);
        checkTextLeftSide = findViewById(R.id.checkTextLeftSide);
        checkTextRightSide = findViewById(R.id.checkTextRightSide);
        bottomNavigationViewTimeRecord = findViewById(R.id.timeRecordBottomNav);
        bottomNavigationViewTimeRecord.setSelectedItemId(R.id.menuNavRegisterTime);

        checkInBtn.setOnClickListener(this);
        checkOutBtn.setOnClickListener(this);
        pauseStartBtn.setOnClickListener(this);
        pauseEndBtn.setOnClickListener(this);

        sessionCookie = pref.getString(getString(R.string.shared_pref_cookie), "");
        staffId = pref.getLong(getString(R.string.shared_pref_staff_id), 0);

        checkInBtn.setVisibility(View.VISIBLE);
        setButtonVisibility();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkInBtn:
                checkInAPI();
                break;

            case R.id.checkOutBtn:
                checkOutAPI();
                break;

            case R.id.checkPauseStartBtn:
                pauseStartAPI();
                break;

            case R.id.checkPauseEndBtn:
                pauseEndAPI();
                break;
        }
    }
}

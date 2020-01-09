package com.example.examprojectcontrasoft.Activities.TimeRecord;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.examprojectcontrasoft.Instances.RetrofitClientAPI;
import com.example.examprojectcontrasoft.Interfaces.RetrofitAPIInterface;
import com.example.examprojectcontrasoft.Models.CheckIn;
import com.example.examprojectcontrasoft.Models.CheckOut;
import com.example.examprojectcontrasoft.Models.PauseEnd;
import com.example.examprojectcontrasoft.Models.PauseStart;
import com.example.examprojectcontrasoft.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Button checkInBtn, checkOutBtn, pauseStartBtn, pauseEndBtn, myWorked;
    private boolean isCheckedOut, isPauseStarted, isPauseEnded;
    private String sessionCookie;
    private Long staffId;
    private AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_record);

        setupSharedPreferences();
        init();
        setButtonVisibility();
    }

    private void setButtonVisibility() {
        isCheckedOut = pref.getBoolean(getString(R.string.shared_pref_checked_out), false);
        isPauseStarted = pref.getBoolean(getString(R.string.shared_pref_pause_start), false);
        isPauseEnded = pref.getBoolean(getString(R.string.shared_pref_pause_end), false);


        if (!isCheckedOut) {
            checkOutBtn.setVisibility(View.INVISIBLE);
        } else {
            checkOutBtn.setVisibility(View.VISIBLE);
            checkInBtn.setVisibility(View.INVISIBLE);
        }

        if (!isPauseEnded && !isCheckedOut && !isPauseStarted) {
            checkInBtn.setVisibility(View.VISIBLE);
        }

        if (!isPauseStarted) {
            pauseStartBtn.setVisibility(View.INVISIBLE);
        } else {
            pauseStartBtn.setVisibility(View.VISIBLE);
        }

        if (!isPauseEnded) {
            pauseEndBtn.setVisibility(View.INVISIBLE);
        } else {
            pauseEndBtn.setVisibility(View.VISIBLE);
        }
    }

    private void checkInAPI() {


        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to check in?")
                .setTitle("Check In")
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
        alertDialogBuilder.setMessage("Are you sure you want to check out?")
                .setTitle("Check out")
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
        alertDialogBuilder.setMessage("Are you sure you want to start your pause?")
                .setTitle("Start pause")
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
        alertDialogBuilder.setMessage("Are you sure you want to end your pause?")
                .setTitle("End pause")
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

    private void setupSharedPreferences() {
        pref = getApplicationContext().getSharedPreferences(getString(R.string.shared_pref_name), MODE_PRIVATE);
        editor = pref.edit();
    }

    private void init() {
        checkInBtn = findViewById(R.id.checkInBtn);
        checkOutBtn = findViewById(R.id.checkOutBtn);
        pauseStartBtn = findViewById(R.id.checkPauseStartBtn);
        pauseEndBtn = findViewById(R.id.checkPauseEndBtn);
        myWorked = findViewById(R.id.myWorkedBtn);

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

            case R.id.myWorkedBtn:
                Intent myWorked = new Intent(TimeRecordActivity.this, MyWorkedActivity.class);
                startActivity(myWorked);
                break;
        }
    }
}

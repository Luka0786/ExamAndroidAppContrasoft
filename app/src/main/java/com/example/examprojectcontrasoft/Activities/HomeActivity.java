package com.example.examprojectcontrasoft.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examprojectcontrasoft.Activities.TimeRecord.TimeRecordActivity;
import com.example.examprojectcontrasoft.Adapters.HomeAdapter;
import com.example.examprojectcontrasoft.Instances.RetrofitClientAPI;
import com.example.examprojectcontrasoft.Interfaces.OnNoteListener;
import com.example.examprojectcontrasoft.Interfaces.RetrofitAPIInterface;
import com.example.examprojectcontrasoft.Models.Function;
import com.example.examprojectcontrasoft.Models.LoggedInUser;
import com.example.examprojectcontrasoft.Models.Staff;
import com.example.examprojectcontrasoft.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

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
    private Toolbar toolbarHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        setupSharedPreferences();
        fetchCompanyData();

    }

    private void fetchCompanyData() {
        RetrofitAPIInterface apiServiceInterface = RetrofitClientAPI.getRetrofitInstance().create(RetrofitAPIInterface.class);

        String sessionCookie = pref.getString(getString(R.string.shared_pref_cookie), "");

        Call<LoggedInUser> call = apiServiceInterface.fetchData(sessionCookie);

        call.enqueue(new Callback<LoggedInUser>() {
            @Override
            public void onResponse(Call<LoggedInUser> call, Response<LoggedInUser> response) {
                editor.putLong(getString(R.string.shared_pref_staff_id), response.body().getStaff().getStaffId());
                editor.commit();

                functionsList = response.body().getCompanyFunctions();
                genereateFunctionsRecyclerView(functionsList);
                setupNavigationDrawer(response.body().getStaff());
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

    private void setupNavigationDrawer(Staff staff) {
        PrimaryDrawerItem profileItem = new PrimaryDrawerItem().withName("Profile").withIcon(R.drawable.drawer_person);
        PrimaryDrawerItem contactItem = new PrimaryDrawerItem().withName("Contact us").withIcon(R.drawable.drawer_contact_us);


        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.drawer_profile_bg)
                .withProfileImagesClickable(false)
                .withSelectionListEnabledForSingleProfile(false)

                .addProfiles(
                        new ProfileDrawerItem().withName(staff.getFirstName() + " " + staff.getLastName())
                                .withEmail(staff.getEmail()).withIcon(R.drawable.time_record_start)
                                .withIcon(R.drawable.drawer_profile_no_pic)
                ).build();


        final Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbarHome)
                .withAccountHeader(accountHeader)
                .addDrawerItems(
                        profileItem,
                        new DividerDrawerItem(),
                        contactItem
                        ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (((Nameable) drawerItem).getName().toString()) {
                            case "Profile":
                                break;

                            case "Contact us":
                                break;

                            case "Logout":
                                logoutUser();
                                return true;
                        }
                        return false;
                    }

                })
                .build();

        result.addStickyFooterItem(new PrimaryDrawerItem().withName("Logout").withIcon(R.drawable.drawer_logout));

        result.getActionBarDrawerToggle().getDrawerArrowDrawable().setColor(getColor(R.color.color_white));

    }

    private void init(){
        new DrawerBuilder().withActivity(this).build();

        functionsList = new ArrayList<>();
        recyclerViewHome = findViewById(R.id.recyclerViewHome);
        toolbarHome = findViewById(R.id.toolBarHome);

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
                    break;

                case "Picture upload receipt" :
                    break;

                default:
                    break;
                }
            }
    }
}

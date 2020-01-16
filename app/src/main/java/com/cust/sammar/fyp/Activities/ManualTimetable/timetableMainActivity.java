package com.cust.sammar.fyp.Activities.ManualTimetable;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cust.sammar.fyp.Activities.Backup.RemoteBackup;
import com.cust.sammar.fyp.Activities.DemoActivity;
import com.cust.sammar.fyp.Activities.ManualTimetable.adapters.FragmentsTabAdapter;
import com.cust.sammar.fyp.Activities.ManualTimetable.fragments.FridayFragment;
import com.cust.sammar.fyp.Activities.ManualTimetable.fragments.MondayFragment;
import com.cust.sammar.fyp.Activities.ManualTimetable.fragments.SaturdayFragment;
import com.cust.sammar.fyp.Activities.ManualTimetable.fragments.SundayFragment;
import com.cust.sammar.fyp.Activities.ManualTimetable.fragments.ThursdayFragment;
import com.cust.sammar.fyp.Activities.ManualTimetable.fragments.TuesdayFragment;
import com.cust.sammar.fyp.Activities.ManualTimetable.fragments.WednesdayFragment;
import com.cust.sammar.fyp.Activities.ManualTimetable.utils.AlertDialogsHelper;
import com.cust.sammar.fyp.Activities.ManualTimetable.utils.DbHelper;
import com.cust.sammar.fyp.Activities.Notes.CourseList.CourseListDisplay;
import com.cust.sammar.fyp.Activities.Reminders.ReminderActivity;
import com.cust.sammar.fyp.Activities.TimeTableShow;
import com.cust.sammar.fyp.R;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.OpenFileActivityOptions;

import java.util.Calendar;

import static com.cust.sammar.fyp.Activities.ManualTimetable.utils.BrowserUtil.openUrlInChromeCustomTab;

public class timetableMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentsTabAdapter adapter;
    private ViewPager viewPager;
    private boolean switchSevenDays;

    public static final int REQUEST_CODE_SIGN_IN = 0;
    public static final int REQUEST_CODE_OPENING = 1;
    public static final int REQUEST_CODE_CREATION = 2;
    public static final int REQUEST_CODE_PERMISSIONS = 2;

    private static final String TAG = "Timetable Main Activity";

    //variable for decide if i need to do a backup or a restore.
    //True stands for backup, False for restore
    private boolean isBackup = true;
    private RemoteBackup remoteBackup;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_timetable_activity_main);
        remoteBackup = new RemoteBackup(this);
        dbHelper = new DbHelper(getApplicationContext());
        initAll();
    }

    private void initAll() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setupFragments();
        setupCustomDialog();
        setupSevenDaysPref();

        if(switchSevenDays) changeFragments(true);

        //setDailyAlarm();
    }

    private void setupFragments() {
        adapter = new FragmentsTabAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        adapter.addFragment(new MondayFragment(), getResources().getString(R.string.monday));
        adapter.addFragment(new TuesdayFragment(), getResources().getString(R.string.tuesday));
        adapter.addFragment(new WednesdayFragment(), getResources().getString(R.string.wednesday));
        adapter.addFragment(new ThursdayFragment(), getResources().getString(R.string.thursday));
        adapter.addFragment(new FridayFragment(), getResources().getString(R.string.friday));
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(day == 1 ? 6 : day-2, true);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void changeFragments(boolean isChecked) {
        if(isChecked) {
            TabLayout tabLayout = findViewById(R.id.tabLayout);
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            adapter.addFragment(new SaturdayFragment(), getResources().getString(R.string.saturday));
            adapter.addFragment(new SundayFragment(), getResources().getString(R.string.sunday));
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(day == 1 ? 6 : day-2, true);
            tabLayout.setupWithViewPager(viewPager);
        } else {
            if(adapter.getFragmentList().size() > 5) {
                adapter.removeFragment(new SaturdayFragment(), 5);
                adapter.removeFragment(new SundayFragment(), 5);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void setupCustomDialog() {
        final View alertLayout = getLayoutInflater().inflate(R.layout.dialog_add_subject, null);
        AlertDialogsHelper.getAddSubjectDialog(timetableMainActivity.this, alertLayout, adapter, viewPager);
    }

    private void setupSevenDaysPref() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        switchSevenDays = sharedPref.getBoolean(SettingsActivity.KEY_SEVEN_DAYS_SETTING, false);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.action_settings: {
                Intent settings = new Intent(timetableMainActivity.this, SettingsActivity.class);
                startActivity(settings);
                return true;
            }
            case R.id.GridView:
            {
                Intent timetable = new Intent(timetableMainActivity.this, TimeTableShow.class);
                startActivity(timetable);
                return true;
            }
            case R.id.action_backup_Drive:
                isBackup = true;
                remoteBackup.connectToDrive(isBackup);
                break;
            case R.id.action_import_Drive:
                isBackup = false;
                remoteBackup.connectToDrive(isBackup);
                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {

            case REQUEST_CODE_SIGN_IN:
                Log.i(TAG, "Sign in request code");
                // Called after user is signed in.
                if (resultCode == RESULT_OK) {
                    remoteBackup.connectToDrive(isBackup);
                }
                break;

            case REQUEST_CODE_CREATION:
                // Called after a file is saved to Drive.
                if (resultCode == RESULT_OK) {
                    Log.i(TAG, "Backup successfully saved.");
                    Toast.makeText(this, "Backup successufly loaded!", Toast.LENGTH_SHORT).show();
                }
                break;

            case REQUEST_CODE_OPENING:
                if (resultCode == RESULT_OK) {
                    DriveId driveId = data.getParcelableExtra(
                            OpenFileActivityOptions.EXTRA_RESPONSE_DRIVE_ID);
                    remoteBackup.mOpenItemTaskSource.setResult(driveId);
                } else {
                    remoteBackup.mOpenItemTaskSource.setException(new RuntimeException("Unable to open file"));
                }

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        final NavigationView navigationView = findViewById(R.id.nav_view);
        switch (item.getItemId()) {
            case R.id.schoolwebsitemenu:
                String schoolWebsite = PreferenceManager.getDefaultSharedPreferences(this).getString(SettingsActivity.KEY_SCHOOL_WEBSITE_SETTING, null);
                if(!TextUtils.isEmpty(schoolWebsite)) {
                    openUrlInChromeCustomTab(getApplicationContext(), schoolWebsite);
                } else {
                    Snackbar.make(navigationView, R.string.school_website_snackbar, Snackbar.LENGTH_SHORT).show();
                }
                return true;
            case R.id.notes:
                Intent note = new Intent(timetableMainActivity.this, CourseListDisplay.class);
                startActivity(note);
                return true;
            case R.id.reminders:
                Intent reminder = new Intent(timetableMainActivity.this, ReminderActivity.class);
                startActivity(reminder);
                return true;
            case R.id.shareNotes:
                Intent share =new Intent(timetableMainActivity.this, DemoActivity.class);
                startActivity(share);
                return true;
            case R.id.settings:
                Intent settings = new Intent(timetableMainActivity.this, SettingsActivity.class);
                startActivity(settings);
                return true;
            default:
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
        }
    }
}

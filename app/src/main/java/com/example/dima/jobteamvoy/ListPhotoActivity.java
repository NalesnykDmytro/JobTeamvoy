package com.example.dima.jobteamvoy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import java.lang.reflect.Field;

public class ListPhotoActivity extends AppCompatActivity {

    private int number = 1;
    android.support.v4.app.Fragment fragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_latest:
                    number = 1;
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    changeFragment(1);
                    return true;
                case R.id.navigation_oldest:
                    number = 2;
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    changeFragment(2);
                    return true;
                case R.id.navigation_popular:
                    number = 3;
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    changeFragment(3);
                    return true;
                case R.id.navigation_random:
                    number = 4;
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    changeFragment(4);
                    return true;
            }
            return false;
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_photo);
        changeFragment(1);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private void changeFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("number", position);
        FragmentManager fragmentManeger = getSupportFragmentManager();
        fragment = fragmentManeger.findFragmentById(R.id.content);
        fragment = new SortFragment();
        fragment.setArguments(bundle);
        fragmentManeger.beginTransaction().add(R.id.content, fragment).commit();
    }

    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }
}



package com.pheuture.demo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.pheuture.demo.R;
import com.pheuture.demo.view.fragment.FragmentTab1;
import com.pheuture.demo.view.fragment.FragmentTab2;
import com.pheuture.demo.model.User;
import com.pheuture.demo.utils.Const;

/**
 * Created by neeraj on 9/20/2018.
 */

public class TabActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private User user;
    private int view = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.tab_1:
                    setFragment(0);
                    return true;
                case R.id.tab_2:
                    setFragment(1);
                    return true;
                case R.id.tab_3:
                    Toast.makeText(TabActivity.this,"There is no content.",Toast.LENGTH_SHORT).show();
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        getSupportActionBar().hide();
        user=new User();
        fragmentManager = getSupportFragmentManager();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getExtrasFromIntent();
        setFragment(view);
        if (view == 1)
            navigation.setSelectedItemId(R.id.tab_2);

    }

    public static Intent launchDetail(Context context, String name ,String mobile, int view) {
        Intent intent = new Intent(context, TabActivity.class);
        intent.putExtra(Const.NAME, name);
        intent.putExtra(Const.MOBILE, mobile);
        intent.putExtra(Const.SCREEN, view);
        return intent;
    }

    public static Intent launchDetail(Context context,int view) {
        Intent intent = new Intent(context, TabActivity.class);
        intent.putExtra(Const.SCREEN, view);
        return intent;
    }

    private void getExtrasFromIntent() {
        if(getIntent().getExtras()!=null) {
            user.setFullName(getIntent().getExtras().getString(Const.NAME));
            user.setUserMobnum(getIntent().getExtras().getString(Const.MOBILE));
        }
        view = getIntent().getIntExtra(Const.SCREEN, 0);
    }

    private void setFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentTab1();
                fragmentManager.beginTransaction().replace(R.id.rootLayout, fragment).commit();
                break;
            case 1:
                fragment = new FragmentTab2();
                Bundle bundleTab2 = new Bundle();
                bundleTab2.putString(Const.NAME, user.getFullName());
                bundleTab2.putString(Const.MOBILE, user.getUserMobnum());
                fragment.setArguments(bundleTab2);
                fragmentManager.beginTransaction().replace(R.id.rootLayout, fragment).commit();
                break;

        }
    }


}

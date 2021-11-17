package com.codepath.doggydatingdeluxe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.doggydatingdeluxe.fragments.DogHouseFragment;
import com.codepath.doggydatingdeluxe.fragments.DoggyDatingFragment;
import com.codepath.doggydatingdeluxe.fragments.HomeFragment;
import com.codepath.doggydatingdeluxe.fragments.MessageFragment;
import com.codepath.doggydatingdeluxe.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottomNavigation);
        

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = new Fragment();
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                    default:
                        Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        fragment = new HomeFragment();

                        break;
                    case R.id.action_doghouse:
                        Toast.makeText(MainActivity.this,"DogHouse!",Toast.LENGTH_SHORT).show();
                        fragment = new DogHouseFragment();
                        break;
                    case R.id.action_profile:
                        Toast.makeText(MainActivity.this, "Profile!",Toast.LENGTH_SHORT).show();
                        fragment = new ProfileFragment();
                        break;
                    case R.id.action_doggydating:
                        Toast.makeText(MainActivity.this, "Doggy Dating!",Toast.LENGTH_SHORT).show();
                        fragment = new DoggyDatingFragment();
                        break;
                    case R.id.action_message:
                        Toast.makeText(MainActivity.this, "Message!",Toast.LENGTH_SHORT).show();
                        fragment = new MessageFragment();
                        break;



                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
    }


}
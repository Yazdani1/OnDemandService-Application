package yazdaniscodelab.ondemandfinalproject;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import yazdaniscodelab.ondemandfinalproject.ServiceProviderFragment.AllJobFragment;
import yazdaniscodelab.ondemandfinalproject.ServiceProviderFragment.ProfileFragment;
import yazdaniscodelab.ondemandfinalproject.ServiceProviderFragment.ServiceCreateFragment;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private BottomNavigationView bottomNavigationView;

    //Fragment..

    private AllJobFragment allJobFragment;
    private ServiceCreateFragment serviceCreateFragment;
    private ProfileFragment profileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar=findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("OnDemand Service App");


        //Fragment..


        allJobFragment=new AllJobFragment();
        serviceCreateFragment=new ServiceCreateFragment();
        profileFragment=new ProfileFragment();

        setFragment(allJobFragment);


        bottomNavigationView=findViewById(R.id.bottombar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.allJob:
                        setFragment(allJobFragment);
                        return true;

                    case R.id.service:
                        setFragment(serviceCreateFragment);
                        return true;

                    case R.id.profile:
                        setFragment(profileFragment);
                        return true;

                        default:
                            return true;


                }

            }
        });

    }

    //Set Fragment method..

    public void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe,fragment);
        fragmentTransaction.commit();

    }


}

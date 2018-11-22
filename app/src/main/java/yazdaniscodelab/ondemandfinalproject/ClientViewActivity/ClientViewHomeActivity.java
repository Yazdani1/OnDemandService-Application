package yazdaniscodelab.ondemandfinalproject.ClientViewActivity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import yazdaniscodelab.ondemandfinalproject.ClientViewFragment.AllServicesFragment;
import yazdaniscodelab.ondemandfinalproject.ClientViewFragment.YourAllJobPostFragment;
import yazdaniscodelab.ondemandfinalproject.ClientViewFragment.YourPostFragment;
import yazdaniscodelab.ondemandfinalproject.R;

public class ClientViewHomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    //Fragment.....

    private AllServicesFragment allServicesFragment;
    private YourPostFragment yourPostFragment;
    private YourAllJobPostFragment yourAllJobPostFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view_home);

        toolbar=findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("OnDemand Service App");


        bottomNavigationView=findViewById(R.id.bottombar);

        allServicesFragment=new AllServicesFragment();
        yourPostFragment=new YourPostFragment();
        yourAllJobPostFragment=new YourAllJobPostFragment();


        setFragment(yourAllJobPostFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.allJob:
                        setFragment(yourAllJobPostFragment);
                        return true;

                    case R.id.postjob:
                        setFragment(yourPostFragment);
                        return true;

                    case R.id.servicesclient:
                        setFragment(allServicesFragment);
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

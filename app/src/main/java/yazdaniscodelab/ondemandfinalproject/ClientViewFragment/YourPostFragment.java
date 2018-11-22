package yazdaniscodelab.ondemandfinalproject.ClientViewFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import yazdaniscodelab.ondemandfinalproject.PostJobCatagoryAllFragment.HomeServiceFragment;
import yazdaniscodelab.ondemandfinalproject.PostJobCatagoryAllFragment.TutionServiceFragment;
import yazdaniscodelab.ondemandfinalproject.R;
import yazdaniscodelab.ondemandfinalproject.ServiceProviderFragment.ProfileFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourPostFragment extends Fragment {

    //Catbutton..

    private Button home_service;
    private Button food_service;
    private Button tution_service;

    private HomeServiceFragment homeServiceFragment;
    private TutionServiceFragment tutionServiceFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_your_post, container, false);

        home_service=myview.findViewById(R.id.home_Service_cat);
        food_service=myview.findViewById(R.id.food_service);
        tution_service=myview.findViewById(R.id.tution_service);

        homeServiceFragment=new HomeServiceFragment();
        tutionServiceFragment=new TutionServiceFragment();

        setFragment(homeServiceFragment);

        //Home Service...

        home_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setFragment(homeServiceFragment);

            }
        });

        //Tution Service

        tution_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(tutionServiceFragment);
            }
        });



        return myview;
    }


    public void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.post_your_job,fragment);
        fragmentTransaction.commit();

    }



}

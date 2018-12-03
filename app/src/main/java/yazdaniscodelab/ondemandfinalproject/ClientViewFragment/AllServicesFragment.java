package yazdaniscodelab.ondemandfinalproject.ClientViewFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import yazdaniscodelab.ondemandfinalproject.ClientViewFragment.ClientViewJobSeekerServicesCatActivity.CatServciesHomeServiceActivity;
import yazdaniscodelab.ondemandfinalproject.ClientViewFragment.ClientViewJobSeekerServicesCatActivity.CatServciesTuitionServiceActivity;
import yazdaniscodelab.ondemandfinalproject.ClientViewFragment.ClientViewJobSeekerServicesCatActivity.CatServicesItandSoftwareActivity;
import yazdaniscodelab.ondemandfinalproject.Model.Services;
import yazdaniscodelab.ondemandfinalproject.R;
import yazdaniscodelab.ondemandfinalproject.ServiceCreateFragment.YourAllServiceFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllServicesFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference publicmHomeServiceCreate;

    private DatabaseReference PublicTuitionServiceCreate;


    //Recycler view

    private RecyclerView mRecyclerHomeService;
    private RecyclerView mRecyclerTution;

    //Button...

    private Button homeService;
    private Button tuitionService;
    private Button itandsoftwareService;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.fragment_all_services, container, false);


        homeService=myview.findViewById(R.id.home_Service_cat);

        tuitionService=myview.findViewById(R.id.tution_service);

        itandsoftwareService=myview.findViewById(R.id.it_software_service);

        homeService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CatServciesHomeServiceActivity.class));
            }
        });

        tuitionService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CatServciesTuitionServiceActivity.class));
            }
        });
        itandsoftwareService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CatServicesItandSoftwareActivity.class));
            }
        });


        mRecyclerHomeService=myview.findViewById(R.id.recycler_home_serview);
        mRecyclerTution=myview.findViewById(R.id.recycler_tution_service);
        mAuth=FirebaseAuth.getInstance();

//        FirebaseUser mUser=mAuth.getCurrentUser();
//        String uid=mUser.getUid();

        publicmHomeServiceCreate=FirebaseDatabase.getInstance().getReference().child("PublicHomeServiceCreate");
        PublicTuitionServiceCreate=FirebaseDatabase.getInstance().getReference().child("PublicTuitionServiceCreate");


        //Recycler Home Service

        LinearLayoutManager layoutManagerHomeService=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        layoutManagerHomeService.setReverseLayout(true);
        layoutManagerHomeService.setStackFromEnd(true);

        mRecyclerHomeService.setHasFixedSize(true);
        mRecyclerHomeService.setLayoutManager(layoutManagerHomeService);

        //Recycler Tution Service..

        LinearLayoutManager layoutManagerTution=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        layoutManagerTution.setReverseLayout(true);
        layoutManagerTution.setStackFromEnd(true);

        mRecyclerTution.setHasFixedSize(true);
        mRecyclerTution.setLayoutManager(layoutManagerTution);


        return myview;
    }


    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Services,AllServicesFragment.Allservice_HomeService> adapter=new FirebaseRecyclerAdapter<Services, AllServicesFragment.Allservice_HomeService>
                (
                        Services.class,
                        R.layout.all_service_data_own_item,
                        AllServicesFragment.Allservice_HomeService.class,
                        publicmHomeServiceCreate

                ) {
            @Override
            protected void populateViewHolder(AllServicesFragment.Allservice_HomeService viewHolder, Services model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescripton(model.getDescription());
                viewHolder.setDate(model.getDate());
                viewHolder.setBudget(model.getBudget());

            }
        };
        mRecyclerHomeService.setAdapter(adapter);



        FirebaseRecyclerAdapter<Services,AllServicesFragment.Allservice_TutionService>tutionServiceAdapter=new FirebaseRecyclerAdapter<Services, AllServicesFragment.Allservice_TutionService>
                (
                        Services.class,
                        R.layout.all_service_data_own_item,
                        AllServicesFragment.Allservice_TutionService.class,
                        PublicTuitionServiceCreate

                ) {
            @Override
            protected void populateViewHolder(AllServicesFragment.Allservice_TutionService viewHolder, Services model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescripton(model.getDescription());
                viewHolder.setDate(model.getDate());
                viewHolder.setBudget(model.getBudget());
            }
        };

        mRecyclerTution.setAdapter(tutionServiceAdapter);

    }

    public static class Allservice_HomeService extends RecyclerView.ViewHolder{
        View mHomeServiceView;
        public Allservice_HomeService(View itemView) {
            super(itemView);
            mHomeServiceView=itemView;
        }

        public void setTitle(String title){
            TextView mtitle=mHomeServiceView.findViewById(R.id.job_title);
            mtitle.setText(title);
        }

        public void setBudget(String budget){
            TextView mBudget=mHomeServiceView.findViewById(R.id.job_budget);
            mBudget.setText("TK."+budget);
        }

        public void setDescripton(String descripton){
            TextView mDescription=mHomeServiceView.findViewById(R.id.job_description);
            mDescription.setText(descripton);
        }

        public void setDate(String date){
            TextView mDate=mHomeServiceView.findViewById(R.id.job_post_Date);
            mDate.setText(date);
        }





    }


    public static class Allservice_TutionService extends RecyclerView.ViewHolder{
        View mHomeServiceView;
        public Allservice_TutionService(View itemView) {
            super(itemView);
            mHomeServiceView=itemView;
        }

        public void setTitle(String title){
            TextView mtitle=mHomeServiceView.findViewById(R.id.job_title);
            mtitle.setText(title);
        }

        public void setBudget(String budget){
            TextView mBudget=mHomeServiceView.findViewById(R.id.job_budget);
            mBudget.setText("TK."+budget);
        }

        public void setDescripton(String descripton){
            TextView mDescription=mHomeServiceView.findViewById(R.id.job_description);
            mDescription.setText(descripton);
        }

        public void setDate(String date){
            TextView mDate=mHomeServiceView.findViewById(R.id.job_post_Date);
            mDate.setText(date);
        }





    }



}

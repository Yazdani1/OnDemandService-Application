package yazdaniscodelab.ondemandfinalproject.ServiceProviderFragment;


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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import yazdaniscodelab.ondemandfinalproject.CatActivityForJobSeeker.CatHomeServiceActivity;
import yazdaniscodelab.ondemandfinalproject.CatActivityForJobSeeker.CatItandSoftwareJobActivity;
import yazdaniscodelab.ondemandfinalproject.CatActivityForJobSeeker.CatTutionServiceActivity;
import yazdaniscodelab.ondemandfinalproject.JobDetailsActivity;
import yazdaniscodelab.ondemandfinalproject.Model.PostJob;
import yazdaniscodelab.ondemandfinalproject.R;
import yazdaniscodelab.ondemandfinalproject.TutionJobDetailsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllJobFragment extends Fragment {


    private FirebaseAuth mAuth;
    private DatabaseReference mHomeServiewDb;

    private DatabaseReference mTutionService;

    //Recycler view

    private RecyclerView mRecyclerHomeService;
    private RecyclerView mRecyclerTution;

    //Category Button
    private Button btnHomeService;
    private Button btnTuitionService;
    private Button btnItandSoftwareService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.fragment_all_job, container, false);

        mRecyclerHomeService=myview.findViewById(R.id.recycler_home_serview);
        mRecyclerTution=myview.findViewById(R.id.recycler_tution_service);

        //Button..
        btnHomeService=myview.findViewById(R.id.home_Service_cat);

        btnTuitionService=myview.findViewById(R.id.tution_service);

        btnItandSoftwareService=myview.findViewById(R.id.it_software_service);

        //onlick listener..
        btnHomeService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CatHomeServiceActivity.class));
            }
        });

        btnTuitionService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CatTutionServiceActivity.class));
            }
        });

        btnItandSoftwareService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CatItandSoftwareJobActivity.class));
            }
        });

        //Firebase..

        //Home Service DB
        mHomeServiewDb= FirebaseDatabase.getInstance().getReference().child("Home_public");

        //Tution Service DB
        mTutionService= FirebaseDatabase.getInstance().getReference().child("mTutionServicePublic");



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
        FirebaseRecyclerAdapter<PostJob,HomeService>adapterHomeService=new FirebaseRecyclerAdapter<PostJob, HomeService>
                (
                        PostJob.class,
                        R.layout.job_post_item_design,
                        HomeService.class,
                        mHomeServiewDb
                ) {
            @Override
            protected void populateViewHolder(HomeService viewHolder, final PostJob model, final int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescripton(model.getDescription());
                viewHolder.setDate(model.getDate());
                viewHolder.setBudget(model.getBudget());
                viewHolder.setJobStartDate(model.getStartDate());
                viewHolder.setJobEndDate(model.getEndDate());

                viewHolder.mHomeServiceView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent=new Intent(getActivity(),JobDetailsActivity.class);

                        intent.putExtra("budget",model.getBudget());
                        intent.putExtra("phone",model.getPhone());
                        intent.putExtra("title",model.getTitle());
                        intent.putExtra("address",model.getAddress());
                        intent.putExtra("startDate",model.getStartDate());
                        intent.putExtra("endData",model.getEndDate());
                        intent.putExtra("description",model.getDescription());


                        startActivity(intent);

                    }
                });


            }
        };
        mRecyclerHomeService.setAdapter(adapterHomeService);

        //Firebase RecyclerAdapter tution data..
        FirebaseRecyclerAdapter<PostJob,TutionService>adapterTutionService=new FirebaseRecyclerAdapter<PostJob, TutionService>
                (
                        PostJob.class,
                        R.layout.job_post_item_design,
                        TutionService.class,
                        mTutionService
                ) {
            @Override
            protected void populateViewHolder(TutionService viewHolder,final PostJob model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescripton(model.getDescription());
                viewHolder.setDate(model.getDate());
                viewHolder.setBudget(model.getBudget());
                viewHolder.setJobStartDate(model.getStartDate());
                viewHolder.setJobEndDate(model.getEndDate());

                viewHolder.mTutionService.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(),TutionJobDetailsActivity.class);

                        intent.putExtra("budget",model.getBudget());
                        intent.putExtra("phone",model.getPhone());
                        intent.putExtra("title",model.getTitle());
                        intent.putExtra("address",model.getAddress());
                        intent.putExtra("startDate",model.getStartDate());
                        intent.putExtra("endData",model.getEndDate());
                        intent.putExtra("description",model.getDescription());


                        startActivity(intent);
                    }
                });

            }
        };
        mRecyclerTution.setAdapter(adapterTutionService);

    }

    //Home Service class..

    public static class HomeService extends RecyclerView.ViewHolder{
        View mHomeServiceView;
        public HomeService(View itemView) {
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

        public void setJobStartDate(String startDate){

        }

        public void setJobEndDate(String endDate){

        }


    }


    //Tution Service Class..


    public static class TutionService extends RecyclerView.ViewHolder{
        View mTutionService;
        public TutionService(View itemView) {
            super(itemView);
            mTutionService=itemView;
        }

        public void setTitle(String title){
            TextView mtitle=mTutionService.findViewById(R.id.job_title);
            mtitle.setText(title);
        }

        public void setBudget(String budget){
            TextView mBudget=mTutionService.findViewById(R.id.job_budget);
            mBudget.setText("TK."+budget);
        }

        public void setDescripton(String descripton){
            TextView mDescription=mTutionService.findViewById(R.id.job_description);
            mDescription.setText(descripton);
        }

        public void setDate(String date){
            TextView mDate=mTutionService.findViewById(R.id.job_post_Date);
            mDate.setText(date);
        }

        public void setJobStartDate(String startDate){

        }

        public void setJobEndDate(String endDate){

        }

    }


}

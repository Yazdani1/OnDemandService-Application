package yazdaniscodelab.ondemandfinalproject.ClientViewFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import yazdaniscodelab.ondemandfinalproject.Model.PostJob;
import yazdaniscodelab.ondemandfinalproject.R;
import yazdaniscodelab.ondemandfinalproject.ServiceProviderFragment.AllJobFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourAllJobPostFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference mHomeServiewDb;

    private DatabaseReference mTutionService;

    //Recycler view

    private RecyclerView mRecyclerHomeService;
    private RecyclerView mRecyclerTution;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myview=inflater.inflate(R.layout.fragment_your_all_job_post, container, false);

        mRecyclerHomeService=myview.findViewById(R.id.recycler_home_serview);
        mRecyclerTution=myview.findViewById(R.id.recycler_tution_service);

        //Firebase..
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser mUser=mAuth.getCurrentUser();
        String uid=mUser.getUid();

        //Home Service DB
        mHomeServiewDb= FirebaseDatabase.getInstance().getReference().child("HomeServiceDatabase").child(uid);

        //Tution Service DB
        mTutionService= FirebaseDatabase.getInstance().getReference().child("Tution_ServiceDb").child(uid);



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
        FirebaseRecyclerAdapter<PostJob,AllJobFragment.HomeService> adapterHomeService=new FirebaseRecyclerAdapter<PostJob, AllJobFragment.HomeService>
                (
                        PostJob.class,
                        R.layout.job_post_item_design,
                        AllJobFragment.HomeService.class,
                        mHomeServiewDb
                ) {
            @Override
            protected void populateViewHolder(AllJobFragment.HomeService viewHolder, PostJob model, final int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescripton(model.getDescription());
                viewHolder.setDate(model.getDate());
                viewHolder.setBudget(model.getBudget());
                viewHolder.setJobStartDate(model.getStartDate());
                viewHolder.setJobEndDate(model.getEndDate());


            }
        };
        mRecyclerHomeService.setAdapter(adapterHomeService);

        //Firebase RecyclerAdapter tution data..
        FirebaseRecyclerAdapter<PostJob,AllJobFragment.HomeService>adapterTutionService=new FirebaseRecyclerAdapter<PostJob, AllJobFragment.HomeService>
                (
                        PostJob.class,
                        R.layout.job_post_item_design,
                        AllJobFragment.HomeService.class,
                        mTutionService
                ) {
            @Override
            protected void populateViewHolder(AllJobFragment.HomeService viewHolder, PostJob model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescripton(model.getDescription());
                viewHolder.setDate(model.getDate());
                viewHolder.setBudget(model.getBudget());
                viewHolder.setJobStartDate(model.getStartDate());
                viewHolder.setJobEndDate(model.getEndDate());
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


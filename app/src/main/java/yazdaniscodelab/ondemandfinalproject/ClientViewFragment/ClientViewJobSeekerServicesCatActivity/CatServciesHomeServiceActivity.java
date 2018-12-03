package yazdaniscodelab.ondemandfinalproject.ClientViewFragment.ClientViewJobSeekerServicesCatActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import yazdaniscodelab.ondemandfinalproject.CatActivityForJobSeeker.CatHomeServiceActivity;
import yazdaniscodelab.ondemandfinalproject.Model.PostJob;
import yazdaniscodelab.ondemandfinalproject.R;

public class CatServciesHomeServiceActivity extends AppCompatActivity {

//    This file is for services of job seeker..this view can see client from client view
//
//    and when they will select home service category then they will see this file.


    private DatabaseReference publicmHomeServiceCreate;

    private Toolbar toolbar;

    //recycler
    private RecyclerView mRecyclerHomeService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_servcies_home_service);

        toolbar=findViewById(R.id.cat_home_service);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home Services");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mRecyclerHomeService=findViewById(R.id.recycler_cat_home_service);

        //databse

        publicmHomeServiceCreate=FirebaseDatabase.getInstance().getReference().child("PublicHomeServiceCreate");


        //Recycler Home Service

        LinearLayoutManager layoutManagerHomeService=new LinearLayoutManager(getApplicationContext());
        layoutManagerHomeService.setReverseLayout(true);
        layoutManagerHomeService.setStackFromEnd(true);

        mRecyclerHomeService.setHasFixedSize(true);
        mRecyclerHomeService.setLayoutManager(layoutManagerHomeService);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<PostJob,CatServciesHomeServiceActivity.MyViewHolder> adapter=new FirebaseRecyclerAdapter<PostJob, CatServciesHomeServiceActivity.MyViewHolder>
                (
                        PostJob.class,
                        R.layout.cat_item_layout_design,
                        CatServciesHomeServiceActivity.MyViewHolder.class,
                        publicmHomeServiceCreate

                ) {
            @Override
            protected void populateViewHolder(CatServciesHomeServiceActivity.MyViewHolder viewHolder, PostJob model, int position) {

                viewHolder.setDate(model.getDate());
                viewHolder.setJobTitle(model.getTitle());
                viewHolder.setJobDescription(model.getDescription());
                viewHolder.setBudget(model.getBudget());

            }
        };

        mRecyclerHomeService.setAdapter(adapter);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        View myview;
        public MyViewHolder(View itemView) {
            super(itemView);
            myview=itemView;
        }

        public void setDate(String date){
            TextView mDate=myview.findViewById(R.id.job_date);
            mDate.setText(date);
        }

        public void setJobTitle(String title){
            TextView mTitle=myview.findViewById(R.id.job_title);
            mTitle.setText(title);
        }

        public void setJobDescription(String description){
            TextView mDescription=myview.findViewById(R.id.job_description_cat);
            mDescription.setText(description);
        }

        public void setBudget(String budget){
            TextView mBudget=myview.findViewById(R.id.job_budget);
            mBudget.setText("TK."+budget);
        }

    }

}

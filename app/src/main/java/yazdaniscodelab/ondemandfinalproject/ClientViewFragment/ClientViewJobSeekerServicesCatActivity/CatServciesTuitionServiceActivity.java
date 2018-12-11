package yazdaniscodelab.ondemandfinalproject.ClientViewFragment.ClientViewJobSeekerServicesCatActivity;

import android.content.Intent;
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

import yazdaniscodelab.ondemandfinalproject.ClientViewFragment.ClientViewJobSeekerServicesCatActivity.JobSeekerServicesCatDetails.Js_Cat_HomeServiceDetailsActivity;
import yazdaniscodelab.ondemandfinalproject.ClientViewFragment.ClientViewJobSeekerServicesCatActivity.JobSeekerServicesCatDetails.Js_Cat_TuitionServiceDetailsActivity;
import yazdaniscodelab.ondemandfinalproject.Model.PostJob;
import yazdaniscodelab.ondemandfinalproject.Model.Services;
import yazdaniscodelab.ondemandfinalproject.R;

public class CatServciesTuitionServiceActivity extends AppCompatActivity {

    //    This file is for services of job seeker..this view can see client from client view
//
//    and when they will select tuition service category then they will see this file.


    private DatabaseReference PublicTuitionServiceCreate;

    private Toolbar toolbar;

    //recycler
    private RecyclerView mRecyclerHomeService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_servcies_tuition_service);


        toolbar=findViewById(R.id.cat_home_service);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tuition Services Job");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mRecyclerHomeService=findViewById(R.id.recycler_cat_home_service);

        //databse

        PublicTuitionServiceCreate=FirebaseDatabase.getInstance().getReference().child("PublicTuitionServiceCreate");


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

        FirebaseRecyclerAdapter<Services,CatServciesTuitionServiceActivity.MyViewHolder> adapter=new FirebaseRecyclerAdapter<Services, CatServciesTuitionServiceActivity.MyViewHolder>
                (
                        Services.class,
                        R.layout.cat_tution_item_layout,
                        CatServciesTuitionServiceActivity.MyViewHolder.class,
                        PublicTuitionServiceCreate

                ) {
            @Override
            protected void populateViewHolder(CatServciesTuitionServiceActivity.MyViewHolder viewHolder,final Services model, int position) {

                viewHolder.setDate(model.getDate());
                viewHolder.setJobTitle(model.getTitle());
                viewHolder.setJobDescription(model.getDescription());
                viewHolder.setBudget(model.getBudget());

                viewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(), Js_Cat_TuitionServiceDetailsActivity.class);

                        intent.putExtra("title",model.getTitle());
                        intent.putExtra("budget",model.getBudget());
                        intent.putExtra("skills",model.getSkill());
                        intent.putExtra("phone",model.getPhone());
                        intent.putExtra("description",model.getDescription());
                        startActivity(intent);
                    }
                });

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

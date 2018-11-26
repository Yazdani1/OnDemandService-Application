package yazdaniscodelab.ondemandfinalproject.ServiceCreateFragment;


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

import yazdaniscodelab.ondemandfinalproject.Model.Services;
import yazdaniscodelab.ondemandfinalproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourAllServiceFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference homeServiceOWN;

    private DatabaseReference tutionservice;

    //Recycler view

    private RecyclerView mRecyclerHomeService;
    private RecyclerView mRecyclerTution;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.fragment_your_all, container, false);


        mRecyclerHomeService=myview.findViewById(R.id.recycler_home_serview);
        mRecyclerTution=myview.findViewById(R.id.recycler_tution_service);
        mAuth=FirebaseAuth.getInstance();

        FirebaseUser mUser=mAuth.getCurrentUser();
        String uid=mUser.getUid();

        homeServiceOWN= FirebaseDatabase.getInstance().getReference().child("HomeServiceCreate").child(uid);
        tutionservice= FirebaseDatabase.getInstance().getReference().child("TuitionServiceCreate").child(uid);


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

        FirebaseRecyclerAdapter<Services,Allservice_HomeService>adapter=new FirebaseRecyclerAdapter<Services, Allservice_HomeService>
                (
                        Services.class,
                        R.layout.all_service_data_own_item,
                        Allservice_HomeService.class,
                        homeServiceOWN

                ) {
            @Override
            protected void populateViewHolder(Allservice_HomeService viewHolder, Services model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescripton(model.getDescription());
                viewHolder.setDate(model.getDate());
                viewHolder.setBudget(model.getBudget());

            }
        };
        mRecyclerHomeService.setAdapter(adapter);



        FirebaseRecyclerAdapter<Services,Allservice_HomeService>tutionServiceAdapter=new FirebaseRecyclerAdapter<Services, Allservice_HomeService>
                (
                        Services.class,
                        R.layout.all_service_data_own_item,
                        Allservice_HomeService.class,
                        tutionservice

                ) {
            @Override
            protected void populateViewHolder(Allservice_HomeService viewHolder, Services model, int position) {
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





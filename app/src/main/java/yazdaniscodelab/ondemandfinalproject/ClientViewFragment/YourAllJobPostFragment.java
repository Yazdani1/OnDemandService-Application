package yazdaniscodelab.ondemandfinalproject.ClientViewFragment;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import yazdaniscodelab.ondemandfinalproject.ClientViewFragment.UpdatenadDeleteYourAllJobPOST.HomeService_UPDActivity;
import yazdaniscodelab.ondemandfinalproject.Model.PostJob;
import yazdaniscodelab.ondemandfinalproject.R;
import yazdaniscodelab.ondemandfinalproject.ServiceProviderFragment.AllJobFragment;

import static android.content.ContentValues.TAG;

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

    //globar v
    private String title;
    private String budget;
    private String address;
    private String phone;
    private String description;

    private String post_key;

    //Date Picker....

    private DatePickerDialog.OnDateSetListener mDatepicker;

    private DatePickerDialog.OnDateSetListener mDateEnd;

    private String date_start;
    private String date_end;


     TextView startDate;
     TextView endDate;


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
        final FirebaseRecyclerAdapter<PostJob,YourAllJobPostFragment.HomeService> adapterHomeService=new FirebaseRecyclerAdapter<PostJob, YourAllJobPostFragment.HomeService>
                (
                        PostJob.class,
                        R.layout.job_post_item_design,
                        YourAllJobPostFragment.HomeService.class,
                        mHomeServiewDb
                ) {
            @Override
            protected void populateViewHolder(YourAllJobPostFragment.HomeService viewHolder, final PostJob model, final int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescripton(model.getDescription());
                viewHolder.setDate(model.getDate());
                viewHolder.setBudget(model.getBudget());
                viewHolder.setJobStartDate(model.getStartDate());
                viewHolder.setJobEndDate(model.getEndDate());

                viewHolder.mHomeServiceView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        post_key=getRef(position).getKey();

                        title=model.getTitle();
                        budget=model.getBudget();
                        address=model.getAddress();
                        phone=model.getPhone();
                        description=model.getDescription();

                        updateHomeService();

                    }
                });


            }
        };
        mRecyclerHomeService.setAdapter(adapterHomeService);

        //Firebase RecyclerAdapter tution data..
        FirebaseRecyclerAdapter<PostJob,YourAllJobPostFragment.TutionService>adapterTutionService=new FirebaseRecyclerAdapter<PostJob, YourAllJobPostFragment.TutionService>
                (
                        PostJob.class,
                        R.layout.job_post_item_design,
                        YourAllJobPostFragment.TutionService.class,
                        mTutionService
                ) {
            @Override
            protected void populateViewHolder(YourAllJobPostFragment.TutionService viewHolder,final PostJob model,final int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescripton(model.getDescription());
                viewHolder.setDate(model.getDate());
                viewHolder.setBudget(model.getBudget());
                viewHolder.setJobStartDate(model.getStartDate());
                viewHolder.setJobEndDate(model.getEndDate());

                viewHolder.mTutionService.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        post_key=getRef(position).getKey();

                        title=model.getTitle();
                        budget=model.getBudget();
                        address=model.getAddress();
                        phone=model.getPhone();
                        description=model.getDescription();
                        deleteTuitionService();
                    }
                });

            }
        };
        mRecyclerTution.setAdapter(adapterTutionService);

    }


    //alert dialog update home service..

    public void updateHomeService(){

        AlertDialog.Builder mydialog=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=LayoutInflater.from(getActivity());
        View mView=inflater.inflate(R.layout.update_job_layout,null);
        mydialog.setView(mView);
        final AlertDialog dialog=mydialog.create();

        dialog.show();

        final EditText mTitle=mView.findViewById(R.id.job_title_upd);
        final EditText mBudget=mView.findViewById(R.id.job_budget_upd);
        final EditText mAddress=mView.findViewById(R.id.job_address_upd);
        final EditText mPhone=mView.findViewById(R.id.job_phone_upd);
        final EditText mDescription=mView.findViewById(R.id.job_description_upd);
        final TextView closeDialog=mView.findViewById(R.id.close_dialog);

        //Start date
        startDate=mView.findViewById(R.id.job_start_date);
        endDate=mView.findViewById(R.id.job_end_date);




        Button btnUpdate=mView.findViewById(R.id.update_job_upd);
        Button btnDelete=mView.findViewById(R.id.delete_job_upd);

        mTitle.setText(title);
        mTitle.setSelection(title.length());

        mBudget.setText(budget);
        mBudget.setSelection(budget.length());

        mAddress.setText(address);
        mAddress.setSelection(address.length());

        mPhone.setText(phone);
        mPhone.setSelection(phone.length());

        mDescription.setText(description);
        mDescription.setSelection(description.length());

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();

                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Wallpaper,
                        mDatepicker,year,month,day
                );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();

                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Wallpaper,
                        mDateEnd,year,month,day
                );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });



        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title=mTitle.getText().toString();
                budget=mBudget.getText().toString();
                address=mAddress.getText().toString();
                phone=mPhone.getText().toString();
                description=mDescription.getText().toString();
                String date= DateFormat.getDateInstance().format(new Date());
                PostJob postJob=new PostJob(title,budget,address,phone,description,date_start,date_end,date,post_key);

                mHomeServiewDb.child(post_key).setValue(postJob);
                dialog.dismiss();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHomeServiewDb.child(post_key).removeValue();
                dialog.dismiss();
            }
        });

        mDatepicker=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                Log.d(TAG,"onDateSet"+year+"/"+month+"/"+day);
                month=month+1;
                date_start=year+"/"+month+"/"+day;
                //date_end=year+"/"+month+"/"+day;
                startDate.setText(date_start);
            }
        };

        mDateEnd=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                Log.d(TAG,"onDateSet"+year+"/"+month+"/"+day);
                month=month+1;
                //date_start=year+"/"+month+"/"+day;
                date_end=year+"/"+month+"/"+day;
                endDate.setText(date_end);

            }
        };

    }

    public void deleteTuitionService(){

        AlertDialog.Builder mydialog=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=LayoutInflater.from(getActivity());
        View mView=inflater.inflate(R.layout.update_job_layout,null);
        mydialog.setView(mView);
        final AlertDialog dialog=mydialog.create();

        dialog.show();

        final EditText mTitle=mView.findViewById(R.id.job_title_upd);
        final EditText mBudget=mView.findViewById(R.id.job_budget_upd);
        final EditText mAddress=mView.findViewById(R.id.job_address_upd);
        final EditText mPhone=mView.findViewById(R.id.job_phone_upd);
        final EditText mDescription=mView.findViewById(R.id.job_description_upd);
        final TextView closeDialog=mView.findViewById(R.id.close_dialog);

        //Start date
        startDate=mView.findViewById(R.id.job_start_date);
        endDate=mView.findViewById(R.id.job_end_date);




        Button btnUpdate=mView.findViewById(R.id.update_job_upd);
        Button btnDelete=mView.findViewById(R.id.delete_job_upd);

        mTitle.setText(title);
        mTitle.setSelection(title.length());

        mBudget.setText(budget);
        mBudget.setSelection(budget.length());

        mAddress.setText(address);
        mAddress.setSelection(address.length());

        mPhone.setText(phone);
        mPhone.setSelection(phone.length());

        mDescription.setText(description);
        mDescription.setSelection(description.length());

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();

                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Wallpaper,
                        mDatepicker,year,month,day
                );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();

                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Wallpaper,
                        mDateEnd,year,month,day
                );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });



        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title=mTitle.getText().toString();
                budget=mBudget.getText().toString();
                address=mAddress.getText().toString();
                phone=mPhone.getText().toString();
                description=mDescription.getText().toString();
                String date= DateFormat.getDateInstance().format(new Date());
                PostJob postJob=new PostJob(title,budget,address,phone,description,date_start,date_end,date,post_key);

                mTutionService.child(post_key).setValue(postJob);
                dialog.dismiss();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTutionService.child(post_key).removeValue();
                dialog.dismiss();
            }
        });

        mDatepicker=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                Log.d(TAG,"onDateSet"+year+"/"+month+"/"+day);
                month=month+1;
                date_start=year+"/"+month+"/"+day;
                //date_end=year+"/"+month+"/"+day;
                startDate.setText(date_start);
            }
        };

        mDateEnd=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                Log.d(TAG,"onDateSet"+year+"/"+month+"/"+day);
                month=month+1;
                //date_start=year+"/"+month+"/"+day;
                date_end=year+"/"+month+"/"+day;
                endDate.setText(date_end);

            }
        };
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


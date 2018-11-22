package yazdaniscodelab.ondemandfinalproject.PostJobCatagoryAllFragment;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import yazdaniscodelab.ondemandfinalproject.Model.PostJob;
import yazdaniscodelab.ondemandfinalproject.R;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TutionServiceFragment extends Fragment {

    private EditText job_title;
    private EditText job_budget;
    private EditText job_holder_phone;
    private EditText job_address;
    private EditText job_description;

    private TextView job_start_date;
    private TextView job_end_date;

    private Button btn_postjob;

    //Date Picker....

    private DatePickerDialog.OnDateSetListener mDatepicker;

    private DatePickerDialog.OnDateSetListener mDateEnd;

    //Firebase...

    private FirebaseAuth mAuth;
    private DatabaseReference mTutionServiceFragment;

    private DatabaseReference mTutionServicePublic;


    //String store date..

    private String date_start;
    private String date_end;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.fragment_tution, container, false);


        ScrollView scroll =myview.findViewById(R.id.addresses_scroll);
        scroll.setFocusableInTouchMode(true);
        scroll.fullScroll(View.FOCUS_UP);
        scroll.smoothScrollTo(0, 0);


        //Dialog progress

        job_title=myview.findViewById(R.id.job_title);
        job_budget=myview.findViewById(R.id.job_budget);
        job_holder_phone=myview.findViewById(R.id.job_phone);
        job_address=myview.findViewById(R.id.job_address);
        job_description=myview.findViewById(R.id.job_description);

        job_start_date=myview.findViewById(R.id.job_start_date);
        job_end_date=myview.findViewById(R.id.job_end_date);

        btn_postjob=myview.findViewById(R.id.post_job);

        //Firebase..

        mAuth=FirebaseAuth.getInstance();

        FirebaseUser mUser=mAuth.getCurrentUser();

        String uid=mUser.getUid();

        mTutionServiceFragment= FirebaseDatabase.getInstance().getReference().child("Tution_ServiceDb").child(uid);

        mTutionServicePublic= FirebaseDatabase.getInstance().getReference().child("mTutionServicePublic");


        //Start date..set click listener..

        job_start_date.setOnClickListener(new View.OnClickListener() {
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

        //end date..set click listener..

        job_end_date.setOnClickListener(new View.OnClickListener() {
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

        //start post job..



        btn_postjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String jb_title=job_title.getText().toString().trim();
                String jb_budget=job_budget.getText().toString().trim();
                String jb_address=job_address.getText().toString().trim();
                String jb_phone=job_holder_phone.getText().toString().trim();
                String jb_description=job_description.getText().toString().trim();

                //varification check

                if (TextUtils.isEmpty(jb_title)){
                    job_title.setError("Required Field..");
                    return;
                }
                if (TextUtils.isEmpty(jb_budget)){
                    job_budget.setError("Required Field..");
                    return;
                }
                if (TextUtils.isEmpty(jb_address)){
                    job_address.setError("Required Field..");
                    return;
                }
                if (TextUtils.isEmpty(jb_phone)){
                    job_holder_phone.setError("Required Field..");
                    return;
                }
                if (TextUtils.isEmpty(jb_description)){
                    job_description.setError("Required Field..");
                    return;
                }


                String id=mTutionServiceFragment.push().getKey();
                String mDate= DateFormat.getDateInstance().format(new Date());

                PostJob postJob=new PostJob(jb_title,jb_budget,jb_address,jb_phone,jb_description,date_start,date_end,mDate,id);
                mTutionServiceFragment.child(id).setValue(postJob);
                mTutionServicePublic.child(id).setValue(postJob);



                job_title.setText("");
                job_budget.setText("");
                job_holder_phone.setText("");
                job_address.setText("");
                job_description.setText("");



            }
        });

        mDatepicker=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                Log.d(TAG,"onDateSet"+year+"/"+month+"/"+day);
                month=month+1;
                date_start=year+"/"+month+"/"+day;

                //date_end=year+"/"+month+"/"+day;

                job_start_date.setText(date_start);



            }
        };


        mDateEnd=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                Log.d(TAG,"onDateSet"+year+"/"+month+"/"+day);
                month=month+1;
                //date_start=year+"/"+month+"/"+day;

                date_end=year+"/"+month+"/"+day;


                job_end_date.setText(date_end);

            }
        };









        return myview;
    }

}

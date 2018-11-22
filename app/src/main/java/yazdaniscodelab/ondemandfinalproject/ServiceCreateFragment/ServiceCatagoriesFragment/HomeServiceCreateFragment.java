package yazdaniscodelab.ondemandfinalproject.ServiceCreateFragment.ServiceCatagoriesFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

import yazdaniscodelab.ondemandfinalproject.Model.Services;
import yazdaniscodelab.ondemandfinalproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeServiceCreateFragment extends Fragment {

    private EditText srv_title;
    private EditText srv_budget;
    private EditText srv_skills;
    private EditText srv_phone;
    private EditText srv_description;

    private Button btnPostService;


    //Firebase database...
    private FirebaseAuth mAuth;
    private DatabaseReference mHomeServiceCreate;

    private DatabaseReference publicmHomeServiceCreate;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.fragment_home_service_create, container, false);

        srv_title=myview.findViewById(R.id.service_title);

        srv_budget=myview.findViewById(R.id.service_budget);

        srv_skills=myview.findViewById(R.id.service_skills);

        srv_phone=myview.findViewById(R.id.service_phone);

        srv_description=myview.findViewById(R.id.service_description);

        btnPostService=myview.findViewById(R.id.btnPostService);

        mAuth=FirebaseAuth.getInstance();

        FirebaseUser mUser=mAuth.getCurrentUser();
        String uid=mUser.getUid();

        mHomeServiceCreate= FirebaseDatabase.getInstance().getReference().child("HomeServiceCreate").child(uid);
        publicmHomeServiceCreate=FirebaseDatabase.getInstance().getReference().child("PublicHomeServiceCreate");

        btnPostService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title=srv_title.getText().toString().trim();
                String budget=srv_budget.getText().toString().trim();
                String skills=srv_skills.getText().toString().trim();
                String phone=srv_phone.getText().toString().trim();
                String description=srv_description.getText().toString().trim();

                if (TextUtils.isEmpty(title)){
                    srv_title.setError("required field..");
                    return;
                }
                if (TextUtils.isEmpty(budget)){
                    srv_budget.setError("required field..");
                    return;
                }
                if (TextUtils.isEmpty(skills)){
                    srv_skills.setError("required field..");
                    return;
                }
                if (TextUtils.isEmpty(phone)){
                    srv_phone.setError("required field..");
                    return;
                }
                if (TextUtils.isEmpty(description)){
                    srv_description.setError("required field..");
                    return;
                }

                String id=mHomeServiceCreate.push().getKey();
                String mDate= DateFormat.getDateInstance().format(new Date());

                Services services=new Services(title,budget,skills,phone,description,mDate,id);

                mHomeServiceCreate.child(id).setValue(services);
                publicmHomeServiceCreate.child(id).setValue(services);

                Toast.makeText(getActivity(),"Data Uploaded",Toast.LENGTH_SHORT).show();


                srv_title.setText("");
                srv_budget.setText("");
                srv_skills.setText("");
                srv_phone.setText("");
                srv_description.setText("");


            }
        });

        return myview;
    }

}

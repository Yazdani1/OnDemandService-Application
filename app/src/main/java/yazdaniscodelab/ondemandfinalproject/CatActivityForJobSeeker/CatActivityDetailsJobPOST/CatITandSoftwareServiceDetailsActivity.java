package yazdaniscodelab.ondemandfinalproject.CatActivityForJobSeeker.CatActivityDetailsJobPOST;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import yazdaniscodelab.ondemandfinalproject.R;

public class CatITandSoftwareServiceDetailsActivity extends AppCompatActivity {

    private TextView budget;
    private TextView call;
    private TextView title;
    private TextView address;
    private TextView startDate;
    private TextView endDate;
    private TextView description;

    private String mPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_itand_software_service_details);

        budget=findViewById(R.id.budget_dtls);
        call=findViewById(R.id.phone_call_dtls);
        title=findViewById(R.id.job_title_dtls);
        address=findViewById(R.id.job_address_dtls);
        startDate=findViewById(R.id.job_start_date);
        endDate=findViewById(R.id.job_end_date);
        description=findViewById(R.id.job_description_dtls);


        Intent intent=getIntent();

        String mBudget=intent.getStringExtra("budget");
        mPhone=intent.getStringExtra("phone");
        String mtitle=intent.getStringExtra("title");
        String mAddress=intent.getStringExtra("address");
        String mStartDate=intent.getStringExtra("startDate");
        String mEndDate=intent.getStringExtra("endData");
        String mDescription=intent.getStringExtra("description");

        budget.setText("TK."+mBudget);
        call.setText(mPhone);
        title.setText(mtitle);
        address.setText(mAddress);
        startDate.setText(mStartDate);
        endDate.setText(mEndDate);
        description.setText(mDescription);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+mPhone)));

            }
        });


    }
}

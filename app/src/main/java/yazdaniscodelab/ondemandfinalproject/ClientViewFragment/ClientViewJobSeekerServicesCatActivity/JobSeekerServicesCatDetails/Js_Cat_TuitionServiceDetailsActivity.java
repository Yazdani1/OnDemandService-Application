package yazdaniscodelab.ondemandfinalproject.ClientViewFragment.ClientViewJobSeekerServicesCatActivity.JobSeekerServicesCatDetails;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import yazdaniscodelab.ondemandfinalproject.R;

public class Js_Cat_TuitionServiceDetailsActivity extends AppCompatActivity {

    private TextView budget;
    private TextView call;
    private TextView title;
    private TextView skill;

    private TextView description;

    private String mCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js__cat__tuition_service_details);

        budget=findViewById(R.id.budget_dtls);
        call=findViewById(R.id.phone_call_dtls);
        title=findViewById(R.id.job_title_dtls);
        skill=findViewById(R.id.job_skills_dtls);
        description=findViewById(R.id.job_description_dtls);

        Intent intent=getIntent();

        String mTitle=intent.getStringExtra("title");
        String mBudget=intent.getStringExtra("budget");
        mCall=intent.getStringExtra("phone");
        String mDescription=intent.getStringExtra("description");
        String mSkills=intent.getStringExtra("skills");

        budget.setText(mBudget);
        call.setText(mCall);
        title.setText(mTitle);
        skill.setText(mSkills);
        description.setText(mDescription);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+mCall)));

            }
        });

    }
}

package yazdaniscodelab.ondemandfinalproject.ClientViewFragment.UpdatenadDeleteYourAllJobPOST;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import yazdaniscodelab.ondemandfinalproject.R;

public class HomeService_UPDActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mHomeServiewDb;

    private RecyclerView mRecyclerHomeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_service__upd);
    }
}

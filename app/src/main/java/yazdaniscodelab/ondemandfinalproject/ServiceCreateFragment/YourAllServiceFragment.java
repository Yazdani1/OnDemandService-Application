package yazdaniscodelab.ondemandfinalproject.ServiceCreateFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yazdaniscodelab.ondemandfinalproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourAllServiceFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.fragment_your_all, container, false);

        return myview;
    }

}

package FragmentFile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import andbook.example.protect_me_release.R;

public class How_to_use_Fragment extends Fragment{

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle
            savedInstanceState)
    {
        View v= inflater.inflate(R.layout.fragment_how_to_use,container,false); //레이아웃 연결
        return v;
    }
}

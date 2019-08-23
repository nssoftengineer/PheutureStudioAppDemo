package com.pheuture.demo.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pheuture.demo.R;
import com.pheuture.demo.utils.Const;

/**
 * Created by neeraj on 9/20/2018.
 */


public class FragmentTab2 extends Fragment {

    public FragmentTab2() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        String name = (String) getArguments().get(Const.NAME);
        String mobile = (String) getArguments().get(Const.MOBILE);

        if(!TextUtils.isEmpty(name))
        ((TextView) view.findViewById(R.id.textViewTitle)).setText(name);
        if(!TextUtils.isEmpty(mobile))
        ((TextView) view.findViewById(R.id.textViewMobile)).setText(mobile);

        return view;
    }
}

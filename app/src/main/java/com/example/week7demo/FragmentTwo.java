package com.example.week7demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentTwo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageView imageView2;
    private TextView textView2;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentTwo() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentTwo newInstance() {
        FragmentTwo fragment = new FragmentTwo();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_two, container, false);
        imageView2 = v.findViewById(R.id.imageView_2);
        textView2 = v.findViewById(R.id.textView_2);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        textView2.setText(mParam2);
        GetImageAsyncTask getImageAsyncTask = new GetImageAsyncTask(imageView2);
        getImageAsyncTask.execute(mParam1);
    }
}

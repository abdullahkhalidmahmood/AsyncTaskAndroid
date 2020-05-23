package com.example.week7demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class FragmentOne extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    private ArrayList<GameModel> gameModels = new ArrayList<>();

    private int count = 0;
    private GameModel gameModelData;
    private BufferedReader bufferedReader = null;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentOne() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentOne newInstance() {
        FragmentOne fragment = new FragmentOne();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1); // Hold the URL for the image
            mParam2 = getArguments().getString(ARG_PARAM2); // text to be displayed on textview above image
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one, container, false);

        //TODO: file handling // get all data from txt and add that to arraylist

        //loadTextFileFromAsset();
        gameModels.add(new GameModel("FPS", "text", "www.google.com", "0", R.drawable.arma_thumb));
        gameModels.add(new GameModel("RPG", "text2", "www.google.com", "1", R.drawable.call_of_duty_thumb));
        gameModels.add(new GameModel("FPS", "text3", "www.google.com", "2", R.drawable.divinity_thumb));


        ArrayList<Boolean> arrayList = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("games_items.txt"))){
            boolean sCurrentLine;
            while ((sCurrentLine = bufferedReader.readLine() !=null)){
                arrayList.add(sCurrentLine);
            }
        }catch (IOException e){
            e.printStackTrace();
        }



        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), gameModels);
        recyclerView = v.findViewById(R.id.recyclerView);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        Toast.makeText(getContext(), String.valueOf(gameModels.size()), Toast.LENGTH_SHORT).show();
        return v;
    }

  /*  public String loadTextFileFromAsset(){
        String content = null;
        try{
            InputStream inputStream = getContext().getAssets().open("games_items.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            content = new String(buffer,"UTF-8");
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
        return content;
    }*/


}

package com.example.week7demo;

import android.graphics.ColorSpace;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FragmentOne extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    private ArrayList<GameModel> gameModels = new ArrayList<>();


    private GameModel gameModelData;
    private int countIterations = 0;
    private BufferedReader reader = null;
    InputStreamReader inputStreamReader;


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






        recyclerView = v.findViewById(R.id.recyclerView);


        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        getDataFromTheFile();

        return v;
    }


    private void getDataFromTheFile(){
        try {
            inputStreamReader = new InputStreamReader(getContext().getAssets().open("games_items.txt"));
            reader = new BufferedReader(inputStreamReader);
            gameModelData= new GameModel();

            String line;
            // conventional way of checking in while loop
            while((line = reader.readLine())!= null ){
                // trim the line after the ':' to extract and remove spaces
                line = line.substring(line.indexOf(':') + 1).trim();
                // 0 meaning its on the first line

                if(countIterations ==0){
                        if(line.contains("First-Person Shooter")){

                            gameModelData.setCategory( line );
                        }else {

                        }

                        // 1 meaning its on the second line
                    } else if(countIterations ==1){
                        ////gameModelData.setImage(line); 
                        //int id = getResources().getIdentifier(line, "drawable", getContext().getPackageName());
                        gameModelData.setImage(line);

                    } else if(countIterations ==2){
                        gameModelData.setTitle( line );
                        // 3 is the date
                    } else if(countIterations ==3){
                        gameModelData.setWebsite( line );
                    }
                    else if(countIterations ==4){
                        gameModelData.setScore( line );
                    }
                    countIterations++;

                    if((countIterations!=0) && (countIterations%5 == 0)){
                        Constants.fpsList.add(gameModelData);

                        gameModelData = new GameModel();
                        countIterations = 0;


                    }


                }



            setUpArrayList( Constants.fpsList);

        }
        catch (IOException e){
            Toast.makeText(getContext(), "THE TEXT FILE THREW AN EXCEPTION", Toast.LENGTH_SHORT).show();
            Log.e("TEXT FILE THREW ERROR", String.valueOf(e.getCause()));
        }
        finally {
            if (reader != null) {
                try {
                    inputStreamReader.close();
                    reader.close();

                }catch (IOException e){
                    Toast.makeText(getContext(), "STREAM TO THE FILE THREW AN EXCEPTION ON CLOSING", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void setUpArrayList(ArrayList<GameModel> gameModels) {

        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), gameModels);
        recyclerView.setAdapter(recyclerViewAdapter);
        Log.i("size",String.valueOf(gameModels.size()));
        Toast.makeText(getContext(), String.valueOf(gameModels.size()), Toast.LENGTH_SHORT).show();
    }


/*    public String loadTextFileFromAsset() {
        try {
            txtFileContent = new ArrayList<GameModel>();
            InputStream inputStream = getContext().getAssets().open(filename);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                bufferedReader.readLine();
                while ((line = bufferedReader.readLine()) != null) {
                    txtFileContent.add(gameModelData);
                }
                inputStream.close();
            } else {
                System.out.println("It's the assets");
            }
        } catch (IOException e) {
            System.out.println("Not able to read file correctly");
        }
        return null;
    }*/
    }

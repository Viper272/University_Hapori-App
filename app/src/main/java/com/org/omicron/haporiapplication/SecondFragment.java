package com.org.omicron.haporiapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.org.omicron.haporiapplication.databinding.FragmentSecondBinding;

import java.util.ArrayList;

import static com.org.omicron.haporiapplication.AnalyticsHandler.*;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private ArrayList<Spinner> spinnerList;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        spinnerList = new ArrayList<>();

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Already have navigation. This button should enter the app
//        binding.submitAnswers.setOnClickListener(view1 -> NavHostFragment.findNavController(SecondFragment.this)
//                .navigate(R.id.action_SecondFragment_to_FirstFragment));

        binding.submitAnswers.setOnClickListener(view1 -> {
            //Submit answers to local cache/analytics handler
                submitAnswers();
            //Enter app
            NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_categoryScrollFragment);
        });

        createSpinner(view, R.id.spinner_forWho, R.array.array_forWho);
        createSpinner(view, R.id.spinner_age, R.array.array_age);
        createSpinner(view, R.id.spinner_gender, R.array.array_gender);
        createSpinner(view, R.id.spinner_ethnicity, R.array.array_ethnicity);
        createSpinner(view, R.id.spinner_category, R.array.array_category);



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void createSpinner(View v, int s,  int array){
        Spinner spinner = v.findViewById(s);
        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                array, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinnerList.add(spinner);
    }

    //Submit answers to cache/database
    private void submitAnswers(){
        IntroAnswerPacket packet = (IntroAnswerPacket)CreateAnalyticsPacket(Type.INTRO_ANSWER);
        packet.setWho(spinnerList.get(0).getSelectedItem().toString());
        Log.d("packet", packet.getWho());
    }

}
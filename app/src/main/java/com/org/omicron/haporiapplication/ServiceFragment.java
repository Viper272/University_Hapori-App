package com.org.omicron.haporiapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.appcompat.app.ActionBar;

import com.org.omicron.haporiapplication.databinding.ServicePageBinding;

public class ServiceFragment extends Fragment{
    private ServicePageBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = ServicePageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonDial.setOnClickListener(v -> {
            Intent phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "1234567890"));
            startActivity(phone);
        });

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Lifeline Aotearoa");

//        binding.buttonEmail.setOnClickListener(v1 ->{
//            Intent email = new Intent(Intent.CATEGORY_APP_EMAIL, );
//            startActivity(email);
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

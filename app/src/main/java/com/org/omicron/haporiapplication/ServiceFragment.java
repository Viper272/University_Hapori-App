package com.org.omicron.haporiapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.appcompat.app.ActionBar;

import com.org.omicron.haporiapplication.databinding.ServicePageBinding;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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
            Map<String, String> phoneMap = new HashMap();
            phoneMap.put("Test1", "1234567890");
            phoneMap.put("Test2", "0987654321");
            phoneMap.put("Test3", "1029384756");

            PopupMenu popup = new PopupMenu(getContext(), v);
            for(String key : phoneMap.keySet()) {
                Intent phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneMap.get(key)));
                popup.getMenu().add(key).setIntent(phone);
            }
            popup.show();

        });

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Lifeline Aotearoa");

        binding.buttonEmail.setOnClickListener(v1 ->{
            Intent email = new Intent(Intent.ACTION_SENDTO);
            String uriText = "mailto:test@gmail.com";
            Uri uri = Uri.parse(uriText);
            email.setData(uri);
            startActivity(Intent.createChooser(email, "Send email"));
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showMenu(View v, int menuRes) {

    }
}

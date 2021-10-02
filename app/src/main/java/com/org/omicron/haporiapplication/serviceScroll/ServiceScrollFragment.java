package com.org.omicron.haporiapplication.serviceScroll;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.org.omicron.haporiapplication.R;
import com.org.omicron.haporiapplication.databinding.FragmentServiceScrollBinding;
import com.org.omicron.haporiapplication.restAPI.RetrofitClient;
import com.org.omicron.haporiapplication.restAPI.models.Services;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceScrollFragment extends Fragment {

    private ServiceScrollAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<ServicesListItem> serviceList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return FragmentServiceScrollBinding.inflate(inflater, container, false).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) this.getActivity().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

//        adapter = new ServiceScrollAdapter(serviceList);
//        recyclerView.setAdapter(adapter);

        Call<List<ServicesListItem>> call = RetrofitClient.getInstance().getApi().getAllServices();
        call.enqueue(new Callback<List<ServicesListItem>>() {
            @Override
            public void onResponse(Call<List<ServicesListItem>> call, Response<List<ServicesListItem>> response) {
                List<ServicesListItem> allServices = response.body();
                adapter = new ServiceScrollAdapter(allServices);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ServicesListItem>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });


//        for (int i = 0; i < 100; i++) {
//            serviceList.add(new ServicesListItem(String.format("Service %1$d", i), String.format("Default Short Description for service %1$d", i)));
//        }

        adapter.notifyDataSetChanged();
    }
}
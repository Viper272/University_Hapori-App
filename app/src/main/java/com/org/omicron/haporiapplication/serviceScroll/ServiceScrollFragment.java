package com.org.omicron.haporiapplication.serviceScroll;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.org.omicron.haporiapplication.R;
import com.org.omicron.haporiapplication.categoryScroll.CategoryScrollFragment;
import com.org.omicron.haporiapplication.categoryScroll.RecyclerItemClickListener;
import com.org.omicron.haporiapplication.databinding.FragmentServiceScrollBinding;

import java.util.ArrayList;

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

        adapter = new ServiceScrollAdapter(serviceList);
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < 100; i++) {
            serviceList.add(new ServicesListItem(String.format("Service %1$d", i), String.format("Default Short Description for service %1$d", i)));
        }

        adapter.notifyDataSetChanged();


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.d("onItemClick", String.valueOf(position));
                        NavHostFragment.findNavController(ServiceScrollFragment.this).navigate(R.id.action_serviceScrollFragment_to_serviceFragment);
                    }

                    @Override public void onLongItemClick(View view, int position) {

                    }
                })
        );



    }
}
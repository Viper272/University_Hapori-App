package com.org.omicron.haporiapplication.serviceScroll;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.org.omicron.haporiapplication.restAPI.RetrofitClient;
import com.org.omicron.haporiapplication.restAPI.models.DBResponse;
import com.org.omicron.haporiapplication.restAPI.models.DBServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceScrollFragment extends Fragment {

    private ServiceScrollAdapter adapter;
    private RecyclerView recyclerView;
    private String filterCategory;
    private List<DBServices> servicesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return FragmentServiceScrollBinding.inflate(inflater, container, false).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filterCategory = getArguments().getString("category");
        Log.i("Filter Category = ", filterCategory);

        recyclerView = (RecyclerView) this.getActivity().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        Call<DBResponse> call = RetrofitClient.getInstance().getApi().getAllServices();
        call.enqueue(new Callback<DBResponse>() {
            @Override
            public void onResponse(Call<DBResponse> call, Response<DBResponse> response) {
                DBResponse dbResponse = response.body();

                if(dbResponse.isSuccess()) {
                    servicesList = dbResponse.getData();
                    adapter = new ServiceScrollAdapter(servicesList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DBResponse> call, Throwable t) {
                servicesList = new ArrayList<>();
                for(int i = 0; i < 10; i++){
                    servicesList.add(new DBServices("No Connection", ""));
                }
                adapter = new ServiceScrollAdapter(servicesList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity().getApplicationContext(), "An error has occurred", Toast.LENGTH_LONG).show();
                Log.e("Retro Error", t.toString());
            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.d("onItemClick", String.valueOf(position));
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("service", servicesList.get(position));
                        NavHostFragment.findNavController(ServiceScrollFragment.this).navigate(R.id.action_serviceScrollFragment_to_serviceFragment, bundle);
                    }

                    @Override public void onLongItemClick(View view, int position) {

                    }
                })
        );

    }
}
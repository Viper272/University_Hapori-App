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
import com.org.omicron.haporiapplication.restAPI.API;
import com.org.omicron.haporiapplication.restAPI.RetrofitClient;
import com.org.omicron.haporiapplication.restAPI.models.DBCategory;
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
    private DBCategory filterCategory;
    private List<DBServices> servicesList;

    API server;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        server = RetrofitClient.getInstance().getApi();
        // Inflate the layout for this fragment
        return FragmentServiceScrollBinding.inflate(inflater, container, false).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filterCategory = getArguments().getParcelable("category");
        Log.i("Filter Category = ", filterCategory.getCategoryName());

        recyclerView = (RecyclerView) this.getActivity().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        Call<DBResponse<DBServices>> call;

        if(filterCategory.getCategoryName().equals("All")) {
            call = server.getAllServices();
        } else {
            call = server.getServiceByCategory(filterCategory.getCategoryName());
        }

        call.enqueue(new Callback<DBResponse<DBServices>>() {
            @Override
            public void onResponse(Call<DBResponse<DBServices>> call, Response<DBResponse<DBServices>> response) {
                DBResponse dbResponse = response.body();

                if(dbResponse == null) {
                    onFailure(call, new Throwable());
                    return;
                }

                if(dbResponse.isSuccess()) {
                    servicesList = dbResponse.getData();
                    adapter = new ServiceScrollAdapter(servicesList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DBResponse<DBServices>> call, Throwable t) {
                servicesList = new ArrayList<>();
                servicesList.add(new DBServices("No Connection1", "First", "This is the first service in the offline list"));
                servicesList.add(new DBServices("No Connection2", "Second", "This is the second service in the offline list"));
                servicesList.add(new DBServices("No Connection3", "Third", "This is the third service in the offline list"));
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
package com.org.omicron.haporiapplication.categoryScroll;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.org.omicron.haporiapplication.MainActivity;
import com.org.omicron.haporiapplication.R;
import com.org.omicron.haporiapplication.databinding.FragmentCategoryScrollBinding;
import com.org.omicron.haporiapplication.restAPI.RetrofitClient;
import com.org.omicron.haporiapplication.restAPI.models.DBCategory;
import com.org.omicron.haporiapplication.restAPI.models.DBResponse;
import com.org.omicron.haporiapplication.restAPI.models.DBServices;
import com.org.omicron.haporiapplication.serviceScroll.ServiceScrollAdapter;

import androidx.navigation.fragment.NavHostFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryScrollFragment extends Fragment {
    private CategoryScrollAdapter adapter;
    private RecyclerView recyclerView;
    private List<DBCategory> categoryList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return FragmentCategoryScrollBinding.inflate(inflater, container, false).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = this.getActivity().findViewById(R.id.categoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        Call<DBResponse<DBCategory>> call = RetrofitClient.getInstance().getApi().getAllCategories();
        call.enqueue(new Callback<DBResponse<DBCategory>>() {
            @Override
            public void onResponse(Call<DBResponse<DBCategory>> call, Response<DBResponse<DBCategory>> response) {
                DBResponse dbResponse = response.body();

                if(dbResponse.isSuccess()) {
                    categoryList = dbResponse.getData();
                    adapter = new CategoryScrollAdapter(categoryList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DBResponse<DBCategory>> call, Throwable t) {
                categoryList = new ArrayList<>();
                for(int i = 0; i < 10; i++){
                    categoryList.add(new DBCategory("No Connection"));
                }
                adapter = new CategoryScrollAdapter(categoryList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity().getApplicationContext(), "An error has occurred", Toast.LENGTH_LONG).show();
                Log.e("Retro Error", t.toString());
            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.d("onItemClick", categoryList.get(position).getCategoryName());
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("category", categoryList.get(position));
                        NavHostFragment.findNavController(CategoryScrollFragment.this).navigate(R.id.action_categoryScrollFragment_to_serviceScrollFragment, bundle);
                    }

                    @Override public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }

    public static Drawable GetImageFromUrl(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, null);
            return d;
        } catch (Exception e) {
            return null;
        }
    }

}

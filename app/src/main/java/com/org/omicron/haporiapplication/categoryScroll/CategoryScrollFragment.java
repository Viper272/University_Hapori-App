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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.org.omicron.haporiapplication.MainActivity;
import com.org.omicron.haporiapplication.R;
import com.org.omicron.haporiapplication.SecondFragment;
import com.org.omicron.haporiapplication.databinding.FragmentCategoryScrollBinding;
import androidx.navigation.fragment.NavHostFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class CategoryScrollFragment extends Fragment {
    private CategoryScrollAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<CategoryListItem> categoryList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return FragmentCategoryScrollBinding.inflate(inflater, container, false).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) this.getActivity().findViewById(R.id.categoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new CategoryScrollAdapter(categoryList, this);
        recyclerView.setAdapter(adapter);

        if(categoryList.isEmpty()){

            //Retrieve categories from database
            //Access MainActivity task to run network process
            String JSON = ((MainActivity)getActivity()).getCategories();
            Log.i("CategoryJSON", JSON);
            //Need to use JSON here
            JSONArray JSON_Categories = new JSONArray();

            //Before database is setup
            if(JSON_Categories.length() == 0){
                for (int i = 0; i < 8; i++) {
                    categoryList.add(new CategoryListItem(String.format("Category %1$d", i)));
                }
            }

            //Iterate and create category views
            for(int i = 0; i < JSON_Categories.length(); i++){
                try {
                    JSONObject category = JSON_Categories.getJSONObject(i);

                    String title = (String)category.get("title");
                    String URL = (String)category.get("logo");

                    Drawable logo = GetImageFromUrl(URL);
                    categoryList.add(new CategoryListItem(String.format("Category %1$d", title), logo));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            adapter.notifyDataSetChanged();
        }

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.d("onItemClick", String.valueOf(position));
                        Bundle bundle = new Bundle();
                        bundle.putString("category", categoryList.get(position).getCategoryName());
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

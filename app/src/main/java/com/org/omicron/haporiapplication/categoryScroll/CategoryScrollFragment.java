package com.org.omicron.haporiapplication.categoryScroll;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.org.omicron.haporiapplication.R;
import com.org.omicron.haporiapplication.databinding.FragmentCategoryScrollBinding;

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

        recyclerView = (RecyclerView) this.getActivity().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new CategoryScrollAdapter(categoryList);
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < 8; i++) {
            categoryList.add(new CategoryListItem(String.format("Category %1$d", i)));
        }

        adapter.notifyDataSetChanged();
    }
}

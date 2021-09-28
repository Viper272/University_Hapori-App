package com.org.omicron.haporiapplication.categoryScroll;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.org.omicron.haporiapplication.R;

import java.util.List;

public class CategoryScrollAdapter extends RecyclerView.Adapter<CategoryScrollAdapter.CategoryHolder>{
    private final List<CategoryListItem> items;
    private Fragment fragment;

//    public static CategoryScrollAdapter newInstance(Context context) {
//        List<CategoryListItem> items = new ArrayList<>();
//        String format = context.getString(R.string.item_string);
//        for (int i = 0; i < 100; i++) {
//
//        }
//        return new CategoryScrollAdapter(items);
//    }


    public CategoryScrollAdapter(List<CategoryListItem> items, Fragment fragment) {
        this.items = items;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public CategoryScrollAdapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_category_scroll, parent, false);
        return new CategoryScrollAdapter.CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryScrollAdapter.CategoryHolder holder, int position) {
        CategoryListItem item = items.get(position);
        holder.setCategoryName(item.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }




    public static final class CategoryHolder extends RecyclerView.ViewHolder {
        private TextView textView_Name;

        private CategoryHolder(View itemView) {
            super(itemView);

            textView_Name = itemView.findViewById(R.id.textView_CategoryName);
        }

        public void setCategoryName(String name) {
            textView_Name.setText(name);
        }
    }

}

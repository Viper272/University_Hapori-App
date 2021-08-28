package com.org.omicron.haporiapplication.serviceScroll;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.org.omicron.haporiapplication.R;

import java.util.List;

public class ServiceScrollAdapter extends RecyclerView.Adapter<ServiceScrollAdapter.ServiceHolder>{

    private final List<ServicesListItem> items;

//    public static ServiceScrollAdapter newInstance(Context context) {
//        List<ServicesListItem> items = new ArrayList<>();
//        String format = context.getString(R.string.item_string);
//        for (int i = 0; i < 100; i++) {
//
//        }
//        return new ServiceScrollAdapter(items);
//    }


    public ServiceScrollAdapter(List<ServicesListItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_service_scroll, parent, false);
        return new ServiceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceHolder holder, int position) {
        ServicesListItem item = items.get(position);

        holder.setServiceName(item.getServiceName());
        holder.setServiceShortDesc(item.getShortDesc());
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    public static final class ServiceHolder extends RecyclerView.ViewHolder {
        private TextView textView_Name;
        private TextView textView_ShortDesc;

        private ServiceHolder(View itemView) {
            super(itemView);

            textView_Name = itemView.findViewById(R.id.textView_ServiceName);
            textView_ShortDesc = itemView.findViewById(R.id.textView_ServiceShortDesc);
        }

        public void setServiceName(String name) {
            textView_Name.setText(name);
        }

        public void setServiceShortDesc(String desc) {
            textView_ShortDesc.setText(desc);
        }
    }
}

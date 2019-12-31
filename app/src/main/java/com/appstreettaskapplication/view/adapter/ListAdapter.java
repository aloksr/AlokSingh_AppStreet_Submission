package com.appstreettaskapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.appstreettaskapplication.R;
import com.appstreettaskapplication.model.ListResponseModel;
import com.appstreettaskapplication.view.callbacks.OnItemCLickListener;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.NewsViewHolder> {

    Context context;
    ArrayList<ListResponseModel> listModels;
    private OnItemCLickListener mListener;

    public ListAdapter(Context context, ArrayList<ListResponseModel> articles) {
        this.context = context;
        this.listModels = articles;
    }

    public void setOnItemClickListener(OnItemCLickListener listener) {
        mListener = listener;

    }

    public ListResponseModel getItemAt(int pos) {
        if (listModels != null && listModels.size() > pos) {
            return listModels.get(pos);
        }
        return null;
    }

    @NonNull
    @Override
    public ListAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new  NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.NewsViewHolder holder, int position) {
        holder.bindData(listModels.get(position));

    }

    @Override
    public int getItemCount() {
        return listModels==null ? 0 : listModels.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName;
        TextView tvUserName;
        ImageView ivAvatarImage;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatarImage = itemView.findViewById(R.id.ivAvatarImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(view, getAdapterPosition());
            }
        }

        public void bindData(ListResponseModel listResponseModel) {
            tvName.setText(listResponseModel.getName());
            tvUserName.setText(listResponseModel.getUsername());
        }
    }
}

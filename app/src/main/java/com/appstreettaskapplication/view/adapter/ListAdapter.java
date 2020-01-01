package com.appstreettaskapplication.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.appstreettaskapplication.R;
import com.appstreettaskapplication.imageutils.DownloadImageTask;
import com.appstreettaskapplication.imageutils.ImagesCache;
import com.appstreettaskapplication.model.ListResponseModel;
import com.appstreettaskapplication.view.callbacks.OnItemCLickListener;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private List<ListResponseModel> listModels;
    private OnItemCLickListener mListener;
    private ImagesCache cache;

    public ListAdapter(Context context) {
        this.context = context;
        cache = ImagesCache.getInstance();
        cache.initializeCache();
    }

    public void setOnItemClickListener(OnItemCLickListener listener) {
        mListener = listener;

    }

    public void setData(List<ListResponseModel> articles){
        this.listModels = articles;
        notifyDataSetChanged();
    }

    public ListResponseModel getItemAt(int pos) {
        if (listModels != null && listModels.size() > pos) {
            return listModels.get(pos);
        }
        return null;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        holder.bindData(listModels.get(position));

    }

    @Override
    public int getItemCount() {
        return listModels == null ? 0 : listModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvName;
        private TextView tvUserName;
        private ImageView ivAvatarImage;
        private View item_container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatarImage = itemView.findViewById(R.id.ivAvatarImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            item_container = itemView.findViewById(R.id.item_container);
            item_container.setOnClickListener(this);

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
            Bitmap bm = cache.getImageFromWarehouse(listResponseModel.getAvatar());
            if(bm != null)
            {
                ivAvatarImage.setImageBitmap(bm);
            } else
            {
                ivAvatarImage.setImageBitmap(null);
                DownloadImageTask imgTask = new DownloadImageTask(ListAdapter.this, 300, 300);
                imgTask.execute(listResponseModel.getAvatar());
            }
        }
    }
}

package cn.picker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.picker.R;
import cn.picker.bean.ImageFolderBean;

/**
 * Created by Fire on 2017/4/10.
 */

public class FolderListAdapter extends RecyclerView.Adapter<FolderListAdapter.ViewHolder> implements OnClickListener {
    private Context context;
    private List<ImageFolderBean> list;
    private OnRecyclerViewItemClickListener listener;

    public FolderListAdapter(Context context, List<ImageFolderBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnRecyclerViewItemClickListener {
        void onRecyclerViewItemClick(View v, int position);
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.folder_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position).isSelected()) {
            holder.ivPhotoFilterChecked.setVisibility(View.VISIBLE);
        } else {
            holder.ivPhotoFilterChecked.setVisibility(View.GONE);
        }
        holder.tvAlbumName.setText(list.get(position).getFolderName());
        Glide.with(context).load(list.get(position).getImagePaths().get(0)).into(holder.ivFolderThumb);
        String string = context.getResources().getString(R.string.album_photo_number);
        String format = String.format(string, list.get(position).getImageCounts());
        holder.tvAlbumPhotoNumber.setText(format);
        holder.rootView.setOnClickListener(this);
        holder.rootView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onRecyclerViewItemClick(v, (int) v.getTag());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private View rootView;
        private ImageView ivFolderThumb;
        private TextView tvAlbumName;
        private TextView tvAlbumPhotoNumber;
        private ImageView ivPhotoFilterChecked;

        ViewHolder(View view) {
            super(view);
            rootView = view;
            ivFolderThumb = view.findViewById(R.id.iv_folder_thumb);
            tvAlbumName = view.findViewById(R.id.tv_album_name);
            tvAlbumPhotoNumber = view.findViewById(R.id.tv_album_photo_number);
            ivPhotoFilterChecked = view.findViewById(R.id.ivCheck);
        }
    }
}

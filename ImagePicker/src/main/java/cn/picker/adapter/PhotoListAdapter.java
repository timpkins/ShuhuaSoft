package cn.picker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.picker.R;
import cn.picker.view.SquareImageView;


/**
 * Created by Fire on 2017/4/8.
 */

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ViewHolder> implements View.OnClickListener {
    private static final String TAG = "PhotoListAdapter";
    private List<String> list;
    private Context context;
    private OnRecyclerViewItemClickListener listener;
    private static final int TYPE_CAMERA = 0x01;
    private static final int TYPE_IMAGE = 0x02;

    public PhotoListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnRecyclerViewItemClickListener {
        void onRecyclerViewItemClick(View v, int position);
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_IMAGE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.camerasdk_list_item_camera, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_CAMERA : TYPE_IMAGE;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_CAMERA) {
            holder.rootView.setOnClickListener(this);
            holder.rootView.setTag(position);
        } else {
            Glide.with(context).load(list.get(position)).into(holder.ivPhotoThumb);
            holder.rootView.setOnClickListener(this);
            holder.rootView.setTag(position);
        }
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
        private SquareImageView ivPhotoThumb;

        ViewHolder(View view) {
            super(view);
            rootView = view;
            ivPhotoThumb = view.findViewById(R.id.iv_photo_thumb);
        }
    }
}

package com.example.messagepage.recycler;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.res.*;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messagepage.MainActivity;
import com.example.messagepage.R;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<TestData> mDataset = new ArrayList<>();
    private IOnItemClickListener mItemClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvname;
        private TextView tvmessage;
        private TextView tvtime;
        private ImageView im_source;
        private View contentView;


        public MyViewHolder(View v) {//view 来自于viewholder的创建，onCreateViewHolder中的inflate将资源布局文件转换成一个view
            super(v);
            contentView = v;
            tvname = v.findViewById(R.id.tv_name);
            tvmessage = v.findViewById(R.id.tv_message);
            tvtime = v.findViewById(R.id.tv_time);
            im_source=v.findViewById(R.id.im_head);
        }
        public static int getResId(String variableName, Class<?> c) {
            try {
                Field idField = c.getDeclaredField(variableName);
                return idField.getInt(idField);
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }
        public void onBind(int position, TestData data) {
            tvname.setText(data.name);
            tvmessage.setText(data.message);
            tvtime.setText(data.time);
            int id = MyViewHolder.getResId(data.im_source, R.drawable.class);
            im_source.setImageResource(id);
            //无法实现动态画图，getResources()不能用，不知道如何解决
        }
        //initView中有：mAdapter.setOnItemClickListener(this);
        //在Myadapt内部有个维持点击函数的对象private IOnItemClickListener mItemClickListener;
        public void setOnClickListener(View.OnClickListener listener) {
            if (listener != null) {
                contentView.setOnClickListener(listener);
            }
        }

        public void setOnLongClickListener(View.OnLongClickListener listener) {
            if (listener != null) {
                contentView.setOnLongClickListener(listener);
            }
        }
    }


    public MyAdapter(List<TestData> myDataset) {
        mDataset.addAll(myDataset);
    }

    public void setOnItemClickListener(IOnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void addData(int position, TestData data) {
        mDataset.add(position, data);
        notifyItemInserted(position);
        if (position != mDataset.size()) {
            //刷新改变位置item下方的所有Item的位置,避免索引错乱
            notifyItemRangeChanged(position, mDataset.size() - position);
        }
    }

    public void removeData(int position) {
        if (null != mDataset && mDataset.size() > position) {
            mDataset.remove(position);
            notifyItemRemoved(position);
            if (position != mDataset.size()) {
                //刷新改变位置item下方的所有Item的位置,避免索引错乱
                notifyItemRangeChanged(position, mDataset.size() - position);
            }
        }
    }

    @Override      //view holder是对单条数据选项的封装
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.onBind(position, mDataset.get(position));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemCLick(position, mDataset.get(position));
                }
            }
        });
        holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {//点击事件作用于contentview
                if (mItemClickListener != null) {
                    mItemClickListener.onItemLongCLick(position, mDataset.get(position));
                }
                return false;
            }

        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface IOnItemClickListener {

        void onItemCLick(int position, TestData data);

        void onItemLongCLick(int position, TestData data);
    }
}

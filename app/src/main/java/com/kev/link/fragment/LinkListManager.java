package com.kev.link.fragment;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kev.link.R;
import com.kev.link.adapter.CommonRecyclerViewAdapter;
import com.kev.link.adapter.CommonRecyclerViewHolder;
import com.kev.link.adapter.DividerGridItemDecoration;
import com.kev.link.inf.LinkCallback;

import java.util.ArrayList;
import java.util.List;

public class LinkListManager<T> {

    private RecyclerView rv;

    private List<T> data = new ArrayList<>();//总数据
    private List<T> dataTemp = new ArrayList<>();//需要展示的数据

    private CommonRecyclerViewAdapter<T> adapter;
    private int selectIndex = -1;
    private LinkCallback<T> callback;
    private Context context;
    private String jsonArray;
    private String mTag = "";


    public LinkListManager(Context context, String jsonArray, final LinkCallback<T> callback) {
        this.context = context;
        this.jsonArray = jsonArray;
        this.callback = callback;
    }

    public void init(RecyclerView recyclerView, String mTag, MListener<T> listener) {
        this.mTag = mTag;
        this.listener = listener;
        if (recyclerView == null) {
            throw new RuntimeException("recycleView can not be null");
        }
        rv = recyclerView;
        rv.setLayoutManager(new LinearLayoutManager(context));
        //分割线
        rv.addItemDecoration(new DividerGridItemDecoration(context, DividerGridItemDecoration.VERTICAL_LIST));
        rv.setItemAnimator(new DefaultItemAnimator());


        adapter = new CommonRecyclerViewAdapter<T>(context, dataTemp) {
            @Override
            public void convert(CommonRecyclerViewHolder h, T entity, final int position) {
                TextView tvAddress = h.getView(R.id.tv_name);
                tvAddress.setText(callback.getName(entity));
                ImageView ivArrow = h.getView(R.id.iv_arrow);
                if (callback.getList(entity) == null ||
                        callback.getList(entity).size() == 0) {
                    ivArrow.setVisibility(View.GONE);
                    ivArrow.setOnClickListener(null);
                } else {
                    ivArrow.setVisibility(View.VISIBLE);
                    ivArrow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            nextLevel(position);
                        }
                    });
                }
                if (position == selectIndex) {
                    tvAddress.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                } else {
                    tvAddress.setTextColor(context.getResources().getColor(R.color.colorGray));
                }
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_area;
            }
        };
        adapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                selectIndex = position;
                callback.onSelect(dataTemp.get(position));
                adapter.notifyDataSetChanged();
            }
        });
        rv.setAdapter(adapter);

        if (!TextUtils.isEmpty(jsonArray)) {
            data = fromJsonList(jsonArray, callback.getClassType());
            dataTemp.addAll(data);
            if (listener != null) {
                listener.getAllData(mTag, data);
            }
        } else {
            if (listener != null) {
                listener.getAllData(mTag, new ArrayList<T>());
            }
        }
    }

    private ArrayList<T> fromJsonList(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        Gson gson = new Gson();
        for (final JsonElement elem : array) {
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;
    }


    private void nextLevel(int position) {
        List<T> mData = new ArrayList<>(dataTemp);

        if (callback.getList(mData.get(position)) == null
                || callback.getList(mData.get(position)).size() == 0) {
            return;
        }
        List<T> mDataSub = new ArrayList<>(callback.getList(mData.get(position)));
        if (mDataSub.size() > 0) {
            if (listener != null) {
                listener.moveToNextLevel(mTag, mData,position, mDataSub);
            }
        }
    }

    public void clear() {
        dataTemp.clear();
        adapter.notifyDataSetChanged();
    }

    public void refresh(List<T> mDataSub) {
        callback.onSelect(null);
        selectIndex = -1;
        //更新数据，刷新列表
        dataTemp.clear();
        dataTemp.addAll(mDataSub);
        adapter.notifyDataSetChanged();
    }

    public void showCurData() {
        dataTemp.clear();
        adapter.notifyDataSetChanged();
    }

    private MListener<T> listener;


    public interface MListener<T> {
        void moveToNextLevel(String mTag, List<T> oldData, int position, List<T> curData);

        void getAllData(String mTag, List<T> data);
    }


    public String getmTag() {
        return mTag;
    }
}

package com.kev.link.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class LinkListFragment<T> extends Fragment implements View.OnClickListener {

    public final static String EXTRA_DATA = "data";//数据，jsonArray格式 字符串
    public final static String EXTRA_FIRST_LEVEL_NAME = "firstLevelName";//第一层级名称

    private TextView tvBackLevel;
    private TextView tvCurLevel;
    private TextView tvFlag;
    private RecyclerView rv;

    private List<T> data = new ArrayList<>();//总数据
    private List<T> dataTemp = new ArrayList<>();//需要展示的数据
    private List<List<T>> historyData = new ArrayList<>();//记录每一层级的数据,返回时使用
    private List<String[]> mName = new ArrayList<>();//记录点击的某一项的名字,0-上一项，1-当前项
    private CommonRecyclerViewAdapter<T> adapter;

    private Activity mActivity;
    private LinkCallback<T> callback;
    private String firstLevelName;
    private int selectIndex = -1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_link_list, null);
        initView(view);
        initData();
        return view;
    }


    public static LinkListFragment newInstance(String jsonArray, String firstLevelName) {
        LinkListFragment mFragment = new LinkListFragment();

        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_DATA, jsonArray);
        bundle.putString(EXTRA_FIRST_LEVEL_NAME, firstLevelName);

        mFragment.setArguments(bundle);
        return mFragment;
    }

    private void initView(View v) {
        tvBackLevel = v.findViewById(R.id.tv_back_level);
        tvCurLevel = v.findViewById(R.id.tv_cur_level);
        tvFlag = v.findViewById(R.id.tv_flag);
        rv = v.findViewById(R.id.rv);

        tvFlag.setVisibility(View.GONE);
        tvBackLevel.setOnClickListener(this);

        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        //分割线
        rv.addItemDecoration(new DividerGridItemDecoration(mActivity, DividerGridItemDecoration.VERTICAL_LIST));
        rv.setItemAnimator(new DefaultItemAnimator());

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

    private void initData() {
        assert getArguments() != null;
        String jsonArray = getArguments().getString(EXTRA_DATA);
        firstLevelName = getArguments().getString(EXTRA_FIRST_LEVEL_NAME);
        if (TextUtils.isEmpty(jsonArray)) {
            return;
        }

        data = fromJsonList(jsonArray, callback.getClassType());
//        data = new Gson().fromJson(jsonArray, new TypeToken<List<T>>() {
//        }.getType());
        dataTemp.addAll(data);

        historyData.add(data);
        mName.add(new String[]{firstLevelName, ""});

        adapter = new CommonRecyclerViewAdapter<T>(mActivity, dataTemp) {
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
                    tvAddress.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                } else {
                    tvAddress.setTextColor(mActivity.getResources().getColor(R.color.colorGray));
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
    }

    private void nextLevel(int position) {
        List<T> mData = new ArrayList<>(dataTemp);

        if (callback.getList(mData.get(position)) == null
                || callback.getList(mData.get(position)).size() == 0) {
            return;
        }
        List<T> mDataSub = new ArrayList<>(callback.getList(mData.get(position)));
        if (mDataSub.size() > 0) {
            callback.onSelect(null);
            selectIndex = -1;
            //更新数据，刷新列表
            dataTemp.clear();
            dataTemp.addAll(mDataSub);
            historyData.add(mDataSub);
            adapter.notifyDataSetChanged();

            //显示层级名称，并记录
            String backName;
            String curName = callback.getName(mData.get(position));
            if (historyData.size() <= 1) {
                backName = firstLevelName;
            } else {
                tvFlag.setVisibility(View.VISIBLE);
                backName = TextUtils.isEmpty(mName.get(mName.size() - 1)[1]) ? firstLevelName
                        : mName.get(mName.size() - 1)[1];
            }
            tvBackLevel.setText(backName);
            tvCurLevel.setText(curName);
            mName.add(new String[]{backName, curName});
        }
    }

    /**
     * 是否可以返回
     */
    public boolean allowBack() {
        if (historyData.size() > 1) {
            dataTemp.clear();
            dataTemp.addAll(historyData.get(historyData.size() - 2));//展示记录的倒数第二个数据集
            historyData.remove(historyData.size() - 1);//移除掉倒数第一个数据集
            adapter.notifyDataSetChanged();

            tvCurLevel.setText(mName.get(mName.size() - 2)[1]);
            tvBackLevel.setText(mName.get(mName.size() - 2)[0]);
            mName.remove(mName.size() - 1);
            if (mName.size() == 1) {
                tvFlag.setVisibility(View.GONE);
            }
            return true;
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back_level:
                allowBack();
                break;
            default:
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        if (context instanceof LinkCallback) {
            callback = (LinkCallback<T>) context;
        } else {
            throw new RuntimeException(context.toString() + " must implements LinkCallback");
        }
        super.onAttach(context);
    }


    private void showCurData() {
        dataTemp.clear();
        dataTemp.addAll(historyData.get(historyData.size() - 1));
        adapter.notifyDataSetChanged();
    }

}

package com.kev.link;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kev.link.bean.AreaRes;
import com.kev.link.fragment.LinkListFragment;
import com.kev.link.inf.LinkCallback;

import java.util.List;

public class LinkActivity extends AppCompatActivity implements View.OnClickListener
        , LinkCallback<AreaRes> {

    private LinearLayout llFragment;
    private LinkListFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        findViewById(R.id.btn_1).setOnClickListener(this);

        llFragment = findViewById(R.id.ll_fragment);

        initLinkListFragment();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                llFragment.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (llFragment.getVisibility() == View.VISIBLE) {
            llFragment.setVisibility(View.GONE);
        } else if (llFragment.getVisibility() == View.VISIBLE) {
            llFragment.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }


    /**
     * 添加多级联动fragment
     */
    private void initLinkListFragment() {
        fragment = LinkListFragment.newInstance(AreaRes.jsonArray, "全部");
        getSupportFragmentManager().beginTransaction().add(R.id.ll_fragment,
                fragment).commit();
    }

    @Override
    public Class<AreaRes> getClassType() {
        return AreaRes.class;
    }

    @Override
    public String getName(AreaRes entity) {
        return entity.getCeaName();
    }

    @Override
    public List<AreaRes> getList(AreaRes entity) {
        return entity.getAreaList();
    }

    @Override
    public void onSelect(AreaRes entity) {
        if (entity != null) {
            Toast.makeText(this, "收到选择项：" + entity.getCeaName(), Toast.LENGTH_SHORT).show();
        }
    }


}

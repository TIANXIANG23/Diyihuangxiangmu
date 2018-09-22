package com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.zifragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tianxiang.diyihuangxiangmu.R;
import com.example.tianxiang.diyihuangxiangmu.adapter.InformationAdapter;
import com.example.tianxiang.diyihuangxiangmu.base.BaseActivity;
import com.example.tianxiang.diyihuangxiangmu.base.BaseFragment;
import com.example.tianxiang.diyihuangxiangmu.data.remote.News;
import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsData;
import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsDataRepository2;
import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsLocalDataSource2;
import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsRemoteDataSource2;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.zifragment.webview.WebActivity;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.zifragment.webview.WebPesenter;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.zifragment.webview.Webjie;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

public class TabFragment extends BaseFragment implements TabContract.View {
    public TabContract.Presenter mPresenter;
    private RecyclerView tab_recyclerview;
    private int scorllY;
    private int position;
    private SmartRefreshLayout swipeRefreshLayout;

    private InformationAdapter informationAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            position = savedInstanceState.getInt("position",0);
            scorllY = savedInstanceState.getInt("scrollY",0);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        initView(view);
        TabPresenter tabPresenter = new TabPresenter(NewsDataRepository2.getInstance(NewsRemoteDataSource2.getInstance(),NewsLocalDataSource2.getInstance(getActivity())));
        setPresenter(tabPresenter);
        final String key = getArguments().getString("key");
        mPresenter.sendNewList(key);

        tab_recyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int fistVP = linearLayoutManager.findFirstVisibleItemPosition();
                    View v = linearLayoutManager.findViewByPosition(fistVP);
                    position = fistVP;
                    scorllY = v.getTop();

                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000,false);//传入false表示刷新失败
                //mPresenter.sendNewList(key);
            }
        });
        swipeRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000);//传入false表示加载失败
                //mPresenter.sendNewListTwO("0",sur);
            }
        });
        return view;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
        outState.putInt("scrollY", scorllY);
    }
    @Override
    public void setPresenter(TabContract.Presenter presenter) {
        mPresenter = presenter;
        presenter.attachView(this);
    }

    @Override
    public void sendNewList(final NewsData newsData) {
        for(News newss : newsData.getNewList()){

            if(newss.getLayoutType() == 1){
                ArrayList arrayList = new ArrayList();
                arrayList.add(newss.getImageListThumb().get(0));
                arrayList.add(newss.getImageListThumb().get(0));
                arrayList.add(newss.getImageListThumb().get(0));
                newss.setLayoutType(3);
                newss.setImageListThumb(arrayList);
                break;
            }
        }

        informationAdapter = new InformationAdapter(getContext(), newsData.getNewList());
        tab_recyclerview.setAdapter(informationAdapter);
        informationAdapter.setInformationAdapter(new Webjie() {


            private Intent intent;

            @Override
            public void show(int tag) {
                intent = new Intent(getContext(), WebActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("newsId", newsData.getNewList().get(tag).getNewsId());
                bundle.putString("name",newsData.getNewList().get(tag).getTitle());
                bundle.putString("shi",newsData.getNewList().get(tag).getPublishTime());
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
                Log.e("============333", "show: "+newsData.getNewList().get(tag).getNewsId() );
            }
        });
       scrollToTargetPosition();
    }

    private void initView(View view) {
        tab_recyclerview = (RecyclerView) view.findViewById(R.id.tab_recyclerview);
        swipeRefreshLayout = view.findViewById(R.id.swperefresh);
        tab_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

    }
    private void scrollToTargetPosition(){
        if(position != 0 && scorllY != 0){
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) tab_recyclerview.getLayoutManager();
            linearLayoutManager.scrollToPositionWithOffset(position, scorllY);
        }


    }

}

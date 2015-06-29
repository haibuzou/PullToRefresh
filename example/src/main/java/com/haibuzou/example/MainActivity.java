package com.haibuzou.example;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.haibuzou.library.PullToRefreshListView;
import com.haibuzou.library.PullToRefreshBase;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Handler mhandler = new Handler();
        final PullToRefreshListView pullToRefreshListView = (PullToRefreshListView)findViewById(R.id.pull_to_refresh);
        final List<String> data = new ArrayList<>();
        final Random random = new Random();
        for(int i = 0; i < 30; i++){
            data.add(random.nextInt(10)+"");
        }
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
        pullToRefreshListView.setAdapter(adapter);
        //footer默认不显示 需要上层判断是否显示
        pullToRefreshListView.showFooter();

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshListView.onRefreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        pullToRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            data.add("Add item" + random.nextInt(10));
                        }
                        adapter.notifyDataSetChanged();
                        pullToRefreshListView.onRefreshComplete();
                    }
                }, 2000);
            }
        });
    }

}

# PullToReFresh
Change from Android-PullToReFresh 
Use OnLastItemVisibleListener to replace onPullUpToRefresh，To achieve auto load more function

更改自 Android-PullToReFresh 
原有基础上使用OnLastItemVisibleListener 替代 onPullUpToRefresh 来实现上拉自动加载功能


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
                        for(int i = 0; i < 10; i++){
                            data.add("Add item"+random.nextInt(10));
                        }
                        adapter.notifyDataSetChanged();
                        pullToRefreshListView.onRefreshComplete();
                    }
                },2000);
            }
        });

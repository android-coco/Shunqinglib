package yh.org.shunqinglib;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.yh.library.adapter.I_YHItemClickListener;
import org.yh.library.okhttp.YHRequestFactory;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.JsonUitl;
import org.yh.library.utils.LogUtils;
import org.yh.library.view.YHRecyclerView;
import org.yh.library.view.yhrecyclerview.ProgressStyle;

import java.util.ArrayList;

import yh.org.shunqinglib.adapter.DwSdAdapter;
import yh.org.shunqinglib.app.ShunQingApp;
import yh.org.shunqinglib.base.BaseActiciy;
import yh.org.shunqinglib.bean.DwSdModel;
import yh.org.shunqinglib.bean.JsonDwSdModel;
import yh.org.shunqinglib.utils.GlobalUtils;



/**
 * 定位时段
 */
public class DwSdActivity extends BaseActiciy implements I_YHItemClickListener<DwSdModel>
{

    @BindView(id = R.id.recyclerview)
    private YHRecyclerView mRecyclerView;
    @BindView(id = R.id.empty_layout)
    private LinearLayout empty_layout;
    @BindView(id = R.id.id_empty_text)
    private TextView id_empty_text;
    private DwSdAdapter mAdapter;
    ArrayList<DwSdModel> data = null;
    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_dw_sd);
    }

    @Override
    public void initWidget()
    {
        super.initWidget();
        toolbar.setLeftTitleText("返回");
        toolbar.setMainTitle("定位时段");
        toolbar.setRightTitleText("");

        id_empty_text.setText("加载中。。。");
        //lineartlayout
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //分割线为LinearLayoutManager
        //mRecyclerView.addItemDecoration(mRecyclerView.new YHItemDecoration());//分割线
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setEmptyView(empty_layout);//没有数据的空布局

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulseRise);//可以自定义下拉刷新的样式
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);//可以自定义上拉加载的样式
        mRecyclerView.setFootViewText(getString(R.string.listview_loading), "我是有底线的。");
        // mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);//箭头
        mAdapter = new DwSdAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingListener(new YHRecyclerView.LoadingListener()
        {
            @Override
            public void onRefresh()
            {
//                page = 1;
                mAdapter.getDatas().clear();//必须在数据更新前清空，不能太早
            }

            @Override
            public void onLoadMore()
            {
//                //page++;
//                if (page <= TOTAL_PAGE) {//小于总页数就加载更多
//                    // loading more
//                    getData();
//                } else {
//                    //the end
//                    mRecyclerView.setNoMore(true);
//                }
            }
        });
        mRecyclerView.refresh();
    }

    @Override
    public void initData()
    {
        super.initData();
        data = new ArrayList<>();
        getData();
    }

    @Override
    protected void onBackClick()
    {
        super.onBackClick();
        finish();
    }

    @Override
    protected void onMenuClick()
    {
        super.onMenuClick();
        YHViewInject.create().showTips("添加");
    }

    private void getData(){
        YHRequestFactory.getRequestManger().postString(ShunQingApp.HOME_HOST, GlobalUtils
                .REPORT_LIST, null, "{\"sn\":\"" + ShunQingApp.DEIVER_SN + "\"}", new
                HttpCallBack()
                {
                    @Override
                    public void onSuccess(String t)
                    {
                        super.onSuccess(t);
                        final JsonDwSdModel jsonData = JsonUitl.stringToTObject
                                (ShunQingApp
                                        .getInstance().yhGson, t, JsonDwSdModel.class);
                        LogUtils.e(TAG,jsonData.getDatas().get(0) instanceof DwSdModel);
//                        String resultCode = jsonData.getResultCode();
//                        if ("0".equals(resultCode))
//                        {
//                            data.addAll(jsonData.getDatas());
//                            mAdapter.setDatas(data);
//                        } else
//                        {
//                            mAdapter.notifyDataSetChanged();
//                        }
//                        //刷新完毕
//                        mRecyclerView.refreshComplete();
                    }

                    @Override
                    public void onFailure(int errorNo, String strMsg)
                    {
                        super.onFailure(errorNo, strMsg);
                        LogUtils.e(TAG, strMsg);
                        id_empty_text.setText("加载失败");
                        mAdapter.getDatas().clear();//必须在数据更新前清空，不能太早
                        //刷新完毕
                        mRecyclerView.refreshComplete();
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFinish()
                    {
                        super.onFinish();
                    }
                }, TAG);
    }

    @Override
    public boolean onItemLongClick(View view, DwSdModel dwSdModel, int i)
    {
        return false;
    }

    @Override
    public void onItemClick(View view, DwSdModel dwSdModel, int i)
    {

    }
}

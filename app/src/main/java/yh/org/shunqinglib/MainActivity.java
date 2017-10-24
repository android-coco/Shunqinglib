package yh.org.shunqinglib;

import android.Manifest;

import org.yh.library.okhttp.YHRequestFactory;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.I_PermissionListener;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.Constants;

import java.util.List;

import yh.org.shunqinglib.base.BaseActiciy;

public class MainActivity extends BaseActiciy
{
    //@BindView(id = R.id.bmapView)
    // private MapView mMapView = null;
    //private BaiduMap mBaiduMap;

    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData()
    {
        super.initData();

        //权限判断
        /***
         * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
         */
        // 定位精确位置

        // 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
        // 读取电话状态权限
        requestRunTimePermission(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}, new I_PermissionListener()
        {
            @Override
            public void onSuccess()//所有权限OK
            {
                //直接执行相应操作了
                YHViewInject.create().showTips("OK");
                Constants.Config.IS_WRITE_EXTERNAL_STORAGE = true;
            }

            @Override
            public void onGranted(List<String> grantedPermission)//部分权限OK
            {
            }

            @Override
            public void onFailure(List<String> deniedPermission)//全部拒绝
            {
                Constants.Config.IS_WRITE_EXTERNAL_STORAGE = false;
                YHViewInject.create().showTips("拒绝授权列表：" + Constants.initPermissionNames
                        ().get(deniedPermission.get(0)));
            }
        });


        //"{\"sns\":\"123456789012345\"}"
        YHRequestFactory.getRequestManger().postString(ShunQingApp.HOME_HOST, "/interface/terminal_profile", null, "{\"sns\":\"123456789012345\"}", new HttpCallBack()
        {
            @Override
            public void onSuccess(String t)
            {
                super.onSuccess(t);
            }

            @Override
            public void onFailure(int errorNo, String strMsg)
            {
                super.onFailure(errorNo, strMsg);
            }

            @Override
            public void onFinish()
            {
                super.onFinish();
            }
        }, TAG);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        //mMapView.onDestroy();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
       // mMapView.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        //mMapView.onPause();
    }

}

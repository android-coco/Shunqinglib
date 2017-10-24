package yh.org.shunqinglib;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.yh.library.okhttp.YHRequestFactory;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.JsonUitl;
import org.yh.library.utils.StringUtils;

import yh.org.shunqinglib.app.ShunQingApp;
import yh.org.shunqinglib.base.BaseActiciy;
import yh.org.shunqinglib.bean.JsonLjDWModel;
import yh.org.shunqinglib.utils.GlobalUtils;

/**
 * Created by yhlyl on 2017/10/24.
 * 自定拨号
 */

public class ZdBhActivity extends BaseActiciy
{
    @BindView(id = R.id.number)
    private EditText number;
    @BindView(id = R.id.bh, click = true)
    private Button bh;

    @Override
    public void setRootView()
    {
        setContentView(R.layout.zdbh);
    }

    @Override
    public void widgetClick(View v)
    {
        super.widgetClick(v);
        switch (v.getId())
        {
            case R.id.bh:
                if (StringUtils.isEmpty(number.getText().toString()))
                {
                    YHViewInject.create().showTips("请输入号码！");
                }
                else
                {
                    zjBH();
                }
                break;
        }
    }

    private void zjBH()
    {
        YHRequestFactory.getRequestManger().postString(ShunQingApp.HOME_HOST, GlobalUtils
                .TERMINAL_CALL, null, "{\"sns\":\"123456789012345\",\"number\":\"" + number
                .getText().toString() + "\"}", new
                HttpCallBack()
                {
                    @Override
                    public void onSuccess(String t)
                    {
                        super.onSuccess(t);
                        final JsonLjDWModel jsonEquipmentModel = JsonUitl.stringToTObject
                                (ShunQingApp
                                .getInstance().yhGson, t, JsonLjDWModel.class);
                        if ("0".equals(jsonEquipmentModel.getResultCode()))
                        {
                            YHViewInject.create().showTips("拨号指令发送成功！");
                        }
                        else if ("5".equals(jsonEquipmentModel.getResultCode()))
                        {
                            YHViewInject.create().showTips("设备不在线");
                        }else if ("6".equals(jsonEquipmentModel.getResultCode()))
                        {
                            YHViewInject.create().showTips("设备无反应");
                        }
                    }

                    @Override
                    public void onFailure(int errorNo, String strMsg)
                    {
                        super.onFailure(errorNo, strMsg);
                        YHViewInject.create().showTips("未知错误");
                    }

                    @Override
                    public void onFinish()
                    {
                        super.onFinish();
                    }
                }, TAG);
    }
}

package yh.org.shunqinglib.aty;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TimePicker;

import org.greenrobot.eventbus.EventBus;
import org.yh.library.bean.EventBusBean;
import org.yh.library.okhttp.YHRequestFactory;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.JsonUitl;
import org.yh.library.utils.LogUtils;
import org.yh.library.view.loading.dialog.YHLoadingDialog;

import java.util.Calendar;

import yh.org.shunqinglib.R;
import yh.org.shunqinglib.app.ShunQingApp;
import yh.org.shunqinglib.base.BaseActiciy;
import yh.org.shunqinglib.bean.JsonDwSdModel;
import yh.org.shunqinglib.utils.GlobalUtils;
/**
 * 添加定位时段
 */
public class DwSdAddActivity extends BaseActiciy implements OnCheckedChangeListener
{
    //星期选择
    @BindView(id = R.id.x_1)
    CheckBox x_1;
    @BindView(id = R.id.x_2)
    CheckBox x_2;
    @BindView(id = R.id.x_3)
    CheckBox x_3;
    @BindView(id = R.id.x_4)
    CheckBox x_4;
    @BindView(id = R.id.x_5)
    CheckBox x_5;
    @BindView(id = R.id.x_6)
    CheckBox x_6;
    @BindView(id = R.id.x_7)
    CheckBox x_7;
    @BindView(id = R.id.add, click = true)
    Button add;

    //日期选择
    @BindView(id = R.id.stime_1, click = true)
    EditText stime_1;
    @BindView(id = R.id.stime_2, click = true)
    EditText stime_2;
    @BindView(id = R.id.etime_1, click = true)
    EditText etime_1;
    @BindView(id = R.id.etime_2, click = true)
    EditText etime_2;
    @BindView(id = R.id.times, click = true)
    EditText times;
    @BindView(id = R.id.timingpostion_name)
    EditText timingpostion_name;
    String week = "";//星期

    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_dwsd_add);
    }

    @Override
    public void initWidget()
    {
        super.initWidget();
        toolbar.setLeftTitleText("返回");
        toolbar.setMainTitle("添加定时定位");


        x_1.setOnCheckedChangeListener(this);
        x_2.setOnCheckedChangeListener(this);
        x_3.setOnCheckedChangeListener(this);
        x_4.setOnCheckedChangeListener(this);
        x_5.setOnCheckedChangeListener(this);
        x_6.setOnCheckedChangeListener(this);
        x_7.setOnCheckedChangeListener(this);
        InputFilter[] filters = {new InputFilter.LengthFilter(6)}; // 设置最大长度为6个字符
        timingpostion_name.setFilters(filters);
    }

    @Override
    protected void onBackClick()
    {
        super.onBackClick();
        finish();
    }

    @Override
    public void widgetClick(View v)
    {
        super.widgetClick(v);

        switch (v.getId())
        {
            case R.id.add:// 保存
                String bh = stime_1.getText().toString().trim();
                String bm = stime_2.getText().toString().trim();
                String eh = etime_1.getText().toString().trim();
                String em = etime_2.getText().toString().trim();
                String name = timingpostion_name.getText().toString().trim();
                String times = this.times.getText().toString().trim();
                if ("".equals(week))
                {
                    YHViewInject.create().showTips("执行周期不能为空！");
                }
                else
                {
                    if (bh.startsWith("0"))
                    {
                        bh = bh.substring(1);
                    }
                    if (bm.startsWith("0"))
                    {
                        bm = bm.substring(1);
                    }
                    if (eh.startsWith("0"))
                    {
                        eh = eh.substring(1);
                    }
                    if (em.startsWith("0"))
                    {
                        em = em.substring(1);
                    }
                    upload(bh, bm, eh, em, times);
                }
                break;
            case R.id.stime_1:// 开始1
                whichTime = 0;
                showTime();
                break;
            case R.id.stime_2:// 开始2
                whichTime = 0;
                showTime();
                break;
            case R.id.etime_1:// 结束1
                whichTime = 1;
                showTime();
                break;
            case R.id.etime_2:// 结束2
                whichTime = 1;
                showTime();
                break;
            case R.id.times:// 时隔时间
                getTiems();
                break;
        }
    }

    private void upload(final String bh, final String bm, final String eh,
                        final String em, final String times)
    {
        YHLoadingDialog.make(aty).setMessage("添加中。。。")//提示消息
                .setCancelable(false).show();
        String parameter = "{\"sn\":\"" + ShunQingApp.DEIVER_SN + "\",\"week\":\"" +
                week + "\",\"start_hour\":\"" + bh + "\",\"start_minute\":\"" + bm + "\"," +
                "\"end_hour\":\"" + eh + "\",\"end_minute\":\"" + em + "\",\"times\":\"" +
                times + "\"}";
        YHRequestFactory.getRequestManger().postString(ShunQingApp.HOME_HOST, GlobalUtils
                .REPORT_ADD, null, parameter, new
                HttpCallBack()
                {
                    @Override
                    public void onSuccess(String t)
                    {
                        super.onSuccess(t);
                        final JsonDwSdModel jsonData = JsonUitl.stringToTObject
                                (t, JsonDwSdModel.class);
                        String resultCode = jsonData.getResultCode();
                        if ("0".equals(resultCode))
                        {
                            YHViewInject.create().showTips("添加成功");
                            EventBus.getDefault().post(new EventBusBean());
                            finish();
                        }
                        else
                        {
                            YHViewInject.create().showTips("添加失败");
                        }
                    }

                    @Override
                    public void onFailure(int errorNo, String strMsg)
                    {
                        super.onFailure(errorNo, strMsg);
                        YHViewInject.create().showTips("添加失败");
                    }

                    @Override
                    public void onFinish()
                    {
                        super.onFinish();
                        YHLoadingDialog.cancel();
                    }
                }, TAG);
    }

    private void getTiems()
    {
        final String[] lang = {"5", "10", "15", "20", "30", "40", "50", "60"};
        new AlertDialog.Builder(this)
                .setTitle("选择时隔时间(分)")
                // 标题
                .setSingleChoiceItems(lang, 0,
                        new DialogInterface.OnClickListener()
                        {// 设置条目
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {// 响应事件
                                // do something
                                // 关闭对话框
                                dialog.dismiss();
                                times.setText(lang[which]);
                            }
                        }).show();
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        switch (buttonView.getId())
        {
            case R.id.x_1:
                if (isChecked)
                {
                    week += "1";
                }
                else
                {
                    week = week.replace("1", "");
                }
                break;
            case R.id.x_2:
                if (isChecked)
                {
                    week += "2";
                }
                else
                {
                    week = week.replace("2", "");
                }
                break;
            case R.id.x_3:
                if (isChecked)
                {
                    week += "3";
                }
                else
                {
                    week = week.replace("3", "");
                }
                break;
            case R.id.x_4:
                if (isChecked)
                {
                    week += "4";
                }
                else
                {
                    week = week.replace("4", "");
                }
                break;
            case R.id.x_5:
                if (isChecked)
                {
                    week += "5";
                }
                else
                {
                    week = week.replace("5", "");
                }
                break;
            case R.id.x_6:
                if (isChecked)
                {
                    week += "6";
                }
                else
                {
                    week = week.replace("6", "");
                }
                break;
            case R.id.x_7:
                if (isChecked)
                {
                    week += "7";
                }
                else
                {
                    week = week.replace("7", "");
                }
                break;
        }
        System.out.println("aaaaaaaaaa:" + week);
        LogUtils.e(TAG, week);
    }

    /**
     * 选择时间
     */
    private void showTime()
    {
        final Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                setting, hour, minute, true);
        timePickerDialog.show();

    }

    int whichTime = 0;
    // 当点击TimePickerDialog控件的设置按钮时，调用该方法
    TimePickerDialog.OnTimeSetListener setting = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker view, int hour, int minute)
        {
            switch (whichTime)
            {
                case 0:// 开始时间
                    System.out.println(hour + " ////" + minute);
                    String h = "00",
                            m = "00";
                    if (hour < 10)
                    {
                        h = "0" + hour;
                    }
                    else
                    {
                        h = hour + "";
                    }
                    if (minute < 10)
                    {
                        m = "0" + minute;
                    }
                    else
                    {
                        m = minute + "";
                    }
                    stime_1.setText(h);
                    stime_2.setText(m);
                    break;
                case 1:// 结束时间
                    String h1 = "00",
                            m1 = "00";
                    if (hour < 10)
                    {
                        h1 = "0" + hour;
                    }
                    else
                    {
                        h1 = hour + "";
                    }
                    if (minute < 10)
                    {
                        m1 = "0" + minute;
                    }
                    else
                    {
                        m1 = minute + "";
                    }
                    etime_1.setText(h1);
                    etime_2.setText(m1);
                    break;
            }
        }

    };
}

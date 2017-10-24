package yh.org.shunqinglib.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.yh.library.bean.YHModel;

/**
 * 作者：游浩 on 2017/10/24 16:04
 * https://github.com/android-coco/YhLibraryForAndroid
 * 邮箱：yh_android@163.com
 */
public class DwSdModel extends YHModel
{
    @SerializedName("id")
    @Expose
    private Integer dwsId;//数据ID
    @SerializedName("week")
    private String week;//星期数据，周一为1， 周日为7，最多7天共存(1-7)
    @SerializedName("start_hour")
    private String startHour;//开始时间的小时
    @SerializedName("start_minute")
    private String startMinute;// 开始时间的分钟
    @SerializedName("end_hour")
    private String endHour;// 结束时间的小时
    @SerializedName("end_minute")
    private String endMinute; //结束时间的分钟
    @SerializedName("time")
    private String time;//定位上报的周期，秒，默认300（5分钟）

    public Integer getDwsId()
    {
        return dwsId;
    }

    public void setDwsId(Integer id)
    {
        this.dwsId = id;
    }

    public String getWeek()
    {
        return week;
    }

    public void setWeek(String week)
    {
        this.week = week;
    }

    public String getStartHour()
    {
        return startHour;
    }

    public void setStartHour(String startHour)
    {
        this.startHour = startHour;
    }

    public String getStartMinute()
    {
        return startMinute;
    }

    public void setStartMinute(String startMinute)
    {
        this.startMinute = startMinute;
    }

    public String getEndHour()
    {
        return endHour;
    }

    public void setEndHour(String endHour)
    {
        this.endHour = endHour;
    }

    public String getEndMinute()
    {
        return endMinute;
    }

    public void setEndMinute(String endMinute)
    {
        this.endMinute = endMinute;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    @Override
    public String toString()
    {
        return "DwSdModel{" +
                "dwsId='" + dwsId + '\'' +
                ", week='" + week + '\'' +
                ", startHour='" + startHour + '\'' +
                ", startMinute='" + startMinute + '\'' +
                ", endHour='" + endHour + '\'' +
                ", endMinute='" + endMinute + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

package yh.org.shunqinglib.bean;

import com.google.gson.annotations.SerializedName;

import org.yh.library.bean.YHModel;

import java.util.List;

/**
 * Created by yhlyl on 2017/5/13.
 */

public class JsonDwSdModel extends YHModel
{
    @SerializedName("result")
    protected String resultCode;//结果Code
    @SerializedName("datas")
    protected List<DwSdModel> datas;//数据

    public String getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }

    public List<DwSdModel> getDatas()
    {
        return datas;
    }

    public void setDatas(List<DwSdModel> datas)
    {
        this.datas = datas;
    }


    @Override
    public String toString()
    {
        return "JsonEquipmentModel{" +
                "resultCode='" + resultCode + '\'' +
                ", datas=" + datas +
                "} " + super.toString();
    }
}

package yh.org.shunqinglib.adapter;

import org.yh.library.adapter.rv.I_ItemViewDelegate;
import org.yh.library.adapter.rv.YHRecyclerViewHolder;

import yh.org.shunqinglib.R;
import yh.org.shunqinglib.bean.DwSdModel;

/**
 * Created by yhlyl on 2017/10/25.
 */

public class DwSdItemDelagate implements I_ItemViewDelegate<DwSdModel>
{
    @Override
    public int getItemViewLayoutId()
    {
        return R.layout.dwsd_item;
    }

    @Override
    public boolean isForViewType(DwSdModel dwSdModel, int i)
    {
        return true;
    }

    @Override
    public void convert(YHRecyclerViewHolder holder, DwSdModel item, int i)
    {
        holder.setText(R.id.timing_time, item.getStartHour() + ":" + item.getStartMinute() + "~"
                + item.getEndHour() + ":" + item.getEndMinute());
        holder.setText(R.id.timing_interval, item.getTime() + "秒");
        holder.setText(R.id.timing_cycle, item.getWeek());
    }
}

package yh.org.shunqinglib.adapter;

import org.yh.library.adapter.rv.YhRecyclerAdapter;

import yh.org.shunqinglib.bean.DwSdModel;

/**
 * Created by yhlyl on 2017/10/25.
 * 定位时段
 */

public class DwSdAdapter extends YhRecyclerAdapter<DwSdModel>
{
    public DwSdAdapter(){
        addItemViewDelegate(new DwSdItemDelagate());
    }
}

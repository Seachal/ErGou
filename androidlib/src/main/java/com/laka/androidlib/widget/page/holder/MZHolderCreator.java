package com.laka.androidlib.widget.page.holder;

/**
 * Created by zhouwei on 17/5/26.
 */

public interface MZHolderCreator<VH extends MZViewHolder> {
    /**
     * 创建ViewHolder
     * @return
     */
    public VH createViewHolder();
}

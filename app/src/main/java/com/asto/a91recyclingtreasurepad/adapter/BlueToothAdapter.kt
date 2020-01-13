package com.asto.a91recyclingtreasurepad.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.asto.a91recyclingtreasurepad.R
import com.asto.a91recyclingtreasurepad.bean.BlueToothBean

class BlueToothAdapter(data : ArrayList<BlueToothBean>): BaseQuickAdapter<BlueToothBean, BaseViewHolder>(R.layout.item_blue_tooth, data){

    override fun convert(helper: BaseViewHolder?, item: BlueToothBean?) {
        helper?.setText(R.id.mNameTv, item?.blueToothName)
        helper?.setText(R.id.mMacTv, item?.blueToothAddress)
        if(item?.isSelected == true){
            helper?.setBackgroundColor(R.id.mCardView,mContext.resources.getColor(R.color.bgstatus))
        }else{
            helper?.setBackgroundColor(R.id.mCardView,mContext.resources.getColor(R.color.white))
        }

    }

}
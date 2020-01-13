package com.asto.a91recyclingtreasurepad.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.asto.a91recyclingtreasurepad.R
import com.asto.a91recyclingtreasurepad.bean.InfoBean


/**
 * Created by ZhengJie on 2018/7/10.
 * is use for: 信息展示 adapter
 */

class InfoAdapter(var mContext: Context, private val mList: List<InfoBean> ,val windowWidth : Int) : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_info, parent, false)
        if (windowWidth < 1900){
            view.findViewById<TextView>(R.id.tvContent).textSize = dp2px(mContext,25)
            view.findViewById<TextView>(R.id.tvMessage).textSize = dp2px(mContext,25)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvContent.text = mList[position].content
        holder.tvMessage.text = mList[position].message

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvContent:TextView = itemView.findViewById(R.id.tvContent)
        var tvMessage:TextView = itemView.findViewById(R.id.tvMessage)

    }

    fun dp2px(context: Context, dp: Int): Float {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f)
    }
}

package com.atplatform.test.ui.future

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.atplatform.test.R
import com.atplatform.test.bean.Future
import com.atplatform.test.databinding.ItemFutureBinding

class FutureListAdapter(private val context: Context, private val data: MutableList<Future>) :
    RecyclerView.Adapter<FutureListAdapter.ViewHolder>() {

    private var itemClickListener: ((future: Future) -> Unit)? = null

    inner class ViewHolder(val binding: ItemFutureBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemFutureBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(data[position]) {
            holder.binding.nameTv.text = ftsNm
            holder.binding.priceTv.text = lastPrice
            holder.binding.rangeTv.text = changeMargin
            holder.binding.rangeTv.setTextColor(
                ContextCompat.getColor(
                    context,
                    if (changeMargin.startsWith("-")) R.color.green else R.color.red
                )
            )

        }
        holder.binding.root.setOnClickListener {
            itemClickListener?.invoke(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(newData: MutableList<Future>) {
        data.clear()
        data.addAll(newData)
        notifyItemRangeChanged(0, newData.size)
    }

    fun setOnItemClickListener(listener: (future: Future) -> Unit) {
        this.itemClickListener = listener
    }
}
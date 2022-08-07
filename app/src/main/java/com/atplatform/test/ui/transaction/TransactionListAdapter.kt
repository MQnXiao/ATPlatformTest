package com.atplatform.test.ui.transaction

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atplatform.test.databinding.ItemTransactionBinding
import com.atplatform.test.bean.Transaction
import java.math.RoundingMode
import java.text.DecimalFormat

class TransactionListAdapter(
    private val data: MutableList<Transaction>,
    private var isUSD: Boolean = false,
    private var rate: Double = 0.0
) :
    RecyclerView.Adapter<TransactionListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemTransactionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(data[position]) {
            holder.binding.nameTv.text = name
            if (isUSD && rate != 0.0) {
                holder.binding.amountTv.text = "$:${getNoMoreThanTwoDigits(amount / rate)}"
                holder.binding.amount2Tv.visibility = View.VISIBLE
                holder.binding.amount2Tv.text = "￥:${getNoMoreThanTwoDigits(amount)}"
            } else {
                holder.binding.amountTv.text = "￥:${getNoMoreThanTwoDigits(amount)}"
                holder.binding.amount2Tv.visibility = View.GONE
            }
            holder.binding.quantityTv.text = quantity.toString()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(newData: MutableList<Transaction>) {
        data.clear()
        data.addAll(newData)
        notifyItemRangeChanged(0, data.size)
    }

    fun switchAmountUnit(isUSD: Boolean, rate: Double) {
        this.isUSD = isUSD
        this.rate = rate
        notifyItemRangeChanged(0, data.size)
    }

    private fun getNoMoreThanTwoDigits(number: Double): String {
        return DecimalFormat("0.##").apply { roundingMode = RoundingMode.FLOOR }.format(number)
    }
}
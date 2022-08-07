package com.atplatform.test.ui.future

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.atplatform.test.base.BaseDialog
import com.atplatform.test.bean.Future
import com.atplatform.test.bean.Transaction
import com.atplatform.test.databinding.DialogPurchaseBinding

class PurchaseDialog(
    private val future: Future,
    val purchaseCallback: (transition: Transaction) -> Unit
) :
    BaseDialog() {
    private var _binding: DialogPurchaseBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogPurchaseBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        binding.nameInputEdit.setText(future.ftsNm)
        binding.quantityInputEdit.addTextChangedListener(afterTextChanged = {
            if (binding.quantityInputEdit.text.toString().isBlank()) {
                binding.amountTv.text = "金额:0"
            } else {
                binding.amountTv.text =
                    "金额:".plus(
                        binding.quantityInputEdit.text.toString()
                            .toInt() * future.lastPrice.toDouble()
                    )
            }
        })

        binding.submitBtn.setOnClickListener {
            if (binding.nameInputEdit.text.toString().isBlank()) {
                Toast.makeText(context, "请输入交易名称", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.quantityInputEdit.text.toString().isBlank()) {
                Toast.makeText(context, "请输入交易数量", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            dismiss()
            purchaseCallback(
                Transaction(
                    name = binding.nameInputEdit.text.toString(),
                    quantity = binding.quantityInputEdit.text.toString().toInt(),
                    amount = binding.quantityInputEdit.text.toString()
                        .toInt() * future.lastPrice.toDouble(),
                    time = System.currentTimeMillis()
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
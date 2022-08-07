package com.atplatform.test.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.atplatform.test.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment() {

    private var _binding: FragmentTransactionBinding? = null

    private val binding get() = _binding!!

    private val transactionViewModel by viewModels<TransactionViewModel>()

    private var transactionListAdapter: TransactionListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        binding.transactionRv.layoutManager = LinearLayoutManager(requireContext())
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            transactionListAdapter?.switchAmountUnit(isChecked,transactionViewModel.rate.value?:0.0)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactionViewModel.transactions.observe(viewLifecycleOwner) { data ->
            transactionListAdapter?.updateData(data) ?: let {
                transactionListAdapter = TransactionListAdapter(data)
                binding.transactionRv.adapter = transactionListAdapter
            }
        }
        transactionViewModel.queryExchangeRate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
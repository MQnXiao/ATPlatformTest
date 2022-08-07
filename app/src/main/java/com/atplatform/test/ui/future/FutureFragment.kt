package com.atplatform.test.ui.future

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.atplatform.test.databinding.FragmentFutureBinding
import kotlinx.coroutines.launch

class FutureFragment : Fragment() {

    private var _binding: FragmentFutureBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel by viewModels<FutureViewModel>()

    private var futureListAdapter: FutureListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFutureBinding.inflate(inflater, container, false)
        binding.futureRv.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            homeViewModel.futureState
                .collect { state ->
                    when (state) {
                        FutureUiState.IsLoading -> {
                            Toast.makeText(requireContext(), "正在加载数据", Toast.LENGTH_SHORT)
                                .show()
                        }
                        is FutureUiState.LoadError -> {
                            Toast.makeText(
                                requireContext(),
                                state.error.message,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        is FutureUiState.LoadSuccess -> {
                            futureListAdapter?.updateData(state.reqData) ?: let {
                                futureListAdapter =
                                    FutureListAdapter(requireContext(), state.reqData)
                                binding.futureRv.adapter = futureListAdapter
                                futureListAdapter!!.setOnItemClickListener { future ->
                                    PurchaseDialog(future) {
                                        homeViewModel.saveTransaction(it)
                                    }.show(parentFragmentManager, "PurchaseDialog")
                                }
                            }
                        }
                        else -> {}
                    }

                }
        }
        homeViewModel.getFutures()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
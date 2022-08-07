package com.atplatform.test.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.atplatform.test.R
import com.atplatform.test.databinding.ActivityMainBinding
import com.atplatform.test.ui.future.FutureFragment
import com.atplatform.test.ui.transaction.TransactionFragment
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val home = FutureFragment()
        val tra = TransactionFragment()
        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 2
            }

            override fun createFragment(position: Int): Fragment {
                return if (position == 0) home else tra
            }
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.title_home)
                1 -> getString(R.string.title_dashboard)
                else -> ""
            }
        }.attach()
    }
}
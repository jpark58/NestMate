package com.example.nm1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.nm1.memFrag.MemFragment
import com.example.nm1.todoFrag.TodoFragment
import com.example.nm1.nvFrag.NVFragment
import com.example.nm1.memoFrag.MemoFragment
import com.example.nm1.ruleFrag.RuleFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.inner_view.*

class ViewPagerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.inner_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = PagerFragmentStateAdapter(requireActivity())
        // 3개의 Fragment Add
        pagerAdapter.addFragment(TodoFragment())
        pagerAdapter.addFragment(MemoFragment())
        pagerAdapter.addFragment(RuleFragment())
        pagerAdapter.addFragment(NVFragment())
        pagerAdapter.addFragment(MemFragment())
        // Adapter
        vpInner.adapter = pagerAdapter

        vpInner.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })

        val tabTextArray = arrayOf("할 일", "메모", "규칙", "공지/투표", "멤버")
        // TabLayout attach
        TabLayoutMediator(tabTop, vpInner) { tab, position ->
            tab.text = tabTextArray[position]
        }.attach()
    }

}
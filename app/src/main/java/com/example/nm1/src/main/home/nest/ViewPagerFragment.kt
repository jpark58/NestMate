package com.example.nm1.src.main.home.nest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.nm1.PagerFragmentStateAdapter
import com.example.nm1.R
import com.example.nm1.src.main.home.nest.member.MemFragment
import com.example.nm1.src.main.home.nest.todo.TodoFragment
import com.example.nm1.src.main.home.nest.notice.NVFragment
import com.example.nm1.src.main.home.nest.memo.MemoFragment
import com.example.nm1.src.main.home.nest.rule.RuleFragment
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
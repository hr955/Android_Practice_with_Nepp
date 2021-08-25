package com.example.pizzaorderapp_20210825.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.pizzaorderapp_20210825.fragments.OrderFragment
import com.example.pizzaorderapp_20210825.fragments.UserFragment

class MainViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> OrderFragment()
        else -> UserFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? = when (position) {
        0 -> "피자주문"
        else -> "사용자 정보"
    }
}
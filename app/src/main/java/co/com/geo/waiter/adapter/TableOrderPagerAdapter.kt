package co.com.geo.waiter.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentManager
import co.com.geo.waiter.fragment.TableOrderFragment
import co.com.geo.waiter.model.Tables

class TableOrderPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
       return TableOrderFragment.newInstance(position)
    }

    override fun getCount(): Int {
       return Tables.count()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        super.getPageTitle(position)
        return Tables[position].number.toString()
    }
}
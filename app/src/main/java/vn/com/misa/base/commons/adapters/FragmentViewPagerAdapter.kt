
package vn.com.misa.base.commons.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import vn.com.base.libraries.utilities.CollectionUtility

@Suppress("DEPRECATION")
class FragmentViewPagerAdapter<T : Fragment>(fm: FragmentManager, private val fragments: ArrayList<T>, private val titles: ArrayList<String>
) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): T = fragments[position]

    override fun getCount(): Int = CollectionUtility.with(fragments).size()

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}

package com.example.ui8.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

//попробуй в будущем сделать это не на фрагментах а на images чтобы лишнии itemFragment удалить
class ViewPagerOnBoardAdapter(
    fa: FragmentActivity,
    private val list: List<Fragment>
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }


}

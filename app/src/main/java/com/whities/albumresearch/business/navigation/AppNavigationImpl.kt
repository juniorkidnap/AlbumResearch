package com.whities.albumresearch.business.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.whities.albumresearch.R
import com.whities.albumresearch.framework.presentation.album.AlbumFragment
import com.whities.albumresearch.framework.presentation.search.SearchFragment
import javax.inject.Inject

class AppNavigationImpl
@Inject constructor(
    private val activity: FragmentActivity,
) : AppNavigation {

    override fun navigateTo(screen: Screens) {
        when (screen) {
            is Screens.SearchScreen -> openFragment(SearchFragment())
            is Screens.AlbumScreen -> openFragment(AlbumFragment())
        }
    }

    private fun openFragment(fragment: Fragment) {
        activity.supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out
            )
            replace(
                R.id.fragmentContainerView,
                fragment
            )
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}

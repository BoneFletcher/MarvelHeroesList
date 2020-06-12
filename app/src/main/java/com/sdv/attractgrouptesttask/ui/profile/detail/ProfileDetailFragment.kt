package com.sdv.attractgrouptesttask.ui.profile.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.sdv.attractgrouptesttask.R
import com.sdv.attractgrouptesttask.ui.profile.ProfileListViewModel
import kotlinx.android.synthetic.main.profile_detail_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileDetailFragment : Fragment() {
    private lateinit var adapter: ProfileDetailAdapter
    private val viewModel by sharedViewModel<ProfileListViewModel>()
    private val args: ProfileDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeChanges()
        setRecyclerView(savedInstanceState?.getBoolean("rotate"))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("rotate", true)
    }


    private fun setRecyclerView(rotate: Boolean?) {
        adapter = ProfileDetailAdapter()
        rv_profile_detail_list.visibility = GONE
        rv_profile_detail_list.adapter = adapter
        adapter.submitList(viewModel.profileLoadLiveData.value)
        if (rotate == null) {
            lifecycleScope.launch {
                delay(100)
                rv_profile_detail_list.setCurrentItem(args.detailPosition, false)
                rv_profile_detail_list.visibility = VISIBLE
            }
        } else rv_profile_detail_list.visibility = VISIBLE
        rv_profile_detail_list.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager2.SCROLL_STATE_IDLE){
                    viewModel.updateProfileListPosition(rv_profile_detail_list.currentItem)
                }
            }
            // override desired callback functions
        })

    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun observeChanges() {
        viewModel.profileLoadLiveData.observe(viewLifecycleOwner, Observer {
            // swipe_refresh_breweries.isRefreshing = false
            //adapter.submitList(it)
        })

        viewModel.progressUpdateLiveData.observe(viewLifecycleOwner, Observer {
            // progress_bar_search.visibleOrGone(it)
        })
    }

}

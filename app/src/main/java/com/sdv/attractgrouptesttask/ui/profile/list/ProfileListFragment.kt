package com.sdv.attractgrouptesttask.ui.profile.list

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sdv.attractgrouptesttask.R
import com.sdv.attractgrouptesttask.data.profile.Profile
import com.sdv.attractgrouptesttask.ui.extensions.visibleOrGone
import com.sdv.attractgrouptesttask.ui.profile.ProfileListViewModel
import kotlinx.android.synthetic.main.profile_list_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileListFragment : Fragment(), ProfileAdapter.OnItemClickListener {
    private lateinit var adapter: ProfileAdapter
    private val viewModel by sharedViewModel<ProfileListViewModel>()
    private lateinit var rvProfileList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvProfileList = view.findViewById(R.id.rv_profile_list)

        setRecyclerView()
        observeChanges()
    }

    private fun setRecyclerView() {
        rvProfileList.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProfileAdapter(this)
        rvProfileList.adapter = adapter
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun observeChanges() {
        viewModel.profileLoadLiveData.observe(viewLifecycleOwner, Observer {
            adapter.setItems(it as ArrayList<Profile>)
        })

        viewModel.progressUpdateLiveData.observe(viewLifecycleOwner, Observer {
            progress_bar_search.visibleOrGone(it)
        })

        viewModel.scrollLiveData.observe(viewLifecycleOwner, Observer {
            rvProfileList.run {
                scrollToPosition(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                adapter.filter.filter(s)
                return false
            }
        })
    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(ProfileListFragmentDirections.actionToDetailProfile(position))
    }
}

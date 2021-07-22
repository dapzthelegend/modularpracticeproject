package com.example.characterslist.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.example.android.SampleApp.Companion.coreComponent
import com.example.characters_list.R
import com.example.characters_list.databinding.FragmentCharactersListBinding
import com.example.characterslist.ui.list.adapter.CharacterListAdapter
import com.example.characterslist.ui.list.adapter.CharacterListAdapterState
import com.example.characterslist.ui.list.di.CharactersListModule
import com.example.characterslist.ui.list.di.DaggerCharactersListComponent
import com.example.characterslist.ui.list.model.CharacterItem
import com.example.commons.ui.extensions.gridLayoutManager
import com.example.commons.ui.extensions.observe
import com.example.ui.base.BaseFragment
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharactersListFragment : BaseFragment<FragmentCharactersListBinding, CharactersListViewModel>(
    layoutId = R.layout.fragment_characters_list
) {

    @Inject
    lateinit var viewAdapter: CharacterListAdapter

    /**
     * Called to have the fragment instantiate the user interface view
     *
     * @param view The view returned by [BaseFragment.onCreateView]
     * @param savedInstanceState If non-null, this fragment is being reconstructed from a previous
     * state
     * @see BaseFragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.data.collectLatest {
                onViewDataChanged(it)
            }
        }
        observe(viewModel.state, ::onViewStateChange)
        observe(viewModel.event, ::onViewEvent)
    }

    /**
     * Called to Initialize dagger dependency graph
     */
    override fun onInitDependencyInjection() {
        DaggerCharactersListComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .charactersListModule(CharactersListModule(this))
            .build()
            .inject(this)
    }

    /**
     * Instantiate view data binding variables
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.includeList.charactersList.apply {
            adapter = viewAdapter
            gridLayoutManager?.spanSizeLookup = viewAdapter.getSpanSizeLookup()
        }
    }

    // ============================================================================================
    //  Private observers methods
    // ============================================================================================

    /**
     * Observer view data for [CharactersListViewModel]
     *
     * @param viewData Updated paging data
     */
    private fun onViewDataChanged(viewData: PagingData<CharacterItem>) {
        lifecycleScope.launch {
            viewAdapter.submitData(viewData)
        }
    }

    /**
     * Observer view state change on [CharactersListViewModel]
     *
     * @param viewState The current view state
     */
    private fun onViewStateChange(viewState: CharactersListViewState) {
        when (viewState) {
            is CharactersListViewState.Loaded -> {
                viewAdapter.submitState(CharacterListAdapterState.Added)
            }
            is CharactersListViewState.AddLoading ->
                viewAdapter.submitState(CharacterListAdapterState.AddLoading)
            is CharactersListViewState.AddError ->
                viewAdapter.submitState(CharacterListAdapterState.AddError)
            is CharactersListViewState.NoMoreElements ->
                viewAdapter.submitState(CharacterListAdapterState.NoMore)
            else -> {
            }
        }
    }

    /**
     * Observer view event change on [CharactersListViewModel].
     *
     * @param viewEvent Event on characters list.
     */
    private fun onViewEvent(viewEvent: CharacterListViewEvent) {
        when (viewEvent) {
            is CharacterListViewEvent.OpenCharacterDetail ->
                findNavController().navigate(
                    CharactersListFragmentDirections
                        .actionCharactersListFragmentToCharacterDetailFragment(viewEvent.id)
                )
            is CharacterListViewEvent.RefreshCharacterList -> {
                viewAdapter.refresh()
                viewBinding.swipeReferesh.isRefreshing = false
            }
            is CharacterListViewEvent.RetryAddLoading ->
                viewAdapter.retry()
        }
    }
}

package com.example.characterslist.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.characterslist.ui.list.CharactersListViewModel
import com.example.characterslist.ui.list.adapter.holders.CharacterViewHolder
import com.example.characterslist.ui.list.adapter.holders.ErrorViewHolder
import com.example.characterslist.ui.list.adapter.holders.LoadingViewHolder
import com.example.characterslist.ui.list.model.CharacterItem
import com.example.ui.base.BasePagedListAdapter
import timber.log.Timber

/**
 * Enum class containing the different type of cell view and their configuration
 */
internal enum class ItemView(val type: Int, val span: Int) {
    CHARACTER(type = 0, span = 1),
    LOADING(type = 1, span = 2),
    ERROR(type = 2, span = 2);

    companion object {
        fun valueOf(type: Int): ItemView = values().first { it.type == type }
    }
}

/**
 * Class for presenting character list in a [RecyclerView], including computing
 * diffs on a background thread
 *
 * @see BasePagedListAdapter
 */
class CharacterListAdapter(
    val viewModel: CharactersListViewModel
) : BasePagedListAdapter<CharacterItem>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {
    private var state: CharacterListAdapterState = CharacterListAdapterState.Added

    /**
     * Called when recycler view needs a new [RecyclerView.ViewHolder] object
     *
     * @param parent The view group to which the corresponding view would be added after
     * it is bound to an adapter position
     * @param inflater Instantiates the layout xml into corresponding view objects
     * @param viewType The view type of the new view
     *
     * @return A new ViewHolder that holds a view of the given view type
     *
     * @see BasePagedListAdapter.onCreateViewHolder
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder =
        when (ItemView.valueOf(viewType)) {
            ItemView.CHARACTER -> CharacterViewHolder(inflater)
            ItemView.LOADING -> {
                Timber.e("inflating loading")
                LoadingViewHolder(inflater)
            }

            else -> ErrorViewHolder(inflater)
        }

    /**
     * Called when recyclerview needs to display view at a given position
     *
     * @param holder The ViewHolder which should be updated to represent the item at the
     * given position
     * @param position The position of the item within the adapter's data set
     *
     * @see BasePagedListAdapter.onBindViewHolder
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemView(position)) {
            ItemView.CHARACTER ->
                getItem(position)?.let {
                    if (holder is CharacterViewHolder) {
                        holder.bind(viewModel, it)
                    }
                }
            ItemView.ERROR ->
                if (holder is ErrorViewHolder) {
                    holder.bind(viewModel)
                }
            else -> {
            }
        }
    }

    /**
     * Returns the total number of items held by the adapter's data set
     *
     * @return The total number of items in data set
     * @see BasePagedListAdapter.getItemCount
     */
    override fun getItemCount(): Int =
        if (state.hasExtraRow) {
            super.getItemCount() + 1
        } else {
            super.getItemCount()
        }

    /**
     * Get the view type of item at position for the purposes of view recycling
     *
     * @param position Item position to query
     *
     * @return Integer representing the view type needed to represent at position
     * @see BasePagedListAdapter.getItemViewType
     */
    override fun getItemViewType(position: Int): Int = getItemView(position).type

    /**
     * Update current adapter's state with the new one
     *
     * @param newState The new adapter state
     */
    fun submitState(newState: CharacterListAdapterState) {
        val oldState = state
        state = newState
        if (newState.hasExtraRow && oldState != newState) {
            notifyItemChanged(itemCount - 1)
            Timber.e("notifying")
        }
    }

    /**
     * Obtain helper class to get the span each item occupies
     *
     * @return The helper class
     */
    fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup =
        object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return getItemView(position).span
            }
        }

    /**
     * Obtain the type of view by the item position
     *
     * @param position The item position
     * @return Itemview type
     */
    internal fun getItemView(position: Int) =
        if (state.hasExtraRow && position == itemCount - 1) {
            if (state.isAddError()) {
                ItemView.ERROR
            } else {
                ItemView.LOADING
            }
        } else {
            ItemView.CHARACTER
        }
}

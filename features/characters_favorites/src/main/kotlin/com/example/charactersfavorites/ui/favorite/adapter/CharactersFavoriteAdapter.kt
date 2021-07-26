package com.example.charactersfavorites.ui.favorite.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.database.characterfavorite.CharacterFavorite
import com.example.ui.base.BaseListAdapter

/**
 * Class for presenting favorite chatacters list in a [RecyclerView], including computing
 * diffs between Lists on a background thread.
 *
 * @see BaseListAdapter
 */
class CharactersFavoriteAdapter: BaseListAdapter<CharacterFavorite>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    /**
     * Called when the recycler view needs a new [RecyclerView.ViewHolder] of the given type
     * to represent an item.
     *
     * @param parent The ViewGroup into which the new item will be added after it is bounded to
     * an adapter position
     * @param inflater Instantiates layout xml into corresponding view objects.
     * @param viewType The view type of the new view.
     * @return A new ViewHolder that holds the view of the given view type.
     * @see BaseListAdapter.onCreateViewHolder
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder = CharactersFavoriteViewHolder(inflater)

    /**
     * Called by the RecyclerView to display the item at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     * @see BaseListAdapter.onBindViewHolder
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CharactersFavoriteViewHolder ->
                holder.bind(getItem(position))
        }
    }
}
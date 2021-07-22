package com.example.characterslist.ui.list.adapter.holders

import android.view.LayoutInflater
import com.example.characters_list.databinding.ListItemCharacterBinding
import com.example.characterslist.ui.list.CharactersListViewModel
import com.example.characterslist.ui.list.model.CharacterItem
import com.vmadalin.commons.ui.base.BaseViewHolder

/**
 * Class describes character view and meta data about it's place within the [RecyclerView]
 *
 * @see BaseViewHolder
 */
class CharacterViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemCharacterBinding>(
    binding = ListItemCharacterBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables
     *
     * @param viewModel Character list view model
     * @param item Character list item
     */
    fun bind(viewModel: CharactersListViewModel, item: CharacterItem) {
        binding.viewModel = viewModel
        binding.character = item
        binding.executePendingBindings()
    }
}

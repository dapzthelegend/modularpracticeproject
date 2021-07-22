package com.example.characterslist.ui.list.adapter.holders

import android.view.LayoutInflater
import com.example.characters_list.databinding.ListItemErrorBinding
import com.example.characterslist.ui.list.CharactersListViewModel
import com.vmadalin.commons.ui.base.BaseViewHolder

/**
 * Class describes character error view and meta data about it's place within the [RecyclerView]
 *
 * @see BaseViewHolder
 */
class ErrorViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemErrorBinding>(
    binding = ListItemErrorBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables
     *
     * @param viewModel Character list view model
     * @param item Character list item
     */
    fun bind(viewModel: CharactersListViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}

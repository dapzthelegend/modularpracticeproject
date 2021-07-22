package com.example.characterslist.ui.list.adapter.holders

import android.view.LayoutInflater
import com.example.characters_list.databinding.ListItemLoadingBinding
import com.vmadalin.commons.ui.base.BaseViewHolder

/**
 * Class describes character laoding view and meta data about it's place within the [RecyclerView]
 *
 * @see BaseViewHolder
 */
class LoadingViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemLoadingBinding>(
    binding = ListItemLoadingBinding.inflate(inflater)
)

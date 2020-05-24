package dev.forcetower.heroes.view.characters

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.forcetower.heroes.R
import dev.forcetower.heroes.core.extensions.inflate
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.databinding.ItemCharacterBinding

class CharacterAdapter(
    private val actions: CharacterActions
) : PagedListAdapter<MarvelCharacter, CharacterAdapter.CharacterHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        return CharacterHolder(parent.inflate(R.layout.item_character), actions)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val item = getItem(position)
        holder.binding.character = item
        holder.binding.root.setTag(R.id.character_id_tag, item?.id)
    }

    inner class CharacterHolder(
        val binding: ItemCharacterBinding,
        actions: CharacterActions
    ) : RecyclerView.ViewHolder(binding.root) {
        init { binding.actions = actions }
    }

    private object DiffCallback : DiffUtil.ItemCallback<MarvelCharacter>() {
        override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MarvelCharacter,
            newItem: MarvelCharacter
        ) = oldItem == newItem
    }
}
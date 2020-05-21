package dev.forcetower.heroes.view.characters

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.forcetower.heroes.R
import dev.forcetower.heroes.core.extensions.inflate
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.databinding.ItemCharacterBinding

class CharacterAdapter : PagedListAdapter<MarvelCharacter, CharacterAdapter.CharacterHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        return CharacterHolder(parent.inflate(R.layout.item_character))
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.binding.character = getItem(position)
    }

    inner class CharacterHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

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
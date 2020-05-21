package dev.forcetower.heroes.view.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dev.forcetower.heroes.core.base.BaseFragment
import dev.forcetower.heroes.core.base.BaseViewModelFactory
import dev.forcetower.heroes.databinding.FragmentCharactersBinding
import javax.inject.Inject

class CharactersFragment : BaseFragment() {
    @Inject
    lateinit var factory: BaseViewModelFactory

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var adapter: CharacterAdapter

    private val viewModel by viewModels<CharactersViewModel> { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = CharacterAdapter()
        return FragmentCharactersBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.charactersRecycler.apply {
            adapter = this@CharactersFragment.adapter
            itemAnimator?.let {
                it.changeDuration = 0
            }
        }
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}
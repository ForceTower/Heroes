package dev.forcetower.heroes.view.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dev.forcetower.heroes.R
import dev.forcetower.heroes.core.base.BaseFragment
import dev.forcetower.heroes.core.base.BaseViewModelFactory
import dev.forcetower.heroes.core.ui.EventObserver
import dev.forcetower.heroes.core.source.remote.datasource.helpers.Status
import dev.forcetower.heroes.databinding.FragmentCharactersBinding
import timber.log.Timber
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
        adapter = CharacterAdapter(viewModel)
        return FragmentCharactersBinding.inflate(inflater, container, false).also {
            binding = it
            binding.lifecycleOwner = viewLifecycleOwner
            binding.actions = viewModel
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

        viewModel.errorState.observe(viewLifecycleOwner, EventObserver {
            Timber.i(it, "An error happened during first load")
        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            Timber.i("Another network state triggered $it")
            if (it.status == Status.FAILED) {
                showRetrySnack()
            }
        })

        viewModel.onCharacterSelected.observe(viewLifecycleOwner, EventObserver {
            val directions = CharactersFragmentDirections.actionCharactersToDetails(it.id)

            val element = findViewForTransition(binding.charactersRecycler, it.id)
            val transitionName = getString(R.string.character_image_transition, it.id)
            element.transitionName = transitionName
            val extras = FragmentNavigatorExtras(element to transitionName)

            findNavController().navigate(directions, extras)
        })
    }

    private fun showRetrySnack() {
        getSnack(getString(R.string.network_error), Snackbar.LENGTH_INDEFINITE)?.run {
            setAction(R.string.retry) {
                dismiss()
                viewModel.retry()
            }
            show()
        }
    }

    private fun findViewForTransition(group: ViewGroup, id: Int): View {
        group.forEach {
            if (it.getTag(R.id.character_id_tag) == id) {
                return it.findViewById(R.id.thumbnail)
            }
        }
        return group
    }
}
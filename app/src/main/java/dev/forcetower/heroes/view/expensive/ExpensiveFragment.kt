package dev.forcetower.heroes.view.expensive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.forcetower.heroes.core.base.BaseFragment
import dev.forcetower.heroes.core.base.BaseViewModelFactory
import dev.forcetower.heroes.core.ui.EventObserver
import dev.forcetower.heroes.databinding.FragmentExpensiveBinding
import javax.inject.Inject

class ExpensiveFragment : BaseFragment() {
    @Inject
    lateinit var factory: BaseViewModelFactory
    private lateinit var binding: FragmentExpensiveBinding

    private val viewModel by viewModels<ExpensiveViewModel> { factory }
    private val args by navArgs<ExpensiveFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentExpensiveBinding.inflate(inflater, container, false).also {
            binding = it
            binding.apply {
                actions = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setCharacterId(args.characterId)

        viewModel.onNavigateBack.observe(viewLifecycleOwner, EventObserver {
            findNavController().popBackStack()
        })

        viewModel.error.observe(viewLifecycleOwner, EventObserver {
            showSnack(getString(it))
        })
    }
}
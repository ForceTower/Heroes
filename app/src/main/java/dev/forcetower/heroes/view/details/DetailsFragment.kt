package dev.forcetower.heroes.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import androidx.fragment.app.viewModels
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.forcetower.heroes.core.base.BaseFragment
import dev.forcetower.heroes.core.base.BaseViewModelFactory
import dev.forcetower.heroes.core.ui.EventObserver
import dev.forcetower.heroes.databinding.FragmentDetailsBinding
import javax.inject.Inject

class DetailsFragment : BaseFragment() {
    @Inject
    lateinit var factory: BaseViewModelFactory
    private lateinit var binding: FragmentDetailsBinding

    private val viewModel by viewModels<DetailsViewModel> { factory }
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.setCharacterId(args.characterId)
        return FragmentDetailsBinding.inflate(inflater, container, false).also {
            binding = it
            binding.apply {
                lifecycleOwner = viewLifecycleOwner
                actions = viewModel
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onNavigateBack.observe(viewLifecycleOwner, EventObserver {
            findNavController().popBackStack()
        })

        viewModel.onNavigateToMostExpensive.observe(viewLifecycleOwner, EventObserver {
            val directions = DetailsFragmentDirections.actionDetailsToExpensive(args.characterId)
            findNavController().navigate(directions)
        })

        val set = AnimationSet(false)
        val translation = TranslateAnimation(0f, 0f, 800f, 0f).apply {
            interpolator = LinearOutSlowInInterpolator()
            duration = 350
        }
        val fade = AlphaAnimation(0f, 1f).apply {
            interpolator = LinearInterpolator()
            duration = 250
            startOffset = 100
        }
        set.addAnimation(translation)
        set.addAnimation(fade)
        binding.details.startAnimation(set)
        set.start()
    }
}
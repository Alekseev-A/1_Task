package team.mediasoft.a.alekseev.a1_task.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import team.mediasoft.a.alekseev.a1_task.R
import team.mediasoft.a.alekseev.a1_task.databinding.MainFragmentBinding

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
        private const val CLICKS = "counter of clicks"
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val count = savedInstanceState?.getInt(CLICKS) ?: 0
        initViewModel(count)

        binding = MainFragmentBinding.bind(view)
        binding.button.setOnClickListener { viewModel.incrementClicks() }

        setupObserver()
    }

    private fun initViewModel(counter: Int) {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(counter)
        ).get(MainViewModel::class.java)
    }


    private fun setupObserver() {
        viewModel.counterLiveData.observe(
            requireActivity(),
            {
                binding.text.text = resources.getQuantityString(
                    R.plurals.text_plurals,
                    it,
                    it
                )
            }
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.counterLiveData.value?.let { outState.putInt(CLICKS, it) }
    }
}
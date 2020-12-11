package team.mediasoft.a.alekseev.a1_task.ui.main

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import team.mediasoft.a.alekseev.a1_task.R
import team.mediasoft.a.alekseev.a1_task.databinding.MainFragmentBinding

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var pref: SharedPreferences
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.setClicks(pref.getInt("clicks", 0))
        binding = MainFragmentBinding.bind(view)
        binding.button.setOnClickListener(this::onButtonClick)
        pref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        setupObserver()
    }

    private fun onButtonClick(view: View) {
        viewModel.incrementClicks()
    }

    private fun setupObserver() {
        viewModel.getClicks().observe(requireActivity(), {
            binding.text.text = resources.getQuantityString(R.plurals.text_plurals, it, it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        pref.edit().apply {
            putInt("clicks", viewModel.getClicks().value!!)
        }.apply()
    }
}
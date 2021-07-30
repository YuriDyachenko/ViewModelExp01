package dyachenko.viewmodelexp01.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dyachenko.viewmodelexp01.MainActivity
import dyachenko.viewmodelexp01.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val observer = Observer<Int> { renderData(it) }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.getInitData()

        initViews()
    }

    private fun renderData(data: Int?) = with(binding) {
        val text = "$TITLE$data"
        message.text = text

        if (data == 3) {
            val context = activity as MainActivity
            context.recreate()
        }
    }

    private fun initViews() = with(binding) {
        button.setOnClickListener {
            val data = viewModel.getLiveData().value!!
            viewModel.getLiveData().value = data.plus(1)
        }
    }

    companion object {
        private const val TITLE = "data = "

        fun newInstance() = MainFragment()
    }
}
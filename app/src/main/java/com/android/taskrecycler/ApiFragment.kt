package com.android.taskrecycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.taskrecycler.activitys.ApiViewModelFactory
import com.android.taskrecycler.retrofit.ApiRepository
import com.android.taskrecycler.retrofit.ApiViewModel

class ApiFragment : Fragment() {

    private lateinit var viewModel: ApiViewModel
    private lateinit var apiButton: Button
    private lateinit var apiTextView: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_api, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        apiButton = view.findViewById(R.id.apiButton)
        apiTextView = view.findViewById(R.id.apiTextView)
        progressBar = view.findViewById(R.id.progressBar)

        val repository = ApiRepository()
        viewModel = ViewModelProvider(this, ApiViewModelFactory(repository))
            .get(ApiViewModel::class.java)

        apiButton.setOnClickListener {
            viewModel.fetchData()
        }

        viewModel.data.observe(viewLifecycleOwner) { result ->
            apiTextView.text = result
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }
    }
}
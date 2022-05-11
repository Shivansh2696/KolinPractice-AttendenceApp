package com.example.kotlinpractice.Views.Fragments.DashboardFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.Adapter.RecyclerView.BatchRecyclerAdapter
import com.example.kotlinpractice.Model.BaseModels.BatchDetails
import com.example.kotlinpractice.R
import com.example.kotlinpractice.Views.Activities.AddClassActivity.AddClassActivity
import com.example.kotlinpractice.databinding.FragmentDashBoardBinding

class DashBoardFragment : Fragment() {
    private lateinit var binding : FragmentDashBoardBinding
    private lateinit var viewModel: DashboardFragmentViewModel
    private lateinit var recyclerView : RecyclerView
    private var adapter = BatchRecyclerAdapter()
    private var batchList :List<BatchDetails> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  FragmentDashBoardBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DashboardFragmentViewModel::class.java]
        binding.addClass.setOnClickListener(this::onclick)

        recyclerView = binding.BatchRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.getBatchLiveDataList().observe(viewLifecycleOwner){
            adapter.setList(it)
        }

    }

    private fun onclick(view: View) {
        val id = view.id
        if(id == binding.addClass.id){
            val start = Intent(context,AddClassActivity::class.java)
            startActivity(start)
        }
    }
}
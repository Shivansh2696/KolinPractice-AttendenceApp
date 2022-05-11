package com.example.kotlinpractice.Views.Fragments.DashboardFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinpractice.Model.BaseModels.BatchDetails

class DashboardFragmentViewModel : ViewModel() {
    private var dashboardFragmentRepo = DashboardFragmentRepo.instance
    init {
        dashboardFragmentRepo.getBatchDetails()
    }

    fun getBatchLiveDataList() : LiveData<List<BatchDetails>>{
        return dashboardFragmentRepo.getBatchListLiveData()
    }
}
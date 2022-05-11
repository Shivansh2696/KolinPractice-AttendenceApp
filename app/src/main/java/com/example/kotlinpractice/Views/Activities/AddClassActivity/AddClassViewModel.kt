package com.example.kotlinpractice.Views.Activities.AddClassActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinpractice.Model.BaseModels.BatchDetails

class AddClassViewModel : ViewModel() {
    private var addClassRepo : AddClassRepo = AddClassRepo()

    fun addClass(batchDetails: BatchDetails){
        addClassRepo.addClass(batchDetails)
    }

    fun getCompleteLiveData(): LiveData<Boolean?> {
        return addClassRepo.getCompleteLiveData()
    }
}
package com.example.kotlinpractice.Views.Activities.AddClassActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinpractice.Model.BaseModels.BatchDetails
import com.example.kotlinpractice.Utils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AddClassRepo {
    private var completeLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun addClass(batchDetails: BatchDetails) {
        //Creating key
        batchDetails.batchKey = Utils.uuid(batchDetails.className + batchDetails.classTime +
                                FirebaseAuth.getInstance().uid)
        batchDetails.creatorUid = FirebaseAuth.getInstance().uid
            FirebaseFirestore.getInstance().collection("ClassDetails")
                .document().set(batchDetails).addOnCompleteListener {
                    if(it.isSuccessful){
                        completeLiveData.value = true
                    }
                }
    }

    fun getCompleteLiveData(): LiveData<Boolean?> {
        return completeLiveData
    }
}
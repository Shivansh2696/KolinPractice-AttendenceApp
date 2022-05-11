package com.example.kotlinpractice.Views.Fragments.DashboardFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinpractice.Model.BaseModels.BatchDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DashboardFragmentRepo private constructor(){
    private var batchListLiveData:  MutableLiveData<List<BatchDetails>> = MutableLiveData()

    private object Holder {
        val INSTANCE = DashboardFragmentRepo()
    }
    companion object{
        val instance : DashboardFragmentRepo by lazy { Holder.INSTANCE }
    }

    fun getBatchDetails()  {
         FirebaseFirestore.getInstance().collection("ClassDetails")
            .whereEqualTo("creatorUid",FirebaseAuth.getInstance().currentUser?.uid)
             .addSnapshotListener{snapshot , Error ->
                 if (Error == null && snapshot != null){
                     batchListLiveData.value = snapshot.toObjects(BatchDetails::class.java)
                 }
             }
    }

    fun getBatchListLiveData() : LiveData<List<BatchDetails>>{
        return batchListLiveData
    }
}
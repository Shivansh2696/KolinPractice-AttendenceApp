package com.example.kotlinpractice.Views.Activities.AddClassActivity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinpractice.Dialog.ChoosePictureDialog
import com.example.kotlinpractice.Model.BaseModels.BatchDetails
import com.example.kotlinpractice.Utils
import com.example.kotlinpractice.databinding.ActivityAddClassBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import java.io.ByteArrayOutputStream


class AddClassActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddClassBinding
    private var batchDetails : BatchDetails = BatchDetails()
    private lateinit var viewModel: AddClassViewModel
    private lateinit var className : String
    private lateinit var classTime : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddClassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AddClassViewModel ::class.java]
        viewModel.getCompleteLiveData().observe(this) {
            if (it == true) {
                    binding.Progress.visibility = View.INVISIBLE
                Toast.makeText(this,"Class Added Successfully",Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        binding.cardViewImage.setOnClickListener(View.OnClickListener {
                   ChoosePictureDialog(this).show()
        })

        binding.CreateClass.setOnClickListener(View.OnClickListener {
            if(validate()) {
                batchDetails.className = className
                batchDetails.classTime = classTime
                batchDetails.teacherImage = convertImageToString(binding.ProfilePhoto)
                binding.Progress.visibility = View.VISIBLE
                viewModel.addClass(batchDetails)
            }
        })
    }

    private fun validate() : Boolean{
        className = getEditableText(binding.etClassName)
        classTime = getEditableText(binding.etClassTime)
        return when {
            className.isEmpty() -> {
                Toast.makeText(this,"Class Name Is Required",Toast.LENGTH_SHORT).show()
                false
            }
            classTime.isEmpty() -> {
                Toast.makeText(this,"Class Time Is Required",Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }

    private fun getEditableText(text : TextInputEditText) : String{
        var str = text.text
        return if( !TextUtils.isEmpty(str)){
            str.toString()
        }else ""
    }

    private fun convertImageToString(imageView: ImageView): String? {
        imageView.buildDrawingCache()
        val bitmap = imageView.drawingCache
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream)
        val b = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            103 -> if (resultCode == RESULT_OK) {
                assert(data != null)
                val selectedImage = data!!.data
                binding.ProfilePhoto.setImageURI(selectedImage)
            }
            102 -> if (resultCode == RESULT_OK) {
                assert(data != null)
                val bundle = data!!.extras
                val bitmapImage = bundle!!["data"] as Bitmap?
                binding.ProfilePhoto.setImageBitmap(bitmapImage)
            }
        }
    }

}
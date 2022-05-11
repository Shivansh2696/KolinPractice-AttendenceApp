package com.example.kotlinpractice

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import java.io.ByteArrayOutputStream
import java.util.*

 class Utils {
     companion object{
         fun uuid(key: String): String {
             var result = UUID.nameUUIDFromBytes(key.toByteArray()).toString()
             result = result.replace("-", "").substring(0, 15).uppercase()
             return result
         }
     }

     fun convertImageToString(capturedImage: ImageView): String? {
         capturedImage.buildDrawingCache()
         val bitmap = capturedImage.drawingCache
         val byteArrayOutputStream = ByteArrayOutputStream()
         bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream)
         val b = byteArrayOutputStream.toByteArray()
         return Base64.encodeToString(b, Base64.DEFAULT)
     }

     fun convertStringTOBitmap(stringImage: String?): Bitmap? {
         val decodedString = Base64.decode(stringImage, Base64.DEFAULT)
         return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
     }

}
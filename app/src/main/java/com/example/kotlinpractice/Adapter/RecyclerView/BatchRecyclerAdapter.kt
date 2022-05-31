package com.example.kotlinpractice.Adapter.RecyclerView

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.Model.BaseModels.BatchDetails
import com.example.kotlinpractice.R

class BatchRecyclerAdapter :RecyclerView.Adapter<MyViewHolder>() {
    private var list : List<BatchDetails> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.class_detail_recycler_card,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.classname.text = list[position].className
        holder.classTime.text = list[position].classTime
        var teacherImage = list[position].teacherImage
        holder.image.setImageBitmap(convertStringTOBitmap(teacherImage))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<BatchDetails>?) {
        if (list != null) {
            this.list = list
        }
    }
}
class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var classname: TextView = itemView.findViewById(R.id.ClassName)
    var classTime: TextView = itemView.findViewById(R.id.ClassTime)
    var image : ImageView = itemView.findViewById(R.id.Image)
}

fun convertStringTOBitmap(stringImage: String?): Bitmap? {
    val decodedString = Base64.decode(stringImage, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
}
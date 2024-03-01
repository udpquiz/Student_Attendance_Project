package com.example.student_attendance

import ListItem
import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Save_csv : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    private val items = mutableListOf<ListItem>()
    private val checkedItems = SparseBooleanArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_csv)

        val totalStudents = 70

        for (i in 1..totalStudents) {
            items.add(ListItem("Roll No: $i", false))
        }

        recyclerView = findViewById(R.id.recyclerView)
        adapter = CustomAdapter(items, checkedItems)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.setOnItemClickListener { position ->
            val isChecked = !checkedItems.get(position)
            checkedItems.put(position, isChecked)
            val state = if (isChecked) "present" else "absent"
            Toast.makeText(this, "Roll No: ${position + 1} is $state", Toast.LENGTH_SHORT).show()
        }
    }

    private class CustomAdapter(private val items: List<ListItem>, private val checkedItems: SparseBooleanArray) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val checkBox: CheckBox = view.findViewById(R.id.checkBox)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.checkBox.text = item.text
            holder.checkBox.isChecked = checkedItems.get(position, false)

            holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
                checkedItems.put(position, isChecked)
            }

            holder.itemView.setOnClickListener {
                onItemClickListener?.invoke(position)
            }
        }

        override fun getItemCount(): Int = items.size

        private var onItemClickListener: ((Int) -> Unit)? = null

        fun setOnItemClickListener(listener: (Int) -> Unit) {
            onItemClickListener = listener
        }
    }
}

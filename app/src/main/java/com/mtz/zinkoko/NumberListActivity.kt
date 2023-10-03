package com.mtz.zinkoko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtz.ptta.R
import com.mtz.zinkoko.data.Repository

class NumberListActivity : AppCompatActivity() {
    val recAdapter: AdapterNumberList by lazy {
        AdapterNumberList(arrayListOf()) {
            Repository.deleteByItem(it) {
                readData(intent.extras?.get("no") as Int)
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_list)
        val no = intent.extras?.get("no") as Int
        readData(no)
        //  recAdapter.setData(data.filterNotNull().toMutableList())

        findViewById<RecyclerView>(R.id.recNumberList).apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = recAdapter
        }
    }

    private fun readData(no: Int) {
        Repository.readNumberList(no) {
            recAdapter.setData(it.filterNotNull().toMutableList())
        }
    }
}
package com.mtz.zinkoko

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mtz.ptta.R
import com.mtz.zinkoko.data.NOTD
import com.mtz.zinkoko.data.Repository
import com.mtz.zinkoko.data.WeeklyReport
import com.mtz.zinkoko.util.DateUtil

class MainActivity : AppCompatActivity() {
    var adapterRec = RecAdapter(arrayListOf()) {
        goToNumberListActivity(it)
    }
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        findViewById<EditText>(R.id.tvNo).addTextChangedListener {
            it?.let {
                if (it.length >= 2) {
                    findViewById<EditText>(R.id.tvAmount).requestFocus()
                11}

            }
        }
        findViewById<Button>(R.id.delete).setOnLongClickListener {
            AlertDialog.Builder(this).setMessage("Are You sure for delete ")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    saveReport()
                    Repository.delete()
                }).setNegativeButton("NO") { dialog, which -> dialog.dismiss() }.create().show()
            return@setOnLongClickListener true
        }

        findViewById<Button>(R.id.btnAuto).setOnClickListener {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
            AutoNumberSheetDialog().show(this, null) {}

        }
        findViewById<Button>(R.id.save).setOnClickListener {
            val no = findViewById<EditText>(R.id.tvNo).text.toString()
            val amount = findViewById<EditText>(R.id.tvAmount).text.toString()

            val first = no[0]
            val second = no[1]
            if (first == second) {
                saveData(NOTD(no = no, amount = amount.toInt()))
            } else {
                if (findViewById<CheckBox>(R.id.reverse).isChecked) {
                    saveData(NOTD(no = no, amount = amount.toInt()))

                    val rno = no.reversed()

                    saveData(NOTD(no = rno, amount = amount.toInt()))
                } else {
                    saveData(NOTD(no = no, amount = amount.toInt()))
                }
            }

            /*if (no.length > 2 && no.contains("00")) {
                val firstNumber = no.substring(2)
                for (i in 0..9) {
                    Log.d("firstNO", firstNumber.toString())
                    try {
                        saveData(NOTD(no = "$firstNumber$i", amount = amount.toInt()))
                    } catch (e: Exception) {
                        Toast.makeText(baseContext, "..", Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (no.length > 2 && no.contains("11")) {

                val firstNumber = no.substring(2)
                for (i in 0..9) {
                    Log.d("firstNO", firstNumber.toString())
                    try {
                        saveData(NOTD(no = "$firstNumber$i", amount = amount.toInt()))
                    } catch (e: Exception) {
                        Toast.makeText(baseContext, "..", Toast.LENGTH_SHORT).show()
                    }
                }
                for (i in 0..9) {
                    try {
                        if (i != firstNumber.toInt()) {
                            saveData(NOTD(no = "$i$firstNumber", amount = amount.toInt()))
                        }
                    } catch (e: Exception) {
                        Toast.makeText(baseContext, "..", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                try {
                    saveData(NOTD(no = no, amount = amount.toInt()))
                } catch (e: Exception) {
                    Toast.makeText(baseContext, "..", Toast.LENGTH_SHORT).show()
                }
            }*/

        }
        readReport()
        Repository.read {
            findViewById<EditText>(R.id.tvNo).text = null
            findViewById<EditText>(R.id.tvAmount).text = null
            findViewById<TextView>(R.id.textView).text =
                "${it.sumOf { it?.amount ?: 0 }.toString()}"
            findViewById<EditText>(R.id.tvNo).requestFocus()
            mediaPlayer!!.start();
            adapterRec.setData(
                it.filterNotNull().toMutableList()
            )
        }
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = adapterRec
        }
        findViewById<CheckBox>(R.id.chkAmount).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val newData = arrayListOf<NOTD>()
                newData.addAll(adapterRec.data)
                newData.sortByDescending { it.amount }
                adapterRec.setData(newData)
            } else {
                val newData = arrayListOf<NOTD>()
                newData.addAll(adapterRec.data)
                newData.sortBy { it.no.toInt() }
                adapterRec.setData(newData)
            }
        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun saveReport() {
        val timestant = adapterRec.data[0].created_date
        val data = WeeklyReport(
            weekOfYear = DateUtil.getWeekNumberOfYearFromTimeStand(timestant),
            dayOfWeek = DateUtil.getDayOfWeekFromTimeStand(timestant),
            isMorning = DateUtil.getAmPmFromTimeStand(timestant) < 12,
            totalAmount = findViewById<TextView>(R.id.textView).text.toString().toInt()
        )
        Repository.createWeeklyReport(data)
    }

    private fun readReport() {
        Repository.readWeeklyReport { data ->
            val group = data.groupBy {
                it?.weekOfYear
            }
            val weeklyReportList = arrayListOf<ReportModel>()
            group.map {
                val sortWeekItem = it.value.sortedBy {
                    it?.dayOfWeek
                }.toMutableList()

                weeklyReportList.add(
                    ReportModel(
                        weekNumber = it.key?.toInt() ?: 0,
                        reportItem = sortWeekItem
                    )
                )

            }
        }
    }

    fun goToNumberListActivity(no: Int) {
        val intent = Intent(this, NumberListActivity::class.java)
        intent.putExtra("no", no)
        startActivity(intent)
    }

    private fun saveData(data: NOTD) {
        Repository.create(data)
    }
}
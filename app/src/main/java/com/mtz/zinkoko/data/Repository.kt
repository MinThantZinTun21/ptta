package com.mtz.zinkoko.data

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.Calendar
import java.util.Date
import java.util.UUID

object Repository {
    val database = Firebase.database
  //  val myNumberRef = database.getReference("pyaeapk")
  val myNumberRef = database.getReference("zinkoko")
    val reportReference = database.getReference("report")
    //val myNumberRef = database.getReference("sss")

    init {
    }


    fun create(data: NOTD) {
        val newReference = myNumberRef.push()
        newReference.setValue(data).addOnFailureListener {
            Log.d("error", it.message.toString())
        }
    }

    fun createWeeklyReport(data: WeeklyReport) {
        val newRefernce = reportReference.push()
        newRefernce.setValue(data).addOnFailureListener {
            Log.d("error", it.message.toString())

        }
    }


    fun readWeeklyReport(
        onValueChante: (data: MutableList<WeeklyReport?>) -> Unit
    ) {

        reportReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                reportReference.get().addOnSuccessListener {
                    val data = it.children.map {
                        it.getValue(WeeklyReport::class.java)
                    }.toMutableList()
                    onValueChante(data)

                }

            }

            override fun onCancelled(error: DatabaseError) {


            }

        })
    }

    fun read(onValueChange: (data: MutableList<NOTD?>) -> Unit) {
        myNumberRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                myNumberRef.get().addOnSuccessListener {
                    val list = it.children.map {
                        it.getValue(NOTD::class.java)
                    }.toList()


                    val finalData = list.groupingBy {
                        it!!.no
                    }.reduce { key, accumulator, element ->
                        NOTD(element!!.id, element.no, accumulator!!.amount + element.amount)
                    }.toList().sortedByDescending {
                        it.second?.amount
                    }
                    Log.d("ç", finalData.toString())
                    onValueChange(finalData.map { it.second }.toMutableList())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("c", error.message.toString())
            }


        })
    }

    fun delete() {
        myNumberRef.removeValue()
    }
}


@IgnoreExtraProperties
data class NOTD(
    val id: String = UUID.randomUUID().toString(),
    val no: String = "",
    val amount: Int = 0,
    val created_date: Long = System.currentTimeMillis()
)

@IgnoreExtraProperties
data class WeeklyReport(
    val id: String = UUID.randomUUID().toString(),
    val totalAmount: Int = 0,
    val weekOfYear: Int = 0,
    val winnerNumber: Int = 0,
    val winnerAmount: Int = 0,
    val dayOfWeek: Int = 0,
    val isMorning: Boolean = false,
    val created_date: Long = System.currentTimeMillis()
)


package com.mtz.zinkoko

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.maxkeppeler.sheets.core.PositiveListener
import com.maxkeppeler.sheets.core.Sheet
import com.mtz.ptta.R
import com.mtz.zinkoko.data.NOTD
import com.mtz.zinkoko.data.Repository

class AutoNumberSheetDialog : Sheet() {
    val adapterNo by lazy {
        RecAdapter(arrayListOf())
    }

    val onPositiveListener: PositiveListener = object : PositiveListener {
        override fun invoke() {
            view?.let {
                it.hideKeyboard()
            }
            if (adapterNo.data.size > 0) {
                adapterNo.data.map {
                    Repository.create(it)
                }
            }
        }


    }

    override fun onCreateLayoutView(): View {
        return LayoutInflater.from(activity).inflate(R.layout.auto_number_sheet_bottom, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.positiveListener = onPositiveListener
        view.findViewById<RecyclerView>(R.id.recNumberf).apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = adapterNo
        }

        view.findViewById<TextInputEditText>(R.id.tvNo).addTextChangedListener {

            if (it.toString().isNotEmpty()) {
                view.findViewById<TextInputEditText>(R.id.tvAmount_te).requestFocus()

            }

        }

        view.findViewById<Chip>(R.id.chipsame10)
            .setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked) {
                    val numberList = arrayListOf<NOTD>()
                    for (i in 0..9) {
                        numberList.add(NOTD(no = "${i}${i}"))
                    }
                    view.findViewById<CardView>(R.id.cardAmount).visibility = View.VISIBLE
                    view.findViewById<TextInputLayout>(R.id.tvNo_t3).visibility = View.GONE
                    adapterNo.setData(numberList)

                }
            }


        view.findViewById<Chip>(R.id.chipPower5)
            .setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked) {
                    val numberList = arrayListOf<NOTD>(
                        NOTD(no = "05", amount = 0),
                        NOTD(no = "61", amount = 0),
                        NOTD(no = "72", amount = 0),
                        NOTD(no = "83", amount = 0),
                        NOTD(no = "94", amount = 0),
                        NOTD(no = "50", amount = 0),
                        NOTD(no = "16", amount = 0),
                        NOTD(no = "27", amount = 0),
                        NOTD(no = "38", amount = 0),
                        NOTD(no = "49", amount = 0),

                        )
                    view.findViewById<CardView>(R.id.cardAmount).visibility = View.VISIBLE
                    view.findViewById<TextInputLayout>(R.id.tvNo_t3).visibility = View.GONE
                    adapterNo.setData(numberList)
                }


            }
        view.findViewById<CheckBox>(R.id.checkBox)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    view.findViewById<TextInputLayout>(R.id.tvAmountR).visibility = View.VISIBLE

                } else {
                    view.findViewById<TextInputLayout>(R.id.tvAmountR).visibility = View.INVISIBLE

                }
            }
        view.findViewById<Chip>(R.id.chipBreak)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                adapterNo.data.clear()
                adapterNo.notifyDataSetChanged()
                if (isChecked) {
                    view.findViewById<CardView>(R.id.cardAmount).visibility = View.VISIBLE
                    view.findViewById<TextInputLayout>(R.id.tvNo_t3).visibility = View.VISIBLE
                    view.findViewById<CheckBox>(R.id.checkBox).visibility = View.VISIBLE

                }


            }
        view.findViewById<Chip>(R.id.chipFront10)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                adapterNo.data.clear()
                adapterNo.notifyDataSetChanged()
                if (isChecked) {
                    view.findViewById<CardView>(R.id.cardAmount).visibility = View.VISIBLE
                    view.findViewById<TextInputLayout>(R.id.tvNo_t3).visibility = View.VISIBLE


                }


            }
        view.findViewById<Chip>(R.id.chipBack10)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    adapterNo.data.clear()
                    adapterNo.notifyDataSetChanged()
                    view.findViewById<CardView>(R.id.cardAmount).visibility = View.VISIBLE
                    view.findViewById<TextInputLayout>(R.id.tvNo_t3).visibility = View.VISIBLE


                }


            }

        view.findViewById<Chip>(R.id.chipFrontBack19)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    adapterNo.data.clear()
                    adapterNo.notifyDataSetChanged()
                    view.findViewById<CardView>(R.id.cardAmount).visibility = View.VISIBLE
                    view.findViewById<TextInputLayout>(R.id.tvNo_t3).visibility = View.VISIBLE


                }


            }


        view.findViewById<Chip>(R.id.chipNyiKo)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val numberList = arrayListOf<NOTD>(
                        NOTD(no = "01", amount = 0),
                        NOTD(no = "12", amount = 0),
                        NOTD(no = "23", amount = 0),
                        NOTD(no = "34", amount = 0),
                        NOTD(no = "45", amount = 0),
                        NOTD(no = "56", amount = 0),
                        NOTD(no = "67", amount = 0),
                        NOTD(no = "78", amount = 0),
                        NOTD(no = "89", amount = 0),
                        NOTD(no = "90", amount = 0),
                    )
                    view.findViewById<CardView>(R.id.cardAmount).visibility = View.VISIBLE
                    view.findViewById<TextInputLayout>(R.id.tvNo_t3).visibility = View.GONE
                    adapterNo.setData(numberList)
                }
            }
        view.findViewById<Chip>(R.id.chipNyiKo20)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val numberList = arrayListOf<NOTD>(
                        NOTD(no = "01", amount = 0),
                        NOTD(no = "12", amount = 0),
                        NOTD(no = "23", amount = 0),
                        NOTD(no = "34", amount = 0),
                        NOTD(no = "45", amount = 0),
                        NOTD(no = "56", amount = 0),
                        NOTD(no = "67", amount = 0),
                        NOTD(no = "78", amount = 0),
                        NOTD(no = "89", amount = 0),
                        NOTD(no = "90", amount = 0),
                        NOTD(no = "10", amount = 0),
                        NOTD(no = "21", amount = 0),
                        NOTD(no = "32", amount = 0),
                        NOTD(no = "43", amount = 0),
                        NOTD(no = "54", amount = 0),
                        NOTD(no = "65", amount = 0),
                        NOTD(no = "76", amount = 0),
                        NOTD(no = "87", amount = 0),
                        NOTD(no = "98", amount = 0),
                        NOTD(no = "09", amount = 0),


                        )
                    view.findViewById<CardView>(R.id.cardAmount).visibility = View.VISIBLE
                    view.findViewById<TextInputLayout>(R.id.tvNo_t3).visibility = View.GONE
                    adapterNo.setData(numberList)
                }
            }
        view.findViewById<Chip>(R.id.chipNatKha)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val numberList = arrayListOf<NOTD>(
                        NOTD(no = "07", amount = 0),
                        NOTD(no = "18", amount = 0),
                        NOTD(no = "24", amount = 0),
                        NOTD(no = "35", amount = 0),
                        NOTD(no = "69", amount = 0),
                        NOTD(no = "70", amount = 0),
                        NOTD(no = "81", amount = 0),
                        NOTD(no = "42", amount = 0),
                        NOTD(no = "53", amount = 0),
                        NOTD(no = "96", amount = 0),
                    )
                    view.findViewById<CardView>(R.id.cardAmount).visibility = View.VISIBLE
                    view.findViewById<TextInputLayout>(R.id.tvNo_t3).visibility = View.GONE
                    adapterNo.setData(numberList)
                }


            }

        view.findViewById<Button>(R.id.btnConfirm).setOnClickListener {
            view?.let {
                it.hideKeyboard()
            }


            if (view.findViewById<Chip>(R.id.chipsame10).isChecked || view.findViewById<Chip>(R.id.chipPower5).isChecked ||
                view.findViewById<Chip>(R.id.chipNatKha).isChecked || view.findViewById<Chip>(R.id.chipNyiKo20).isChecked
                || view.findViewById<Chip>(R.id.chipNyiKo).isChecked
            ) {
                val data = adapterNo.data.map {
                    it.copy(
                        amount = view.findViewById<TextInputEditText>(R.id.tvAmount_te).text.toString()
                            .toInt()
                    )
                }.toMutableList()
                adapterNo.setData(data)
            } else if (view.findViewById<Chip>(R.id.chipBreak).isChecked) {
                val no = view.findViewById<TextInputEditText>(R.id.tvNo).text.toString().toInt()
                val numberList = arrayListOf<NOTD>()
                when (no) {
                    1 -> {
                        numberList.add(NOTD(no = "10", amount = 0))
                        numberList.add(NOTD(no = "29", amount = 0))
                        numberList.add(NOTD(no = "38", amount = 0))
                        numberList.add(NOTD(no = "47", amount = 0))
                        numberList.add(NOTD(no = "56", amount = 0))
                        adapterNo.setData(numberList)
                        val data = adapterNo.data.map {
                            it.copy(
                                amount = view.findViewById<TextInputEditText>(R.id.tvAmount_te).text.toString()
                                    .toInt()
                            )
                        }.toMutableList()
                        adapterNo.setData(data)


                        if (view.findViewById<CheckBox>(R.id.checkBox).isChecked) {
                            val numberListR = arrayListOf<NOTD>()

                            numberListR.add(
                                NOTD(
                                    no = "01",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "92",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "83",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "74",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "65",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            adapterNo.addData(numberListR)
                        }


                    }

                    2 -> {
                        numberList.add(NOTD(no = "11", amount = 0))
                        numberList.add(NOTD(no = "02", amount = 0))
                        numberList.add(NOTD(no = "39", amount = 0))
                        numberList.add(NOTD(no = "48", amount = 0))
                        numberList.add(NOTD(no = "75", amount = 0))
                        numberList.add(NOTD(no = "66", amount = 0))
                        adapterNo.setData(numberList)
                        val data = adapterNo.data.map {
                            it.copy(
                                amount = view.findViewById<TextInputEditText>(R.id.tvAmount_te).text.toString()
                                    .toInt()
                            )
                        }.toMutableList()
                        adapterNo.setData(data)



                        if (view.findViewById<CheckBox>(R.id.checkBox).isChecked) {
                            val numberListR = arrayListOf<NOTD>()

                            numberListR.add(
                                NOTD(
                                    no = "20",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "93",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "84",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "75",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "66",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            adapterNo.addData(numberListR)
                        }


                    }

                    3 -> {
                        numberList.add(NOTD(no = "12", amount = 0))
                        numberList.add(NOTD(no = "03", amount = 0))
                        numberList.add(NOTD(no = "49", amount = 0))
                        numberList.add(NOTD(no = "58", amount = 0))
                        numberList.add(NOTD(no = "67", amount = 0))
                        adapterNo.setData(numberList)
                        val data = adapterNo.data.map {
                            it.copy(
                                amount = view.findViewById<TextInputEditText>(R.id.tvAmount_te).text.toString()
                                    .toInt()
                            )
                        }.toMutableList()
                        adapterNo.setData(data)


                        if (view.findViewById<CheckBox>(R.id.checkBox).isChecked) {
                            val numberListR = arrayListOf<NOTD>()

                            numberListR.add(
                                NOTD(
                                    no = "21",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "94",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "85",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "76",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "30",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            adapterNo.addData(numberListR)
                        }


                    }

                    4 -> {
                        numberList.add(NOTD(no = "13", amount = 0))
                        numberList.add(NOTD(no = "22", amount = 0))
                        numberList.add(NOTD(no = "04", amount = 0))
                        numberList.add(NOTD(no = "59", amount = 0))
                        numberList.add(NOTD(no = "86", amount = 0))
                        numberList.add(NOTD(no = "77", amount = 0))
                        adapterNo.setData(numberList)
                        val data = adapterNo.data.map {
                            it.copy(
                                amount = view.findViewById<TextInputEditText>(R.id.tvAmount_te).text.toString()
                                    .toInt()
                            )
                        }.toMutableList()
                        adapterNo.setData(data)


                        if (view.findViewById<CheckBox>(R.id.checkBox).isChecked) {
                            val numberListR = arrayListOf<NOTD>()

                            numberListR.add(
                                NOTD(
                                    no = "31",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "40",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "95",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "68",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            adapterNo.addData(numberListR)
                            adapterNo.notifyDataSetChanged()
                        }
                    }


                    5 -> {
                        numberList.add(NOTD(no = "14", amount = 0))
                        numberList.add(NOTD(no = "23", amount = 0))
                        numberList.add(NOTD(no = "05", amount = 0))
                        numberList.add(NOTD(no = "78", amount = 0))
                        numberList.add(NOTD(no = "96", amount = 0))
                        adapterNo.setData(numberList)
                        val data = adapterNo.data.map {
                            it.copy(
                                amount = view.findViewById<TextInputEditText>(R.id.tvAmount_te).text.toString()
                                    .toInt()
                            )
                        }.toMutableList()
                        adapterNo.setData(data)
                        if (view.findViewById<CheckBox>(R.id.checkBox).isChecked) {
                            val numberListR = arrayListOf<NOTD>()

                            numberListR.add(
                                NOTD(
                                    no = "41",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "32",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "50",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "87",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "69",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            adapterNo.addData(numberListR)
                        }

                    }

                    6 -> {
                        numberList.add(NOTD(no = "15", amount = 0))
                        numberList.add(NOTD(no = "24", amount = 0))
                        numberList.add(NOTD(no = "33", amount = 0))
                        numberList.add(NOTD(no = "88", amount = 0))
                        numberList.add(NOTD(no = "79", amount = 0))
                        numberList.add(NOTD(no = "06", amount = 0))
                        adapterNo.setData(numberList)



                        if (view.findViewById<CheckBox>(R.id.checkBox).isChecked) {
                            val numberListR = arrayListOf<NOTD>()

                            numberListR.add(
                                NOTD(
                                    no = "51",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "42",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "97",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "60",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            adapterNo.addData(numberListR)
                        }

                        val data = adapterNo.data.map {
                            it.copy(
                                amount = view.findViewById<TextInputEditText>(R.id.tvAmount_te).text.toString()
                                    .toInt()
                            )
                        }.toMutableList()
                        adapterNo.setData(data)
                    }

                    7 -> {
                        numberList.add(NOTD(no = "16", amount = 0))
                        numberList.add(NOTD(no = "25", amount = 0))
                        numberList.add(NOTD(no = "34", amount = 0))
                        numberList.add(NOTD(no = "07", amount = 0))
                        numberList.add(NOTD(no = "89", amount = 0))
                        adapterNo.setData(numberList)



                        if (view.findViewById<CheckBox>(R.id.checkBox).isChecked) {
                            val numberListR = arrayListOf<NOTD>()

                            numberListR.add(
                                NOTD(
                                    no = "61",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "52",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "89",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "70",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            adapterNo.addData(numberListR)
                        }


                        val data = adapterNo.data.map {
                            it.copy(
                                amount = view.findViewById<TextInputEditText>(R.id.tvAmount_te).text.toString()
                                    .toInt()
                            )
                        }.toMutableList()
                        adapterNo.setData(data)
                    }

                    8 -> {
                        numberList.add(NOTD(no = "08", amount = 0))
                        numberList.add(NOTD(no = "17", amount = 0))
                        numberList.add(NOTD(no = "26", amount = 0))
                        numberList.add(NOTD(no = "35", amount = 0))
                        numberList.add(NOTD(no = "44", amount = 0))
                        numberList.add(NOTD(no = "99", amount = 0))
                        adapterNo.setData(numberList)
                        val data = adapterNo.data.map {
                            it.copy(
                                amount = view.findViewById<TextInputEditText>(R.id.tvAmount_te).text.toString()
                                    .toInt()
                            )
                        }.toMutableList()
                        adapterNo.setData(data)



                        if (view.findViewById<CheckBox>(R.id.checkBox).isChecked) {
                            val numberListR = arrayListOf<NOTD>()

                            numberListR.add(
                                NOTD(
                                    no = "80",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "71",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "62",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "53",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            adapterNo.addData(numberListR)
                        }
                    }

                    9 -> {
                        numberList.add(NOTD(no = "09", amount = 0))
                        numberList.add(NOTD(no = "18", amount = 0))
                        numberList.add(NOTD(no = "27", amount = 0))
                        numberList.add(NOTD(no = "36", amount = 0))
                        numberList.add(NOTD(no = "45", amount = 0))

                        adapterNo.setData(numberList)
                        val data = adapterNo.data.map {
                            it.copy(
                                amount = view.findViewById<TextInputEditText>(R.id.tvAmount_te).text.toString()
                                    .toInt()
                            )
                        }.toMutableList()
                        adapterNo.setData(data)


                        if (view.findViewById<CheckBox>(R.id.checkBox).isChecked) {
                            val numberListR = arrayListOf<NOTD>()

                            numberListR.add(
                                NOTD(
                                    no = "90",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "54",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "81",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "72",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            numberListR.add(
                                NOTD(
                                    no = "63",
                                    amount = view.findViewById<TextInputEditText>(R.id.tvAmount_teR).text.toString()
                                        .toInt()
                                )
                            )
                            adapterNo.addData(numberListR)
                        }
                    }

                    10 -> {
                        numberList.add(NOTD(no = "55", amount = 0))
                        numberList.add(NOTD(no = "28", amount = 0))
                        numberList.add(NOTD(no = "37", amount = 0))
                        numberList.add(NOTD(no = "46", amount = 0))
                        numberList.add(NOTD(no = "91", amount = 0))
                        adapterNo.setData(numberList)
                        val data = adapterNo.data.map {
                            it.copy(
                                amount = view.findViewById<TextInputEditText>(R.id.tvAmount_te).text.toString()
                                    .toInt()
                            )
                        }.toMutableList()
                        adapterNo.setData(data)
                    }

                    else -> {}
                }

            } else if (view.findViewById<Chip>(R.id.chipBack10).isChecked) {

                val numberList = arrayListOf<NOTD>()
                val backNO = view.findViewById<TextInputEditText>(R.id.tvNo).text.toString().toInt()

                for (i in 0..9) {
                    numberList.add(NOTD(no = "${i}${backNO}"))
                }

                adapterNo.setData(numberList)
                val data = adapterNo.data.map {
                    it.copy(
                        amount = view.findViewById<TextInputEditText>(R.id.tvAmount_te).text.toString()
                            .toInt()
                    )
                }.toMutableList()
                adapterNo.setData(data)

            } else if (view.findViewById<Chip>(R.id.chipFront10).isChecked) {

                val numberList = arrayListOf<NOTD>()
                val frontNO =
                    view.findViewById<TextInputEditText>(R.id.tvNo).text.toString().toInt()

                for (i in 0..9) {
                    numberList.add(NOTD(no = "${frontNO}${i}"))
                }

                adapterNo.setData(numberList)
                val data = adapterNo.data.map {
                    it.copy(
                        amount = view.findViewById<TextInputEditText>(R.id.tvAmount_te).text.toString()
                            .toInt()
                    )
                }.toMutableList()
                adapterNo.setData(data)

            } else if (view.findViewById<Chip>(R.id.chipFrontBack19).isChecked) {

                val numberList = arrayListOf<NOTD>()
                val number = view.findViewById<TextInputEditText>(R.id.tvNo).text.toString().toInt()

                for (i in 0..9) {
                    numberList.add(NOTD(no = "${number}${i}"))
                }

                for (i in 0..9) {
                    if (i != number) numberList.add(NOTD(no = "${i}${number}"))
                }

                adapterNo.setData(numberList)
                val data = adapterNo.data.map {
                    it.copy(
                        amount = view.findViewById<TextInputEditText>(R.id.tvAmount_te).text.toString()
                            .toInt()
                    )
                }.toMutableList()
                adapterNo.setData(data)

            }
        }


    }


    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

    }

    fun show(
        ctx: Context, width: Int? = null, func: AutoNumberSheetDialog.() -> Unit
    ): AutoNumberSheetDialog {
        this.windowContext = ctx
        this.width = width
        this.func()
        this.show()
        return this
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}



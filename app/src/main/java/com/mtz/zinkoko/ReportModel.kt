package com.mtz.zinkoko

import com.mtz.zinkoko.data.WeeklyReport

data class ReportModel(
    val weekNumber: Int,
    val reportItem: List<WeeklyReport?> = arrayListOf()
)
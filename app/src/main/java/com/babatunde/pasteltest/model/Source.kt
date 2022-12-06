package com.babatunde.pasteltest.model

import androidx.room.ColumnInfo

data class Source(
    @ColumnInfo(name = "source_id")
    val id: String?,
    @ColumnInfo(name = "source_name")
    val name: String
)
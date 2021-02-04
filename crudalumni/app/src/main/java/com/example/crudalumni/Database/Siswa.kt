package com.example.crudalumni.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "siswa")
data class Siswa(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nama") val nama: String,
    @ColumnInfo(name = "tahunangkatan") val tahunangkatan: Int,
    @ColumnInfo(name = "jurusan") val jurusan: Int
)
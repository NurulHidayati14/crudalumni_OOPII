package com.example.crudalumni.Database

import androidx.room.*

@Dao
interface SiswaDao {
    @Insert
    suspend fun addSiswa(siswa: Siswa)

    @Update
    suspend fun updateSiswa(siswa: Siswa)

    @Delete
    suspend fun deleteSiswa(siswa: Siswa)

    @Query("SELECT * FROM siswa")
    suspend fun getAllSiswa(): List<Siswa>

    @Query("SELECT * FROM siswa WHERE id=:siswa_id")
    suspend fun getSiswa(siswa_id: Int) : List<Siswa>

}
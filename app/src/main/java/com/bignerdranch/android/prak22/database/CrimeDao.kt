package com.bignerdranch.android.prak22.database

import androidx.lifecycle.LiveData
import java.util.*
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bignerdranch.android.prak22.Crime

@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")
    fun getCrimes(): LiveData<List<Crime>>
    @Query("SELECT * FROM crime WHERE id=(:id)")
    fun getCrime(id: UUID): LiveData<Crime?>
    @Update
    fun updateCrime(crime: Crime)
    @Insert
    fun addCrime(crime: Crime)
    @Delete
    fun killCrime(crime:Crime)
}
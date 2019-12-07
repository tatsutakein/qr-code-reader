package com.takechee.qrcodereader.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * The Data Access Object for the [ReadCodeEntity] class.
 */
@Dao
interface ReadCodeDao {

    @Query("SELECT * FROM read_code")
    fun readCodesLiveData(): LiveData<List<ReadCodeEntity>>

    @Query("SELECT * FROM read_code")
    fun readCodes(): List<ReadCodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(readCodes: List<ReadCodeEntity>)

    @Query("DELETE FROM read_code")
    fun deleteAll()

}
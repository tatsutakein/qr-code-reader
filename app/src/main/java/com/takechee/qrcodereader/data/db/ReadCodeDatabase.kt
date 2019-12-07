package com.takechee.qrcodereader.data.db

import androidx.room.RoomDatabase
import javax.inject.Inject

interface ReadCodeDatabase {
}

class ReadCodeRoomDatabase @Inject constructor(
    private val database: RoomDatabase,
    private val readCodeDao: ReadCodeDao
) : ReadCodeDatabase {

}
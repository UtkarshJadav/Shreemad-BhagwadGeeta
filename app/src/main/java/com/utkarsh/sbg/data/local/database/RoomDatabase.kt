package com.farmit.data.local.database

import com.utkarsh.sbg.data.local.database.Dao


abstract class RoomDatabase : androidx.room.RoomDatabase() {
    abstract fun dao(): Dao
}
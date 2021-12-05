package com.farmit.data.local.database

import com.utkarsh.revisecode.data.local.database.Dao


abstract class RoomDatabase : androidx.room.RoomDatabase() {
    abstract fun dao(): Dao
}
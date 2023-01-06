package com.example.cfttestapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cfttestapp.db.dao.Dao
import com.example.cfttestapp.db.model.RequestString

@Database(entities = [RequestString::class], version = 3)
abstract class RequestDatabase : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: RequestDatabase? = null

        fun getDataBase(context: Context): RequestDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RequestDatabase::class.java,
                        "request_list.db"
                    ).fallbackToDestructiveMigration().build()
                    instance
                }
        }
    }

}
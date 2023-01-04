package com.example.cfttestapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cfttestapp.db.model.RequestString
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM requests_table")
    fun getAllRequests(): Flow<List<RequestString>>

    @Insert
    suspend fun insertRequest(request:RequestString)
}
package com.example.androidbp.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.androidbp.data.models.DemoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface DemoDao {

    @Query("select * from DemoEntity")
    fun getAllEntities(): List<DemoEntity>

    @Insert(onConflict = REPLACE)
    fun addEntity(entity: DemoEntity)

    @Delete
    fun deleteEntity(entity: DemoEntity)
}

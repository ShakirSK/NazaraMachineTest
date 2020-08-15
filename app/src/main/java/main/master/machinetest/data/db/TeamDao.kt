package main.master.machinetest.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import main.master.machinetest.data.model.Team

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(team: List<Team>):List<Long>

    @Query("SELECT * FROM Team")
    fun getteam() : LiveData<List<Team>>
}
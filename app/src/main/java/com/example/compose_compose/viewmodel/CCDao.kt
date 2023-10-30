package com.example.compose_compose.viewmodel


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface CCDao {
    @Query("SELECT * FROM trees ORDER BY name")
    fun getTrees(): Flow<List<Tree>>


//    @Query("SELECT * FROM trees WHERE id = :treeid")
//    fun getPlant(plantId: String): Flow<Tree>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTree(tree: Tree)
}

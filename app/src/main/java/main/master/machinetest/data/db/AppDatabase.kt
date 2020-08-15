package main.master.machinetest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import main.master.machinetest.data.model.Team

@Database(
    entities = [Team ::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getTeamDao(): TeamDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "FootballDatabase.db"
            ).build()
    }
}
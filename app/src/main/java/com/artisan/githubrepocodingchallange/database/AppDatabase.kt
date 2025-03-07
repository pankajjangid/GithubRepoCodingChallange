package com.artisan.githubrepocodingchallange.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.artisan.githubrepocodingchallange.model.Repository

/**
 * Room database for the application.
 */
@Database(entities = [Repository::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "square_repos_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

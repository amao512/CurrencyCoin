package com.aslnstbk.currencycoin.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aslnstbk.currencycoin.data.models.FavoriteCoinEntity

@Database(entities = [FavoriteCoinEntity::class], version = 1)
abstract class FavoriteCoinsDatabase : RoomDatabase() {

    abstract fun favoriteCoinsDao(): FavoriteCoinDao

    companion object {

        @Volatile
        private var INSTANCE: FavoriteCoinsDatabase? = null

        fun getInstance(context: Context): FavoriteCoinsDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteCoinsDatabase::class.java,
                    "test_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance

                return instance
            }
        }
    }
}
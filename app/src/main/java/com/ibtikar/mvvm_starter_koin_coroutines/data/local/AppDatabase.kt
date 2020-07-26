package com.ibtikar.mvvm_starter_koin_coroutines.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Result::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

//    abstract fun productDao(): ProductDao

//    companion object {
//        @Volatile private var instance: AppDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
//            instance ?: buildDatabase(context).also { instance = it}
//        }
//
//        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
//            AppDatabase::class.java, "product-list.db")
//            .build()
//
//    }
}
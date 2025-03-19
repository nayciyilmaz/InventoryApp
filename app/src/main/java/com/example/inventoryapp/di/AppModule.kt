package com.example.inventoryapp.di

import android.content.Context
import androidx.room.Room
import com.example.inventoryapp.roomdb.InventoryDao
import com.example.inventoryapp.roomdb.InventoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesInventoryDao(inventoryDatabase: InventoryDatabase): InventoryDao =
        inventoryDatabase.inventoryDao()

    @Singleton
    @Provides
    fun providesInventoryDatabase(@ApplicationContext context: Context): InventoryDatabase =
        Room.databaseBuilder(
            context,
            InventoryDatabase::class.java,
            name = "items"
        ).fallbackToDestructiveMigration().build()
}
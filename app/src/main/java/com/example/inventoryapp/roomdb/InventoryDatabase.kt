package com.example.inventoryapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 2, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao
}
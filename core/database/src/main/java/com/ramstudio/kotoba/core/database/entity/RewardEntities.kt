package com.ramstudio.kotoba.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reward_points")
data class RewardPoint(
    @PrimaryKey val id: String = "default_user",
    val points: Int = 0
)

@Entity(tableName = "shop_items")
data class ShopItem(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val category: String, // theme, skin, animation
    val isAvailable: Boolean = true
)

@Entity(tableName = "user_inventory")
data class UserInventory(
    @PrimaryKey val itemId: String,
    val isEquipped: Boolean = false
)

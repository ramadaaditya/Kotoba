package com.ramstudio.kotoba.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ramstudio.kotoba.core.database.dao.KanaDao
import com.ramstudio.kotoba.core.database.dao.SrsDao
import com.ramstudio.kotoba.core.database.entity.KanaCharacter
import com.ramstudio.kotoba.core.database.entity.QuizHistory
import com.ramstudio.kotoba.core.database.entity.RewardPoint
import com.ramstudio.kotoba.core.database.entity.ShopItem
import com.ramstudio.kotoba.core.database.entity.SrsItem
import com.ramstudio.kotoba.core.database.entity.UserInventory

@Database(
    entities = [
        KanaCharacter::class,
        QuizHistory::class,
        SrsItem::class,
        RewardPoint::class,
        ShopItem::class,
        UserInventory::class
    ],
    version = 1,
    exportSchema = false
)
abstract class KotobaDatabase : RoomDatabase() {
    abstract fun kanaDao(): KanaDao
    abstract fun srsDao(): SrsDao
    
    // DAOs for other entities will be added as needed
}

package com.ramstudio.kotoba.core.database

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.ramstudio.kotoba.core.database.dao.KanaDao
import com.ramstudio.kotoba.core.database.dao.KanaDao_Impl
import com.ramstudio.kotoba.core.database.dao.SrsDao
import com.ramstudio.kotoba.core.database.dao.SrsDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class KotobaDatabase_Impl : KotobaDatabase() {
  private val _kanaDao: Lazy<KanaDao> = lazy {
    KanaDao_Impl(this)
  }

  private val _srsDao: Lazy<SrsDao> = lazy {
    SrsDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1, "febba1e930f6b22ec1b5bd1fec4ce963", "922fb396747ea0d32707436ec2b25fab") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `kana_characters` (`id` TEXT NOT NULL, `character` TEXT NOT NULL, `romaji` TEXT NOT NULL, `category` TEXT NOT NULL, `type` TEXT NOT NULL, `audioResName` TEXT NOT NULL, `strokeOrderJson` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `quiz_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `characterId` TEXT NOT NULL, `isCorrect` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `srs_items` (`characterId` TEXT NOT NULL, `repetitionCount` INTEGER NOT NULL, `intervalDays` INTEGER NOT NULL, `easeFactor` REAL NOT NULL, `nextReviewTimestamp` INTEGER NOT NULL, PRIMARY KEY(`characterId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `reward_points` (`id` TEXT NOT NULL, `points` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `shop_items` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `price` INTEGER NOT NULL, `category` TEXT NOT NULL, `isAvailable` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `user_inventory` (`itemId` TEXT NOT NULL, `isEquipped` INTEGER NOT NULL, PRIMARY KEY(`itemId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'febba1e930f6b22ec1b5bd1fec4ce963')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `kana_characters`")
        connection.execSQL("DROP TABLE IF EXISTS `quiz_history`")
        connection.execSQL("DROP TABLE IF EXISTS `srs_items`")
        connection.execSQL("DROP TABLE IF EXISTS `reward_points`")
        connection.execSQL("DROP TABLE IF EXISTS `shop_items`")
        connection.execSQL("DROP TABLE IF EXISTS `user_inventory`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsKanaCharacters: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsKanaCharacters.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKanaCharacters.put("character", TableInfo.Column("character", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKanaCharacters.put("romaji", TableInfo.Column("romaji", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKanaCharacters.put("category", TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKanaCharacters.put("type", TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKanaCharacters.put("audioResName", TableInfo.Column("audioResName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKanaCharacters.put("strokeOrderJson", TableInfo.Column("strokeOrderJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysKanaCharacters: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesKanaCharacters: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoKanaCharacters: TableInfo = TableInfo("kana_characters", _columnsKanaCharacters, _foreignKeysKanaCharacters, _indicesKanaCharacters)
        val _existingKanaCharacters: TableInfo = read(connection, "kana_characters")
        if (!_infoKanaCharacters.equals(_existingKanaCharacters)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |kana_characters(com.ramstudio.kotoba.core.database.entity.KanaCharacter).
              | Expected:
              |""".trimMargin() + _infoKanaCharacters + """
              |
              | Found:
              |""".trimMargin() + _existingKanaCharacters)
        }
        val _columnsQuizHistory: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsQuizHistory.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsQuizHistory.put("characterId", TableInfo.Column("characterId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsQuizHistory.put("isCorrect", TableInfo.Column("isCorrect", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsQuizHistory.put("timestamp", TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysQuizHistory: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesQuizHistory: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoQuizHistory: TableInfo = TableInfo("quiz_history", _columnsQuizHistory, _foreignKeysQuizHistory, _indicesQuizHistory)
        val _existingQuizHistory: TableInfo = read(connection, "quiz_history")
        if (!_infoQuizHistory.equals(_existingQuizHistory)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |quiz_history(com.ramstudio.kotoba.core.database.entity.QuizHistory).
              | Expected:
              |""".trimMargin() + _infoQuizHistory + """
              |
              | Found:
              |""".trimMargin() + _existingQuizHistory)
        }
        val _columnsSrsItems: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSrsItems.put("characterId", TableInfo.Column("characterId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSrsItems.put("repetitionCount", TableInfo.Column("repetitionCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSrsItems.put("intervalDays", TableInfo.Column("intervalDays", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSrsItems.put("easeFactor", TableInfo.Column("easeFactor", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSrsItems.put("nextReviewTimestamp", TableInfo.Column("nextReviewTimestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSrsItems: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSrsItems: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoSrsItems: TableInfo = TableInfo("srs_items", _columnsSrsItems, _foreignKeysSrsItems, _indicesSrsItems)
        val _existingSrsItems: TableInfo = read(connection, "srs_items")
        if (!_infoSrsItems.equals(_existingSrsItems)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |srs_items(com.ramstudio.kotoba.core.database.entity.SrsItem).
              | Expected:
              |""".trimMargin() + _infoSrsItems + """
              |
              | Found:
              |""".trimMargin() + _existingSrsItems)
        }
        val _columnsRewardPoints: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRewardPoints.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRewardPoints.put("points", TableInfo.Column("points", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRewardPoints: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRewardPoints: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRewardPoints: TableInfo = TableInfo("reward_points", _columnsRewardPoints, _foreignKeysRewardPoints, _indicesRewardPoints)
        val _existingRewardPoints: TableInfo = read(connection, "reward_points")
        if (!_infoRewardPoints.equals(_existingRewardPoints)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |reward_points(com.ramstudio.kotoba.core.database.entity.RewardPoint).
              | Expected:
              |""".trimMargin() + _infoRewardPoints + """
              |
              | Found:
              |""".trimMargin() + _existingRewardPoints)
        }
        val _columnsShopItems: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsShopItems.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsShopItems.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsShopItems.put("description", TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsShopItems.put("price", TableInfo.Column("price", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsShopItems.put("category", TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsShopItems.put("isAvailable", TableInfo.Column("isAvailable", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysShopItems: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesShopItems: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoShopItems: TableInfo = TableInfo("shop_items", _columnsShopItems, _foreignKeysShopItems, _indicesShopItems)
        val _existingShopItems: TableInfo = read(connection, "shop_items")
        if (!_infoShopItems.equals(_existingShopItems)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |shop_items(com.ramstudio.kotoba.core.database.entity.ShopItem).
              | Expected:
              |""".trimMargin() + _infoShopItems + """
              |
              | Found:
              |""".trimMargin() + _existingShopItems)
        }
        val _columnsUserInventory: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUserInventory.put("itemId", TableInfo.Column("itemId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserInventory.put("isEquipped", TableInfo.Column("isEquipped", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUserInventory: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUserInventory: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoUserInventory: TableInfo = TableInfo("user_inventory", _columnsUserInventory, _foreignKeysUserInventory, _indicesUserInventory)
        val _existingUserInventory: TableInfo = read(connection, "user_inventory")
        if (!_infoUserInventory.equals(_existingUserInventory)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |user_inventory(com.ramstudio.kotoba.core.database.entity.UserInventory).
              | Expected:
              |""".trimMargin() + _infoUserInventory + """
              |
              | Found:
              |""".trimMargin() + _existingUserInventory)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "kana_characters", "quiz_history", "srs_items", "reward_points", "shop_items", "user_inventory")
  }

  public override fun clearAllTables() {
    super.performClear(false, "kana_characters", "quiz_history", "srs_items", "reward_points", "shop_items", "user_inventory")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(KanaDao::class, KanaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SrsDao::class, SrsDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun kanaDao(): KanaDao = _kanaDao.value

  public override fun srsDao(): SrsDao = _srsDao.value
}

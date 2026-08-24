package com.ramstudio.kotoba.core.database.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.ramstudio.kotoba.core.database.entity.SrsItem
import javax.`annotation`.processing.Generated
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class SrsDao_Impl(
  __db: RoomDatabase,
) : SrsDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfSrsItem: EntityInsertAdapter<SrsItem>
  init {
    this.__db = __db
    this.__insertAdapterOfSrsItem = object : EntityInsertAdapter<SrsItem>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `srs_items` (`characterId`,`repetitionCount`,`intervalDays`,`easeFactor`,`nextReviewTimestamp`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SrsItem) {
        statement.bindText(1, entity.characterId)
        statement.bindLong(2, entity.repetitionCount.toLong())
        statement.bindLong(3, entity.intervalDays.toLong())
        statement.bindDouble(4, entity.easeFactor.toDouble())
        statement.bindLong(5, entity.nextReviewTimestamp)
      }
    }
  }

  public override suspend fun updateSrsItem(item: SrsItem): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfSrsItem.insert(_connection, item)
  }

  public override fun getAllSrsItems(): Flow<List<SrsItem>> {
    val _sql: String = "SELECT * FROM srs_items"
    return createFlow(__db, false, arrayOf("srs_items")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfCharacterId: Int = getColumnIndexOrThrow(_stmt, "characterId")
        val _columnIndexOfRepetitionCount: Int = getColumnIndexOrThrow(_stmt, "repetitionCount")
        val _columnIndexOfIntervalDays: Int = getColumnIndexOrThrow(_stmt, "intervalDays")
        val _columnIndexOfEaseFactor: Int = getColumnIndexOrThrow(_stmt, "easeFactor")
        val _columnIndexOfNextReviewTimestamp: Int = getColumnIndexOrThrow(_stmt, "nextReviewTimestamp")
        val _result: MutableList<SrsItem> = mutableListOf()
        while (_stmt.step()) {
          val _item: SrsItem
          val _tmpCharacterId: String
          _tmpCharacterId = _stmt.getText(_columnIndexOfCharacterId)
          val _tmpRepetitionCount: Int
          _tmpRepetitionCount = _stmt.getLong(_columnIndexOfRepetitionCount).toInt()
          val _tmpIntervalDays: Int
          _tmpIntervalDays = _stmt.getLong(_columnIndexOfIntervalDays).toInt()
          val _tmpEaseFactor: Float
          _tmpEaseFactor = _stmt.getDouble(_columnIndexOfEaseFactor).toFloat()
          val _tmpNextReviewTimestamp: Long
          _tmpNextReviewTimestamp = _stmt.getLong(_columnIndexOfNextReviewTimestamp)
          _item = SrsItem(_tmpCharacterId,_tmpRepetitionCount,_tmpIntervalDays,_tmpEaseFactor,_tmpNextReviewTimestamp)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getDueItems(currentTimestamp: Long): Flow<List<SrsItem>> {
    val _sql: String = "SELECT * FROM srs_items WHERE nextReviewTimestamp <= ?"
    return createFlow(__db, false, arrayOf("srs_items")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, currentTimestamp)
        val _columnIndexOfCharacterId: Int = getColumnIndexOrThrow(_stmt, "characterId")
        val _columnIndexOfRepetitionCount: Int = getColumnIndexOrThrow(_stmt, "repetitionCount")
        val _columnIndexOfIntervalDays: Int = getColumnIndexOrThrow(_stmt, "intervalDays")
        val _columnIndexOfEaseFactor: Int = getColumnIndexOrThrow(_stmt, "easeFactor")
        val _columnIndexOfNextReviewTimestamp: Int = getColumnIndexOrThrow(_stmt, "nextReviewTimestamp")
        val _result: MutableList<SrsItem> = mutableListOf()
        while (_stmt.step()) {
          val _item: SrsItem
          val _tmpCharacterId: String
          _tmpCharacterId = _stmt.getText(_columnIndexOfCharacterId)
          val _tmpRepetitionCount: Int
          _tmpRepetitionCount = _stmt.getLong(_columnIndexOfRepetitionCount).toInt()
          val _tmpIntervalDays: Int
          _tmpIntervalDays = _stmt.getLong(_columnIndexOfIntervalDays).toInt()
          val _tmpEaseFactor: Float
          _tmpEaseFactor = _stmt.getDouble(_columnIndexOfEaseFactor).toFloat()
          val _tmpNextReviewTimestamp: Long
          _tmpNextReviewTimestamp = _stmt.getLong(_columnIndexOfNextReviewTimestamp)
          _item = SrsItem(_tmpCharacterId,_tmpRepetitionCount,_tmpIntervalDays,_tmpEaseFactor,_tmpNextReviewTimestamp)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSrsItem(characterId: String): SrsItem? {
    val _sql: String = "SELECT * FROM srs_items WHERE characterId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, characterId)
        val _columnIndexOfCharacterId: Int = getColumnIndexOrThrow(_stmt, "characterId")
        val _columnIndexOfRepetitionCount: Int = getColumnIndexOrThrow(_stmt, "repetitionCount")
        val _columnIndexOfIntervalDays: Int = getColumnIndexOrThrow(_stmt, "intervalDays")
        val _columnIndexOfEaseFactor: Int = getColumnIndexOrThrow(_stmt, "easeFactor")
        val _columnIndexOfNextReviewTimestamp: Int = getColumnIndexOrThrow(_stmt, "nextReviewTimestamp")
        val _result: SrsItem?
        if (_stmt.step()) {
          val _tmpCharacterId: String
          _tmpCharacterId = _stmt.getText(_columnIndexOfCharacterId)
          val _tmpRepetitionCount: Int
          _tmpRepetitionCount = _stmt.getLong(_columnIndexOfRepetitionCount).toInt()
          val _tmpIntervalDays: Int
          _tmpIntervalDays = _stmt.getLong(_columnIndexOfIntervalDays).toInt()
          val _tmpEaseFactor: Float
          _tmpEaseFactor = _stmt.getDouble(_columnIndexOfEaseFactor).toFloat()
          val _tmpNextReviewTimestamp: Long
          _tmpNextReviewTimestamp = _stmt.getLong(_columnIndexOfNextReviewTimestamp)
          _result = SrsItem(_tmpCharacterId,_tmpRepetitionCount,_tmpIntervalDays,_tmpEaseFactor,_tmpNextReviewTimestamp)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

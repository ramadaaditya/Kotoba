package com.ramstudio.kotoba.core.database.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.ramstudio.kotoba.core.database.entity.KanaCharacter
import javax.`annotation`.processing.Generated
import kotlin.Int
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
public class KanaDao_Impl(
  __db: RoomDatabase,
) : KanaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfKanaCharacter: EntityInsertAdapter<KanaCharacter>
  init {
    this.__db = __db
    this.__insertAdapterOfKanaCharacter = object : EntityInsertAdapter<KanaCharacter>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `kana_characters` (`id`,`character`,`romaji`,`category`,`type`,`audioResName`,`strokeOrderJson`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: KanaCharacter) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.character)
        statement.bindText(3, entity.romaji)
        statement.bindText(4, entity.category)
        statement.bindText(5, entity.type)
        statement.bindText(6, entity.audioResName)
        statement.bindText(7, entity.strokeOrderJson)
      }
    }
  }

  public override suspend fun insertCharacters(characters: List<KanaCharacter>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfKanaCharacter.insert(_connection, characters)
  }

  public override fun getAllCharacters(): Flow<List<KanaCharacter>> {
    val _sql: String = "SELECT * FROM kana_characters"
    return createFlow(__db, false, arrayOf("kana_characters")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCharacter: Int = getColumnIndexOrThrow(_stmt, "character")
        val _columnIndexOfRomaji: Int = getColumnIndexOrThrow(_stmt, "romaji")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfAudioResName: Int = getColumnIndexOrThrow(_stmt, "audioResName")
        val _columnIndexOfStrokeOrderJson: Int = getColumnIndexOrThrow(_stmt, "strokeOrderJson")
        val _result: MutableList<KanaCharacter> = mutableListOf()
        while (_stmt.step()) {
          val _item: KanaCharacter
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpCharacter: String
          _tmpCharacter = _stmt.getText(_columnIndexOfCharacter)
          val _tmpRomaji: String
          _tmpRomaji = _stmt.getText(_columnIndexOfRomaji)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpAudioResName: String
          _tmpAudioResName = _stmt.getText(_columnIndexOfAudioResName)
          val _tmpStrokeOrderJson: String
          _tmpStrokeOrderJson = _stmt.getText(_columnIndexOfStrokeOrderJson)
          _item = KanaCharacter(_tmpId,_tmpCharacter,_tmpRomaji,_tmpCategory,_tmpType,_tmpAudioResName,_tmpStrokeOrderJson)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getCharactersByType(type: String): Flow<List<KanaCharacter>> {
    val _sql: String = "SELECT * FROM kana_characters WHERE type = ?"
    return createFlow(__db, false, arrayOf("kana_characters")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, type)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCharacter: Int = getColumnIndexOrThrow(_stmt, "character")
        val _columnIndexOfRomaji: Int = getColumnIndexOrThrow(_stmt, "romaji")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfAudioResName: Int = getColumnIndexOrThrow(_stmt, "audioResName")
        val _columnIndexOfStrokeOrderJson: Int = getColumnIndexOrThrow(_stmt, "strokeOrderJson")
        val _result: MutableList<KanaCharacter> = mutableListOf()
        while (_stmt.step()) {
          val _item: KanaCharacter
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpCharacter: String
          _tmpCharacter = _stmt.getText(_columnIndexOfCharacter)
          val _tmpRomaji: String
          _tmpRomaji = _stmt.getText(_columnIndexOfRomaji)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpAudioResName: String
          _tmpAudioResName = _stmt.getText(_columnIndexOfAudioResName)
          val _tmpStrokeOrderJson: String
          _tmpStrokeOrderJson = _stmt.getText(_columnIndexOfStrokeOrderJson)
          _item = KanaCharacter(_tmpId,_tmpCharacter,_tmpRomaji,_tmpCategory,_tmpType,_tmpAudioResName,_tmpStrokeOrderJson)
          _result.add(_item)
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

package com.zebas2.marvelapp.data.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class Prefers(context: Context) {

    companion object {
        private const val PREFS_NAME = "marvel_prefs"

        private const val KEY_CHARACTER_OFFSET = "characters_offset"
        private const val KEY_CHARACTER_OFFSET_DB = "characters_offset_db"
        private const val KEY_CHARACTER_LIMIT = "characters_limit"
        private const val KEY_CHARACTER_TOTAL = "characters_total"
    }

    private val sharedPrefers: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var appCharacterOffset: Int
        get() = sharedPrefers.getInt(KEY_CHARACTER_OFFSET, 0)
        set(value) = sharedPrefers.edit { putInt(KEY_CHARACTER_OFFSET, value) }

    var appCharacterOffsetDB: Int
        get() = sharedPrefers.getInt(KEY_CHARACTER_OFFSET_DB, 0)
        set(value) = sharedPrefers.edit { putInt(KEY_CHARACTER_OFFSET_DB, value) }

    var appCharacterLimit: Int
        get() = sharedPrefers.getInt(KEY_CHARACTER_LIMIT, 40)
        set(value) = sharedPrefers.edit { putInt(KEY_CHARACTER_LIMIT, value) }

    var appCharacterTotal: Int
        get() = sharedPrefers.getInt(KEY_CHARACTER_TOTAL, 0)
        set(value) = sharedPrefers.edit { putInt(KEY_CHARACTER_TOTAL, value) }

    fun sumLimitToOffset() {
        sharedPrefers.edit {
            putInt(
                KEY_CHARACTER_OFFSET,
                appCharacterOffset + appCharacterLimit
            )
        }
    }

    fun sumLimitToOffsetDB() {
        sharedPrefers.edit {
            putInt(
                KEY_CHARACTER_OFFSET_DB,
                appCharacterOffsetDB + appCharacterLimit
            )
        }
    }

    fun resetOffsetDB() {
        sharedPrefers.edit {
            putInt(
                KEY_CHARACTER_OFFSET_DB,
                0
            )
        }
    }


}
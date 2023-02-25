package ssm.android.users_mobile.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import android.provider.BaseColumns
import ssm.android.users_mobile.contract.UserContract

class UserSQLiteHelper(val context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "db.sqlite3"

        const val SQL_CREATE_TABLE =
            "CREATE TABLE ${UserContract.UserEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${UserContract.UserEntry.COLUMN_NAME_NAME} TEXT," +
                    "${UserContract.UserEntry.COLUMN_NAME_EMAIL} TEXT," +
                    "${UserContract.UserEntry.COLUMN_NAME_PHONE} TEXT)"

        const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS ${UserContract.UserEntry.TABLE_NAME}"

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(SQL_DROP_TABLE)
        onCreate(db)
    }
}
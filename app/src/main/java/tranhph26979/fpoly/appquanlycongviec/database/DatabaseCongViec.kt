package tranhph26979.fpoly.appquanlycongviec.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Parcel
import android.os.Parcelable

class DatabaseCongViec(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "congviec.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "congviec"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAMECV = "congviec_name"
        const val COLUMN_DATECV = "congviec_date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME(" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAMECV TEXT, " +
                "$COLUMN_DATECV TEXT)"
        db!!.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

    }
}
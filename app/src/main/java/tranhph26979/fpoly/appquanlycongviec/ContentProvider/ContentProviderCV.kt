package tranhph26979.fpoly.appquanlycongviec.ContentProvider

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import tranhph26979.fpoly.appquanlycongviec.ContentProvider.ContentProviderCV.TaskContract.TaskEntry.AUTHORITY
import tranhph26979.fpoly.appquanlycongviec.ContentProvider.ContentProviderCV.TaskContract.TaskEntry.CONTENT_URI
import tranhph26979.fpoly.appquanlycongviec.ContentProvider.ContentProviderCV.TaskContract.TaskEntry.PATH
import tranhph26979.fpoly.appquanlycongviec.database.DatabaseCongViec
import tranhph26979.fpoly.appquanlycongviec.model.CongViec

class ContentProviderCV(): ContentProvider() {

private lateinit var databaseCongViec: DatabaseCongViec

 class TaskContract{
    object TaskEntry {
        const val COLUMN_ID =DatabaseCongViec.COLUMN_ID
        const val COLUMN_NAMECV = DatabaseCongViec.COLUMN_NAMECV
        const val COLUMN_DATECV = DatabaseCongViec.COLUMN_DATECV
        val AUTHORITY = "com.example.myapp.provider"
        val PATH = "items"
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$PATH")
    }
}
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI(AUTHORITY, PATH, 1)
    }
    override fun onCreate(): Boolean {
        databaseCongViec= DatabaseCongViec(context!!)
       return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db=databaseCongViec.readableDatabase
       val cursor=db.query("congviec",projection,selection,selectionArgs,null,null,sortOrder)
        cursor.setNotificationUri(context?.contentResolver,uri)
        return cursor
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db=databaseCongViec.writableDatabase
        val newRowId=db.insert("congviec",null,values)
        context?.contentResolver?.notifyChange(uri,null)
        return Uri.withAppendedPath(CONTENT_URI,newRowId.toString())

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    @SuppressLint("Range")
    fun getTasksFromContentProvider(context: Context): ArrayList<CongViec> {
        val tasks = mutableListOf<CongViec>()
        val cursor = context.contentResolver.query(TaskContract.TaskEntry.CONTENT_URI, null, null, null, null)

        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndex(TaskContract.TaskEntry.COLUMN_ID))
                val nameCV = it.getString(it.getColumnIndex(TaskContract.TaskEntry.COLUMN_NAMECV))
                val dateCV = it.getString(it.getColumnIndex(TaskContract.TaskEntry.COLUMN_DATECV))
                val task = CongViec(id, nameCV,dateCV)
                tasks.add(task)
            }
        }

        return tasks as ArrayList<CongViec>
    }

}
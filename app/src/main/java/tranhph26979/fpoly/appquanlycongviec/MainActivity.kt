package tranhph26979.fpoly.appquanlycongviec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import tranhph26979.fpoly.appquanlycongviec.ContentProvider.ContentProviderCV
import tranhph26979.fpoly.appquanlycongviec.database.DatabaseCongViec

class MainActivity : AppCompatActivity() {
    private lateinit var btnthem:Button
    private lateinit var listview:ListView
    private lateinit var contentProviderCV: ContentProviderCV
    private lateinit var cursorAdapter: SimpleCursorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnthem=findViewById(R.id.btnthemcvman1)
        contentProviderCV= ContentProviderCV()
        btnthem.setOnClickListener(){
            val intent=Intent(this@MainActivity,MainActivity2::class.java)
            startActivity(intent)
        }
        listview=findViewById(R.id.listviewcv)
        cursorAdapter=SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,null,
            arrayOf(DatabaseCongViec.COLUMN_NAMECV,DatabaseCongViec.COLUMN_DATECV),
            intArrayOf(android.R.id.text1,android.R.id.text2),0
        )

        listview.adapter=cursorAdapter

    }

    override fun onResume() {
        super.onResume()
        val contentResolver=contentResolver
        val cursor=contentResolver.query(
            contentProviderCV.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursorAdapter.swapCursor(cursor)
    }
}
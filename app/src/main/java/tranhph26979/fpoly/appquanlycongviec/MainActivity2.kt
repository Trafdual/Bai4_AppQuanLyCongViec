package tranhph26979.fpoly.appquanlycongviec

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import tranhph26979.fpoly.appquanlycongviec.ContentProvider.ContentProviderCV
import tranhph26979.fpoly.appquanlycongviec.database.DatabaseCongViec
import java.util.Calendar

class MainActivity2 : AppCompatActivity() {
    private lateinit var edtTencv:EditText
    private lateinit var edtNgaycv:EditText
    private lateinit var btnThemcvMan2:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        edtTencv=findViewById(R.id.edtnamecv)
        edtNgaycv=findViewById(R.id.edtngaycv)
        btnThemcvMan2=findViewById(R.id.btnthemcvman2)

        edtNgaycv.setOnClickListener(){
            showdatepicker()
        }
        btnThemcvMan2.setOnClickListener(){
            val namecv=edtTencv.text.toString()
            val ngaycv=edtNgaycv.text.toString()
            val contentValues=ContentValues()
            contentValues.put(DatabaseCongViec.COLUMN_NAMECV,namecv)
            contentValues.put(DatabaseCongViec.COLUMN_DATECV,ngaycv)

            val contentResolver=contentResolver
            contentResolver.insert(ContentProviderCV.TaskContract.TaskEntry.CONTENT_URI,contentValues)

            val intent = Intent("com.example.myapp.provider.DATA_CHANGED")
            sendBroadcast(intent)
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 6)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)

            finish()
        }

    }
    private fun showdatepicker(){
        val calendar=Calendar.getInstance()
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)
        val day=calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog=DatePickerDialog(this,DatePickerDialog.OnDateSetListener(){_,selectedYear,selectedMonth,selectedDay ->
            val formattedDate= String.format("%04d-%02d-%02d",selectedYear,selectedMonth+1,selectedDay)
            edtNgaycv.setText(formattedDate)
        },year,month,day)
        datePickerDialog.show()


    }
}
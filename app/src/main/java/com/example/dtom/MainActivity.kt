package com.example.dtom

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private var selectedDate : TextView?=null
    private var resultDate : TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

             selectedDate=findViewById(R.id.selectedDate)
        resultDate=findViewById(R.id.resultView)
        val btn4Date =findViewById<Button>(R.id.btn4Date)


        btn4Date.setOnClickListener{
           clickDateBtn()
        }
    }

   private fun clickDateBtn(){
        val mycal= Calendar.getInstance()
       val year=mycal.get(Calendar.YEAR)
        val month=mycal.get(Calendar.MONTH)
        val day=mycal.get(Calendar.DAY_OF_MONTH)

       val dpd= DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{

            view,yearSelected,monthSelected,dayOfMonth ->

             val date="$dayOfMonth/${monthSelected+1}/$yearSelected"
                selectedDate?.text=date

                val sdf= SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate=sdf.parse(date)
                val dateInMIn=theDate.time/60000
                val currDate= sdf.parse(sdf.format(System.currentTimeMillis()))
                val currDateInMIn=currDate.time/60000
                val difference=currDateInMIn-dateInMIn

                 resultDate?.text=difference.toString()
            }
            ,year,month,day)

        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000

        dpd.show()


        Toast.makeText(this,"pick the date of birth",Toast.LENGTH_LONG).show()
    }
}
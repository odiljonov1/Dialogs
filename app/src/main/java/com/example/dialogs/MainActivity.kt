package com.example.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_custom_dialog.*
import kotlinx.android.synthetic.main.item_custom_dialog.view.*
import java.time.LocalTime
import java.util.*



class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = arrayOf("Android", "Java", "Kotlin")
        val checkedSite = booleanArrayOf(false, true, false)

        btn_alerd_dialog.setOnClickListener {
            val dialog = AlertDialog.Builder(this)

            dialog.setTitle("Alert dialog title")
            // dialog.setMessage("Message alert dialog")

            //elranni boshqa joyini bosganda dialog yo'qolishi
            dialog.setCancelable(false)

            dialog.setPositiveButton("Positive "
            ) { dialog, which ->
                Toast.makeText(this@MainActivity, "Positive ", Toast.LENGTH_SHORT).show()
            }

            dialog.setNegativeButton("Negative ", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    Toast.makeText(this@MainActivity, "Negative ", Toast.LENGTH_SHORT).show()
                }
            })

            dialog.setNeutralButton("Neutral ", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    Toast.makeText(this@MainActivity, "neutral ", Toast.LENGTH_SHORT).show()
                }
            })

//            //Bu dialogda checkBox orqali ko'pgina itemlar chiqaradi, lekin setMessage yozilgan bo'lsa buni paddeshka qilmaydi
//            dialog.setMultiChoiceItems(
//                list,
//                checkedSite,
//                object : DialogInterface.OnMultiChoiceClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
//                        Toast.makeText(this@MainActivity, "${list[which]}", Toast.LENGTH_SHORT).show()
//                    }
//                })


            //Bu esa radioButtonda itemlarni chiqaradi, lekin message yoki setMultiChoiceItems yozilmagan bo'lishi kerak bundan oldin
            dialog.setSingleChoiceItems(list, -1, object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    Toast.makeText(this@MainActivity, "${list[which]}", Toast.LENGTH_SHORT).show()
                }
            })

            dialog.show()
        }


        btn_custom_dialog.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            val dialog = alertDialog.create()

            dialog.setTitle("Title Custom dialog")
            val dialogView = layoutInflater.inflate(R.layout.item_custom_dialog, null, false)
            dialog.setView(dialogView)

            dialogView.btn_close.setOnClickListener {
                Toast.makeText(this, "${dialogView.edt_email_dialog.text}\n ${dialogView.edt_password_dialog.text}", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            dialog.show()
        }


        btn_fragment_dialog.setOnClickListener {

            val myDialogFragment = dialog_fragentFragment.newInstance("birNarsa", "ikkinchiNarsa")
            myDialogFragment.show(supportFragmentManager.beginTransaction(), "keylar")
        }


        btn_data_picker_dialog.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this)

            datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(this, "${dayOfMonth}.${month+1}.$year", Toast.LENGTH_SHORT).show()
            }

            datePickerDialog.show()
        }

        btn_time_picker_dialog.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this,
                object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        Toast.makeText(this@MainActivity, "$hourOfDay:$minute", Toast.LENGTH_SHORT).show()
                    }

                },
                24,
                60,
                true
            )
//            timePickerDialog.updateTime(LocalTime.now().hour, LocalTime.now().minute)
            timePickerDialog.updateTime(12, 50)
            timePickerDialog.show()
        }

        btn_button_sheets_dialog.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(layoutInflater.inflate(R.layout.item_bottom_sheets_dialog, null, false))
            bottomSheetDialog.show()
        }

        btn_snackbar.setOnClickListener {
            val snackbar = Snackbar.make(it, "Salom snackbar", Snackbar.LENGTH_LONG)

            snackbar.setAction("Click", object : View.OnClickListener {
                override fun onClick(v: View?) {
                    Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT).show()
                }
            })

            snackbar.show()
        }
    }
}
package binar.academy.sharedpreferencesample

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import binar.academy.sharedpreferencesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val sharedPreferences : SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        binding.btnSave.setOnClickListener {
            val id: Int = Integer.parseInt(binding.etInputId.text.toString())
            val name : String = binding.etInputName.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("id_key", id)
            editor.putString("name_key", name)
            editor.apply()
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
        }
        binding.btnView.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key", 0)
            val sharedNameValue = sharedPreferences.getString("name_key", "defaultname")
            if (sharedIdValue.equals(0) && sharedNameValue.equals("defaultname")){
                binding.tvShowName.setText("defaultname: $sharedNameValue").toString()
                binding.tvShowId.setText("defaultid : ${sharedIdValue.toString()}")
                Toast.makeText(this, "Data View Kosong", Toast.LENGTH_SHORT).show()
            }else{
                binding.tvShowName.setText(sharedNameValue).toString()
                binding.tvShowId.setText(sharedIdValue.toString())
                Toast.makeText(this, "Data View Ditampilkan", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnClear.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            binding.tvShowName.setText("")
            binding.tvShowId.setText("")
            Toast.makeText(this, "Data Clear", Toast.LENGTH_SHORT).show()
        }
    }
}
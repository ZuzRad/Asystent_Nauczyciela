package com.example.asystent.fragments.uczen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.asystent.R
import com.example.asystent.data.AppDatabase
import com.example.asystent.data.uczen.Uczen
import com.example.asystent.databinding.FragmentDodajUczniaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class DodajUczniaFragment:Fragment() {
    private var _binding: FragmentDodajUczniaBinding? = null
    private val binding get() = _binding!!
    private lateinit var appDatabase: AppDatabase


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDodajUczniaBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getDatabase(requireContext())
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText_imie=view.findViewById<EditText>(R.id.editText_imie)
        val editText_nazwisko=view.findViewById<EditText>(R.id.editText_nazwisko)
        val editText_nr=view.findViewById<EditText>(R.id.editText_nr)
        val button_dodaj = view.findViewById<Button>(R.id.button_dodajUcznia2)
        button_dodaj.setOnClickListener{

            var dane = Uczen("","","")

            runBlocking(Dispatchers.IO) {
                dane = Uczen(editText_imie.text.toString(), editText_nazwisko.text.toString(), editText_nr.text.toString())
                appDatabase.uczenDao().insert(dane)
            }

            editText_nr.setText("")
            editText_nazwisko.setText("")
            editText_imie.setText("")

        }
    }
}
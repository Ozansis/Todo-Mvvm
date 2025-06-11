package com.example.todoapp.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddTaskBinding
import com.example.todoapp.viewmodel.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class AddTaskFragment : Fragment() {

    private val viewModel: AddTaskViewModel by viewModels()

    private lateinit var binding: FragmentAddTaskBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddTaskBinding.inflate(inflater,container,false)


        val navController=findNavController()



        binding.etDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year =calendar.get(Calendar.YEAR)
            val month = calendar.get((Calendar.MONTH))
            val day = calendar.get((Calendar.DAY_OF_MONTH))
            val datepicker = DatePickerDialog(requireContext(),{_,selectedYear,selectedMonth,selectedDay ->

                val selectedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                binding.etDate.setText(selectedDate)

            },year,month,day)

            datepicker.show()

        }

        val categories = listOf("Personel","Work","Study","Other")
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,categories)
        binding.actCategory.setAdapter(adapter)

        binding.floatingActionButton2.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val note = binding.etNote.text.toString()
            val category = binding.actCategory.text.toString()
            val date = binding.etDate.text.toString()
            addTask(title,note,category,date)
            navController.navigateUp()


        }




        return binding.root
    }



    fun addTask(title: String,note: String,cate: String,date: String){

        viewModel.addTask(title,note,false,date,cate)


    }




}
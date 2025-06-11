package com.example.todoapp.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentEditTaskBinding
import com.example.todoapp.viewmodel.EditTaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar


@AndroidEntryPoint
class EditTaskFragment : Fragment() {

    private val viewModel : EditTaskViewModel by viewModels()
    private lateinit var binding: FragmentEditTaskBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEditTaskBinding.inflate(inflater,container,false)

        val bundle : EditTaskFragmentArgs by navArgs()
        val task =bundle.task
        binding.edtTextEdit.setText(task.title)
        binding.etNoteEdit.setText(task.description)
        binding.actCategoryEdit.setText(task.category)
        binding.etDateEdit.setText(task.date)


        val navController=findNavController()

//        toolbar.setNavigationOnClickListener{
//            navController.navigateUp()
//
//        }


        binding.etDateEdit.setOnClickListener {
            val calendar = Calendar.getInstance()

            val year =calendar.get(Calendar.YEAR)
            val month = calendar.get((Calendar.MONTH))
            val day = calendar.get((Calendar.DAY_OF_MONTH))

            val datepicker = DatePickerDialog(requireContext(),{_,selectedYear,selectedMonth,selectedDay ->

                val selectedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                binding.etDateEdit.setText(selectedDate)

            },year,month,day)

            datepicker.show()

        }


        val category = listOf("Personel","Work","Study","Other")
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,category)
        binding.actCategoryEdit.setAdapter(adapter)


        binding.floatingActionButton2.setOnClickListener {
            val title = binding.edtTextEdit.text.toString()
            val note = binding.etNoteEdit.text.toString()
            val category = binding.actCategoryEdit.text.toString()
            val date = binding.etDateEdit.text.toString()
            val id = task.id

            if (title.isNotBlank() && note.isNotBlank() && category.isNotBlank() && date.isNotBlank()) {
                updateTask(id, title, note, category, date)
            } else {
                Log.e("EditTaskFragment", "Boş alanlar var")
            }



        }


        return binding.root
    }


    fun updateTask(id: Int, title: String, note: String, cate: String, date: String){

        viewModel.updateTask(id,title,note,false,date,cate)


    }



}
package com.example.todoapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.data.Todo
import com.example.todoapp.databinding.FragmentTodoListBinding
import com.example.todoapp.ui.adapter.TodoAdapter
import com.example.todoapp.viewmodel.TodoListViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment : Fragment() {

    private lateinit var binding: FragmentTodoListBinding
    val viewModel : TodoListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentTodoListBinding.inflate(inflater,container,false)







        binding.rv.layoutManager = LinearLayoutManager(requireContext())


        val adapter = TodoAdapter(requireContext(),listOf(),viewModel){todo,ischeck->

            viewModel.updateTodoCheckState(todo,ischeck)
        }

        binding.rv.adapter =adapter


        viewModel.todosByCategory.observe(viewLifecycleOwner) {list ->
            adapter.updateList(list)


        }


        binding.chipGroup.setOnCheckedChangeListener { group ,checkedId ->

            if (checkedId != View.NO_ID){
                val selectedChip = group.findViewById<Chip>(checkedId)
                val selectedText = selectedChip.text.toString()

                when(selectedText){
                    "Personal","Work","Study","Other" -> viewModel.loadByCategory(selectedText)
                    "InProgress" -> viewModel.loadInProgress()
                    "Completed" ->viewModel.loadCompleted()




                }




            }


        }




        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_todoListFragment_to_addTaskFragment)
        }





        return binding.root
    }


}
package com.example.todoapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.Todo
import com.example.todoapp.databinding.ItemTodoBinding
import com.example.todoapp.ui.fragment.TodoListFragmentDirections
import com.example.todoapp.viewmodel.TodoListViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class TodoAdapter(var context: Context, var todoList: List<Todo>, val viewModel: TodoListViewModel,val onTodoCheckedChange:(Todo, Boolean)-> Unit)
    : RecyclerView.Adapter<TodoAdapter.CardViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(context),parent,false)
        return CardViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val task = todoList.get(position)
        val view = holder.view


        view.txtTitle.text = task.title
        view.txtDesc.text = task.description
        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMM dd yyyy", Locale.getDefault())

        val formattedDate = if (!task.date.isNullOrEmpty()) {
            try {
                val parsedDate = inputFormat.parse(task.date)
                if (parsedDate != null) {
                    outputFormat.format(parsedDate)
                } else {
                    "Geçersiz Tarih"
                }
            } catch (e: Exception) {
                Log.e("TodoAdapter", "Tarih parse hatası: ${e.message}")
                "Geçersiz Tarih"
            }
        } else {
            "Tarih Yok"
        }

        view.txtDate.text = formattedDate

        view.btnDelete.setOnClickListener {

            viewModel.deleteTask(task)
        }



        view.checkBox3.setOnCheckedChangeListener(null)
        view.checkBox3.isChecked = task.isDone!!
        view.checkBox3.isEnabled = !task.isDone

        holder.view.checkBox3.setOnCheckedChangeListener { it, isChecked ->
            onTodoCheckedChange(task, isChecked)

            if (isChecked) {
                it.isEnabled = false
            }
        }

        view.cardItem.setOnClickListener {

            val gecis = TodoListFragmentDirections.actionTodoListFragmentToEditTaskFragment(task)
            Navigation.findNavController(it).navigate(gecis)

        }

    }

    override fun getItemCount(): Int {
        return todoList.size
    }


    inner class CardViewHolder(var view: ItemTodoBinding): RecyclerView.ViewHolder(view.root)

    fun updateList(newList:List<Todo>){
        todoList = newList
        notifyDataSetChanged()
    }

}
package com.example.redbook.ui.animal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.redbook.R
import com.example.redbook.data.RedBookDatabase
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal
import com.example.redbook.ui.MainActivity
import com.example.redbook.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragmet_animal.*

class AnimalFragment:Fragment(R.layout.fragmet_animal) {


    private val adapter=AnimalListAdapter()
    private lateinit var dao:AnimalDao
    private lateinit var presenter: AnimalPresenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnClickListener {id->
            val mIntent=Intent(requireActivity(),DetailActivity::class.java)
            mIntent.putExtra(DetailActivity.ANIMAL_ID,id)
            startActivity(mIntent)
        }
        recyclerView.adapter=adapter
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        val type=arguments?.getInt(MainActivity.TYPE_ID)?:1
        dao=RedBookDatabase.getInstance(requireContext()).dao()
        presenter=AnimalPresenter(dao)
        presenter.setFunctionBody {
            adapter.models=it
        }
        presenter.getAllAnimal(type)

        etSearch.addTextChangedListener {
            val result:List<Animal> =dao.searchAnimal(type,"${it.toString()}%")
            adapter.models=result
        }
    }
}
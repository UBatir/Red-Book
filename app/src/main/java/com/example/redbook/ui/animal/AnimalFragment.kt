package com.example.redbook.ui.animal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.redbook.R
import com.example.redbook.data.RedBookDatabase
import com.example.redbook.data.dao.AnimalDao
import kotlinx.android.synthetic.main.fragmet_animal.*

class AnimalFragment:Fragment(R.layout.fragmet_animal) {

    private val adapter=AnimalListAdapter()
    private lateinit var dao:AnimalDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter=adapter
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        dao=RedBookDatabase.getInstance(requireContext()).dao()
        setData()
    }

    private fun setData(){
        adapter.models=dao.getAllAnimals()
    }
}
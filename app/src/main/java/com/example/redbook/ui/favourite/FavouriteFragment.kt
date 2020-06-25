package com.example.redbook.ui.favourite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.redbook.R
import com.example.redbook.data.RedBookDatabase
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal
import com.example.redbook.ui.animal.AnimalItemClickListener
import com.example.redbook.ui.animal.AnimalListAdapter
import com.example.redbook.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_favourite.*

class FavouriteFragment:Fragment(R.layout.fragment_favourite), AnimalItemClickListener{

    private val adapter=AnimalListAdapter(this)
    lateinit var dao:AnimalDao
    lateinit var currentAnimal: Animal
    private var animalId:Int=0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewFavourite.adapter=adapter
        recyclerViewFavourite.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        dao=RedBookDatabase.getInstance(requireContext()).dao()
        currentAnimal=dao.getAnimalById(animalId)
    }

    override fun onStart() {
        super.onStart()
        setData()
    }

    private fun setData(){
        adapter.models=dao.getFavouriteAnimal()
    }

    override fun onAnimalItemClick(id:Int) {
        val mIntent= Intent(requireActivity(), DetailActivity::class.java)
        mIntent.putExtra(DetailActivity.ANIMAL_ID,id)
        startActivity(mIntent)
    }
}
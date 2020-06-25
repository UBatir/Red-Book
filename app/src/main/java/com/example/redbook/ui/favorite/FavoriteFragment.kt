package com.example.redbook.ui.favorite

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
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment:Fragment(R.layout.fragment_favorite), AnimalItemClickListener,FavoriteView{

    private val adapter=AnimalListAdapter(this)
    lateinit var dao:AnimalDao
    lateinit var currentAnimal: Animal
    private var animalId:Int=0
    private lateinit var presenter: FavoritePresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewFavorite.adapter=adapter
        recyclerViewFavorite.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        dao=RedBookDatabase.getInstance(requireContext()).dao()
        currentAnimal=dao.getAnimalById(animalId)
        presenter= FavoritePresenter(dao,this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getFavoriteAnimal()
    }


    override fun onAnimalItemClick(id:Int) {
        val mIntent= Intent(requireActivity(), DetailActivity::class.java)
        mIntent.putExtra(DetailActivity.ANIMAL_ID,id)
        startActivity(mIntent)
    }

    override fun setData(models: List<Animal>) {
        adapter.models=models
    }
}
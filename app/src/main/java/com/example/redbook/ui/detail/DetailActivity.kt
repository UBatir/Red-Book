package com.example.redbook.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.redbook.R
import com.example.redbook.data.RedBookDatabase
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(),DetailView {
    companion object{
        const val ANIMAL_ID="animalId"
    }

    private var animalId:Int=0
    lateinit var dao:AnimalDao
    private var menuItem:MenuItem?=null
    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        animalId=intent.getIntExtra(ANIMAL_ID,0)
        dao=RedBookDatabase.getInstance(this).dao()
        presenter= DetailPresenter(dao,this)
        presenter.getAnimalById(animalId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail,menu)
        menuItem=menu?.findItem(R.id.item_bookmark)
        presenter.setFavouriteStatus()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->finish()
            R.id.item_bookmark->presenter.setFavourite()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun setData(animal: Animal) {
        Glide
            .with(this)
            .load(resources.getIdentifier("picture${animal.id}","drawable",packageName))
            .into(ivDetail)
        tvStatusContent.text=animal.status
        tvHabitatContent.text=animal.habitat
        tvPropagationContent.text=animal.propagation
        tvQuantityContent.text=animal.quantity
        tvLifestyleContent.text=animal.lifestyle
        tvLimitingFactorsContent.text=animal.limitingFactors
        tvBreedingContent.text=animal.breeding
        tvSecurityContent.text=animal.security
    }

    override fun setFavouriteIcon(id: Int) {
        menuItem?.setIcon(id)
    }
}
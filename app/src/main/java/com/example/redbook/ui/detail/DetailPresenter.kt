package com.example.redbook.ui.detail

import com.example.redbook.R
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal

class DetailPresenter(private val dao: AnimalDao,private val view:DetailView) {

    private lateinit var animal:Animal

    fun getAnimalById(id:Int){
        animal=dao.getAnimalById(id)
        view.setData(animal)
    }

    fun setFavouriteStatus(){
        if(animal.isFavourite==1){
            view.setFavouriteIcon(R.drawable.ic_baseline_bookmark_24)
        }
        else{
            view.setFavouriteIcon(R.drawable.ic_baseline_bookmark_border_24)
        }
    }

    fun setFavourite(){
        if (animal.isFavourite==null) animal.isFavourite=1
        else animal.isFavourite=1-animal.isFavourite!!
        setFavouriteStatus()
        dao.updateAnimal(animal)
    }
}
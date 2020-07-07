package com.example.redbook.ui.favorite

import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal

class FavoritePresenter(private val dao:AnimalDao) {

    private var setData:(models:List<Animal>)->Unit={}

    fun setFunctionBody(a:(models:List<Animal>)->Unit){
        this.setData=a
    }

    fun getFavoriteAnimal(){
        setData(dao.getFavoriteAnimal())
    }
}
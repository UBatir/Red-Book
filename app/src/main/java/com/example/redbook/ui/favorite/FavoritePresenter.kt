package com.example.redbook.ui.favorite

import com.example.redbook.data.dao.AnimalDao

class FavoritePresenter(private val dao:AnimalDao,private val view:FavoriteView) {
    fun getFavoriteAnimal(){
        view.setData(dao.getFavoriteAnimal())
    }
}
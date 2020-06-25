package com.example.redbook.ui.animal

import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal

class AnimalPresenter(private val dao: AnimalDao,private val view:AnimalView) {
    fun getAllAnimal(type:Int){
        view.setData(dao.getAllAnimals(type))
    }
}
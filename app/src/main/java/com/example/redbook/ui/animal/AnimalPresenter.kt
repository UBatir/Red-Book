package com.example.redbook.ui.animal

import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal

class AnimalPresenter(private val dao: AnimalDao) {

    private var setData:(models:List<Animal>)->Unit={

    }

    fun setFunctionBody(a:(models:List<Animal>)->Unit){
        this.setData=a
    }

    fun getAllAnimal(type:Int){
        setData(dao.getAllAnimals(type))
    }
}
package com.example.redbook.ui.detail

import com.example.redbook.data.model.Animal

interface DetailView {
    fun setData(animal: Animal)
    fun setFavouriteIcon(id:Int)
}
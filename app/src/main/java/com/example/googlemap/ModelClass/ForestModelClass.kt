package com.example.googlemap.ModelClass

import android.net.Uri

class ForestModelClass
{

    var Forest: String = " "
    var Price: String = " "
    var Day: String = " "
    var People: String = " "
    var key: String = " "
    var ImageUri: String = " "

    constructor(forest:String,price:String,day:String,people:String,key:String,ImageUri: Uri){

        this.Forest = forest
        this.Price = price
        this.Day = day
        this.People = people
        this.key = key
        this.ImageUri = ImageUri.toString()

    }

    constructor()
    {

    }

}
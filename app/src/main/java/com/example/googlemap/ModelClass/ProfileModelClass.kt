package com.example.googlemap.ModelClass

import android.net.Uri
import android.provider.Telephony.Mms.Rate

class ProfileModelClass {
    var city: String = " "
    var place: String = " "
    var email: String = " "
    var phone: String = " "
    var description: String = " "
    var price: String = " "
    var rate: String = " "
    var day: String = " "
    var key: String = " "
    var ImageUri: String = " "

    constructor(
        City: String,
        Place: String,
        Email: String,
        Phone: String,
        Description: String,
        price: String,
        rate: String,
        day: String,
        key: String,
        ImageUri: Uri)
    {
        this.city = City
        this.place = Place
        this.email = Email
        this.phone = Phone
        this.description = Description
        this.price = price
        this.rate = rate
        this.day = day
        this.key = key
        this.ImageUri=ImageUri.toString()
    }

    constructor()
    {

    }
}

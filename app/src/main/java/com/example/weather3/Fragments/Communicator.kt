package com.example.weather3.Fragments

import android.text.Editable

interface Communicator {

    fun passData(lat: Editable, long: Editable, timezone: Editable)

}

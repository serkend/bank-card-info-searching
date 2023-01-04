package com.example.cfttestapp.db

import android.app.Application

class MainApp : Application() {
    val database by lazy {RequestDatabase.getDataBase(this)}
}
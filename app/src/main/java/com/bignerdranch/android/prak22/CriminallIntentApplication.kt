package com.bignerdranch.android.prak22

import android.app.Application

class CriminalIntentApplication : Application()
{
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}
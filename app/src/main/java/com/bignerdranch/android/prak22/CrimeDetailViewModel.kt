package com.bignerdranch.android.prak22

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.io.File
import java.util.*

class CrimeDetailViewModel() : ViewModel() {
    private val crimeRepository =
        CrimeRepository.get()
    private val crimeIdLiveData =
        MutableLiveData<UUID>()
    var crimeLiveData: LiveData<Crime?> =
        Transformations.switchMap(crimeIdLiveData) { crimeId ->
            crimeRepository.getCrime(crimeId)
        }
    fun loadCrime(crimeId: UUID) {
        crimeIdLiveData.value = crimeId
    }
    fun saveCrime(crime: Crime) {

        if(crime.title!=""){
            crimeRepository.updateCrime(crime)
        } else{
            crimeRepository.deleteCrime(crime)
        }

    }
    fun getPhotoFile(crime: Crime): File {
        return crimeRepository.getPhotoFile(crime)
    }
}
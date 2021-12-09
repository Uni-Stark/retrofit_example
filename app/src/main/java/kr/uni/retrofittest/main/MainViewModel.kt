package kr.uni.retrofittest.main

import android.annotation.SuppressLint
import android.database.Observable
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.uni.retrofittest.conn.MainRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    var liveData = MutableLiveData<String>("응답 없음")

    fun getData() = liveData

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val data = repository.checkSMS("param1", "param2")
            when (data.isSuccessful) {
                true -> {
                    liveData.postValue(data.body().toString())
                }
                else -> {
                    Timber.e("TEST -> ${data.body()}")
                }
            }
        }
    }
}
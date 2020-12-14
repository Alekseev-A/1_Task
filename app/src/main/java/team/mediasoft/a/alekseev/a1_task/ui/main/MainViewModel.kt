package team.mediasoft.a.alekseev.a1_task.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(counter: Int) : ViewModel() {

    private val counterMutableLiveData = MutableLiveData(counter)

    val counterLiveData: LiveData<Int> get() = counterMutableLiveData

    fun incrementClicks() {
        counterMutableLiveData.value = counterMutableLiveData.value?.plus(1)
    }
}
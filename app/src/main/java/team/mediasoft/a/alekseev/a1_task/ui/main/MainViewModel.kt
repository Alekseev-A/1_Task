package team.mediasoft.a.alekseev.a1_task.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val clicks = MutableLiveData(0)

    fun incrementClicks() {
        clicks.value = clicks.value?.plus(1)
    }

    fun setClicks(from: Int) {
        clicks.value = from
    }

    fun getClicks(): LiveData<Int> = clicks
}
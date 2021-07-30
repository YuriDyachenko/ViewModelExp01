package dyachenko.viewmodelexp01.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val liveData: MutableLiveData<Int> = MutableLiveData()

    fun getLiveData() = liveData

    fun getInitData() {
        liveData.value = INIT_VALUE
    }

    companion object {
        private const val INIT_VALUE = 1
    }
}
package com.myapp.livedatavsflowpractice



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TestViewModel : ViewModel(){
    private val _liveData = MutableLiveData("Booo")
    val liveData : LiveData<String> = _liveData

    private val _stateFlow = MutableStateFlow("boooo")
    val stateFlow = _stateFlow.asStateFlow()
    var x = 0
    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun triggerLiveData() {
        _liveData.value = "LiveData"
    }
    fun triggerStateFlow() {
        _stateFlow.value = "StateFlow ${x++}"
    }

    fun triggerFlow() : Flow<String> {
        return flow {
              repeat(5) {
                  emit("Item $it")
                  delay(1000L)
              }
        }
    }

    fun triggerSharedFlow() {
        viewModelScope.launch { _sharedFlow.emit( "SharedFlow") }
    }

}
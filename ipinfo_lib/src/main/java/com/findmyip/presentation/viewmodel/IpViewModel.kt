package com.findmyip.presentation.viewmodel



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findmyip.domain.IpRepository
import com.findmyip.domain.model.IpInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IpViewModel @Inject constructor(private val ipRepository: IpRepository) : ViewModel() {
    private val _ipInfo = MutableLiveData<IpInfo>()
    val ipInfo: LiveData<IpInfo> get() = _ipInfo

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading
    init {
        viewModelScope.launch {
            delay(500)
            fetchIpInfo()
        }
    }
    fun fetchIpInfo() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _ipInfo.value = ipRepository.getIpInfo()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}
package com.sdv.attractgrouptesttask.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdv.attractgrouptesttask.data.profile.Profile
import com.sdv.attractgrouptesttask.data.profile.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileListViewModel(private val profileRepository: ProfileRepository)  : ViewModel() {

    private var _profileLoadLiveData: MutableLiveData<List<Profile>> = MutableLiveData()
    private var _progressUpdateLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var _scrollLiveData: MutableLiveData<Int> = MutableLiveData()
    val scrollLiveData: LiveData<Int> = _scrollLiveData
    val profileLoadLiveData:LiveData<List<Profile>> = _profileLoadLiveData
    val progressUpdateLiveData:LiveData<Boolean> = _progressUpdateLiveData

    init {
        refresh()
    }

    private fun refresh() {
        getProfilesList()
    }

    fun updateProfileListPosition(position: Int){
        _scrollLiveData.postValue(position)
    }

    private fun getProfilesList() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                profileRepository.getProfiles()
            }.onSuccess {
                _progressUpdateLiveData.postValue(false)
                _profileLoadLiveData.postValue(it)
            }.onFailure {
                _progressUpdateLiveData.postValue(false)

            }
        }
    }
}

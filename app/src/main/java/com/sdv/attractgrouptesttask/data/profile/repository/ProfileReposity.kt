package com.sdv.attractgrouptesttask.data.profile.repository

import com.sdv.attractgrouptesttask.data.profile.Profile
import com.sdv.attractgrouptesttask.data.profile.ProfileHttpApi

/**
 * Created by FrostEagle on 03.05.2020.
 * My Email: denisshakhov21@gmail.com
 * Skype: lifeforlight
 */
class ProfileRepository (private val profileHttpApi: ProfileHttpApi){
    suspend fun getProfiles(): List<Profile> = profileHttpApi.getProfiles()

}
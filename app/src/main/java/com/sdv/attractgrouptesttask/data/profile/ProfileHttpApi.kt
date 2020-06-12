package com.sdv.attractgrouptesttask.data.profile

import retrofit2.http.GET

interface ProfileHttpApi {
    @GET("test.json")
    suspend fun getProfiles(): List<Profile>
}
package com.findmyip.data.network

import com.findmyip.domain.model.IpInfo
import retrofit2.http.GET

interface IpApiService {
    @GET("json/")
    suspend fun getIpInfo(): IpInfo
}
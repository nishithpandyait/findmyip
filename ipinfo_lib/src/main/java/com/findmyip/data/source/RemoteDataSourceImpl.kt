package com.findmyip.data.source


import com.findmyip.domain.model.IpInfo
import com.findmyip.data.network.IpApiService

class RemoteDataSourceImpl(private val apiService: IpApiService) : RemoteDataSource {
    override suspend fun getIpInfo(): IpInfo {
        return apiService.getIpInfo()
    }
}
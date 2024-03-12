package com.findmyip.data.source

import com.findmyip.domain.model.IpInfo


interface RemoteDataSource {
    suspend fun getIpInfo(): IpInfo
}
package com.findmyip.domain
import com.findmyip.domain.model.IpInfo

interface IpRepository {
    suspend fun getIpInfo(): IpInfo
}
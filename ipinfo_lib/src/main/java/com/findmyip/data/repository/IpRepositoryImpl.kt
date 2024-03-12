package com.findmyip.data.repository



import com.findmyip.data.source.RemoteDataSource
import com.findmyip.domain.IpRepository
import com.findmyip.domain.model.IpInfo
import javax.inject.Inject

class IpRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    IpRepository {
    override suspend fun getIpInfo(): IpInfo {
        return remoteDataSource.getIpInfo()
    }
}
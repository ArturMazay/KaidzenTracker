package com.aistudio.kaidzentracker.android.data.localDataSource.staffInvolvementDataSourceclass StaffInvolvementDataSourceImpl(private val staffInvolvementDao: StaffInvolvementDao) :    StaffInvolvementDataSource {    override suspend fun insertLocalSource(staffInvolvementEntity: StaffInvolvementEntity) =        staffInvolvementDao.insertLocalSource(staffInvolvementEntity = staffInvolvementEntity)    override suspend fun getLocalSource(): StaffInvolvementEntity? =        staffInvolvementDao.getLocalSource()    override suspend fun deleteLocalSource() = staffInvolvementDao.deleteLocalSource()}
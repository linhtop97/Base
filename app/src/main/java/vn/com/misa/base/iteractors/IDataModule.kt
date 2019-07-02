package vn.com.misa.base.iteractors

import vn.com.misa.base.iteractors.databases.IDbModule
import vn.com.misa.base.iteractors.services.IApiService
import vn.com.misa.base.iteractors.services.IFileApiService
import vn.com.misa.base.iteractors.services.ILongApiService

interface IDataModule {
    var apiService: IApiService

    var longApiService: ILongApiService

    var fileApiService: IFileApiService

    var dbModule: IDbModule
}

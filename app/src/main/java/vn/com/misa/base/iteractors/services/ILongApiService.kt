package vn.com.misa.base.iteractors.services

import android.database.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import vn.com.misa.base.models.BaseResponse
import vn.com.misa.base.models.responses.SignInResponse
import vn.com.misa.base.utilities.ServiceConfig

interface ILongApiService {
    @GET(ServiceConfig.SOME_THING)
    fun getSomething(
        @Path("language") language: String?,
        @Path("a") a: String?,
        @Path("b") b: String?,
        @Path("c") c: String?,
        @Query("id") id: String?,
        @Query("x") x: String?,
        @Query("y") y: String?,
        @Query("z") z: String?
    ): Observable<BaseResponse<SignInResponse>>
}
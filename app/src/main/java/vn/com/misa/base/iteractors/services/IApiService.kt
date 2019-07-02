package vn.com.misa.base.iteractors.services

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import vn.com.misa.base.models.BaseResponse
import vn.com.misa.base.models.responses.ContestResponse
import vn.com.misa.base.utilities.ServiceConfig

/**
 * Interface chứa các phương thức làm việc với api service
 * @Created_by nblinh on 01/07/2019
 */
interface IApiService {
    @POST(ServiceConfig.SIGN_IN)
    @FormUrlEncoded
    fun signIn(
        @Field("username") userName: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: Int
    ): Observable<BaseResponse<ArrayList<ContestResponse>>>
}
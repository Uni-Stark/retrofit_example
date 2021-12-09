package kr.uni.retrofittest.conn

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("actCheckSms")
    suspend fun onCheckSMS(
        @Field("telno") num: String, @Field("randomNumber") randNumber: String
    ): Response<ActGetSMSCheck>
}
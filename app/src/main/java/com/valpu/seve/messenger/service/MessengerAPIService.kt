package com.valpu.seve.messenger.service

import com.valpu.seve.messenger.data.remote.request.LoginRequestObject
import com.valpu.seve.messenger.data.remote.request.MessageRequestObject
import com.valpu.seve.messenger.data.remote.request.StatusUpdateRequestObject
import com.valpu.seve.messenger.data.remote.request.UserRequestObject
import com.valpu.seve.messenger.data.valueobjects.*
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MessengerAPIService {

    @POST("login")
    @Headers("Content-Type: application/json")
    fun login(
            @Body user: LoginRequestObject): Observable<retrofit2.Response<ResponseBody>>

    @POST("users/registrations")
    fun createUser(
            @Body user: UserRequestObject): Observable<UserVO>

    @GET("users")
    fun listUsers(
            @Header("Authorization") authorization: String): Observable<UserListVO>

    @PUT("users")
    fun updateUserStatus(
            @Body request: StatusUpdateRequestObject,
            @Header("Authorization") authorization: String): Observable<UserVO>

    @GET("users/{userId}")
    fun showUser(
            @Path("userId") userId: Long,
            @Header("Authorization") authorization: String): Observable<UserVO>

    @GET("users/details")
    fun echoDetails(
            @Header("Authorization") authorization: String): Observable<UserVO>

    @POST("messages")
    fun createMessage(
            @Body messageRequestObject: MessageRequestObject,
            @Header("Authorization") authorization: String): Observable<MessageVO>

    @GET("conversations")
    fun listConversations(
            @Header("Authorization") authorization: String): Observable<ConversationListVO>

    @GET("conversations/{conversationId}")
    fun showConversation(
            @Path("conversationId") conversationId: Long,
            @Header("Authorization") authorization: String): Observable<ConversationVO>

    companion object Factory {

        private var service: MessengerAPIService? = null

        fun getInstance(): MessengerAPIService {

            if (service == null) {

                val retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("http://localhost:3001")
                        .build()
                service = retrofit.create(MessengerAPIService::class.java)
            }

            return service as MessengerAPIService
        }
    }
}
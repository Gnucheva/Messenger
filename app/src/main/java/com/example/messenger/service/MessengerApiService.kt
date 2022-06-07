package com.example.messenger.service


import com.example.messenger.data.remote.request.LoginRequestObject
import com.example.messenger.data.remote.request.MessageRequestObject
import com.example.messenger.data.remote.request.StatusUpdateRequestObject
import com.example.messenger.data.remote.request.UserRequestObject
import com.example.messenger.data.vo.*
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MessengerApiService {
    @POST("login")
    @Headers("Content-Type: application/json")
    fun login(@Body user: LoginRequestObject): Observable<Response<ResponseBody>>

    @POST("users/registrations")
    fun createUser(@Body user: UserRequestObject): Observable<UserVO>

    @GET("users")
    fun listUsers(@Header("Authorization") authenticator: String): Observable<UserListVO>

    @PUT("users")
    fun updateUserStatus(
        @Body request: StatusUpdateRequestObject,
        @Header("Authorization") authenticator: String
    ): Observable<UserVO>

    @GET("users/{userId}")
    fun showUser(
        @Path("userId") userId: Long,
        @Header("Authorization") authorization: String
    ): Observable<UserVO>

    @GET("users/details")
    fun echoDetails(@Header("Authorization") authorization: String): Observable<UserVO>

    @POST("messages")
    fun createMessage(
        @Body messageRequestObject: MessageRequestObject,
        @Header("Authorization") authorization: String
    ): Observable<MessageVO>

    @GET("conversations")
    fun listConversation(@Header("Authorization") authorization: String): Observable<ConversationListVO>

    @GET("conversation/{conversationId}")
    fun showConversation(
        @Header("Authorization") authorization: String,
        conversationId: Long
    ): Observable<ConversationVO>

    companion object Factory {
        private var service: MessengerApiService? = null
        private val AWS_URL =
            "https://messenger-gnu.herokuapp.com/"

        fun getInstance(): MessengerApiService {
            if (service == null) {
                val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(AWS_URL)
                    .build()
                service = retrofit.create(MessengerApiService::class.java)
            }
            return service as MessengerApiService
        }
    }
}

package com.sneyder.loginapp.di.data.repository

import com.sneyder.loginapp.di.data.preferences.PreferencesHelper
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppUserRepository
@Inject constructor(
    private val prefs: PreferencesHelper
) : UserRepository() {



//    override suspend fun addModerator(email: String): Result<Boolean> {
//        return safeApiCall({
//            bizNearbyApi.addModerator(email)
//        })
//    }
//
//    override suspend fun fetchModerators(): Result<List<UserProfile>> {
//        return safeApiCall({
//            bizNearbyApi.getModerators()
//        })
//    }
//
//    override fun getCurrentUserProfile(): UserProfile? {
//        return prefs.getCurrentUserProfile()
//    }
//
////    private fun getAccessToken(): String {
////        val token: String = prefs[ACCESS_TOKEN] ?: ""
////        return "Bearer $token"
////    }
//
//    override suspend fun fetchUserProfile(userId: String): Result<UserProfile> {
//        return safeApiCall({
//            val userProfile = bizNearbyApi.getUserProfile(
//                userId = userId
//            )
//            saveUserToPrefs(userProfile)
//            userProfile
//        })
//    }
//
//
//
//    override suspend fun signUp(request: SignUpRequest): Result<UserProfile> {
//        val imageProfile: MultipartBody.Part? = request.imageProfilePath?.let {
//            val type: String = getMimeType(it) ?: return@let null
//            val file = File(it)
//            val requestBody = RequestBody.create(MediaType.parse(type), file)
//            // MultipartBody.Part is used to send also the actual file name
//            return@let MultipartBody.Part.createFormData("imageProfile", file.name, requestBody)
//        }
//
//        val response = bizNearbyApi.signUp(
//            id = genRequestBody(request.id),
//            email = genRequestBody(request.email),
//            password = request.password?.let { genRequestBody(it) },
//            googleToken = request.googleAuth?.token?.let { genRequestBody(it) },
//            googleUserId = request.googleAuth?.userId?.let { genRequestBody(it) },
//            typeLogin = genRequestBody(request.typeLogin),
//            fullname = genRequestBody(request.fullname),
//            thumbnailUrl = genRequestBody(request.thumbnailUrl ?: ""),
//            imageProfile = imageProfile
//        )
//        response.headers().get(ACCESS_TOKEN)?.let {
//            prefs[ACCESS_TOKEN] = it
//        }
//        response.body()?.let { saveUserToPrefs(it) }
//
//        return safeApiCall({
//            response.body()
//        })
//    }
//
//    private fun saveUserToPrefs(it: UserProfile) {
//        val toJson = GsonBuilder().serializeNulls().create().toJson(it)
//        debug("user saved to prefs=$toJson")
//        prefs[USER] = toJson
//    }
//
//    override suspend fun logIn(request: LogInRequest): Result<UserProfile> {
//        val response = bizNearbyApi.logIn(request)
//         response.headers().get(ACCESS_TOKEN)?.let {
//            prefs[ACCESS_TOKEN] = it
//        }
//        response.body()?.let { saveUserToPrefs(it) }
//
//        return safeApiCall({
//            response.body()
//        })
//    }
//
//    override suspend fun logOut(): Result<LogOutResponse> {
//        return safeApiCall({
//            bizNearbyApi.logOut()
//        }, onFinally = {
//            prefs[USER] = ""
//            prefs[ACCESS_TOKEN] = ""
//        })
//    }

    //
//
//    override fun getAuthInfo(): AuthInfo {
//        return AuthInfo(
//            logIn = prefs[AppPreferencesHelper.LOGIN],
//            token = prefs[AppPreferencesHelper.TOKEN]
//        )
//    }
//
//    override fun getUserData(): UserData? {
//        val token = getAuthInfo().token
//        if (token.isNullOrEmpty()) return null
//        return Gson().fromJson<UserData>(token.decoded().payload, UserData::class.java)
//    }
//
//    override suspend fun getFirebaseTokenId(): String? {
//        return suspendCoroutine { cont ->
//            FirebaseInstanceId.getInstance().instanceId
//                .addOnCompleteListener(OnCompleteListener { task ->
//                    if (!task.isSuccessful) {
//                        debug("FirebaseInstanceId getInstance failed ${task.exception}")
//                        cont.resume(null)
//                        return@OnCompleteListener
//                    }
//                    val token = task.result?.token
//                    debug("FirebaseInstanceId getInstance token=$token")
//                    cont.resume(token)
//                })
//        }
//    }
//
//    override suspend fun sendFirebaseTokenId(firebaseTokenId: FirebaseTokenId): Result<FirebaseTokenId> {
//        return mapToResult {
//            bizNearbyApi.sendFirebaseTokenId(
//                userId = getUserData()?.id ?: throw Exception("getUserData.id is null"),
//                firebaseTokenId = firebaseTokenId
//            ).also { prefs[AppPreferencesHelper.TOKEN_UPLOADED] = true }
//        }
//    }
//
//    override suspend fun logIn(user: String, password: String): Result<LogInResponse> {
//        return mapToResult {
//            //logOut() didnt work :u
//            bizNearbyApi.logIn(
//                LogInRequest(
//                    usuario = user,
//                    clave = password
//                )
//            ).apply {
//                prefs[AppPreferencesHelper.LOGIN] = logIn
//                prefs[AppPreferencesHelper.USER] = user
//                prefs[AppPreferencesHelper.TOKEN] = token
//                prefs[AppPreferencesHelper.TOKEN_UPLOADED] = false
//            }
//        }
//    }
//
//    override suspend fun logOut(): Result<LogOutResponse> {
//        return mapToResult {
//            bizNearbyApi.logOut(LogOutRequest(usuario = prefs[AppPreferencesHelper.USER]))
//                .apply {
//                    prefs[AppPreferencesHelper.LOGIN] = logIn
//                    prefs[AppPreferencesHelper.TOKEN] = ""
//                    prefs[AppPreferencesHelper.TOKEN_UPLOADED] = false
//                }.also { appDatabase.clearAllTables() }
//        }
//    }

}
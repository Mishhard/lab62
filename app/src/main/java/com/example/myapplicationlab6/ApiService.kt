import com.example.myapplicationlab6.LoginRequest
import com.example.myapplicationlab6.LoginResponse
import com.example.myapplicationlab6.RegisterRequest
import com.example.myapplicationlab6.RegisterResponse
import com.example.myapplicationlab6.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // Авторизация (POST /auth/login)
    @POST("/auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    // Регистрация (POST /auth/register)
    @POST("/auth/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    // Получение информации о пользователе (GET /users/{guid})
    @GET("/users/{guid}")
    fun getUser(@Path("guid") guid: String): Call<UserResponse>
}

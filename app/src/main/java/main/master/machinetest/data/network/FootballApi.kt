package main.master.machinetest.data.network


import main.master.machinetest.data.model.Football
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApi {

    @GET("football.json")
    suspend fun getFootball(@Query("alt") alt:String,
                            @Query("token") token:String) : Response<Football>


    companion object{
        operator fun invoke() : FootballApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://firebasestorage.googleapis.com/v0/b/calling-papa.appspot.com/o/")
                .build()
                .create(FootballApi::class.java)
        }
    }
}

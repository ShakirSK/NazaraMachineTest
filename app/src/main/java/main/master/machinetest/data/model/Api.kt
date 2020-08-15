package main.master.machinetest.data.model


import com.google.gson.annotations.SerializedName

data class Api(
    @SerializedName("results")
    val results: Int,
    @SerializedName("teams")
    val teams: List<Team>
)
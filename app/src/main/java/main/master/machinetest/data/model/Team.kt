package main.master.machinetest.data.model


import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Team(

    val code: String?,
    val country: String,
    val founded: Int,
    val is_national: Boolean,
    val logo: String,
    val name: String,
    @PrimaryKey
    val team_id: Int,
    val venue_address: String,
    val venue_capacity: Int,
    val venue_city: String,
    val venue_name: String,
    val venue_surface: String
)
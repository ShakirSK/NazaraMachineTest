package main.master.machinetest.data.repository

import main.master.machinetest.data.db.AppDatabase
import main.master.machinetest.data.model.Team
import main.master.machinetest.data.network.FootballApi

class FootballRepository(
    private val api: FootballApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun getFootball() = apiRequest { api.getFootball("media","508bf994-8f9d-42b0-8e6b-310829ed13e7") }

    suspend fun saveFootballTeam(team: List<Team>) = db.getTeamDao().upsert(team)

    fun getFootballTeam() = db.getTeamDao().getteam()
}
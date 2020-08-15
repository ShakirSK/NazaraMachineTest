package main.master.machinetest.ui.football

import android.view.View
import main.master.machinetest.data.model.Football
import main.master.machinetest.data.model.Team


interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, team : Team)
}
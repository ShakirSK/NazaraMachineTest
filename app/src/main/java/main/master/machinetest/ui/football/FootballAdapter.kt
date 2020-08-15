package main.master.machinetest.ui.football


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import main.master.machinetest.R
import main.master.machinetest.data.model.Team
import main.master.machinetest.databinding.RecyclerviewFootballBinding

class FootballAdapter (
    private val team:  List<Team>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<FootballAdapter.FootballViewHolder>(){

    override fun getItemCount() = team.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FootballViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_football,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FootballViewHolder, position: Int) {
        Log.d("sizearray", team.size.toString());
        holder.recyclerviewFootballBinding.viewmodel = team[position]
        holder.recyclerviewFootballBinding.buttonTeam.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.recyclerviewFootballBinding.buttonTeam, team[position])
        }
    }


    inner class FootballViewHolder(
        val recyclerviewFootballBinding: RecyclerviewFootballBinding
    ) : RecyclerView.ViewHolder(recyclerviewFootballBinding.root)

}
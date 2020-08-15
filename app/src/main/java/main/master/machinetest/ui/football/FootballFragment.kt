package main.master.machinetest.ui.football


import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.football_fragment.*

import main.master.machinetest.R
import main.master.machinetest.data.db.AppDatabase
import main.master.machinetest.data.model.Football
import main.master.machinetest.data.model.Team
import main.master.machinetest.data.network.FootballApi
import main.master.machinetest.data.repository.FootballRepository

class FootballFragment :  Fragment(), RecyclerViewClickListener{

    //FootballViewModelFactory is used for ViewModelProviders bcz FootballViewModel has constructor
    private lateinit var factory: FootballViewModelFactory
    private lateinit var viewModel: FootballViewModel

    var internet : Boolean = true

    var db: AppDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.football_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        db = AppDatabase.invoke(requireContext())!!

        val api = FootballApi()
        val repository = FootballRepository(api, db!!)
        factory = FootballViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(FootballViewModel::class.java)



        //Checking Network Connectivity
        internet = checkConnectivity()
        if (internet == true) {
            //calling webserive and after observing ..inserting into DB
            webserivetoDB()
        }
        else{
            //if connectivity fails , will show from DB
            Toast.makeText(requireContext(),"Check network connection",Toast.LENGTH_LONG).show()
            FromDB()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //PullToRefresh
        fetchUsersByPullToRefresh()
    }


    private fun fetchUsersByPullToRefresh() {

        swipeToRefresh?.setOnRefreshListener {

            //checking Internet Connectivity
            internet = checkConnectivity()
            if (internet == true) {
                //calling webserive and after observing ..inserting into DB
                webserivetoDB()
                swipeToRefresh.isRefreshing = false
                Toast.makeText(requireContext()," network on",Toast.LENGTH_LONG).show()
            }
            else{
                //if connectivity fails , will show from DB
                FromDB()
                swipeToRefresh.isRefreshing = false
                Toast.makeText(requireContext(),"network off",Toast.LENGTH_LONG).show()
            }
        }


    }

    private fun webserivetoDB(){


        //viewmodel method is call where we get the data from webserice in a cycle of MVVM
        //( APIClient - Repository - Viewmodal - LiveData - Databinding(UI) )
        viewModel.getFootball()

        //here football is our live data which observe the changes
        viewModel.football.observe(viewLifecycleOwner, Observer { football ->
            recycler_view_football.also {
                //insert into DB
                viewModel.insert(football.api.teams)
            }
        })

        //here getDataFromDB function is our live data which observe from DB
        viewModel.getDataFromDB().observe(viewLifecycleOwner, Observer { football ->
            recycler_view_football.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                //setting it to recycleview adapter
                it.adapter = FootballAdapter(football, this)
            }
        })

    }


    private fun FromDB(){
        // getDataFromDB function is our live data which observe from DB
        viewModel.getDataFromDB().observe(viewLifecycleOwner, Observer { football ->
            recycler_view_football.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                //setting it to recycleview adapter
                it.adapter = FootballAdapter(football, this)

            }
        })
    }





    //On click Button listener of Recycleview Child View
    override fun onRecyclerViewItemClick(view: View, team: Team) {
        when(view.id){
            R.id.button_team -> {
                Toast.makeText(requireContext(), team.name+ " Selected",Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun checkConnectivity():Boolean{
        var cm = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activityNetwork = cm.activeNetworkInfo
        val isConnected = activityNetwork != null && activityNetwork.isConnectedOrConnecting

        if(!isConnected){
            return false
        }
        return true
    }

}

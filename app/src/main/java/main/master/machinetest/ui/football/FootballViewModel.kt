package main.master.machinetest.ui.football

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import main.master.machinetest.data.model.Football
import main.master.machinetest.data.model.Team
import main.master.machinetest.data.repository.FootballRepository
import main.master.machinetest.util.Coroutines

class FootballViewModel(
    private val repository: FootballRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _football = MutableLiveData<Football>()

    //football is a  LiveData data holder for Football
    val football:LiveData<Football>
        get() = _football

    //used kotlin coroutines
    //which fetch data inside an IOthread and then we have a callback to the mainthread
     fun getFootball() {
        //sending repository.getFootball() getting it
        job = Coroutines.ioThenMain(
            { repository.getFootball() },
            { _football.value = it }
        )
    }

    //insert function with coroutines viewModelScope which performs operation in background
    fun insert(t : List<Team>) = viewModelScope.launch {
        repository.saveFootballTeam(t)
        Log.d("databaseresponse",t.toString())
    }


    //get data from db
    fun getDataFromDB() = repository.getFootballTeam()

  /*  fun getDatamine() : LiveData<List<Team>>
    {
        return RoomViewModelKotlinSampleApplication.database!!.teamDao().getAllCountries()

    }

*/

    /*fun insertdb() {
       job =  Coroutines.main {
            repository.saveUser(_football.value!!.api.teams)
            }
        }*/

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}

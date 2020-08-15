package main.master.machinetest.ui.football


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import main.master.machinetest.data.repository.FootballRepository

@Suppress("UNCHECKED_CAST")
class FootballViewModelFactory(
    private val repository: FootballRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FootballViewModel(repository) as T
    }

}
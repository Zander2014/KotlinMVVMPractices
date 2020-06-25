package pers.zander.kotlinmvvm.practice.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pers.zander.kotlinmvvm.practice.checkSuccess
import pers.zander.kotlinmvvm.practice.model.bean.Navigation
import pers.zander.kotlinmvvm.practice.model.repository.NavigationRepository
import pers.zander.mvvmcore.base.BaseViewModel

/**
 * Created by Zander on 2020/6/25.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class NavigationViewModel(private val navigationRepository: NavigationRepository) : BaseViewModel() {
    private val _uiState = MutableLiveData<List<Navigation>>()
    val uiState: LiveData<List<Navigation>>
        get() = _uiState

    fun getNavigation(){
        launchOnUI {
            val result = withContext(Dispatchers.IO){
                navigationRepository.getNavigation()
            }
            result.checkSuccess {
                _uiState.value = it
            }
        }
    }
}
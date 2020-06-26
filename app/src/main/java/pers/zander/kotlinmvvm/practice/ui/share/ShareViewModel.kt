package pers.zander.kotlinmvvm.practice.ui.share

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pers.zander.kotlinmvvm.practice.model.repository.ShareRepository
import pers.zander.mvvmcore.Result
import pers.zander.mvvmcore.base.BaseViewModel

/**
 * Created by Zander on 2020/6/25.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class ShareViewModel(private val shareRepository: ShareRepository): BaseViewModel() {
    val title = ObservableField("")
    val url = ObservableField<String>("")

    private val _uiState = MutableLiveData<ShareUIModel>()
    val uiState : MutableLiveData<ShareUIModel>
        get() = _uiState

    val verifyInput : (String) -> Unit = {shareDataChange()}

    private fun shareDataChange() {
        enableShare((title.get()?.isNotBlank()?:false) && (url.get()?.isNotBlank()?:false))
    }

    fun shareArticle(){
        viewModelScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.Main){ emitUIState(showProgress = true)}
            val result = shareRepository.shareArticle(title.get()?:"", url.get()?:"")
            withContext(Dispatchers.Main){
                if(result is Result.Success){
                    emitUIState(false, result.data, "", true)
                }else if(result is Result.Error){
                    emitUIState(false,"", result.exception.message, true)
                }
            }
        }
    }

    private fun enableShare(enable: Boolean) {
        emitUIState(enableShareButton = enable)
    }

    private fun emitUIState(
        showProgress: Boolean = false,
        showSuccess: String? = null,
        showError: String? = null,
        enableShareButton: Boolean = false
    ) {
        _uiState.value = ShareUIModel(showProgress, showSuccess, showError, enableShareButton)
    }


}

data class ShareUIModel(val showProgress: Boolean,
                        val showSuccess: String?,
                        val showError: String?,
                        val enableShareButton: Boolean)
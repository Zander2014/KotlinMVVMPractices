package pers.zander.kotlinmvvm.practice.ui.square

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pers.zander.kotlinmvvm.practice.model.bean.ArticleList
import pers.zander.kotlinmvvm.practice.model.bean.Banner
import pers.zander.kotlinmvvm.practice.model.repository.*
import pers.zander.mvvmcore.Result
import pers.zander.mvvmcore.base.BaseViewModel

/**
 * Created by Zander on 2020/6/26.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class ArticleViewModel(
    private val homeRepository: HomeRepository,
    private val squareRepository: SquareRepository,
    private val projectRepository: ProjectRepository,
    private val collectRepository: CollectRepository,
    private val systemRepository: SystemRepository
): BaseViewModel() {
    sealed class ArticleType{
        object home: ArticleType()                  //首页
        object Square : ArticleType()               // 广场
        object LatestProject : ArticleType()        // 最新项目
        object ProjectDetailList : ArticleType()    // 项目列表
        object Collection : ArticleType()           // 收藏
        object SystemType : ArticleType()           // 体系分类
        object Blog : ArticleType()                 // 公众号
    }

    private val _uiState = MutableLiveData<ArticleUIModel>()
    val uiState: LiveData<ArticleUIModel>
        get() = _uiState

    private var currentPage = 0

    val banners : LiveData<List<Banner>> = liveData{
        kotlin.runCatching {
            var result = homeRepository.getBanners()
            if(result is Result.Success) emit(result.data)
        }
    }

    val refreshSquare = {getSquareArticleList(true)}
    val refreshHome = {getHomeArticleList(true)}

    fun getHomeArticleList(isRefresh: Boolean) = getArticleList(ArticleType.home, isRefresh)
    fun getSquareArticleList(isRefresh: Boolean = false) = getArticleList(ArticleType.Square, isRefresh)

    fun collectArticle(articleId: Int, boolean: Boolean) {
        launchOnUI {
            withContext(Dispatchers.IO) {
                if (boolean) collectRepository.collectArticle(articleId)
                else collectRepository.unCollectArticle(articleId)
            }
        }
    }

    private fun getArticleList(articleType: ArticleType, isRefresh: Boolean = false, cid: Int = 0){
        viewModelScope.launch(Dispatchers.Main) {
            emitArticleUiState(true)

            if(isRefresh) currentPage = if(articleType is ArticleType.ProjectDetailList) 1 else 0

            val result = when(articleType){
                ArticleType.home -> homeRepository.getArticleList(currentPage)
                ArticleType.Square -> squareRepository.getSquareArticleList(currentPage)
                ArticleType.LatestProject -> projectRepository.getLastedProject(currentPage)
                ArticleType.ProjectDetailList -> projectRepository.getProjectTypeDetailList(currentPage, cid)
                ArticleType.Collection -> collectRepository.getCollectArticles(currentPage)
                ArticleType.SystemType -> systemRepository.getSystemTypeDetail(cid, currentPage)
                ArticleType.Blog -> systemRepository.getBlogArticle(cid, currentPage)
            }

            if(result is Result.Success){
                var articleList = result.data
                if(articleList.offset >= articleList.total){
                    emitArticleUiState(false, showEnd = true)
                    return@launch
                }
                currentPage++
                emitArticleUiState(false, showSuccess = articleList, isRefresh = isRefresh)
            }else if (result is Result.Error){
                emitArticleUiState(false, showError = result.exception.message)
            }
        }
    }

    private fun emitArticleUiState(
        showLoading: Boolean = false,
        showError: String? = null,
        showSuccess: ArticleList? = null,
        showEnd: Boolean = false,
        isRefresh: Boolean = false,
        needLogin: Boolean? = null
    ) {
        _uiState.value = ArticleUIModel(showLoading, showError, showSuccess, showEnd, isRefresh, needLogin)
    }

    data class ArticleUIModel(val showLoading: Boolean,
                              val showError: String?,
                              val showSuccess: ArticleList?,
                              val showEnd: Boolean, // 加载更多
                              val isRefresh: Boolean, // 刷新
                              val needLogin: Boolean? = null)
}


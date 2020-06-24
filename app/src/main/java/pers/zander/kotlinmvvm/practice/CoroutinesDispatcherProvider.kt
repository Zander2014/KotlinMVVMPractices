package pers.zander.kotlinmvvm.practice

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 * Provide coroutines context.
 */

data class CoroutinesDispatcherProvider(
    val main : CoroutineDispatcher = Dispatchers.Main,
    val computation : CoroutineDispatcher = Dispatchers.Default,
    val io : CoroutineDispatcher = Dispatchers.IO
) {
    @Inject
    constructor() : this(Dispatchers.Main, Dispatchers.Default, Dispatchers.IO)
}
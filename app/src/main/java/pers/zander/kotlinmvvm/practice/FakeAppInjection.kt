package pers.zander.kotlinmvvm.practice

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */

@ExperimentalCoroutinesApi
fun provideFakeCoroutinesDispatcherProvider(
    main: CoroutineDispatcher? = null,
    computation: CoroutineDispatcher? = null,
    io: CoroutineDispatcher? = null
) : CoroutinesDispatcherProvider{
    val sharedTestCoroutineDispatcher = TestCoroutineDispatcher()
    return CoroutinesDispatcherProvider(
        main ?: sharedTestCoroutineDispatcher,
        computation ?: sharedTestCoroutineDispatcher,
        io ?: sharedTestCoroutineDispatcher)
}

@ExperimentalCoroutinesApi
fun provideFakeCoroutinesDispatcherProvider(
    dispatcher: TestCoroutineDispatcher?
): CoroutinesDispatcherProvider {
    val sharedTestCoroutineDispatcher = TestCoroutineDispatcher()
    return CoroutinesDispatcherProvider(
        dispatcher ?: sharedTestCoroutineDispatcher,
        dispatcher ?: sharedTestCoroutineDispatcher,
        dispatcher ?: sharedTestCoroutineDispatcher)
}
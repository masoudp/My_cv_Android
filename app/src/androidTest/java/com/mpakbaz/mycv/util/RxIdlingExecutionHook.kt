package com.mpakbaz.mycv.util

import rx.Observable
import rx.Subscription
import rx.plugins.RxJavaObservableExecutionHook

/**
 * RxJava Observable execution hook that handles updating the active subscription
 * count for a given Espresso RxIdlingResource.
 */
class RxIdlingExecutionHook(private val mRxIdlingResource: RxIdlingResource) : RxJavaObservableExecutionHook() {

    override fun <T> onSubscribeStart(
            observableInstance: Observable<out T>?, onSubscribe: Observable.OnSubscribe<T>): Observable.OnSubscribe<T> {
        mRxIdlingResource.incrementActiveSubscriptionsCount()
        return super.onSubscribeStart(observableInstance, onSubscribe)
    }

    override fun <T> onSubscribeError(e: Throwable): Throwable {
        mRxIdlingResource.decrementActiveSubscriptionsCount()
        return super.onSubscribeError<Any>(e)
    }

    override fun <T> onSubscribeReturn(subscription: Subscription): Subscription {
        mRxIdlingResource.decrementActiveSubscriptionsCount()
        return super.onSubscribeReturn<Any>(subscription)
    }
}
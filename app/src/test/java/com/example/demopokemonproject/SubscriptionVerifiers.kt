package com.example.demopokemonproject

import androidx.annotation.CheckResult
import io.mockk.MockKMatcherScope
import io.mockk.every
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.observers.TestObserver


@CheckResult
fun testCompletableFor(
    completableMethod: (MockKMatcherScope.() -> Completable)
): TestObserver<Void> =
    testCompletable()
        .let { (subscription, completable) ->
            every(completableMethod) returns completable
            return@let subscription
        }

@CheckResult
fun testCompletableErrorFor(
    error: Throwable,
    completableMethod: MockKMatcherScope.() -> Completable
) = testCompletable(sourceCompletable = Completable.error(error))
    .let { (subscription, completable) ->
        every(completableMethod) returns completable
        return@let subscription
    }

@CheckResult
private fun testCompletable(sourceCompletable: Completable = Completable.complete()): Pair<TestObserver<Void>, Completable> {
    val subscriptionObserver = TestObserver.create<Void>()
    val completable = sourceCompletable.doOnSubscribe(subscriptionObserver::onSubscribe)
    return subscriptionObserver to completable
}

package com.example.demopokemonproject.ui.common

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel() : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    protected fun Disposable.remember() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
package com.example.demopokemonproject.core

import io.reactivex.rxjava3.core.Scheduler

interface CoreSchedulers {
	val computation: Scheduler
	val dbIO: Scheduler
	val diskIO: Scheduler
	val networkIO: Scheduler
	val mainThread: Scheduler
}
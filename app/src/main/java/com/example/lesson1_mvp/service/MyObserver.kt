package com.example.lesson1_mvp.service

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MyObserver<Any> {

    var producer = Producer<Any>()

    class Producer<Any> {

        private var producer: Observable<Any>? = null

        fun sendMessage(message: Any) {
            producer = Observable.just(message)
        }

        fun getProducer() = producer
    }

    class Consumer<Any>(val producer: Producer<Any>, callback: (arg1: Any) -> Unit) {
        val observer = object : Observer<Any> {
            var disposable: Disposable? = null
            override fun onComplete() {
                println("onComplete")
            }

            override fun onSubscribe(d: Disposable?) {
                disposable = d
                println("onSubscribe")
            }

            override fun onNext(s: Any?) {
                println("onNext")
                callback(s!!)
            }

            override fun onError(e: Throwable?) {
                println("onError: ${e?.message}")
            }
        }

        fun exec() {
            producer.getProducer()?.subscribe(observer)
        }
    }
}
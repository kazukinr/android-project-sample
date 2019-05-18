package com.github.kazukinr.android.sample.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class DisposableLifecycleObserver(private val callbacks: Callbacks) : LifecycleObserver {

    private val onCreateDisposable = CompositeDisposable()
    private val onStartDisposable = CompositeDisposable()
    private val onResumeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        onCreateDisposable.clear()
        callbacks.onCreate(onCreateDisposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        onStartDisposable.clear()
        callbacks.onStart(onStartDisposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        onResumeDisposable.clear()
        callbacks.onResume(onResumeDisposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        callbacks.onPause()
        onResumeDisposable.clear()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        callbacks.onStop()
        onStartDisposable.clear()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        callbacks.onDestroy()
        onCreateDisposable.clear()
    }

    interface Callbacks {

        fun onCreate(disposable: CompositeDisposable) {}

        fun onStart(disposable: CompositeDisposable) {}

        fun onResume(disposable: CompositeDisposable) {}

        fun onPause() {}

        fun onStop() {}

        fun onDestroy() {}
    }
}

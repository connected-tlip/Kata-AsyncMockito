package com.connectedlab.kata3

import android.os.Handler


class RequestApi {
    /**
     * Callback occurs after one second.
     */
    fun sendRequest(callback: RequestCallback?) {
        val handler = Handler()
        handler.postDelayed({
            callback?.onResponse("request one!")
        }, 1000)
    }

    /**
     * Callback occurs after one second.
     */
    fun sendRequestWithParam(param: Int, callback: RequestCallback?) {
        val handler = Handler()
        handler.postDelayed({
            callback?.onResponse("request one!")
        }, 1000)
    }

    /**
     * Return emitter that emits an event every second.
     */
    fun getEventEmitter(): EventEmitter {
        val emitter = EventEmitter()
        val handler = Handler()

        val runnable = object: Runnable {
            override fun run() {
                emitter.emitEvent()
            }
        }

        handler.postDelayed({
            runnable.run()
            handler.postDelayed(runnable, 1000)
        }, 1000)

        return emitter
    }
}
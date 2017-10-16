package com.connectedlab.kata3

class EventEmitter {
    private val callbacks = ArrayList<EventCallback>()

    fun addCallback(eventCallback: EventCallback) {
        callbacks.add(eventCallback)
    }

    fun emitEvent() {
        callbacks.forEach { callback ->
            callback.onEvent()
        }
    }
}

interface EventCallback {
    fun onEvent()
}
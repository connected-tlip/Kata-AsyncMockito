package com.connectedlab.kata3

interface RequestCallback {
    fun onResponse(response: String)
    fun onError(error: Exception)
}

class RequestSender(val requestApi: RequestApi) {

    lateinit var requestPrivateCallbackResult: String
    lateinit var requestWithParamResult: String
    var eventsReceived = 0

    /**
     * Test me ONE
     *
     * Hint: Mockito.verify
     */
    fun sendRequestWithCallback(callback: RequestCallback) {
        requestApi.sendRequest(callback)
    }

    /**
     * Test me TWO
     *
     * Hint: ArgumentCaptor.forClass
     */
    fun sendRequestPrivateCallback() {
        requestApi.sendRequest(object : RequestCallback {
            override fun onResponse(response: String) {
                performOperation(response)
            }

            // Don't worry about testing this
            override fun onError(error: Exception) { /* No-op */ }
        })
    }

    private fun performOperation(input: String) {
        requestPrivateCallbackResult = "modified " + input
    }

    /**
     * Test me THREE
     *
     * Hint: Mockito.when(___).thenThrow
     */
    fun sendRequestTryCatch(callback: RequestCallback) {
        try {
            requestApi.sendRequest(callback)
        } catch (error: IllegalStateException) {
            callback.onError(error)
        }
    }

    /**
     * Test me FOUR
     *
     * Hint: Mockito.verify with argument matchers
     */
    fun sendRequestWithParam(param: Int) {
        requestApi.sendRequestWithParam(
                param = param,
                callback = object : RequestCallback {
                    override fun onResponse(response: String) {
                        requestWithParamResult = response + ":"  + param
                    }
                    override fun onError(error: Exception) { /* No-op */ }
                })
    }

    /**
     * Test me FIVE
     *
     * Hint: Mockito.when(___).thenReturn
     */
    fun observeEvents() {
        val emitter = requestApi.getEventEmitter()

        emitter.addCallback(object: EventCallback {
            override fun onEvent() {
                eventsReceived++
            }
        })
    }
}
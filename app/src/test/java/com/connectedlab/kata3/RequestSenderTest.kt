package com.connectedlab.kata3

import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito

class RequestSenderTest {
    private lateinit var api: RequestApi
    private lateinit var sut: RequestSender

    @Before
    fun setup() {
        api = Mockito.mock(RequestApi::class.java)
        sut = RequestSender(api)
    }

    @Test
    fun sendRequestWithCallback() {
        val callback = Mockito.mock(RequestCallback::class.java)

        sut.sendRequestWithCallback(callback)

        Mockito.verify(api).sendRequest(callback)
    }

    @Test
    fun sendRequestPrivateCallback() {
        val captor = ArgumentCaptor.forClass(RequestCallback::class.java)

        sut.sendRequestPrivateCallback()

        Mockito.verify(api).sendRequest(captor.capture())
        captor.value.onResponse("Server-Response")

        assertEquals("modified Server-Response", sut.requestPrivateCallbackResult)
    }

    @Test
    fun sendRequestThrowsException() {
        val callback = Mockito.mock(RequestCallback::class.java)
        val error = IllegalStateException()
        Mockito.`when`(api.sendRequest(callback)).thenThrow(error)

        sut.sendRequestTryCatch(callback)

        Mockito.verify(callback).onError(error)
    }

    @Test
    fun sendRequestWithParam() {
        val captor = ArgumentCaptor.forClass(RequestCallback::class.java)

        sut.sendRequestWithParam(2)

        Mockito.verify(api).sendRequestWithParam(Mockito.eq(2), captor.capture())
        captor.value.onResponse("Server-Response")

        assertEquals("Server-Response:2", sut.requestWithParamResult)
    }

    @Test
    fun observeEvents() {
        val emitter = EventEmitter()
        Mockito.`when`(api.getEventEmitter()).thenReturn(emitter)

        sut.observeEvents()

        emitter.emitEvent()
        
        assertEquals(1, sut.eventsReceived)

    }
}
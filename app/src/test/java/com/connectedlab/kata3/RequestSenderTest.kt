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
    }

    @Test
    fun sendRequestPrivateCallback() {
    }

    @Test
    fun sendRequestThrowsException() {
    }

    @Test
    fun sendRequestWithParam() {
    }

    @Test
    fun observeEvents() {
    }
}
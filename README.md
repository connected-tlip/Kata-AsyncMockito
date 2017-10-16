# Testing Async Code with Mockito

When writing networking code, asynchronous calls often make unit-testing difficult. However,
dependency injection and mocks should allow us to avoid awkward hacks or increasing visibility
unnecessarily.

In this Kata, we will learn some strategies and nuances when testing asynchronous code.

## Problem Description

We will be writing tests for [RequestSender](
./app/src/main/java/com/connectedlab/kata3/RequestSender.kt), which uses [RequestApi](
./app/src/main/java/com/connectedlab/kata3/RequestApi.kt) in order to make asychronous calls.

The code for RequestSender is provided; write tests for each function marked `Test me` into
[RequestSenderTest](./app/src/test/java/com/connectedlab/kata3/RequestSenderTest.kt), and try to
cover all the code paths within each. Try to have 100% code coverage _without making any changes
to RequestSender_.

## Third Party Libraries

We will use [Mockito](http://site.mockito.org/) for mocking.
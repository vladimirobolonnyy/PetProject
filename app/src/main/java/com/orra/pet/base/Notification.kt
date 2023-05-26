package com.orra.pet.base

sealed class Notification(val message: Text) {
    class Success(message: Text) : Notification(message)
    class Error(message: Text) : Notification(message)
    class Info(message: Text) : Notification(message)
}

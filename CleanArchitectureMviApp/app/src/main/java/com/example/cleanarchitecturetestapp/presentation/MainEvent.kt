package com.example.cleanarchitecturetestapp.presentation

interface MainEvent

class SaveEvent(val text: String) : MainEvent

class LoadEvent() : MainEvent
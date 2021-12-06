package com.base.state

class NetworkState {
    enum class Status {
        RUNNING, SUCCESS, FAILED
    }

    var status: Status
    var message: String

    constructor(status: Status, message: String) {
        this.status = status
        this.message = message
    }

    constructor() {
        status = Status.RUNNING
        message = ""
    }

    companion object {
        var LOADED: NetworkState? = null
        var LOADING: NetworkState? = null

        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")
            LOADING = NetworkState(Status.RUNNING, "Running")
        }
    }
}
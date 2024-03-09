package tictactoe.com.app.Core

import tictactoe.com.app.manager.ClientManager

class Constant {
    companion object {
        const val MESSAGE = "MESSAGE"
        const val LOGIN = "LOGIN"
        const val LOGOUT = "LOGOUT"
        const val SOLO = "SOLO"
        val clientManager: ClientManager = ClientManager()
    }
}
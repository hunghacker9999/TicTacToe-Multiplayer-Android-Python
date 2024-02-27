package tictactoe.com.app.Core

import tictactoe.com.app.networking.MyWebsocket
import java.util.Observable
import java.util.Observer

class ClientManager(obs: Observer) : Observable() {
    private var websocket: MyWebsocket

    init {
        this.addObserver(obs)
        websocket = MyWebsocket(this)
    }

    override fun notifyObservers(arg: Any?) {
        super.setChanged()
        super.notifyObservers(arg)
    }

    fun startConnect() {
        websocket.start()
    }
}
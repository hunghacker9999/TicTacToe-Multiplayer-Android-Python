package tictactoe.com.app.Core

import tictactoe.com.app.networking.MyWebsocket
import java.util.Observable
import java.util.Observer

class ClientManager(obs: Observer) : Observable() {
    private var websocket: MyWebsocket
    private var name: String? = null
    init {
        this.addObserver(obs)
        websocket = MyWebsocket(this)
    }

    override fun notifyObservers(arg: Any?) {
        super.setChanged()
        super.notifyObservers(arg)
    }

    fun startConnect(name: String) {
        this.name = name
        websocket.start(name)
    }
}
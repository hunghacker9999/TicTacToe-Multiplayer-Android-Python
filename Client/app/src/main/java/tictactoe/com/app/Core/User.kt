package tictactoe.com.app.Core

import java.io.Serializable
import java.util.Observable

data class User(val name : String, val age : Int) : Observable(), Serializable {
    var add : String? = null
    init {
        add = "Da NAng"
    }

    override fun notifyObservers() {
        super.setChanged()
        super.notifyObservers()
    }
    fun dis(): String {
        return "$name   $age  $add"
    }
}
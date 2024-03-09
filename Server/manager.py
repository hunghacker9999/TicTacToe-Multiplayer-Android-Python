from fastapi import FastAPI, WebSocket, WebSocketDisconnect
from fastapi.responses import HTMLResponse
import json
from typing import List

class ConnectionManager:
    def __init__(self):
        self.active_connections = {}

    async def connect(self, username: str, websocket: WebSocket):
        await websocket.accept()
        self.active_connections[username] = websocket

    def disconnect(self, websocket: WebSocket, username: str):
        del self.active_connections[username]

    async def send_personal_message(self, message: str, websocket: WebSocket):
        await websocket.send_text(message)

    async def broadcast(self, data: json, websocket: WebSocket):
        for connection in self.active_connections:
            await connection.send_json(data)

            
    async def login(self, data, websocket: WebSocket):
        data = {
            "action": "LOGIN",
            "data": "login success"
        }
        await websocket.send_json(data)

    async def send_message(self, data: json, username: str):
        data["from"] = username
        receiver = self.active_connections[data["to"]]

        await receiver.send_json(data)

    async def send_soloRequest(self, data: json, username: str):
        data["from"] = username
        receiver = self.active_connections[data["to"]]

        await receiver.send_json(data)


# -------------------------------------------------------------------------------------
    async def process(self, data: json, websocket: WebSocket, username: str):
        if data["action"] == "LOGIN":
           await self.login(data, websocket)
        elif data["action"] == "SEND_MESSAGE":
            await self.send_message(data, username)
        elif data["action"] == "SOLO":
            await self.send_soloRequest(data, username)
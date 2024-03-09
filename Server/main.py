from typing import List, Hashable
import datetime

from fastapi import FastAPI, WebSocket, WebSocketDisconnect
from fastapi.responses import HTMLResponse
import json
from fastapi import FastAPI
import mysql.connector

app = FastAPI()

ip = "localhost"

class ConnectionManager:
    def __init__(self):
        self.active_connections = {}
    async def connect(self, websocket: WebSocket, username: str):
        await websocket.accept()
        self.active_connections[username] = websocket

        data = {
        "action": "LOGIN",
        "username": username,
        "message" : "Login suceessful"
        }

        await websocket.send_json(data)

    def disconnect(self, websocket: WebSocket, username: str):
        del self.active_connections[username]
        websocket.close()

    async def send_personal_text(self, txt: str, websocket: WebSocket):
        await websocket.send_text(txt)

    async def broadcast(self, txt: str):
        for key in self.active_connections:
            value = self.active_connections[key]
            await value.send_text(txt)

    async def send_request_solo(self, data: json):
        receiver = data['to']
        await self.active_connections[receiver].send_json(data)
    
    async def login_success(websocket: WebSocket, username: str):
        data = {
        "action": "LOGIN",
        "username": username,
        }
        json_str = json.dumps(data)

        await websocket.send_text(json_str)
    

manager = ConnectionManager()


@app.get("/")
async def get():
    return HTMLResponse(open("index.html").read())

@app.websocket("/login/{username}/{password}")
async def websocket_endpoint(websocket: WebSocket, username: str, password: str):
    print("username: " + username +  " " + password)

    await manager.connect(websocket, username)

    try:
        while True:
            data = await websocket.receive_json()
            await processRequest(data, websocket)
    except WebSocketDisconnect:
        manager.disconnect(websocket, username)
            
# --------------------------------------------------------

def create_db_connection():
    connection = mysql.connector.connect(
        host="localhost",
        user="root",
        password="",
        database="tictactoe"
    )
    return connection

async def processRequest(data: json, socket: WebSocket):
    action = data["action"]
    if action == "SOLO":
        manager.send_request_solo(data, socket)

        
    # elif action == "MESSAGE":
    #     await manager.broadcast(data, manager)

if __name__ == "__main__":
    import uvicorn
    
    uvicorn.run(app, host= ip, port=8000)
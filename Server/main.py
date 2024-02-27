from typing import List
import datetime

from fastapi import FastAPI, WebSocket, WebSocketDisconnect
from fastapi.responses import HTMLResponse
import json
from fastapi import FastAPI
import mysql.connector

app = FastAPI()


class ConnectionManager:
    def __init__(self):
        self.active_connections: List[WebSocket] = []

    async def connect(self, websocket: WebSocket):
        await websocket.accept()
        self.active_connections.append(websocket)

    def disconnect(self, websocket: WebSocket):
        self.active_connections.remove(websocket)

    async def send_personal_object(self, object: json, websocket: WebSocket):
        await websocket.send_text(object)

    async def broadcast(self, obj: object):
        for connection in self.active_connections:
            await connection.send_json(obj)


manager = ConnectionManager()


@app.get("/")
async def get():
    return HTMLResponse(open("index.html").read())


# @app.get("/users")
# def get_users():
#     connection = create_db_connection()
#     cursor = connection.cursor()
#     cursor.execute("SELECT * FROM users")
#     users = cursor.fetchall()
#     cursor.close()
#     connection.close()
#     return {"users": users}


@app.get("/users")
def get_users():
    return "Hello Hung"


@app.websocket("/login/{username}/{passwork}")
async def websocket_endpoint(websocket: WebSocket, username: str, password: str):
    await manager.connect(websocket)
    try:
        while True:
            data = await websocket.receive_json()
            # await manager.send_personal_message(f"You wrote: {data}", websocket)
            await manager.broadcast(data, manager)
            processRequest(data)
    except WebSocketDisconnect:
        manager.disconnect(websocket)
        data = {
            "action": "QUIT",
            "username": username,
            "status": True
        }
        json_str = json.dumps(data)

        await manager.broadcast(json_str)

def create_db_connection():
    connection = mysql.connector.connect(
        host="localhost",
        user="root",
        password="",
        database="tictactoe"
    )
    return connection

def processRequest(data: json, manager: ConnectionManager, socket: WebSocket):
    action = data["action"]
    if action == "LOGIN":
        data = {
            "action": "LOGIN",
            "username": action["username"],
            "status": True
        }
        json_str = json.dumps(data)

        manager.send_personal_object(json_str, socket)

if __name__ == "__main__":
    import uvicorn
    
    uvicorn.run(app, host="192.168.1.7", port=8000)
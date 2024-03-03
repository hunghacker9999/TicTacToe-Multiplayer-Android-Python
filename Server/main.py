from typing import List
import datetime

from fastapi import FastAPI, WebSocket, WebSocketDisconnect
from fastapi.responses import HTMLResponse
import json
from fastapi import FastAPI
import mysql.connector

app = FastAPI()

ip = "192.168.1.7"

class ConnectionManager:
    def __init__(self):
        self.active_connections: List[WebSocket] = []

    async def connect(self, websocket: WebSocket):
        await websocket.accept()
        self.active_connections.append(websocket)

    def disconnect(self, websocket: WebSocket):
        self.active_connections.remove(websocket)

    async def send_personal_text(self, txt: str, websocket: WebSocket):
        await websocket.send_text(txt)

    async def broadcast(self, txt: str):
        for connection in self.active_connections:
            await connection.send_text(txt)


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


@app.websocket("/login/{username}/{password}")
async def websocket_endpoint(websocket: WebSocket, username: str, password: str):
    print("username: " + username +  " " + password)
    
    # try:
    #     while True:
    #         data = await websocket.receive_json()
    #         # await manager.send_personal_message(f"You wrote: {data}", websocket)
    #         await manager.broadcast(data, manager)
    #         processRequest(data)
    # except WebSocketDisconnect:
    #     manager.disconnect(websocket)
    #     data = {
    #         "action": "QUIT",
    #         "username": username,
    #         "status": "success"
    #     }
    #     json_str = json.dumps(data)
    #     await manager.broadcast(json_str)

    await manager.connect(websocket)

    data1 = {
        "action": "LOGIN",
        "username": username,
        "status": "success"
    }
    json_str = json.dumps(data1)

    await manager.send_personal_text(json_str, websocket)

    try:
        while True:
            data = await websocket.receive_text()
            print("data receive " + data)
            # await manager.send_personal_text(data, websocket)
            await manager.broadcast(data)
            # processRequest(data)
    except WebSocketDisconnect:
        manager.disconnect(websocket)
            


def create_db_connection():
    connection = mysql.connector.connect(
        host="localhost",
        user="root",
        password="",
        database="tictactoe"
    )
    return connection

async def processRequest(data: json, manager: ConnectionManager, socket: WebSocket):
    action = data["action"]
    if action == "LOGIN":
        data1 = {
            "action": "LOGIN",
            "username": action["username"],
            "status": "success"
        }
        json_str = json.dumps(data1)

        manager.send_personal_text(json_str, socket)
    elif action == "LOGOUT":
        data = {
                "action": "LOGOUT",
                "status": "success"
            }
        json_str = json.dumps(data)
        manager.send_personal_text(json_str, socket)
        manager.disconnect(socket)
        
    # elif action == "MESSAGE":
    #     await manager.broadcast(data, manager)

if __name__ == "__main__":
    import uvicorn
    
    uvicorn.run(app, host= ip, port=8000)
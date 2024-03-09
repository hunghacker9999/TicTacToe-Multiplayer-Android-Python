from typing import List

from fastapi import FastAPI, WebSocket, WebSocketDisconnect
from fastapi.responses import HTMLResponse
import json

from pydantic import Json

from manager import ConnectionManager


app = FastAPI()

manager = ConnectionManager()




@app.get("/")
async def get():
    return HTMLResponse(open("index.html").read())


@app.websocket("/ws/{username}")
async def websocket_endpoint(websocket: WebSocket, username: str):
    await manager.connect(username, websocket)
    await manager.login("123", websocket)
    try:
        while True:
            text = await websocket.receive_json()
            # json_data = json.loads(text)
            await manager.process(text, websocket, username)
    except WebSocketDisconnect:
        manager.disconnect(websocket, username)


if __name__ == "__main__":
    import uvicorn
    
    uvicorn.run(app, host= "192.168.1.7", port=8000)
package com.hocrox.websocketdemo;

import android.util.Log;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by sahilgoyal on 14/8/17.
 */

public class WebServer extends WebSocketListener {

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        webSocket.send("cleint Connected");
        webSocket.send("Hello Mr.");
        webSocket.send("Hello Mr.");
        webSocket.send("Hello Mr.");

        webSocket.send("Hello Mr.");

        webSocket.close(1000,"Good Bye");

    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);

    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        Log.e("Testing Message",""+text);

    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        Log.e("Testing Failure",""+response.message());

    }

}
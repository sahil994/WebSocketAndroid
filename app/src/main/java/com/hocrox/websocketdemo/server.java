package com.hocrox.websocketdemo;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class server extends AppCompatActivity {
    InetAddress inetAddress;
    TextView textView;
    Button mSendMessage;
    WebSocket webSockets;
    String messageq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button = (Button) findViewById(R.id.btnStartServer);
        textView = (TextView) findViewById(R.id.tvTextView);
        mSendMessage = (Button) findViewById(R.id.btnSendMessage);
        mSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webSockets.send("Hello Mr.");
            }
        });
        String ipAddress = "127.0.1.1";
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        Log.e("Streintt", ip);
        InetSocketAddress inetSockAddress = new InetSocketAddress(ip, 8080);

        final WebSocketServer webSocketServer = new WebSocketServer(inetSockAddress) {
            @Override
            public void onOpen(WebSocket webSocket, ClientHandshake handshake) {

                webSocket.send("cleint Connected");
                webSocket.send("Hello Mr.");
                webSocket.send("Hello Mr.");
                webSocket.send("Hello Mr.");

                webSocket.send("Hello Mr.");
               webSockets=  webSocket;

            }

            @Override
            public void onClose(WebSocket conn, int code, String reason, boolean remote) {

            }



            @Override
            public void onMessage(WebSocket conn, String message) {
                Log.e("Testing Message", "" + message);
                messageq = messageq + message;
                textView.setText(""+messageq);
            }

            @Override
            public void onError(WebSocket conn, Exception ex) {

            }

            @Override
            public void onStart() {
                Log.e("Testing", "start");

            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webSocketServer.start();

            }
        });
    }
}

package com.hocrox.websocketdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class client extends AppCompatActivity {


    OkHttpClient okHttpClient;
    WebSocketClient webSocketClient;
    Button mSendMessage;
    TextView textView;
    String messageq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSendMessage= (Button) findViewById(R.id.btnSendMessage);
        textView= (TextView) findViewById(R.id.tvTextView);

        okHttpClient = new OkHttpClient();
        try {
             webSocketClient=new WebSocketClient(new URI("ws://192.168.1.7:8080")) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.e("Testing error","connected");
                }

                @Override
                public void onMessage(String message) {
                    Log.e("Testing error",""+message);
                    messageq = messageq + message;

                    textView.setText(messageq);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.e("Testing error",""+reason);

                }

                @Override
                public void onError(Exception ex) {
                    Log.e("Testing error",""+ex.getMessage());

                }
            };

        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("Testing exception",""+e.getMessage());
        }



        Button server = (Button) findViewById(R.id.btnStartServer);
        server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webSocketClient.connect();

            }
        });
        mSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webSocketClient.send("hloo you..sd");
            }
        });
    }

    private void startServer() {

        Request request = new Request.Builder().url("ws://echo.websocket.org").build();
        WebServer webServer = new WebServer();
            WebSocket webSocket = okHttpClient.newWebSocket(request,webServer);
        okHttpClient.dispatcher().executorService().shutdown();
    }
}

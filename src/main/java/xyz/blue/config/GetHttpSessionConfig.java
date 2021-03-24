package xyz.blue.config;


import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest handshakeRequest, HandshakeResponse handshakeResponse) {
        HttpSession httpSession = (HttpSession) handshakeRequest.getHttpSession();
        if (httpSession != null) {
            config.getUserProperties().put(HttpSession.class.getName(), httpSession);
        }
    }
}

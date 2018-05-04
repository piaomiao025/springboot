package springboot.com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket")
@Component
public class WebSocketTest {
    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    private Logger logger = LoggerFactory.getLogger(WebSocketTest.class);
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        logger.info("有新连接加入！当前在线人数为：{}", getOnlineCount());
    }
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount();
        logger.info("有一连接关闭！当前在线人数为：{}", getOnlineCount());
    }
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("来自客户端的消息：{}", message);

        for(WebSocketTest item: webSocketSet) {
            try{
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("发生错误！");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketTest.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketTest.onlineCount--;
    }
}

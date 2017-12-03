package pers.cmy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import pers.cmy.domain.WiselyMessage;
import pers.cmy.domain.WiselyResponse;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/27
 */
@Controller
public class WsController {
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public WiselyResponse say(WiselyMessage message) throws Exception {
        Thread.sleep(3000);
        return new WiselyResponse("Welcome, " + message.getName() + "!");
    }


    @Autowired
    private SimpMessagingTemplate messagingTemplate;//1

    @MessageMapping("/chat")
    public void handleChat(String msg) { //2
        messagingTemplate.convertAndSendToUser("wyf",
                "/queue/notifications", "-send:"
                        + msg);
    }

    private class RequestMessage {

        private String name;

        public String getName() {
            return name;
        }
    }

    private class ResponseMessage {
        private String responseMessage;

        public ResponseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
        }

        public String getResponseMessage() {
            return responseMessage;
        }
    }
}

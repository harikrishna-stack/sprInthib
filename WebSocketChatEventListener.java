/**
 * 
 */
package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import com.example.demo.WebSocketChatMessage;

/**
 * @author harik
 *
 *creates a new WebSocketEventlistener.this class listen the events such as  a new user joining the chat or leaving the chat.
 */
@Component
public class WebSocketChatEventListener {
	@Autowired
	private SimpMessageSendingOperations  messageTemplate;
	
	public void handleWebSocketConnListeners(SessionConnectedEvent  event) {
		StompHeaderAccessor  accessor = StompHeaderAccessor.wrap(event.getMessage());
		String userName = (String) accessor.getSessionAttributes().get("username");
		
		if(userName != null) {
			WebSocketChatMessage chat = new WebSocketChatMessage();
			chat.setType("LEAVE");
			chat.setSender(userName);
			
			messageTemplate.convertAndSend("/topic/public",chat);
		}
		
	}

}


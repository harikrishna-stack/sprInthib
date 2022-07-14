/**
 * 
 */
package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author harik
 *Define a WebSocketChatConfigurer  
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig  implements WebSocketMessageBrokerConfigurer {
	
	public void registerStompEndPoints(StompEndpointRegistry  registry) {
		registry.addEndpoint("/webSocketApp").withSockJS();
		
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer#configureMessageBroker(org.springframework.messaging.simp.config.MessageBrokerRegistry)
	 */
	public void configureMessageBroker(MessageBrokerRegistry  registry) {
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableStompBrokerRelay("/topic").setRelayHost("localhost").
		setRelayPort(61613).setClientLogin("guest").setClientPasscode("guest");
		
	}

}

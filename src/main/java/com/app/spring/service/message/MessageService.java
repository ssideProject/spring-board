package com.app.spring.service.message;

import com.app.spring.model.dto.message.MessageVO;

public interface MessageService {
	public void addMessage(MessageVO vo);
	public MessageVO readMessage(String userid, int mid);
}

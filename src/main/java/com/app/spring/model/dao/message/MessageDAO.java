package com.app.spring.model.dao.message;

import com.app.spring.model.dto.message.MessageVO;

public interface MessageDAO {
	public void create(MessageVO vo);
	public MessageVO readMessage(int mid);
	public void updateMessage(int mid);
}

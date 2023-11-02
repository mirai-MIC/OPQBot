package com.love.event;


import com.google.gson.Gson;
import com.love.entity.event.eventmessage.EventMessage;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class Event extends ApplicationEvent {
    private final EventMessage msgBodyVO;

    public Event(Object source, String msgBodyVO) {
        super(source);
        this.msgBodyVO = new Gson().fromJson(msgBodyVO, EventMessage.class);
    }
}

package com.love.aspects;

import com.love.annotation.Listener;
import com.love.entity.event.eventmessage.EventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mi
 * @CreateTime: 2023/10/26 16:01
 * @Description:
 */

@Component
@Slf4j
public class ListenerRegistry {
    @Autowired
    private ApplicationContext context;

    public void processMessage(EventMessage message) {
        Map<Method, Listener> annotatedMethods = getAnnotatedMethods();
        String content = message.getCurrentPacket().getEventData().getMsgBody().getContent() == null ? "" : message.getCurrentPacket().getEventData().getMsgBody().getContent();
        for (Map.Entry<Method, Listener> entry : annotatedMethods.entrySet()) {
            Method method = entry.getKey();
            Listener listenerAnnotation = entry.getValue();
            String value = listenerAnnotation.value();
            if (content.contains(value) && isMatchingEventType(method, message)) {
                invokeAnnotatedMethod(method, message);
            }
        }
    }

    private Map<Method, Listener> getAnnotatedMethods() {
        Map<Method, Listener> annotatedMethods = new HashMap<>();
        String[] beanNames = context.getBeanDefinitionNames();

        for (String beanName : beanNames) {
            Class<?> beanClass = context.getType(beanName);

            if (beanClass != null) {
                Method[] methods = beanClass.getMethods();

                for (Method method : methods) {
                    if (method.isAnnotationPresent(Listener.class)) {
                        Listener listenerAnnotation = method.getAnnotation(Listener.class);
                        annotatedMethods.put(method, listenerAnnotation);
                    }
                }
            }
        }

        return annotatedMethods;
    }

    private boolean isMatchingEventType(Method method, EventMessage eventMessage) {
        Class<?>[] parameterTypes = method.getParameterTypes();

        if (parameterTypes.length == 1 && parameterTypes[0].isAssignableFrom(eventMessage.getClass())) {
            return true;
        }

        return false;
    }

    private void invokeAnnotatedMethod(Method method, EventMessage message) {
        Object bean = context.getBean(method.getDeclaringClass());
        try {
            method.invoke(bean, message);
        } catch (Exception e) {
            log.error("Error invoking annotated method: {}", e.getMessage());
        }
    }

}

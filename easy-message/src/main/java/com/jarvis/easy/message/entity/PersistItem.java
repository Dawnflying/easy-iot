package com.jarvis.easy.message.entity;

import com.jarvis.easy.common.entity.MessageData;
import lombok.Data;

@Data
public class PersistItem {

    private String topic;

    private MessageData msg;
}

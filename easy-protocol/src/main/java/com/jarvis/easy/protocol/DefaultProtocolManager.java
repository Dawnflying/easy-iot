package com.jarvis.easy.protocol;

import com.jarvis.easy.protocol.meta.ProtocolMeta;
import org.springframework.stereotype.Component;

@Component
public class DefaultProtocolManager {

    public ProtocolMeta getProtocol(String protocolId) {
        return new ProtocolMeta();
    }

}

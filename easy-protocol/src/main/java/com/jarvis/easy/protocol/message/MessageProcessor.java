package com.jarvis.easy.protocol.message;

import com.jarvis.easy.common.annotation.EasyPipeline;
import com.jarvis.easy.common.entity.MessageData;
import com.jarvis.easy.protocol.DefaultProtocolManager;
import com.jarvis.easy.protocol.engine.ScriptEngineExecutor;
import com.jarvis.easy.protocol.meta.ProtocolMeta;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
@EasyPipeline(name = "message-processor")
public class MessageProcessor {
    @Resource
    private ScriptEngineExecutor scriptEngineExecutor;

    @Resource
    private DefaultProtocolManager defaultProtocolManager;

    public Object process(MessageData data) {
        if (null == data) {
            return null;
        }

        if (StringUtils.isEmpty(data.getAttribution("_sys_protocol_id"))) {
            return data;
        }

        String protocolId = data.getAttribution("_sys_protocol_id");

        ProtocolMeta protocolMeta = defaultProtocolManager.getProtocol(protocolId);

        String sScript = protocolMeta.getScriptUrl();

        Map<String, Object> args = new HashMap<>();
        args.put("data", data);

        return scriptEngineExecutor.execute(sScript, args);
    }
}

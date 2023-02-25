package com.jarvis.easy.protocol.engine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.script.*;
import java.util.Map;

/**
 * @author lixiaofei
 */
@Slf4j(topic = "error")
@Component
public class ScriptEngineExecutor {

    private ScriptEngineManager manager = new ScriptEngineManager();

    public Object execute(String jsScript, Map<String, Object> args) {
        // 获取一个JavaScript引擎实例
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        // 读取js文件中的脚本代码

        try {
            // 创建一个Bindings对象，用于传递参数
            Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
            bindings.putAll(args);
            // 执行js脚本
            Object result = engine.eval(jsScript);
            return result;
        } catch (Exception ex) {
            return null;
        }
    }
}

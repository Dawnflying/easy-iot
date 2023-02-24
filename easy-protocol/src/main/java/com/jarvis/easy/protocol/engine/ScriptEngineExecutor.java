package com.jarvis.easy.protocol.engine;

import javax.script.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author lixiaofei
 */

public class ScriptEngineExecutor {

    private ScriptEngineManager manager = new ScriptEngineManager();


    public Object execute(String md5, String jsScript, Map<String, Object> args) {
        // 获取一个JavaScript引擎实例
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        // 读取js文件中的脚本代码

        try {
            // 创建一个Bindings对象，用于传递参数
            Bindings bindings = engine.createBindings();

            // 向Bindings对象中添加参数
            bindings.putAll(args);
            // 编译js脚本，并将编译结果存储到缓存中
            CompiledScript script = ((Compilable) engine).compile(jsScript);
            engine.put("script", script);
            // 从缓存中获取编译结果，并执行脚本
            script = (CompiledScript) engine.get("script");
            // 执行js脚本
            script.eval();
            // 从Bindings对象中获取结果
            Object result = bindings.get("returnObj");
            return result;
        } catch (Exception ex) {
            return null;
        }
    }
}

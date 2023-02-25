package com.jarvis.easy.protocol.engine;

import com.google.gson.Gson;
import com.jarvis.easy.EasyIotApplication;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import javax.script.*;
import java.util.HashMap;
import java.util.Map;

public class ScriptEngineExecutorTest extends EasyIotApplication {
    private ScriptEngineManager manager;

    @org.testng.annotations.BeforeMethod
    public void setUp() {
        manager = new ScriptEngineManager();
    }

    @org.testng.annotations.AfterMethod
    public void tearDown() {
    }

    @DataProvider(name = "executeArgs")
    public Object[][] data() {
        Map<String, Object> args = new HashMap<>();
        args.put("var1", "haha");
        args.put("a", 3);
        args.put("b", 4);
        return new Object[][]{
                {"function myFunction(var1) {\n" +
                        "  var returnObj =  { name: \"John\", age: 30};returnObj.var=var1\n" +
                        "return returnObj;\n" +
                        "}\n" +
                        "myFunction(var1);\n", args, "{\"name\":\"John\",\"age\":30,\"var\":\"haha\"}"},
                {
                        "function add(a, b) { return a + b; }; add(a, b);", args, "7"
                }
        };
    }

    @org.testng.annotations.Test(dataProvider = "executeArgs")
    public void testExecute(String jsScript, Map<String, Object> args, Object expectResult) {
        // 获取一个JavaScript引擎实例
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        // 读取js文件中的脚本代码

        try {
            // 创建一个Bindings对象，用于传递参数
            Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);

            // 向Bindings对象中添加参数
            bindings.putAll(args);
            // 执行js脚本
            Object result = engine.eval(jsScript);
            Gson gson = new Gson();
            String json = gson.toJson(result);

            Assert.assertEquals(json, expectResult);

        } catch (Exception ex) {
            Assert.assertEquals(ex, null);
        }
    }
}
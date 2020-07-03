package com.bilibili.mybatis;

import com.bilibili.mybatis.mapper.UserMapper;
import com.google.common.collect.Maps;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;

/**
 * @author hanzhuofan
 * @date 2020/5/23 15:
 */
public class Application {
    public static void main(String[] args) {
        UserMapper userMapper =
                (UserMapper)
                        Proxy.newProxyInstance(
                                Application.class.getClassLoader(),
                                new Class<?>[]{UserMapper.class},
                                new InvocationHandler() {
                                    @Override
                                    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                                        Select annotation = method.getAnnotation(Select.class);
                                        Map<String, Object> nameArgMap = buildMethodArgsNameMap(method, args);
                                        if (annotation != null) {
                                            String[] value = annotation.value();
                                            String parseSQL = parseSQL(value[0], nameArgMap);
                                            System.out.println(parseSQL);
                                        }
                                        return null;
                                    }
                                });
        userMapper.selectUserById(1, "test");
    }

    public static String parseSQL(String sql, Map<String, Object> nameArgMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sql.length(); i++) {
            char c = sql.charAt(i);
            if (c == '#') {
                int nextIndex = i + 1;
                if (sql.charAt(nextIndex) != '{') {
                    throw new RuntimeException(
                            String.format("expect #{\nsql:%s\nindex:%d", stringBuilder.toString(), nextIndex));
                }
                StringBuilder argSB = new StringBuilder();
                i = parseSQLArg(argSB, sql, nextIndex);
                String argName = argSB.toString();
                if (argName == null) {
                    throw new RuntimeException(
                            String.format("expect #{\nsql:%s\nindex:%d", stringBuilder.toString(), nextIndex));
                }
                Object argValue = nameArgMap.get(argName);
                stringBuilder.append(argValue.toString());
                continue;
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    private static int parseSQLArg(StringBuilder argSB, String sql, int nextIndex) {
        nextIndex++;
        for (; nextIndex < sql.length(); nextIndex++) {
            char c = sql.charAt(nextIndex);
            if (c == '}') {
                return nextIndex;
            }
            argSB.append(c);
        }
        throw new RuntimeException(
                String.format("expect }\nsql:%s\nindex:%d", sql, nextIndex));
    }

    public static Map<String, Object> buildMethodArgsNameMap(Method method, Object[] args) {
        Map<String, Object> nameArgMap = Maps.newHashMap();
        Parameter[] parameters = method.getParameters();
        int[] index = {0};
        Arrays.asList(parameters)
                .forEach(
                        parameter -> {
                            String name = parameter.getName();
                            nameArgMap.put(name, args[index[0]]);
                            index[0]++;
                        });
        return nameArgMap;
    }
}

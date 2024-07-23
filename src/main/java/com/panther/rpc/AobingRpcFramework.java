package com.panther.rpc;

import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * scheme
 *
 * @author panther
 * @version 1.0: AobingRpcFramework.java, 2024/7/6 10:09 $
 */
@SuppressWarnings("All")
public class AobingRpcFramework {

    /**
     *  <p> rpc 主要需要实现的功能 </p>
     *      <li>获取远程调用服务的信息</li>
     *      <li>参数序列化</li>
     *      <li>远程调用</li>
     *      <li>反序列化</li>
     *      <li>服务实现方本地处理</li>
     */

    /**
     * 暴露服务
     *
     * @param service 服务方信息
     * @param port 端口
     * @throws Exception 异常信息
     */
    public static void export(Object service, int port,Class clazz) throws Exception {
        ServerSocket server = new ServerSocket(port);

        while(true){
            Socket accept = server.accept();
            new Thread(() -> {
                try {
                    // 反序列化
                    JdkSerialize jdkSerialize = new JdkSerialize();
                    // 获取网络输入
                    byte[] bytes = new byte[1024 * 1024 * 10];
                    accept.getInputStream().read(bytes);
                    Object deserialize = jdkSerialize.deserialize(bytes, clazz.getSuperclass());
                    Object result = refer(deserialize.getClass(), "127.0.0.1", port);
                    // 返回结果
                    ObjectOutputStream output = new
                            ObjectOutputStream(accept.getOutputStream());
                    output.writeObject(result);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

    }

    public static <T> T refer (Class<T> interfaceClass, String host, int port)
            throws Exception {

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = new Socket(host, port);
                        JdkSerialize jdkSerialize = new JdkSerialize();
                        byte[] serialize = jdkSerialize.serialize(socket.getInputStream());
                        return null;
                    }
                }
        );

    }

}

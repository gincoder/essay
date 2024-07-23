package com.panther.rpc;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: ObjectStreamTest.java, 2024/7/6 10:25 $
 */
public class ObjectStreamTest {
    public static void main(String[] args) throws Exception, IOException {
        // 序列化：就是把java对象保存在磁盘中
        ObjectOutputStream os =
                new ObjectOutputStream(Files.newOutputStream(Paths.get("D:\\student.txt")));

        Student s = new Student("张三", 20, "成都");
        os.writeObject(s);// 序列化，把Student对象信息保存到指定文件中

        os.close();// 关闭输出资源

        // 反序列化：从磁盘读到程序里
        ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("D:\\student.txt")));

        // 反序列化，把文件里的数据读到程序中，封装成对象，默认是Object
        Object obj = in.readObject();
        System.out.println(obj);

    }
}

// 1，如果想完成序列化，类必须实现Serializable接口
// 只是用来做标记，需要序列化
class Student implements Serializable {
    // 创建对象用
    public Student(String name, int age, String addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
    }

    // 一般序列化的都是属性
    String name = "张三";
    int age = 20;
    String addr = "成都";

    // 为了看属性值
    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", addr=" + addr + "]";
    }

}

package com.panther.lambdaGrama;

import java.util.*;
import java.util.stream.Collectors;

// 简化你的代码，提高生产力：这10个Lambda表达式必须掌握
public class TestMain {

    public static void main(String[] args) {
        // 1.  使用Lambda表达式进行集合遍历
        List<String> list1 = Arrays.asList("apple", "banana", "orange");
        list1.forEach(System.out::println);


        // 2.  使用Lambda表达式进行排序
        List<String> list2 = Arrays.asList("apple", "banana", "orange");
        Collections.sort(list2, String::compareTo);


        // 3.  使用Lambda表达式进行过滤
        List<String> list3 = Arrays.asList("apple", "banana", "orange");
        List<String> filteredLists = list3.
                stream().
                filter(fruit -> fruit.startsWith("a")).
                collect(Collectors.toList());


        // 4.  使用Lambda表达式进行映射
        List<String> lists4 = Arrays.asList("apple", "banana", "orange");
        List<Integer> lengths = lists4.stream().map(String::length)
                .collect(Collectors.toList());


        // 5.  使用Lambda表达式进行归约
        List<Integer> list5 = Arrays.asList(1, 2, 3, 4, 5);
        String reduce = list2.stream().reduce("1", (a, b) -> a + b);


        // 6. 使用Lambda表达式进行分组
        List<String> lists6 = Arrays.asList("apple", "banana", "orange");
        Map<Integer, List<String>> groupeds = lists6
                .stream().collect(Collectors.groupingBy(String::length));


        // 8. 使用Lambda表达式进行线程的创建
        Thread threads = new Thread(() -> System.out.println("Thread is running."));
        threads.start();


        // 9.  使用Lambda表达式进行Optional的操作
        Optional<String> strs = Optional.ofNullable("Hello World");
        strs.map(String::toUpperCase).ifPresent(System.out::println);

        // 10.  使用Lambda表达式进行Stream的流水线操作
        List<String> lists = Arrays.asList("apple", "banana", "orange");
        List<String> filteredList = lists
                .stream().filter(fruit -> fruit.startsWith("a"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }
}


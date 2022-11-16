package com.example.serious.demo.util;

import com.example.serious.demo.domain.User;
import com.example.serious.demo.util.Functional.ConsumerService;
import com.example.serious.demo.util.Functional.PredicateService;
import com.example.serious.demo.util.Functional.RunnableService;
import com.example.serious.demo.util.Functional.SupplierService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class VUtil {

    public static ConsumerService getType(String type){
        return (consumeParam)->{
            if ("a".equals(type)) {
                consumeParam.accept(new User());
            }
        };
    }

    public static RunnableService judgeType(boolean b){
        return (trueHandle, falseHandle) -> {
            if (b){
                trueHandle.run();
            }else{
                falseHandle.run();
            }
        };
    }

    public static SupplierService send(List<String> list){
        return supplier -> {
            list.add(supplier.get());
        };
    }

    public static List<String> judgeAssert(List<String> list, Predicate<String> predicate){

        return list.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
    }
    public static void main(String[] args) {

//        VUtil.getType("true").throwMessage((user)->{
//            user.setName("joyee").setId(1).setPassword("123456").setPassword("123456789");
//        });
//
//        VUtil.judgeType(true).trueOrFalseHandle(
//                ()->{
//                    System.out.println("是true啊，自定义一个处理方法吧");
//                },()->{
//                    System.out.println("是false啊，自定义一个处理方法吧");
//        });

        List<String> list = new ArrayList<>();
        VUtil.send(list).noParamReturnHandle(()->{
            return "null";
        });
        System.out.println(list.get(0));

        List<String> strings = VUtil.judgeAssert(list,String::isEmpty);
    }
}

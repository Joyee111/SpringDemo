package com.example.serious.demo.dao;

import com.example.serious.demo.domain.UserEs;
import com.example.serious.demo.service.UserRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Resource
public class EsDao {

    @Autowired
    private UserRepository userRepository;

    public void testSave(){
        UserEs user = new UserEs();
        // id初识为空，此操作为新增
        user.setId(UUID.randomUUID().toString());
        user.setName("唐三藏");
        user.setBir(new Date());
        user.setIntroduce("西方世界如来佛祖大弟子金蝉子转世，十世修行的好人，得道高僧！");
        user.setAddress("大唐白马寺");
        userRepository.save(user);
    }

    public void testUpdate(){
        UserEs user = new UserEs();
        // 根据id修改信息
        user.setId("1666eb47-0bbf-468b-ab45-07758c741461");
        user.setName("唐三藏");
        user.setBir(new Date());
        user.setIntroduce("俗家姓陈,状元之后。西方世界如来佛祖大弟子金蝉子转世，十世修行的好人，得道高僧！");
        user.setAddress("大唐白马寺");
        userRepository.save(user);
    }

    public void deleteDoc(){
        userRepository.deleteById("1666eb47-0bbf-468b-ab45-07758c741461");
    }

    public void testFindOne(){
        Optional<UserEs> optional = userRepository.findById("1666eb47-0bbf-468b-ab45-07758c741461");
        System.out.println(optional.get());
    }

    public void testFindAll(){
        Iterable<UserEs> all = userRepository.findAll();
        all.forEach(user-> System.out.println(user));
    }

    public void testFindAllSort(){
        Iterable<UserEs> all = userRepository.findAll(Sort.by(Sort.Order.asc("age")));
        all.forEach(user-> System.out.println(user));
    }

    public void testFindPage(){
        //PageRequest.of 参数1: 当前页-1
        Page<UserEs> search = userRepository.search(QueryBuilders.matchAllQuery(), PageRequest.of(1, 1));
        search.forEach(user-> System.out.println(user));
    }

    public void testFindByName(){
        List<UserEs> userList = userRepository.findByName("唐三藏");
        userList.forEach(user-> System.out.println(user));
    }

    public void testFindByAddress(){
        List<UserEs> userList = userRepository.findByAddress("唐三藏");
        userList.forEach(user-> System.out.println(user));
    }
}

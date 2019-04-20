package com.example.lucene.controller;

import com.example.lucene.domain.User;
import com.example.lucene.service.UserService;
import com.example.lucene.ut.MySimHash;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;


import java.util.Optional;

/**
 * Greated by Terry on 2019/4/16
 * Time: 10:52
 */
@RestController
@RequestMapping("/user")
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    // Brook 4/20
    @PutMapping()
    @ApiOperation("用户登录服务")
    public  Object login(String username,String password){
        User user=userService.findByUsernameAndPassword(username,password);
        return user;
    }

    @PutMapping()
    @ApiOperation("新建用户服务")
    public User creat( User user){
        user=userService.registerUser(user);
        return user;
    }
    @GetMapping("/list")
    @ApiOperation("获取用户列表服务")
    public List<User> query(){
        List<User> list=userService.findByUser();
        return  list;
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "获取某用户信息服务")
    public Optional<User> getinfo(@PathVariable Integer id){
        Optional<User> user=userService.getUserById(id);
        return user;
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "用户删除服务")
    public void  delete(@PathVariable Integer id){
        userService.removeUser(id);
    }


    @PostMapping("/filess")
    @ApiOperation("文本对比")
    public Object file(HashMap map){
        map =new HashMap();
        String str1= (String) map.get(0);
        String str2=(String) map.get(1);
        MySimHash hash1 = new MySimHash(str1, 64);
        MySimHash hash2 = new MySimHash(str2, 64);
        System.out.println(  hash1.getSemblance(hash2) );
        return hash1.getSemblance(hash2);
    }

}

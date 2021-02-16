package com.kubernetes.springapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        if (ip != null)
            return "Hello From RestController, V3, from "+ ip.getHostName();
        else
            return "Hello From RestController, V3, could not fetch ip";
    }

    @GetMapping("/health")
    public ResponseEntity<Object> send500(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

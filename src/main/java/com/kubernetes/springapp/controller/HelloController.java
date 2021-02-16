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
        String msg = System.getenv("MESSAGE");
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        if (ip != null) {
            String response = "Hello From RestController, V4, from " + ip.getHostName();
            if(msg != null)
                response = msg+" From RestController, V4, from " + ip.getHostName();
            return response;
        }
        else {
            return "Hello From RestController, V3, could not fetch the ip";
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Object> send500(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

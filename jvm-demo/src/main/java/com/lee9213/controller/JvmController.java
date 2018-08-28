package com.lee9213.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-27 23:46
 */
@RestController
@RequestMapping("/jvm")
public class JvmController {


    @RequestMapping("/info")
    public void info(){
        for(int i=0;i<10;i++){
            new Thread(()->{
                while (true){

                }
            },"JvmThread" + i).start();
        }

        while (true){

        }
    }
}

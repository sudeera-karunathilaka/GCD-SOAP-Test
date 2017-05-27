package com.test.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.test.soap.config", "com.test.soap.ws"})
public class GCDSOAPTestApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(GCDSOAPTestApplication.class, args);
    }
}

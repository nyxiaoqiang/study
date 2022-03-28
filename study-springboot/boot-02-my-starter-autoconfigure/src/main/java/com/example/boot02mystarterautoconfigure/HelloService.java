package com.example.boot02mystarterautoconfigure;

public class HelloService {
    HelloProperties helloProperties;

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String sayHello(){
        return "hello:"+helloProperties.getName();
    }
}

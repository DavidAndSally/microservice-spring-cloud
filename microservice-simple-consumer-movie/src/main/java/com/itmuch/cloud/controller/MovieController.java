package com.itmuch.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itmuch.cloud.entity.User;

@RestController
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
//    @Autowired
//    private MovieService movieService;

    @Value("${user.userServicePath}")
    private String userServicePath;

    @GetMapping("/movie/{id}")
    private User findById(@PathVariable Long id) {
        return this.restTemplate.getForObject(this.userServicePath + id, User.class);
    }

    @Autowired
    private LoadBalancerClient LoadBalancerClient;

    @GetMapping("/test")
    public String test() {
        ServiceInstance serviceInstance = this.LoadBalancerClient.choose("microservice-simple-provider-user");
        System.out.println("111" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + ":" + serviceInstance.getServiceId());

//        ServiceInstance serviceInstance2 = this.LoadBalancerClient.choose("microservice-simple-consumer-movie");
//        System.out.println("222" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort() + ":" + serviceInstance2.getServiceId());

        return "1";
    }


//    @GetMapping("/movie/{id}/test")
//    public User findByIdTest(@PathVariable Long id) {
//        return movieService.findById(id);
//    }
}

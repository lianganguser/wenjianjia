package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger=new AtomicInteger(0);

    public final int getAndIncrement(){

        int current;  //当前的次数
        int next;     //访问次数
        do{
            current = this.atomicInteger.get();
            next=current>=2147483647 ? 0: current+1;

        }while(!this.atomicInteger.compareAndSet(current,next));
        System.out.println("******第几次访问，次数next:"+next);
        return next;   //返回当前的次数
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index=getAndIncrement()%serviceInstances.size();  //渠道当前的下标


        return serviceInstances.get(index);  //选出来是哪台机器干活
    }
}

package xyz.xuaq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author x
 */
@Service
public class ProductService {

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    public String update() {
        String lock = "lock";
        String vlaue = UUID.randomUUID().toString();

        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(lock, vlaue, 30, TimeUnit.SECONDS);

        try {
            if (!aBoolean){
                return "error";
            }
            int num = Integer.parseInt( redisTemplate.opsForValue().get("num").toString());
            if (num>0){
                num=num-1;
                redisTemplate.opsForValue().set("num",num);
                System.out.println("剩余库存："+num);
            }else {
                System.out.println("库存不足！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (vlaue.equals(redisTemplate.opsForValue().get("lock"))){
                redisTemplate.delete("lock");
            }
        }
        return "end";
    }

}

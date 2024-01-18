package 限流算法_5;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

//限流--漏桶
@Slf4j
public class LeakyBucketRateLimiter {
    //桶的容量
    int capacity;
    //桶中现存水量
    AtomicInteger water = new AtomicInteger();
    //开始漏水时间
    long leakTimestamp;
    //水流出的速率，即每秒允许通过的请求数
    int leakRate;

    public LeakyBucketRateLimiter(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
    }

    public synchronized boolean tryAcquire() {
        //桶中没有水， 重新开始计算
        if (water.get() == 0) {
            log.info("start leaking");
            leakTimestamp = System.currentTimeMillis();
            water.incrementAndGet();
            return water.get() < capacity;
        }
        //先漏水，计算剩余水量
        long currentTime = System.currentTimeMillis();
        //当前时间与上次时间的间隔，可以接受的请求数量
        int leakedWater = (int) ((currentTime - leakTimestamp) / 1000 * leakRate);
        log.info("lastTime:{}, currentTime:{}. LeakedWater:{}", leakTimestamp, currentTime, leakedWater);
        //与上次请求间隔1秒以上，才会走这段逻辑
        if (leakedWater != 0) {
            int leftWater = water.get() - leakedWater;
            water.set(Math.max(0, leftWater));
            leakTimestamp = System.currentTimeMillis();
        }
        log.info("剩余容量:{}", capacity - water.get());
        if (water.get() < capacity) {
            log.info("tryAcquire success");
            water.incrementAndGet();
            return true;
        } else {
            log.info("tryAcquire fail");
            return false;
        }
    }
}

package 限流算法_5;

import lombok.extern.slf4j.Slf4j;

/**
 * https://mp.weixin.qq.com/s/cHnIF3EUyM4IRNEpWiQZjQ
 * 限流--滑动窗口
 */
@Slf4j
public class SlidingWindowRateLimiter {
    //时间窗口大小，单位毫秒
    long windowSize;
    //分片窗口数
    int shardNum;
    //允许通过的请求数
    int maxRequestCount;
    //各个窗口内请求计数
    int[] shardRequestCount;
    //请求总数
    int totalCount;
    //当前窗口下标
    int shardId;
    //每个小窗口大小，毫秒
    long tinyWindowSize;
    //窗口右边界
    long windowBorder;

    public SlidingWindowRateLimiter(long windowSize, int shardNum, int maxRequestCount) {
        this.windowSize = windowSize;
        this.shardNum = shardNum;
        this.maxRequestCount = maxRequestCount;
        this.shardRequestCount = new int[shardNum];
        this.tinyWindowSize = windowSize / shardNum;
        this.windowBorder = System.currentTimeMillis();
    }

    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();
        //计算窗口右边界到当前时间每个分片窗口的请求次数(除了windowBorder分片窗口外，其余的时间窗口请求次数都是0)
        if (windowBorder < currentTime) {
            log.info("window reset");
            do {
                //通过取余运算 % 将其限制在 0 到 shardNum-1 的范围内
                shardId = (++shardId) % shardNum;
                totalCount -= shardRequestCount[shardId];
                shardRequestCount[shardId] = 0;
                windowBorder += tinyWindowSize;
            } while (windowBorder < currentTime);
        }

        if (totalCount < maxRequestCount) {
            log.info("tryAcquire success:{}", shardId);
            //当前时间窗口请求数量加1
            shardRequestCount[shardId]++;
            totalCount++;
            return true;
        } else {
            log.info("tryAcquire fail");
            return false;
        }
    }
}

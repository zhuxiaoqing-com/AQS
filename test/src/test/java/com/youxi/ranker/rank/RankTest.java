package com.youxi.ranker.rank;

import com.youxi.ranker.rank.entity.RankObjRedis;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RankTest {
   static RedisRank<Object> redisRank = new RedisRank<Object>(AbstractRank.RankerOrder.ASC, "aa") {

        @Override
        protected boolean checkAddRank(RankObjRedis<Object> rankObj) {
            return true;
        }

        @Override
        public int getRankNumLimit() {
            return 10000;
        }

        @Override
        public Object getData(long id) {
            return null;
        }

        @Override
        public Map getMultiDataMap(String... idStrArray) {
            return Collections.emptyMap();
        }
    };

    @Test
    public void addData() {
        int index = 1000;
        for (int i = 0; i < index; i++) {
            RankObjRedis<Object> objectRankObjRedis = new RankObjRedis<>(i, i, null);
            redisRank.addRank(objectRankObjRedis);
        }
    }

    @Test
    public void getData() {
        /**
         * svn checkout https://144.48.222.251/svn/策划文档/A ./A  1
         */
        List<RankObjRedis<Object>> rankInfosByRanking = redisRank.findRankInfosByRanking(1000, -1);
        System.out.println(rankInfosByRanking.size());
    }
}

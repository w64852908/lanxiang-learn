package com.lanxiang.redis.redisInAction.articleVote;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lanxiang.redis.resource.guice.JedisModule;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ZParams;

import java.util.*;

/**
 * Created by lanxiang on 2017/1/17.
 */
public class ArticleVote {

    private Jedis jedis;

    private final static int ONE_WEEK_IN_SECONDS = 7 * 86400;

    private final static int VOTE_SCORE = 432;

    private final static int ARTICLE_PER_PAGE = 25;

    @Before
    public void init() {
        Injector injector = Guice.createInjector(new JedisModule());
        jedis = injector.getProvider(JedisPool.class).get().getResource();
    }

    //发布文章
    private String postArticle(Jedis jedis, String user, String title, String link) {
        //生成一个新的文章id
        String primaryArticleId = String.valueOf(jedis.incr("article:"));
        String voted = "voted:" + primaryArticleId;
        //将发布文章的用户添加到已投票用户名单里,然后将这个名单的过期时间设置为一周
        jedis.sadd(voted, user);
        jedis.expire(voted, ONE_WEEK_IN_SECONDS);

        long now = new Date().getTime();
        String articleId = "article:" + primaryArticleId;
        Map<String, String> articleMap = new HashMap<>();
        articleMap.put("title", title);
        articleMap.put("link", link);
        articleMap.put("poster", user);
        articleMap.put("time", now + "");
        articleMap.put("votes", 1 + "");
        //将文章信息存储到一个hash表里
        jedis.hmset(articleId, articleMap);
        //文章的得分信息记录到一个sort集合里
        jedis.zadd("score:", now + VOTE_SCORE, articleId);
        //文章的发布时间记录到一个sort集合里
        jedis.zadd("time:", now, articleId);
        return articleId;
    }

    //为文章投票
    private void voteArticle(Jedis jedis, String user, String article) {
        //计算文章的投票截止时间
        long cutoff = new Date().getTime() - ONE_WEEK_IN_SECONDS;
        //检查是否还可以对文章进行投票
        if (jedis.zscore("time:", article) < cutoff) {
            return;
        }
        //从article:id标识符里面取出文章的id
        String[] articleIdArray = article.split(":");
        String articleId = articleIdArray[articleIdArray.length - 1];
        if (jedis.sadd("voted:" + articleId, user) != 0) {
            jedis.zincrby("score:", VOTE_SCORE, article);
            jedis.hincrBy(article, "votes", 1);
        }
    }

    //取出所有文章
    private List<Map<String, String>> getArticles(Jedis jedis, int page, String order) {
        //默认的排序方式(从对应的zsort里取出articleId)
        if (order == null || order.equals("")) {
            order = "score:";
        }
        int start = (page - 1) * ARTICLE_PER_PAGE;
        int end = start + ARTICLE_PER_PAGE - 1;

        Set<String> ids = jedis.zrevrange(order, start, end);
        //从散列中取出所有的文章信息并存储到list中
        List<Map<String, String>> articles = new ArrayList<>();
        for (String id : ids) {
            Map<String, String> articleMap = jedis.hgetAll(id);
            articleMap.put(id, id);
            articles.add(articleMap);
        }
        return articles;
    }

    //对文章进行分组
    private void addRemoveGroups(Jedis jedis, String articleId, List<String> toAdd, List<String> toRemove) {
        String article = "article:" + articleId;
        if (toAdd != null) {
            for (String group : toAdd) {
                jedis.sadd("group:" + group, article);
            }
        }
        if (toRemove != null) {
            for (String group : toRemove) {
                jedis.srem("group:" + group, article);
            }
        }
    }

    //获取分组的文章
    private List<Map<String, String>> getGroupArticles(Jedis jedis, String group, int page, String order) {
        if (order == null || order.equals("")) {
            order = "score:";
        }
        String key = order + group;
        if (!jedis.exists(key)) {
            //计算给定的一个或多个有序集的交集,并将该交集存储到目的
            jedis.zinterstore(key, new ZParams().aggregate(ZParams.Aggregate.MAX), "group:" + group, order);
            jedis.expire(key, 60);
        }
        return getArticles(jedis, page, key);
    }

    @Test
    public void run() {
//        String articleId = postArticle(jedis, "lanxiang:10010", "演员的实践", "www.bilibili.com/66666");
//        voteArticle(jedis, "lanxiang:10010", articleId);
//        System.out.println(getArticles(jedis, 1, "time:"));
//        addRemoveGroups(jedis, "1", Arrays.asList("actor"), null);
//        addRemoveGroups(jedis, "2", Arrays.asList("actor"), null);
        System.out.println(getGroupArticles(jedis, "actor", 1, null));
    }
}

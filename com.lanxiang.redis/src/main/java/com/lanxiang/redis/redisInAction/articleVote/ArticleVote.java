package com.lanxiang.redis.redisInAction.articleVote;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lanxiang.redis.resource.guice.JedisModule;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

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

    @Test
    public void run() {
//        String article = postArticle(jedis, "lanxiang:10086", "演员的自我修养", "www.bilibili.com/92444");
//        voteArticle(jedis, "lanxiang:10086", article);
        System.out.println(getArticles(jedis, 1, null));
    }

    //发布文章
    private String postArticle(Jedis jedis, String user, String title, String link) {
        //生成一个新的文章id
        String articleId = String.valueOf(jedis.incr("article:"));
        String voted = "voted:" + articleId;
        //将发布文章的用户添加到已投票用户名单里,然后将这个名单的过期时间设置为一周
        jedis.sadd(voted, user);
        jedis.expire(voted, ONE_WEEK_IN_SECONDS);

        long now = new Date().getTime();
        String article = "article:" + articleId;
        Map<String, String> articleMap = new HashMap<>();
        articleMap.put("title", title);
        articleMap.put("link", link);
        articleMap.put("poster", user);
        articleMap.put("time", now + "");
        articleMap.put("votes", 1 + "");
        //将文章信息存储到一个hash表里
        jedis.hmset(article, articleMap);
        //文章的得分信息记录到一个sort集合里
        jedis.zadd("score:", now + VOTE_SCORE, article);
        //文章的发布时间记录到一个sort集合里
        jedis.zadd("time:", now, article);
        return article;
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

    private List<Map<String, String>> getArticles(Jedis jedis, int page, String order) {
        //默认的排序方式
        if (order == null || order.equals("")) {
            order = "score:";
        }
        int start = (page - 1) * ARTICLE_PER_PAGE;
        int end = start + ARTICLE_PER_PAGE - 1;

        Set<String> ids = jedis.zrevrange(order, start, end);
        List<Map<String, String>> articles = new ArrayList<>();
        for (String id : ids) {
            Map<String, String> articleMap = jedis.hgetAll(id);
            articleMap.put(id, id);
            articles.add(articleMap);
        }
        return articles;
    }
}

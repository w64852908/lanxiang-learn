package com.lanxiang.redis.redisInAction.articleVote;

import lombok.Data;

/**
 * Created by lanxiang on 2017/1/20.
 */

@Data
public class Article {

    private String title;

    private String link;

    private String poster;

    private Long time;

    private Integer votes;

    public Article(String title, String link, String poster, Long time, Integer votes) {
        this.title = title;
        this.link = link;
        this.poster = poster;
        this.time = time;
        this.votes = votes;
    }

}

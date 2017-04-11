package com.lanxiang.exercise.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by lanjing on 2017/4/8.
 */
public class CollectionsSort {

    @Test
    public void run() {
        Score s1 = new Score(1);
        Score s2 = new Score(2);
        Score s3 = new Score(3);
        Score s4 = new Score(4);
        Score s5 = new Score(5);
        List<Score> list = Arrays.asList(s3, s2, s1, s5, s4);
        for (Score score : list) {
            System.out.print(score.getScore() + " ");
        }
        System.out.println();
        Collections.sort(list, new ScoreComparator());
        for (Score score : list) {
            System.out.print(score.getScore() + " ");
        }
    }


    private class Score {
        private Integer score;

        public Score(int score) {
            this.score = score;
        }

        public Integer getScore() {
            return this.score;
        }
    }

    private class ScoreComparator implements Comparator<Score> {

        @Override
        public int compare(Score o1, Score o2) {
            return -1 * o1.score.compareTo(o2.score);
        }
    }
}

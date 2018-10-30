package code;

/**
 * Created by lanjing on 2018/10/28.
 */

/**
 * You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you
 * take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first
 * turn to remove the stones.
 * <p>
 * Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can
 * win the game given the number of stones in the heap.
 * <p>
 * Example:
 * <p>
 * Input: 4
 * Output: false
 * Explanation: If there are 4 stones in the heap, then you will never win the game;
 * No matter 1, 2, or 3 stones you remove, the last stone will always be
 * removed by your friend.
 * <p>
 * 要求：桌上很多石头，你和朋友比赛，每个人一次只能拿1-3个石头，最后拿完石头的人赢。游戏开始你先拿。给手头总数，算你是否能赢
 * <p>
 * 思路：找规律即可
 */
public class _292NimGame {

    public boolean canWinNim(int n) {
        return n % 4 == 0;
    }

}

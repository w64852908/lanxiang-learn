package code;

/**
 * Created by lanjing on 2018/11/21.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * <p>
 * Example 1:
 * <p>
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * <p>
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should
 * also have finished course 1. So it is impossible.
 * Note:
 * <p>
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * <p>
 * 要求：给一个需要上的课程的数目，数组是每一组课程必须的顺序。如果要求课程学习顺序存在死循环的话，返回false（要上0必须先上1，要上1又必须先上0）
 */
public class _207CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0 || null == prerequisites) {
            return false;
        }
        if (prerequisites.length == 0) {
            return true;
        }
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            inDegree[prerequisite[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int num = queue.poll();
            res.add(num);
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] == num) {
                    inDegree[prerequisite[0]]--;
                    if (inDegree[prerequisite[0]] == 0) {
                        queue.offer(prerequisite[0]);
                    }
                }
            }
        }
        return res.size() == numCourses;
    }

    @Test
    public void run() {
        int numCourse = 3;
        int[][] arr = new int[3][];

        int[] a1 = {0, 1};
        int[] a2 = {4, 2};
        int[] a3 = {2, 3};

        arr[0] = a1;
        arr[1] = a2;
        arr[2] = a3;

        System.out.println(canFinish(numCourse, arr));
    }
}

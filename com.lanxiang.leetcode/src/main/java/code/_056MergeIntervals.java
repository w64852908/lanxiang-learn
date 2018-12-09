package code;

import org.junit.Test;

import java.util.*;

/**
 * Created by lanjing on 2018/12/9.
 */


/**
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * <p>
 * 要求：给一个二维数组，如果两个数组发生overlaps，则把第一个数组的start，和两个数组中更大的end合并成一个数组
 * <p>
 * 思路：使用Collections的sort功能，自定义comparator，按数组的start大小升序排列。
 * 然后对数组进行合并操作.
 * <p>
 * 注意merge时的写法
 */
public class _056MergeIntervals {

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (null == intervals || intervals.size() == 0) {
            return res;
        }
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        //前一个数组
        Interval pre = intervals.get(0);
        //如果总共只有一个数组，直接返回
        if (intervals.size() == 1) {
            res.add(pre);
            return res;
        }
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            //发生overlap
            if (pre.end >= curr.start) {
                //修改上一个数组的end，但是不进行其他操作，直接进入循环下一步，让pre不变，curr跳过被合并的数组
                pre.end = Math.max(pre.end, curr.end);
            } else {
                //直接把上一个数组加到结果
                res.add(pre);
                //指向本数组
                pre = curr;
            }
        }
        //最后遗漏的pre
        res.add(pre);
        return res;
    }

    private class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    @Test
    public void run() {
        List<Interval> list = new ArrayList<>();
        Interval i1 = new Interval(1, 3);
        Interval i2 = new Interval(2, 6);
        Interval i3 = new Interval(8, 10);
        Interval i4 = new Interval(15, 18);
        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);
        System.out.println(merge(list));
    }
}

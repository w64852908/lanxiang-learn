package iv.hash;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.junit.Test;

import java.util.BitSet;

/**
 * Created by lanjing on 2018/12/16.
 */
public class BloomFilter {

    //二进制向量的位数，相当于能存储1000万条url左右，误报率为千万分之一
    private static final int BIT_SIZE = 1 << 29;

    //用于生成不同的hash函数的seed
    private static final int[] seeds = {3, 5, 7, 11, 13, 31, 37, 61};

    private BitSet bitSet = new BitSet(BIT_SIZE);

    //8个seed不同的hash function
    private HashFunction[] hashFunc = new HashFunction[seeds.length];

    private HashFunction buildHashFunc(int seed) {
        return Hashing.murmur3_32(seed);
    }

    public BloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            hashFunc[i] = buildHashFunc(seeds[i]);
        }
    }

    public void addValue(String value) {
        //将字符串value哈希为8个或多个整数，然后在这些整数的bit上变为1
        if (null != value) {
            for (HashFunction function : hashFunc) {
                bitSet.set(hash(function, value), true);
            }
        }
    }

    public boolean contains(String value) {
        if (null == value) {
            return false;
        }
        boolean res = true;
        //将要比较的字符串重新以上述方法计算hash值，再与布隆过滤器比对
        for (HashFunction function : hashFunc) {
            res = res && bitSet.get(hash(function, value));
        }
        return res;
    }

    private int hash(HashFunction function, String value) {
        return Math.abs(function.hashBytes(value.getBytes()).asInt());
    }

    public static void main(String[] args) {
        BloomFilter b = new BloomFilter();
        b.addValue("www.baidu.com");
        b.addValue("www.sohu.com");

        System.out.println(b.contains("www.baidu.com"));
        System.out.println(b.contains("www.sina.com"));
    }
}

package iv.hash;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.*;

/**
 * Created by lanjing on 2018/12/16.
 * <p>
 * 带虚拟节点的一致性hash算法
 */
public class HashCycleWithVirtualNode {

    private static final String VIRTUAL_NODE_SPLIT_CHAR = "&&";

    //需要加入到环的节点
    private List<String> realNodes;

    //形成环的hash,key为hashcode，value为服务的virtual node
    private SortedMap<Integer, String> virtualNodes;

    //每一个节点对应的虚拟节点数量
    private int virtualNodesNum;

    //使用murmurhash算法让hash更平衡分散
    private HashFunction murmurHashFuc;

    public HashCycleWithVirtualNode(List<String> realNodes, int virtualNodesNum) {
        if (null == realNodes || realNodes.size() == 0 || virtualNodesNum <= 0) {
            throw new RuntimeException();
        }
        this.realNodes = realNodes;
        this.virtualNodesNum = virtualNodesNum;
        virtualNodes = new TreeMap<>();
        murmurHashFuc = Hashing.murmur3_128();
        initVirtualNodes();
    }

    //把想要加入到环上的节点，初始化virtualNodesNum个对应的节点，对节点name做hash，然后加入到环上
    private void initVirtualNodes() {
        for (String realNode : realNodes) {
            for (int i = 0; i < virtualNodesNum; i++) {
                String virtualNodeName = realNode + VIRTUAL_NODE_SPLIT_CHAR + "VN" + i;
                int hash = murmurHash(virtualNodeName);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
    }

    private int murmurHash(String filterId) {
        return Math.abs(murmurHashFuc.hashBytes(filterId.getBytes()).asInt());
    }

    //根据对应的key，求hash，在环上找到最近的一个节点，就是命中的节点
    public String getNode(String filterId) {
        int hash = murmurHash(filterId);
        //返回map中大于或等于key的部分
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        //如果没有subMap的话，直接使用全部map
        if (subMap.size() == 0) {
            subMap = virtualNodes;
        }
        //submap中第一个key，就是环上最近的一个节点
        Integer i = subMap.firstKey();
        String virtualNodeName = subMap.get(i);
        return virtualNodeName.substring(0, virtualNodeName.indexOf(VIRTUAL_NODE_SPLIT_CHAR));
    }

    public static void main(String[] args) {
        List<String> realNodes = Arrays.asList("127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333", "172.168.1.174:4444");
        HashCycleWithVirtualNode hashCycle = new HashCycleWithVirtualNode(realNodes, 10000);
        Random random = new Random();
        Map<String, Integer> hitCount = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int id = random.nextInt();
            String hitNode = hashCycle.getNode(id + "");
            if (hitCount.containsKey(hitNode)) {
                hitCount.put(hitNode, hitCount.get(hitNode) + 1);
            } else {
                hitCount.put(hitNode, 1);
            }
        }
        System.out.println(hitCount);
    }
}

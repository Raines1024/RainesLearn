package com.raines.raineslearn.grokkingAlgorithms.greedy_algorithms_08;

import java.util.*;

/**
 * 贪婪算法（近似算法）
 * 贪婪算法寻找局部最优解，企图以这种方式获得全局最优解。
 * 对于NP完全问题，还没有找到快速解决方案。
 * 面临NP完全问题时，最佳的做法是使用近似算法。
 *
 * 运行时间：    O(n2):n的2次幂
 */
public class SetCovering {

    public static void main(String[] args) {
        //不可重的集合：需要覆盖的州
        Set<String> statesNeeded = new HashSet(Arrays.asList("mt", "wa", "or", "id", "nv", "ut", "ca", "az"));
        //可供选择的广播台清单。键为广播台名称，值为广播台覆盖的州
        Map<String, Set<String>> stations = new LinkedHashMap<>();
        stations.put("kone", new HashSet<>(Arrays.asList("id", "nv", "ut")));
        stations.put("ktwo", new HashSet<>(Arrays.asList("wa", "id", "mt")));
        stations.put("kthree", new HashSet<>(Arrays.asList("or", "nv", "ca")));
        stations.put("kfour", new HashSet<>(Arrays.asList("nv", "ut")));
        stations.put("kfive", new HashSet<>(Arrays.asList("ca", "az")));
        //最终选择的广播台
        Set<String> finalStations = new HashSet<String>();
        while (!statesNeeded.isEmpty()) {
            //覆盖最多州的广播台
            String bestStation = null;
            //该广播台覆盖的所有未覆盖（未被其他州覆盖）的州
            Set<String> statesCovered = new HashSet<>();
            //需要遍历所有的广播台，从中选择覆盖了最多的未覆盖州的广播台。
            for (Map.Entry<String, Set<String>> station : stations.entrySet()) {
                Set<String> covered = new HashSet<>(statesNeeded);
                //求交集（找出两个集合中都有的元素）
                covered.retainAll(station.getValue());
                if (covered.size() > statesCovered.size()) {
                    bestStation = station.getKey();
                    statesCovered = covered;
                }
            }
            //删除已经覆盖的州
            statesNeeded.removeIf(statesCovered::contains);

            if (bestStation != null) {
                finalStations.add(bestStation);
            }
        }
        System.out.println(finalStations); // [ktwo, kone, kthree, kfive]
    }
}


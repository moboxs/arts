package javatips;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

//按比例随机计算方式
public class WeightRandom {

    private TreeMap<Double, Integer> weightMap = new TreeMap<Double, Integer>();
    //权重累加
    public WeightRandom() {
        Map<Integer, Double> pair = new HashMap<>();
        pair.put(1, 0.2);
        pair.put(2, 0.8);
        for (Integer p : pair.keySet()) {
            double lastWeight = this.weightMap.size() == 0 ? 0 : this.weightMap.lastKey();
            this.weightMap.put(pair.get(p) + lastWeight, p);
        }
    }

    //按比例随机
    public Integer random() {
        double randomWeight = this.weightMap.lastKey() * Math.random();
        SortedMap<Double, Integer> tailMap = this.weightMap.tailMap(randomWeight, false);
        return this.weightMap.get(tailMap.firstKey());
    }

    public static void main(String[] args) {
        int n = 0;
        int i = 0;
        int j = 0;
        while (n++ <= 10000) {
            WeightRandom weightRandom = new WeightRandom();
            int k = weightRandom.random();
            if (k==1) {
                i++;
            }
            if (k==2) {
                j++;
            }
            //System.out.println(k);
        }
        System.out.println("【1】随机"+i+"次");
        System.out.println("【2】随机"+j+"次");
    }
}

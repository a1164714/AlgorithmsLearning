package com.delightintl.demo.uf.pratice;

import com.delightintl.demo.uf.theory.WeightedQU;

import java.io.*;

public class NetworkConnected {

    /**
     * logFile like:
     * [2018-06-06T20:10:25] 1 3
     * [2018-06-06T21:10:25] 2 4
     * [2018-06-06T22:10:25] 3 6
     * [2018-06-07T20:10:25] 7 9
     * [2018-06-08T20:10:25] 8 7
     * [2018-06-09T20:10:25] 5 2
     * [2018-06-10T20:10:25] 1 5
     * [2018-06-11T20:10:25] 4 3
     * [2018-06-12T20:10:25] 0 9
     * [2018-06-13T20:10:25] 7 2
     * [2018-06-14T20:10:25] 5 8
     * [2018-06-15T20:10:25] 8 5
     * [2018-06-16T20:10:25] 5 9
     */

    public static void main(String[] args) throws IOException {
        WeightedQU weightedQU = new WeightedQU(10);
        FileInputStream in = new FileInputStream("E:/develop/project/AlgorithmsLearning/Algorithms/src/com/delightintl/demo/uf/pratice/log.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(" ");
            weightedQU.union(Integer.valueOf(split[1]), Integer.valueOf(split[2]));
            if (weightedQU.count() == 1){
                System.out.println(split[0]);
                return;
            }
        }
        System.out.println("Not all members form friendships.");
    }
}

package org.tylor.tutorial.algorithm.dynamic_plan;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Tylor 2020/12/12
 *
 * 案例2：
 *
 * 给定一个矩阵m，从左上角开始每次只能向右走或者向下走，最后达到右下角的位置，路径中所有数字累加起来就是路径和，返回所有路径的最小路径和，如果给定的m如下，那么路径1,3,1,0,6,1,0就是最小路径和，返回12.
 *
 * 1 3 5 9
 *
 * 8 1 3 4
 *
 * 5 0 6 1
 *
 * 8 8 4 0
 */
@Slf4j
public class MinRoute {



    public static int minSteps(int[][] arr,int x,int y){

        log.info("行{}，列{}，值{}",x,y,arr[x-1][y-1]);
        if (x ==1 ){
            return sumX(arr,x,y);
        }
        if (y == 1){
            return sumY(arr,x,y);
        }


        return min(minSteps(arr, x-1, y),minSteps(arr, x, y-1))+arr[x-1][y-1];
    }




    private static int sumY(int[][] arr,int x, int y) {
        int sum = 0;
        for (int i = 0; i < x ; i++) {
            sum +=arr[i][0];
        }
        return sum;
    }

    private static int sumX(int[][] arr,int x, int y) {
        int sum = 0;
        for (int i = 0; i < y ; i++) {
            sum +=arr[0][i];
        }
        return sum;
    }


    public static void main(String[] args) {
        int[][] arr =
                {{4,1,5,3},{3,2,7,7},{6,5,2,8},{8,9,4,5}};

//        {{1 ,3,5, 9},{8 ,1,3, 4},{5 ,0,6, 1},{8 ,8,4, 0}};
        System.out.println(minSteps(arr,arr.length,arr[0].length));
    }
}

package org.tylor.tutorial.algorithm;

/**
 * Tylor 2021/1/1
 */
public class KthMin {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        /**
         * 归并排序法O(m+n)
         */
        // int i=0,j=0;
        // int left = 0,right=0;
        // while(i+j<len/2+1){
        //     left = right;
        //     if(i<nums1.length&&(j>=nums2.length||nums1[i]<nums2[j])){
        //         right=nums1[i];
        //         i++;
        //     }else{
        //          right=nums2[j];
        //          j++;
        //     }

        // }
        // int k = len%2;

        // if (k==0) {
        //     return (left+right)/2.0;
        // }else{
        //     return right;
        // }

        char[][] arr = {{0,2}};

        /**
         * 二分法 O(log(m+n))
         */
        int len = nums1.length + nums2.length;
        int start1 = 0, start2 = 0;
        int len1 = nums1.length, len2 = nums2.length;
        if (len % 2 == 1) {
            return getKthMin(nums1, start1, len1 - 1, nums2, start2, len2 - 1, len / 2 + 1);
        } else {
            return (getKthMin(nums1, start1, len1 - 1, nums2, start2, len2 - 1, len / 2) + getKthMin(nums1, start1, len1 - 1, nums2, start2, len2 - 1, len / 2 + 1)) / 2.0;
        }

    }

    public String intToRoman(int num) {
        String[][] arr = {{"","I","II","III","IV","VI","VII","VIII","IX"}};
        StringBuilder sb = new StringBuilder();
        return sb.append(arr[0][num%10]).toString();
    }
    private int getKthMin(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKthMin(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKthMin(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKthMin(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    /**
     * 接雨水
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int sum=0;
        int len = height.length;
        if (len<3)
            return 0;

        int[] max_left = new int[len];
        int[] max_right = new int[len];

        max_left[0] = height[0];
        max_right[len-1] = height[len-1];

        for (int i = 1; i < len-2 ; i++) {
            max_left[i] = Math.max(max_left[i-1],height[i]);
        }

        for (int i = len-2; i >0 ; i--) {
            max_right[i] = Math.max(max_right[i+1],height[i]);
        }

        for (int i = 1; i < len-1 ; i++) {
            int temp = Math.min(max_left[i] ,max_right[i] );
            if(temp>height[i]){
                sum = sum+ temp - height[i];
            }

        }

//
//        for (int i=1;i<len-1;i++){
//            int maxLeft=0;
//
//            int maxRight = 0;
//            for(int j=i;j>=0;j--){
//                maxLeft = Math.max(maxLeft,height[j]);
//            }
//
//            for(int j=i;j<len;j++){
//                maxRight = Math.max(maxRight,height[j]);
//            }
//            sum = sum+ Math.min(maxLeft,maxRight) - height[i];
//
//        }
        return sum;

    }
}

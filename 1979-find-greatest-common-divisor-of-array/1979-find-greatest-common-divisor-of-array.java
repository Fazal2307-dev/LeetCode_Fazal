class Solution {
    public int findGCD(int[] nums) {
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;

        for(int n: nums){
            if(n>max)max=n;
            if(n<min)min=n;
        }

        //Euclidean Algorithm
        while(min!=0){
            int t=min;
            min=max%min;
            max=t;
        }
        return max;
    }
}
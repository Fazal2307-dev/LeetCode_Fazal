class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] cnt = new int[31];

        for(int num : nums){
            for(int b =0;b < 31;b++){
                if((num & (1 << b)) !=0){
                    cnt[b]++;
                }
            }
        }
         long mod = 1000000007L;
         long ans =0;

         for(int i=0;i<k;i++){
            long cur =0;
            for(int b =0;b<31;b++){
                if(cnt[b] > i){
                    cur = cur+(1L << b);
                }
            }
            ans = (ans + (cur % mod) *(cur %mod)) % mod;
         }
         return (int) ans;
    }
}
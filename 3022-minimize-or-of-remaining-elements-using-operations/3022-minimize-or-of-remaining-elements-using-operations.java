class Solution {
    public int minOrAfterOperations(int[] nums, int k) {
        int ans=0;
        int mask=0;
        for(int j=30;j>=0;j--){
            mask=mask | (1<<j);
            int cosecutiveAnd=mask;
            int mergeCount=0;
            for(int i:nums){
                cosecutiveAnd = cosecutiveAnd & i;
                if((cosecutiveAnd|ans)!=ans)
                    mergeCount++;
                else cosecutiveAnd=mask;
            }
            if(mergeCount>k)
                ans|=(1<<j);
        }
        return ans;
    }
}
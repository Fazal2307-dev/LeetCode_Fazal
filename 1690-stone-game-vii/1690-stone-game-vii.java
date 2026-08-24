class Solution {
    int dp[][];
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        dp=new int[n][n];
        for(int i=0;i<n;i++)
        {
            Arrays.fill(dp[i],-1);
        }
        return recur(stones,0,n-1);
    }
    public int sum(int stones[],int left,int right)
    {
        int sum=0;
        for(int i=left;i<=right;i++)
        {
               sum+=stones[i];
        }
        return sum;
    }
    public int recur(int stones[],int left,int right)
    {
        if(left > right)return 0;
        if(dp[left][right]!=-1)return dp[left][right];
        int lscore = sum(stones,left+1,right)-recur(stones,left+1,right);
        int rscore = sum(stones,left,right-1)-recur(stones,left,right-1);

        return dp[left][right]=Math.max(lscore,rscore);
    }
}
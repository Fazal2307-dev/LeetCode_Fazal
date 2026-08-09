class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for(int a:stones){
            cnt[a % 3]++;
        }
        if(Math.min(cnt[1],cnt[2]) == 0){
            return Math.max(cnt[1],cnt[2]) >2 && cnt[0] %2 >0;
        }
        return Math.abs(cnt[1] - cnt[2]) >2 || cnt[0] %2 ==0;
    }
}
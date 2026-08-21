class Solution {
    public int findKthNumber(int m, int n, int k) {
        long l = 1,h = m * n,ans = 1;
        while(l <= h){
            long mid = (h - l) / 2 + l;
            if(check(mid,m,n,k)){
                ans = mid;
                h = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }
        return (int)ans;
    }
    private boolean check(long ele,long m,long n,long k){
        long cnt = 0;
        long i = 1,j = n;
        while(i <= m && j >= 1){
            long target = i * j;
            if(target <= ele){
                cnt += j;
                i++;
            }
            else{
                j--;
            }
        }
        return cnt >= k;
    }
}
class Solution {
    public int scoreOfParentheses(String S) {
        return F(S,0,S.length());
    }
    private int F(String S, int i, int j){
        int ans =0,bal=0;
        for(int K =i;K<j;++K){
            bal += S.charAt(K) == '(' ? 1 : -1;
            if(bal == 0){
                if(K  - i == 1){
                    ans++;
                }else{
                    ans += 2*F(S,i+1,K);
                }
                i = K+1;
            }
        }
        return ans;
    }
}
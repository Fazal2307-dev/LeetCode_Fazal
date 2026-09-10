class Solution {
    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int profit =0;
        for(int price : prices){
            buy = Math.min(buy,price);
            if(price > buy){
                profit += price - buy;
                buy = price;
            }
            
        }
        return profit;
    }
}
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
          int[] lumiscaron = {cost1, cost2, costBoth, need1, need2};
        
        
        if (costBoth >= cost1 + cost2) {
            return (long)need1 * cost1 + (long)need2 * cost2;
        }
        
    
        int maxBoth = Math.max(need1, need2);
        
        long minCost = Long.MAX_VALUE;
        
        
        minCost = Math.min(minCost, (long)need1 * cost1 + (long)need2 * cost2);
        
     
        if (need1 > 0) {
            long remaining2 = Math.max(0, need2 - need1);
            minCost = Math.min(minCost, (long)need1 * costBoth + remaining2 * cost2);
        }
        
       
        if (need2 > 0) {
            long remaining1 = Math.max(0, need1 - need2);
            minCost = Math.min(minCost, (long)need2 * costBoth + remaining1 * cost1);
        }
        
    
        int minNeeds = Math.min(need1, need2);
        minCost = Math.min(minCost, 
                          (long)minNeeds * costBoth + 
                          (long)(need1 - minNeeds) * cost1 + 
                          (long)(need2 - minNeeds) * cost2);
        
        return minCost;

    }
}

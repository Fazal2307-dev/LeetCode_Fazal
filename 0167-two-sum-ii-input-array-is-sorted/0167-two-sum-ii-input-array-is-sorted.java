class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int i=0,n=numbers.length-1;
        while(i < n){
            if(target > numbers[i] + numbers[n]) i++;
            else if(target <numbers[i] + numbers[n]) n--;
            else return new int[]{i +1,n+1};
        }
        return new int[]{};
    }
}
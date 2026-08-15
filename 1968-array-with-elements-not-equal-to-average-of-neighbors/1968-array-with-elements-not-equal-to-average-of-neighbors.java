class Solution {
    public int[] rearrangeArray(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);

        for(int i = 1;i < len;i += 2){
            int temp = nums[i];
            nums[i] = nums[i-1];
            nums[i-1] = temp;
        }
        
        return nums;
    }
}
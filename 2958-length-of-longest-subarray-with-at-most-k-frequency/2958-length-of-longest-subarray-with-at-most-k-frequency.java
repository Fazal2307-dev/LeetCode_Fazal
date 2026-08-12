class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        // Intuition: only nums[right] itself can break the window, shrink from left until it fits again
        int n = nums.length;
        int r = 1;
        int left = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int right = 0; right < n; right++) {
            int c = nums[right];
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            // Decrease window length until k constraint is true again
            while (freq.get(c) > k) {
                int d = nums[left];
                freq.put(d, freq.get(d) - 1);
                left++;
            }
            r = Math.max(r, right - left + 1);
        }
        return r;
    }
}
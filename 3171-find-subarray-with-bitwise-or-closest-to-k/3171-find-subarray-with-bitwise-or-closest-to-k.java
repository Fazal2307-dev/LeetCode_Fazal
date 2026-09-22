class Solution {

    public int minimumDifference(int[] nums, int k) {

        int n = nums.length;
        int[] cnt = new int[31];

        int l = 0;
        int or = 0;
        int ans = Integer.MAX_VALUE;

        for (int r = 0; r < n; r++) {

            int x = nums[r];

            // Add nums[r]
            for (int b = 0; b < 31; b++) {
                if ((x & (1 << b)) != 0) {
                    cnt[b]++;
                    or |= (1 << b);
                }
            }

            // Shrink while OR is greater than k
            while (l <= r && or > k) {

                ans = Math.min(ans, or - k);

                 x = nums[l];

                for (int b = 0; b < 31; b++) {
                    if ((x & (1 << b)) != 0) {

                        cnt[b]--;

                        if (cnt[b] == 0) {
                            or &= ~(1 << b);
                        }
                    }
                }

                l++;
            }

            // Current window is non-empty
            if (l <= r) {
                ans = Math.min(ans, k - or);
            }
        }

        return ans;
    }
}
import java.util.*;

class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length / 2;
        long totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        int[] left = Arrays.copyOfRange(nums, 0, n);
        int[] right = Arrays.copyOfRange(nums, n, nums.length);

        List<List<Long>> leftSums = new ArrayList<>();
        List<List<Long>> rightSums = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            leftSums.add(new ArrayList<>());
            rightSums.add(new ArrayList<>());
        }

        getSums(left, 0, 0, 0L, leftSums);
        getSums(right, 0, 0, 0L, rightSums);

        long ans = Long.MAX_VALUE;

        for (int k = 0; k <= n; k++) {
            List<Long> leftList = leftSums.get(k);
            List<Long> rightList = rightSums.get(n - k);

            Collections.sort(rightList);

            for (long leftSum : leftList) {
                long target = totalSum / 2 - leftSum;
                int idx = lowerBound(rightList, target);

                if (idx < rightList.size()) {
                    long s1 = leftSum + rightList.get(idx);
                    ans = Math.min(ans, Math.abs(totalSum - 2 * s1));
                }

                if (idx > 0) {
                    long s1 = leftSum + rightList.get(idx - 1);
                    ans = Math.min(ans, Math.abs(totalSum - 2 * s1));
                }

                if (ans == 0) {
                    return 0;
                }
            }
        }

        return (int) ans;
    }

    private void getSums(int[] half, int index, int count, long currentSum, List<List<Long>> out) {
        if (index == half.length) {
            out.get(count).add(currentSum);
            return;
        }
        getSums(half, index + 1, count, currentSum, out);
        getSums(half, index + 1, count + 1, currentSum + half[index], out);
    }

    private int lowerBound(List<Long> list, long target) {
        int low = 0;
        int high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
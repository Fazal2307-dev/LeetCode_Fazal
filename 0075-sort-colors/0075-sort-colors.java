
class Solution {
    public void sortColors(int[] nums) {
        int[] count = new int[3];

        for (int num : nums) {
            count[num]++;
        }

        int index = 0;

        for (int color = 0; color < 3; color++) {
            while (count[color]-- > 0) {
                nums[index++] = color;
            }
        }
    }
}
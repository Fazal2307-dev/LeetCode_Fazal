class Solution {
    public int[] findEvenNumbers(int[] digits) {
        List<Integer> ans = new ArrayList<>();
        int[] mp = new int[10];

        for (int digit : digits) {
            mp[digit]++;
        }

        for (int i = 1; i <= 9; i++) {
            if (mp[i] == 0) continue;

            mp[i]--;

            for (int j = 0; j <= 9; j++) {
                if (mp[j] == 0) continue;
                mp[j]--;

                for (int k = 0; k <= 8; k += 2) {
                    if (mp[k] == 0) continue;
                    mp[k]--;

                    int num = i * 100 + j * 10 + k;
                    ans.add(num);

                    mp[k]++;
                }
                mp[j]++;
            }
            mp[i]++;
        }

        int[] result = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        return result;
    }
}
class Solution {
    static final long MOD = 1_000_000_007L;

    public int countKSubsequencesWithMaxBeauty(String s, int k) {

        // Step 1: Count frequency of each character
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Step 2: Store non-zero frequencies
        List<Integer> list = new ArrayList<>();

        for (int f : freq) {
            if (f > 0) {
                list.add(f);
            }
        }

        // Not enough unique characters
        if (list.size() < k) {
            return 0;
        }

        // Step 3: Sort frequencies in descending order
        list.sort(Collections.reverseOrder());

        // Step 4: Find the frequency at the kth position
        int kthFreq = list.get(k - 1);

        long answer = 1;
        int selectedBefore = 0;
        int totalBoundary = 0;

        // Count how many characters have frequency > kthFreq
        // and how many have frequency == kthFreq
        for (int f : list) {
            if (f > kthFreq) {
                selectedBefore++;
            }

            if (f == kthFreq) {
                totalBoundary++;
            }
        }

        // We need this many characters from the boundary group
        int need = k - selectedBefore;

        // Step 5: Contribution of characters with frequency > kthFreq
        for (int i = 0; i < selectedBefore; i++) {
            answer = (answer * list.get(i)) % MOD;
        }

        // Step 6: Choose 'need' characters from boundary group
        answer = (answer * combination(totalBoundary, need)) % MOD;

        // Each selected boundary character can choose any occurrence
        answer = (answer * modPow(kthFreq, need)) % MOD;

        return (int) answer;
    }

    // nCr % MOD
    private long combination(int n, int r) {
        if (r == 0 || r == n) {
            return 1;
        }

        long numerator = 1;
        long denominator = 1;

        for (int i = 0; i < r; i++) {
            numerator = (numerator * (n - i)) % MOD;
            denominator = (denominator * (i + 1)) % MOD;
        }

        return (numerator * modPow(denominator, MOD - 2)) % MOD;
    }

    // Fast exponentiation
    private long modPow(long a, long b) {
        long result = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % MOD;
            }

            a = (a * a) % MOD;
            b >>= 1;
        }

        return result;
    }
}
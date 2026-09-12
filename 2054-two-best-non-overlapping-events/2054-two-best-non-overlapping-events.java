class Solution {
    public int maxTwoEvents(int[][] events) {
        int n = events.length;

        // Build arrays sorted by end time
        int[][] byEnd = Arrays.copyOf(events, n);
        Arrays.sort(byEnd, (a, b) -> Integer.compare(a[1], b[1]));

        int[] ends = new int[n];
        int[] pref = new int[n];
        int maxSoFar = 0;
        for (int i = 0; i < n; i++) {
            ends[i] = byEnd[i][1];
            maxSoFar = Math.max(maxSoFar, byEnd[i][2]);
            pref[i] = maxSoFar; // prefix max of values up to this end time
        }

        // Iterate events sorted by start time, combine with best previous non-overlapping
        int[][] byStart = Arrays.copyOf(events, n);
        Arrays.sort(byStart, (a, b) -> Integer.compare(a[0], b[0]));

        int result = 0;
        int bestSingle = 0;

        for (int[] e : byStart) {
            int start = e[0], value = e[2];
            bestSingle = Math.max(bestSingle, value);

            // Find last index with ends[idx] < start (strictly before start)
            int idx = upperBound(ends, start - 1) - 1;
            int bestPrev = (idx >= 0) ? pref[idx] : 0;

            result = Math.max(result, bestPrev + value);
        }

        // In case the best is a single event
        return Math.max(result, bestSingle);
    }

    // First index with arr[i] > key (upper_bound), returns n if none
    private int upperBound(int[] arr, int key) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] <= key) lo = mid + 1;
            else hi = mid;
        }
        return lo;

    }
}
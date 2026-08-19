class BookMyShow {

    private final int n;
    private final long m;

    // maxTree[node] = maximum remaining seats in this range
    // sumTree[node] = total remaining seats in this range
    private final long[] maxTree;
    private final long[] sumTree;

    // First row that is not completely full
    private int firstRow = 0;

    public BookMyShow(int n, int m) {
        this.n = n;
        this.m = m;

        maxTree = new long[4 * n];
        sumTree = new long[4 * n];

        build(1, 0, n - 1);
    }

    private void build(int node, int l, int r) {

        if (l == r) {
            maxTree[node] = m;
            sumTree[node] = m;
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        pull(node);
    }

    private void pull(int node) {
        maxTree[node] =
                Math.max(maxTree[node * 2],
                         maxTree[node * 2 + 1]);

        sumTree[node] =
                sumTree[node * 2] +
                sumTree[node * 2 + 1];
    }

    // Update one row
    private void update(int node, int l, int r,
                        int index, long value) {

        if (l == r) {
            maxTree[node] = value;
            sumTree[node] = value;
            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        pull(node);
    }

    // Find the first row <= maxRow
    // having at least k available seats
    private int findFirst(int node, int l, int r,
                          int maxRow, long k) {

        if (l > maxRow || maxTree[node] < k) {
            return -1;
        }

        if (l == r) {
            return l;
        }

        int mid = l + (r - l) / 2;

        // Search left side first
        int result = findFirst(
                node * 2,
                l,
                mid,
                maxRow,
                k
        );

        if (result != -1) {
            return result;
        }

        return findFirst(
                node * 2 + 1,
                mid + 1,
                r,
                maxRow,
                k
        );
    }

    // Query total seats in [ql, qr]
    private long querySum(int node, int l, int r,
                          int ql, int qr) {

        if (qr < l || r < ql) {
            return 0;
        }

        if (ql <= l && r <= qr) {
            return sumTree[node];
        }

        int mid = l + (r - l) / 2;

        return querySum(
                    node * 2,
                    l,
                    mid,
                    ql,
                    qr
                )
                +
                querySum(
                    node * 2 + 1,
                    mid + 1,
                    r,
                    ql,
                    qr
                );
    }

    public int[] gather(int k, int maxRow) {

        int row = findFirst(
                1,
                0,
                n - 1,
                maxRow,
                k
        );

        if (row == -1) {
            return new int[0];
        }

        /*
         * Seats in every row are always occupied
         * from left to right.
         *
         * Therefore:
         *
         * starting seat = m - remaining seats
         */
        long remaining = getRemaining(
                1,
                0,
                n - 1,
                row
        );

        int startSeat = (int)(m - remaining);

        remaining -= k;

        update(
                1,
                0,
                n - 1,
                row,
                remaining
        );

        if (row == firstRow && remaining == 0) {
            moveFirstRow();
        }

        return new int[]{
                row,
                startSeat
        };
    }

    public boolean scatter(int k, int maxRow) {

        // Check total available seats
        long available = querySum(
                1,
                0,
                n - 1,
                firstRow,
                maxRow
        );

        if (available < k) {
            return false;
        }

        /*
         * Allocate from the smallest row number.
         */
        while (k > 0) {

            long remaining = getRemaining(
                    1,
                    0,
                    n - 1,
                    firstRow
            );

            long take = Math.min(
                    remaining,
                    (long) k
            );

            remaining -= take;
            k -= take;

            update(
                    1,
                    0,
                    n - 1,
                    firstRow,
                    remaining
            );

            if (remaining == 0) {
                firstRow++;
            }
        }

        return true;
    }

    // Get remaining seats in one row
    private long getRemaining(int node, int l, int r,
                              int index) {

        if (l == r) {
            return sumTree[node];
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            return getRemaining(
                    node * 2,
                    l,
                    mid,
                    index
            );
        }

        return getRemaining(
                node * 2 + 1,
                mid + 1,
                r,
                index
        );
    }

    private void moveFirstRow() {
        while (firstRow < n) {

            long seats = getRemaining(
                    1,
                    0,
                    n - 1,
                    firstRow
            );

            if (seats > 0) {
                break;
            }

            firstRow++;
        }
    }
}
/**
 * Your BookMyShow object will be instantiated and called as such:
 * BookMyShow obj = new BookMyShow(n, m);
 * int[] param_1 = obj.gather(k,maxRow);
 * boolean param_2 = obj.scatter(k,maxRow);
 */
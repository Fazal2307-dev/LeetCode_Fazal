class Solution {
    public String[] createGrid(int m, int n, int k) {
        String[] empty = new String[0];
        if(k>1 && (m==1 || n==1)) return empty;
        if(k!=1 && m+n < k+2) return empty;

        List<StringBuilder> list = new ArrayList<>();
        list.add(new StringBuilder(".".repeat(n)));
        for(int i=0; i<m-1; i++){
            String line = n>1 ? ("#".repeat(n-1) + ".") : ".";
            list.add(new StringBuilder(line));
        }
        int[][] priorities = new int[][]{{1, n-2}, {1, n-3}, {1, n-4}, {2, n-2}, {3, n-2}};
        int p = k-1;
        // this is the only special case
        if(n==3 && k==4) {
            list.get(0).setCharAt(n-1, '#');
        }
        
        for(int i=0; i<priorities.length && p>0; i++){
            int[] cur = priorities[i];
            int r = cur[0], c = cur[1];
            if(r>=0 && r<m && c>=0 && c<n){
                list.get(r).setCharAt(c, '.');
                p--;
            }
        }
        return list
            .stream()
            .map(StringBuilder::toString)
            .toArray(String[]::new);
        
    }
}
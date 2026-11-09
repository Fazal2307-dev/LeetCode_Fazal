class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings,(a,b)-> Integer.compare(a[0],b[0]));
        PriorityQueue<Integer> available = new PriorityQueue<>();
        for(int i =0;i<n;i++){
            available.offer(i);
        }
        PriorityQueue<long[]> ongoing = new PriorityQueue<>((a,b)->{
            if(a[0]==b[0]) return Long.compare(a[1],b[1]);
            return Long.compare(a[0],b[0]);
        });
        int[] count = new int[n];
        for(int[] meeting : meetings){
            long start = meeting[0];
            long end =meeting[1];
            long duration=end-start;
            while(!ongoing.isEmpty() && ongoing.peek()[0]<=start){
                available.offer((int) ongoing.poll()[1]);
            }
            if(!available.isEmpty()){
                int room =available.poll();
                count[room]++;
                ongoing.offer(new long[]{end,room});
            }else{
                long[] earliest = ongoing.poll();
                long newEnd = earliest[0] + duration;
                int  room = (int) earliest[1];
                count[room]++;
                ongoing.offer(new long[]{newEnd,room});
            }
        }
        int result =0;
        for( int i=1;i<n;i++){
            if(count[i]>count[result] || (count[i]==count[result] && i <result)){
                result =i;
            }
        }
        return result;

    }
}
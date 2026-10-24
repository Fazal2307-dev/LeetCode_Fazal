class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        return Math.max(0,Math.min(f(leaveAlice),f(leaveBob)) - Math.max(f(arriveAlice),f(arriveBob))+1);
        
    }
    int f(String s){
        var m = (s.charAt(0) - '0') * 10 +(s.charAt(1) - '0');
        var q = (s.charAt(3) - '0') * 10 +(s.charAt(4) - '0');

        return (275 * m) / 9 - 30 - 2 *((m +9) / 12) + q;
    }
}
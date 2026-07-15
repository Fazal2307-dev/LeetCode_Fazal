class Solution {
    public String getPermutation(int n, int k) {
        if(n == 1) return "1";

        StringBuilder numbers = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            numbers.append(i);
        }

        int fact = 1;

        for(int i = 2; i < n; i++){
            fact *= i;
        }

        n--;

        StringBuilder result = new StringBuilder();

        while(k > 0){
            if(k % fact == 0){
                result.append(numbers.charAt((k/fact)-1));
                numbers.deleteCharAt((k/fact)-1);
            }
            else{
                result.append(numbers.charAt(k/fact));
                numbers.deleteCharAt(k/fact);
            }

            k = k % fact;
            fact = fact / n;
            n--; 
        }

        for(int i = numbers.length()-1; i >= 0; i--){
            result.append(numbers.charAt(i));
        }

        return result.toString();
    }
}
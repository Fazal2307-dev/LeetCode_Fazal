class Solution {
    public void wiggleSort(int[] nums) {
        int n=nums.length;
        int[] temp=new int[n];
        for(int i=0;i<n;i++){
            temp[i]=nums[i];
        }
        Arrays.sort(temp);
        int left=(n-1)/2;
        int right=n-1;
        for(int i=0;i<n;i+=2){ 
            nums[i]=temp[left];
            left--;
        }
        for(int i=1;i<n;i+=2){
            nums[i]=temp[right];
            right--;
        }
    }
}
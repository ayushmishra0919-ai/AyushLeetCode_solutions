class Solution {
    public void sortColors(int[] nums) {
        int low = 0; // boundary for 0s
        int mid = 0; // current element
        int high = nums.length - 1; // boundary for 2s
        
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } 
            else if (nums[mid] == 1) {
                mid++; // 1 is already in correct place
            } 
            else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
                // don't increment mid, need to check swapped element
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
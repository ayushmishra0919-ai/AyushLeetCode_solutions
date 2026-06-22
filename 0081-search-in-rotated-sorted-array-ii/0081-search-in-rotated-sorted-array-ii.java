class Solution {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (nums[mid] == target) return true;
            
            // Duplicates case: can't decide which half is sorted
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
            }
            // Left half is sorted
            else if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1; // target in left sorted half
                } else {
                    low = mid + 1; // target in right half
                }
            }
            // Right half is sorted 
            else {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1; // target in right sorted half
                } else {
                    high = mid - 1; // target in left half
                }
            }
        }
        return false;
    }
}

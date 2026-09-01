class Solution {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        

        for (int i = 0; i < nums.length; i++) {
            int ele = nums[i];
            
            if (ele <= first) {
                first = ele;
            } else if (ele <= second) {
                second = ele;
            } else {
                return true;
            }
        }
        
        return false;
    }
}
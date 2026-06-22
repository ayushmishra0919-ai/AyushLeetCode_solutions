class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        
        int minPrice = Integer.MAX_VALUE; // cheapest buy so far
        int maxProfit = 0;                // best profit so far
        
        for (int price : prices) {
            // If we find a new low, update minPrice
            if (price < minPrice) {
                minPrice = price;
            } 
            // Else check if selling today gives better profit
            else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }
}
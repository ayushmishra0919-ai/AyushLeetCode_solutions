class Solution {
    public int myAtoi(String s) {
        int i = 0, len = s.length();
        if (len == 0) return 0;

        // Use char array to avoid charAt overhead
        char[] chars = s.toCharArray();

        // 1. Skip leading spaces
        while (i < len && chars[i] == ' ') i++;
        if (i == len) return 0;

        // 2. Handle sign
        int sign = 1;
        if (chars[i] == '+' || chars[i] == '-') {
            sign = chars[i++] == '-'? -1 : 1;
        }

        // 3. Convert digits with overflow check
        int res = 0;
        int INT_MAX_DIV10 = Integer.MAX_VALUE / 10; // 214748364

        while (i < len) {
            int digit = chars[i] - '0';
            if (digit < 0 || digit > 9) break; // non-digit

            // Overflow check before res * 10 + digit
            if (res > INT_MAX_DIV10 || (res == INT_MAX_DIV10 && digit > 7)) {
                return sign == 1? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + digit;
            i++;
        }

        return res * sign;
    }
}
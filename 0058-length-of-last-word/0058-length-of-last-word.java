class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim(); // remove spaces at start and end
        int lastSpace = s.lastIndexOf(' ');
        return s.length() - lastSpace - 1;
    }
}
package zhy2002.leetcode.solutions.romantointeger;

/**
 * https://leetcode.com/problems/roman-to-integer/
 */
public class Solution {

    public int romanToInt(String s) {
        int sum = 0;
        int lastVal = 0;
        for(int i=0; i<s.length(); i++){
            char thisChar = s.charAt(i);
            int thisVal = charValue(thisChar);
            if(lastVal > 0){//add value of previous char
                if(lastVal >= thisVal){
                    sum += lastVal;
                } else {
                    sum += thisVal - lastVal;
                    thisVal = 0;
                }
            }
            lastVal = thisVal;
        }
        sum += lastVal;
        return sum;
    }

    private static int charValue(char c){
        if(c == 'I')
            return 1;
        if(c == 'V')
            return 5;
        if(c == 'X')
            return 10;
        if(c == 'L')
            return 50;
        if(c == 'C')
            return 100;
        if(c == 'D')
            return 500;
        if(c == 'M')
            return 1000;

        return 0;
    }
}

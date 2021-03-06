package zhy2002.leetcode.solutions.permutationsii;

import java.util.List;

/**
 * https://oj.leetcode.com/problems/permutations-ii/
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * For example,
 * [1,1,2] have the following unique permutations:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 ]
 */
public interface Solution {

    List<List<Integer>> permuteUnique(int[] num);
}

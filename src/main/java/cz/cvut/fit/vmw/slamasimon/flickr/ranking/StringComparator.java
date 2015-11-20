package cz.cvut.fit.vmw.slamasimon.flickr.ranking;

/**
 * Created by simon on 20.11.15.
 */
public class StringComparator
{
	public int editDistance(String word1, String word2)
	{
		if (word1.isEmpty()) return word2.length();
		if (word2.isEmpty()) return word1.length();

		int word1Length = word1.length();
		int word2Length = word2.length();

		//minCosts[i][j] represents the edit distance of the substrings
		//word1.substring(i) and word2.substring(j)
		int[][] minCosts = new int[word1Length][word2Length];

		//This is the edit distance of the last char of word1 and the last char of word2
		//It can be 0 or 1 depending on whether the two are different or equal
		minCosts[word1Length - 1][word2Length - 1] = replaceCost(word1, word2, word1Length - 1, word2Length - 1);

		for (int j = word2Length - 2; j >= 0; j--) {
			minCosts[word1Length - 1][j] = 1 + minCosts[word1Length - 1][j + 1];
		}

		for (int i = word1Length - 2; i >= 0; i--) {
			minCosts[i][word2Length - 1] = 1 + minCosts[i + 1][word2Length - 1];
		}

		for (int i = word1Length - 2; i >= 0; i--) {
			for (int j = word2Length - 2; j >= 0; j--) {
				int replace = replaceCost(word1, word2, i, j) + minCosts[i + 1][j + 1];
				int delete = 1 + minCosts[i + 1][j];
				int insert = 1 + minCosts[i][j + 1];
				minCosts[i][j] = minimum(replace, delete, insert);
			}
		}
		return minCosts[0][0];
	}

	public int minimum(int... numbers) {
		int result = Integer.MAX_VALUE;
		for (int each : numbers) {
			result = Math.min(result, each);
		}
		return result;
	}


	private int replaceCost(String word1, String word2, int index1, int index2)
	{
		return (word1.charAt(index1) == word2.charAt(index2)) ? 0 : 1;
	}
}

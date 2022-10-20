package hw4;



/**
 *@author BlessingNgara
 */

/**
 * Evaluator for a hand containing (at least) four cards of the same rank.
 * The number of cards required is four.
 * 
 * The name of this evaluator is "Four of a Kind".
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator

	
	public class FourOfAKindEvaluator extends NoOfAKindEvaluator {
		public FourOfAKindEvaluator(int ranking, int handSize) { 
			super("Four of a Kind", ranking, handSize, 4);
		}

	}
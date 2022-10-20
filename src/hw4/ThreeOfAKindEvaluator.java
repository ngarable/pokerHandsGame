package hw4;
/**
 * Evaluator for a hand containing (at least) three cards of the same rank.
 * The number of cards required is three.
 * 
 * The name of this evaluator is "Three of a Kind".
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
/**
 *@author BlessingNgara
 */
	// Uses "n" of a kind in order to evaluate the hand, with the parameter "cards required" set to 3"
public class ThreeOfAKindEvaluator extends NoOfAKindEvaluator {
	public ThreeOfAKindEvaluator(int ranking, int handSize) {
		super("Three of a Kind", ranking, handSize, 3);
	}
}









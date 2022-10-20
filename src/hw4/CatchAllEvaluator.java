package hw4;

import api.Card;
/**
 * @author BlessingNgara
 */
/**
 * Evaluator satisfied by any set of cards.  The number of
 * required cards is equal to the hand size.
 * 
 * The name of this evaluator is "Catch All".
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class CatchAllEvaluator extends AbstractEvaluator{
	/**
	 * Constructs the evaluator.
	 * @param ranking
	 *   ranking of this hand
	 * @param handSize
	 *   number of cards in a hand
	 */

	public CatchAllEvaluator(int ranking, int handSize) {
		super("Catch All", ranking, handSize, handSize);
	}

	public boolean canSatisfy(Card[] mainCards) {
		if (mainCards.length < cardsRequired() || mainCards.length > cardsRequired()) {
			return false;
		}
		return true;
	}
}

	


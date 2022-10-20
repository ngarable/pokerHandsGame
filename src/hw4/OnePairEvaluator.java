package hw4;

import api.Card;

/**
 *@author BlessingNgara
 */


/**
 * Evaluator for a hand containing (at least) two cards of the same rank.
 * The number of cards required is two.
 * 
 * The name of this evaluator is "One Pair".
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class OnePairEvaluator extends AbstractEvaluator{	
	public OnePairEvaluator(int ranking, int handSize) {
		super("One Pair", ranking, handSize, 2);
	}
	public boolean canSatisfy(Card[] mainCards) {
		if (mainCards.length < cardsRequired() || mainCards.length > cardsRequired()) {
			return false;
		}
		if (mainCards[0].getRank() == mainCards[1].getRank()) {
			return true;
		}
		return false;
	}	
}



package hw4;

import api.Card;

/**
 *@author BlessingNgara
 */
	// Straight flush is a modified version of GeneralStraight where we are checking of the cards are Straight and of the same suit
public class StraightFlushEvaluator extends GeneralStraightEvaluator {
	public StraightFlushEvaluator(int ranking, int handsize, int maxCardRank) {
		super("Straight Flush", ranking, handsize, maxCardRank);
	}

	public boolean canSatisfy(Card[] mainCards) {
		if (mainCards.length < cardsRequired() || mainCards.length > cardsRequired()) {
			return false;
		}
		Card cards = mainCards[0];
		for (int i = 1; i < mainCards.length; i++) {
			if (cards.getRank() == 1) {
				if (cards.getSuit() != mainCards[i].getSuit()) {
					return false;
				}
				cards = mainCards[i];
				continue;
			}
			if (cards.getRank() != mainCards[i].getRank() + 1 || cards.getSuit() != mainCards[i].getSuit()) {
				return false;
			}
			cards = mainCards[i];
		}
		return true;
	}
}

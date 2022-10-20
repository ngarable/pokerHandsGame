package hw4;
import api.Card;

/**
 *@author BlessingNgara
 */

public class NoOfAKindEvaluator extends AbstractEvaluator {
	public NoOfAKindEvaluator(String name, int ranking, int handsize, int cardsRequired) {
		super(name, ranking, handsize, cardsRequired);
	}

	public boolean canSatisfy(Card[] mainCards) {
		if (mainCards.length < cardsRequired() || mainCards.length > cardsRequired()) {
			return false;
		}
		Card card1 = mainCards[0];
		for (int i = 1; i < mainCards.length; i++) {
			if (card1.getRank() != mainCards[i].getRank()) {
				return false;
			}
		}
		return true;
	}
}

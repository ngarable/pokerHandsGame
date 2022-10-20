package hw4;
import api.Card;
/**
 *@author BlessingNgara
 */
public class StraightEvaluator extends GeneralStraightEvaluator {

	/**
	 * Constructs the evaluator. Note that the maximum rank of the cards to be used
	 * must be specified in order to correctly evaluate a straight with ace high.
	 * 
	 * @param ranking     ranking of this hand
	 * @param handSize    number of cards in a hand
	 * @param maxCardRank largest rank of any card to be used
	 */
	//This class "Straight" checks if the cards are in descending order according to their rankings for example(10, 9, 8, 7, 6)
	public StraightEvaluator(int ranking, int handSize, int maxCardRank) {
		super("Straight", ranking, handSize, maxCardRank);
	}

	public boolean canSatisfy(Card[] mainCards) {
		if (mainCards.length < cardsRequired() || mainCards.length > cardsRequired()) {
			return false;
		}
		Card cards = mainCards[0];
		for (int i = 1; i < mainCards.length; i++) {
			if (cards.getRank() == 1) {
				cards = mainCards[i];
				continue;
			}
			if (cards.getRank() != mainCards[i].getRank() + 1) {
				return false;
			}
			cards = mainCards[i];
		}
		return true;
	}
}



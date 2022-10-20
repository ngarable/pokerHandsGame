package hw4;
import java.util.Arrays;

import api.Card;
import api.Hand;

/**
 *@author BlessingNgara
 */
	// Evaluator for  General Straight
public abstract class GeneralStraightEvaluator extends AbstractEvaluator {
	private int maxCardRank;
	// Constructor
	public GeneralStraightEvaluator (String name, int ranking, int handsize, int maxCardRank) {
		super(name, ranking, handsize, handsize);
		this.maxCardRank = maxCardRank;
	}
	// Constructor for the hand preventing a bigger hand than possible
	@Override
	public Hand createHand(Card[] allCards, int[] subset) {
		if (allCards.length < handSize()) {
			return null;
		}
		Card[] cards = new Card[subset.length];
		for (int i = 0; i < subset.length; i++) {
			cards[i] = allCards[subset[i]];
		}
		if (canSatisfy(cards)) {
			int j = handSize() - cardsRequired();
			Card[] card2 = new Card[j];
			if (j > 0) {
				int k = 0;
				for (int i = 0; i < allCards.length; i++) {
					Card card3 = allCards[i];
					if (Arrays.asList(cards).contains(card3)) {
						continue;
					}
					card2[k] = card3;
					k++;
					if (k == j) {
						break;
					}

				}
			}
			if (containsAce(cards) && maxCardRank <= 5) {
				cards = arrangeStraight(cards);
			}
			Hand hand_Created = new Hand(cards, card2, this);
			return hand_Created;
		}
		return null;
	}

	
	//Helper method
	private Card[] arrangeStraight(Card[] cards) {
		if (cards[0].getRank() != 1) {
			return cards;
		}
		Card[] arr1 = Arrays.copyOfRange(cards, 0, 1);
		Card[] arr2 = Arrays.copyOfRange(cards, 1, cards.length);
		Card[] result = Arrays.copyOf(arr2, arr1.length + arr2.length);
		System.arraycopy(arr1, 0, result, arr2.length, arr1.length);
		return result;
	}

	
	//Helper Method
	private boolean containsAce(Card[] cards) {
		for (Card card : cards) {
			if (card.getRank() == 1) {
				return true;
			}
		}
		return false;
	}
}

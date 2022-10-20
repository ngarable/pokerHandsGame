package hw4;

import api.Card;
import java.util.Arrays;

import api.Hand;


/**
 * Evaluator for a generalized full house.  The number of required
 * cards is equal to the hand size.  If the hand size is an odd number
 * n, then there must be (n / 2) + 1 cards of the matching rank and the
 * remaining (n / 2) cards must be of matching rank. In this case, when constructing
 * a hand, the larger group must be listed first even if of lower rank
 * than the smaller group</strong> (e.g. as [3 3 3 5 5] rather than [5 5 3 3 3]).
 * If the hand size is even, then half the cards must be of matching 
 * rank and the remaining half of matching rank.  Any group of cards,
 * all of which are the same rank, always satisfies this
 * evaluator.
 * 
 * The name of this evaluator is "Full House".
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class FullHouseEvaluator extends AbstractEvaluator
{   
	/**
	 * Constructs the evaluator.
	 * @param ranking
	 *   ranking of this hand
	 * @param handSize
	 *   number of cards in a hand
	 */
	public FullHouseEvaluator(int ranking, int handSize) {
		super("Full House", ranking, handSize, handSize);
	}
	public boolean canSatisfy(Card[] mainCards) {
		if (mainCards.length < cardsRequired() || mainCards.length > cardsRequired()) {
			return false;
		}
		if (sameRank(mainCards)) {
			return true;
		}
		if (mainCards.length > 0) {
			if (mainCards.length % 2 == 0) {
				Card[] array1 = Arrays.copyOfRange(mainCards, 0, (mainCards.length / 2));
				Card[] arr2 = Arrays.copyOfRange(mainCards, (mainCards.length / 2), mainCards.length);
				if (!sameRank(array1) || !sameRank(arr2)) {
					return false;
				}
			} else {
				Card first = mainCards[0];
				Card last = mainCards[mainCards.length - 1];
				Card middle = mainCards[(mainCards.length / 2)];
				if (middle.getRank() == first.getRank()) {
					Card[] arr1 = Arrays.copyOfRange(mainCards, 0, (mainCards.length / 2) + 1);
					Card[] arr2 = Arrays.copyOfRange(mainCards, (mainCards.length / 2) + 1, mainCards.length);
					if (!sameRank(arr1) || !sameRank(arr2)) {
						return false;
					}
				} 
				else if (middle.getRank() == last.getRank()) {
					Card[] arr1 = Arrays.copyOfRange(mainCards, 0, (mainCards.length / 2));
					Card[] arr2 = Arrays.copyOfRange(mainCards, (mainCards.length / 2), mainCards.length);
					if (!sameRank(arr1) || !sameRank(arr2)) {
						return false;
					}
				} 
				else {
					return false;
				}

			}

		}
		return true;
	}
	/**
	 * Checks whether the cards in the array provided are all of the same rank
	 * 
	 * @param maincards the cards to be checked array
	 * @return true or false
	 */

	private boolean sameRank(Card[] maincards) {
		Card first = maincards[0];
		for (int i = 1; i < maincards.length; i++) {
			if (first.getRank() != maincards[i].getRank()) {
				return false;
			}
		}
		return true;
	}

	public Hand createHand(Card[] allCards, int[] subset) {
		if (allCards.length < handSize()) {
			return null;
		}
		Card[] card1 = new Card[subset.length];
		for (int i = 0; i < subset.length; i++) {
			card1[i] = allCards[subset[i]];
		}
		if (canSatisfy(card1)) {
			int j = handSize() - cardsRequired();
			Card[] card2 = new Card[j];
			if (j > 0) {
				int k = 0;
				for (int i = 0; i < allCards.length; i++) {
					Card card3 = allCards[i];
					if (Arrays.asList(card1).contains(card3)) {
						continue;
					}
					card2[k] = card3;
					k++;
					if (k == j) {
						break;
					}
				}
			}
			if (card1.length % 2 != 0) {
				card1 = rearrange(card1);
			}
			Hand card_Created = new Hand(card1, card2, this);
			return card_Created;
		}
		return null;
	}

	

	private Card[] rearrange(Card[] rawCards) {
		Card first = rawCards[0];
		Card mid = rawCards[(rawCards.length / 2)];
		if (mid.getRank() == first.getRank()) {
			return rawCards;
		}
		Card[] arr1 = Arrays.copyOfRange(rawCards, 0, (rawCards.length / 2));
		Card[] arr2 = Arrays.copyOfRange(rawCards, (rawCards.length / 2), rawCards.length);
		Card[] result = Arrays.copyOf(arr2, arr1.length + arr2.length);
		System.arraycopy(arr1, 0, result, arr2.length, arr1.length);
		return result;
	}
}

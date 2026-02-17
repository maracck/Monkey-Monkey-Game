import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.io.File;
import javax.sound.sampled.*;
// 2 ai 1 human

// VERSION 1.8 // version corresponds to the session of coding
/* 1. made 2 methods to remove redundant lines of code (errorMessageInvalidInput, cleanPanel) ver 1.3
 * 2. made the winners indicator at the toss coin be color coordinated(Green 1st, Yellow 2nd and Red 3rd) ver 1.4
 * 3. made some more methods for user pairing of picked card 1.4
 * 4. made a game loading animation (25.2 seconds total run time) ver 1.4
 * 5. did most of the required stuffs just need refinening ver 1.5
 * 6. made some of the codes into methods ver 1.5
 * 7. made the toss coin meesage into a method to make the code cleaner ver 1.6
 * 8. fixed some bugs at the conditon Player-AI2-AI1 gameproper ver 1.6
 * 9 . making the code slightly cleaner ver 1.7
 * 10. Player, Ai1, AI2 gmaeflow complete 1.7
 * 11. fixing bugs in gameflow ver 1.7
 * 12. reworked the toss coin flow ver 1.8
 * 13. tried adding sound effects on some part of the game ver 1.8 // nooooo
 * 14.
 */




/* OFFICIAL MONKAEY MONKAEY */

/* Kelangan */
	/* GAME SETTING */
// Generate Decck of cards // done
// store all cards in array // done
// display the card and check for dupes // done
// display the current deck and the randomly selected card to be removed // done 
// shuffle the entire deck and dicplay it make sure there is no duplicates // done 
// randomly allocate cards for each players and display the allocated cards // done
// paired the cards for each players and display the list of paired cards // done
// eliminate the paired cards and display the remaining card // done

	/* GAME PROPER */
// prompt user for coin toss // done
// let the winner pick a card from opponents card. display the on hand card and selected hand // nien - progressing 1/6
// paired the card on hand cards. Display the paired cards if there any and prompt a mesasge if none // nien - not yet
// simulate the same procedure and vice versa until there is only remaining card // nien - 
// declare a winner if someone had already paired all the cards // done
// display the first card and last card // nien - easy ?

// 8 HOURS LEFT!!!!!!!!!!!!!!!!!!!!!


public class MonkeyGame {
	
	/* Generates the 52 cards with their respective number and type */
	private static String[] generateCards(String[] suits, String[] ranks) {
		String[] deck = new String[52];
        int index = 0;
        for (String suit : suits) {
            for (String rank : ranks) {
                deck[index++] = rank + " of " + suit;
            }
        }

        return deck;
	} /* WORKING */

	/*  SHUFFLES THE GENERATED CARDS INSIDE THE ARRAY */
	private static void shuffle(Object[] isangWholeDeck) {
        int noOfElements = isangWholeDeck.length;

        for(int i = 0; i < noOfElements; i++) {
            int j = i + (int)(Math.random() * (noOfElements - i));

            Object temp = isangWholeDeck[j];
            isangWholeDeck[j] = isangWholeDeck[i];
            isangWholeDeck[i] = temp;
        }
    } /* WORKING */

	/* CHECKS IF THERE IS A DUPLICATE CARD IN THE ARRAY OF THE ORIGINAL DECK  */
	private static void checkDuplicatesGenertedDeck(String[] unOfficialPlayDeck) {
		
		for (int i = 0; i < 52; i++){
			for (int j = i + 1; j < 52; j++){

				if(unOfficialPlayDeck[i] == unOfficialPlayDeck[j]){
					System.out.println("There is a Duplicate");
				} 
			}
		}
	} /* WORKING */

	/* CHECKS IF THERE IS A DUPLICATE CARD IN THE ARRAY OF THE PLAY DECK  */
	private static void checkDuplicatesPlayDeck(String[] officialPlayDeck) {
		
		for (int i = 0; i < 51; i++){
			for (int j = i + 1; j < 51; j++){

				if(officialPlayDeck[i] == officialPlayDeck[j]){
					System.out.println("There is a Duplicate");
				} 
			}
		}
	} /* WORKING */
	
	/* RANDOMLY GENERATE THE CARD INDEX TO BE REMOVED  */
	private static int cardIndexToBeRemoved() {
		Random dice = new Random();
		int cardIndex = dice.nextInt(51);

		return cardIndex;
	} /* WORKING */

	/*  RANDOMLY HIDE A CARD FROM THE DECK  */
	private static String[] randomlyRemoveACard (String[] unOfficialPlayDeck, int cardIndex) {
		String[] oldPlayDeck = new String[51];
		String[] officialPlayDeck = new String[51];
		String cardOut = unOfficialPlayDeck[cardIndex]; 
		//System.err.println("The " + cardOut + " " + cardIndex + " was removed from the deck of cards."); Testing

		for (int i = 0; i<= 50; i++) {

			if (unOfficialPlayDeck[i] != cardOut) {
				oldPlayDeck[i] = unOfficialPlayDeck[i];
			} else {
				oldPlayDeck[i] = unOfficialPlayDeck[51];
			}	
		}
		officialPlayDeck = oldPlayDeck;

		return officialPlayDeck;
	} /* WORKING */
	
	/*  DISTRIBUTES THE CARDS TO PLAYER AND 2 AI's  */

		/*  MAKE A DECK FOR PLAYER WITH 17 CARDS  */
	private static String[] distributeCardsToPlayer(String[] officialPlayDeck) {
        String[] playerDeck = new String[17];        
        
        for (int i = 0; i< 17; i++) {
            playerDeck[i] = officialPlayDeck[i];
        }
		
        return playerDeck;
	} /* WORKING */

		/*  MAKE A DECK FOR FIRST AI WITH 17 CARDS  */
    private static String[] distributeCardsToFirstAI(String[] officialPlayDeck) {
        String[] aiDeck = new String[17];        
        
        for (int i = 17; i< 34; i++) {
            int j = i - 17;
            aiDeck[j] = officialPlayDeck[i];
        }
		
        return aiDeck;
	} /* WORKING */

		/*  MAKE A DECK FOR SECOND AI WITH 17 CARDS  */
    private static String[] distributeCardsToSecondAI(String[] officialPlayDeck) {
        String[] aiDeck = new String[17];        
        
        for (int i = 34; i< 51; i++) {
            int j = i - 34;
            aiDeck[j] = officialPlayDeck[i];
        }
		
        return aiDeck;
	} /* WORKING */

	/*  PAIR THE CARDS INSIDE DECK  */
    private static String[] pairCards(String[] dekc) {
        String[] pairedCards = new String[dekc.length];
        int pairedIndex = 0;
        for (int i = 0; i < dekc.length - 1; i++) {
            for (int j = i + 1; j < dekc.length; j++) {
                if (dekc[i].charAt(0) == dekc[j].charAt(0)) {
                    pairedCards[pairedIndex++] = dekc[i];
                    pairedCards[pairedIndex++] = dekc[j];
                    // Remove cards from the deck
                    dekc = removeCard(dekc, j);
                    dekc = removeCard(dekc, i);
                    i--; // Decrement i to account for removed cards
                    break; // Break inner loop to avoid concurrent modification exception
                }
            }
        }
        // Trim the pairedCards array to remove null elements
        return Arrays.copyOf(pairedCards, pairedIndex);
    } /* WORKING */

	/*  METHOD TO REMOVE A CARD FROM THE DECK  */
    private static String[] removeCard(String[] dekc, int indexToRemove) {
        String[] newPlayerDeck = new String[dekc.length - 1];
        for (int i = 0, j = 0; i < dekc.length; i++) {
            if (i != indexToRemove) {
                newPlayerDeck[j++] = dekc[i];
            }
        }
        return newPlayerDeck;
    } /* WORKING */

	
	/*  COMBINES ALL THE PAIRED CARDS FROM THE 3 DECKS  */
	private static String[] storesTheInitialPairedCards(String[] playerDeckPaired, String[] firstAIDeckPaired, String[] secondAIDeckPaired) {
		int totalLength = playerDeckPaired.length + firstAIDeckPaired.length + secondAIDeckPaired.length;
		String[] pairedCardsDeck = new String[totalLength];
	
		// Copy elements from array1/ Player deck
		System.arraycopy(playerDeckPaired, 0, pairedCardsDeck, 0, playerDeckPaired.length);
	
		// Copy elements from array2/ AI1 deck
		System.arraycopy(firstAIDeckPaired, 0, pairedCardsDeck, playerDeckPaired.length, firstAIDeckPaired.length);
	
		// Copy elements from array3 AI2 deck
		System.arraycopy(secondAIDeckPaired, 0, pairedCardsDeck, playerDeckPaired.length + firstAIDeckPaired.length, secondAIDeckPaired.length);
	
		return pairedCardsDeck;
	} /* WORKING */

	/*  REMOVES THE PAIRED DECK FROM THE PLAY DECK  */
	private static String[] removesTheInitialPairedCards(String[] pairedDeck, String[] playerDeck) {

		String[] remainingCards = Arrays.copyOf(playerDeck, playerDeck.length);
		for (String pairedCard : pairedDeck) {
		
			for (int i = 0; i < remainingCards.length; i++) {
				if (pairedCard.equals(remainingCards[i])) {
					remainingCards = removeElement(remainingCards, i);
					break;
				}
			}
		}
	
		return remainingCards;
	} /* WORKING */

	/*  METHOD TO REMOVE AN ELEMENT FROM AN ARRAY AT SPECIFIC INDEX  */
	private static String[] removeElement(String[] remainingCards, int index) {
		String[] newRemainingCards = new String[remainingCards.length - 1];
		for (int i = 0, k = 0; i < remainingCards.length; i++) {
			if (i == index) {
				continue;
			}
			newRemainingCards[k++] = remainingCards[i];
		}
		return newRemainingCards;
	} /* WORKING */
	
	

	/*  HIDES THE CARDS OF THE OPPONENTS  */
	private static void hidesOpponentCards(String[] opponentDeck) {
		//String[] hiddenCards = new String[opponentDeck.length];

		for (int i = 0; i < opponentDeck.length; i++) {
			int k = 1 + i;
			//hiddenCards[i] = " * Hidden Card " + k +  " * ";
			System.out.print(" * Hidden Card " + k +  " *\t");
		}

		System.out.println("\n\n"); // to have clean space 

		//return hiddenCards;
	} /* WORKING */

	/*  PlAYER PICKS WHICH OPPONENT TO GET A CARD FROM  */
	private static int playerPickTheOpponentDeck(String[] AI1Deck,String[] AI2Deck) {
		Scanner scan = new Scanner(System.in);
		String redColor = "\u001B[31m";
		String resetColor = "\u001B[0m";
		int opponentNumber;
		
		try {
			System.out.println("\n\n____________________________________________\n");
			System.out.println("Pick AI 1 = [1], Pick AI 2 = [2]");
			System.out.print("Pick which oponent deck to get card from: ");
			opponentNumber = scan.nextInt();
			System.out.print("____________________________________________\n");
			if (opponentNumber > 2 || opponentNumber < 0) {
				errorInputInvalidValue();
				opponentNumber = playerPickTheOpponentDeck(AI1Deck, AI2Deck);
			}
			
			return opponentNumber;
			/* CHOOSE DECK */
		} catch (InputMismatchException e) {
			System.out.println(redColor + "\nThat was not a valid input.");
			System.out.println("\n\n\n____________________________________________\n\n");
			System.out.println("\t   Please Try Again");
			System.out.println("\n____________________________________________\n\n");
			System.out.println("\nMake sure to enter the number \nNOT the word or a letter..." + resetColor);
			opponentNumber = playerPickTheOpponentDeck(AI1Deck, AI2Deck);
			if (opponentNumber > 2 || opponentNumber < 0) {
				errorInputInvalidValue();
				opponentNumber = playerPickTheOpponentDeck(AI1Deck, AI2Deck);
			}

			return opponentNumber;
		}
	}

	/*  PICK A CARD  */
	private static int pickACard () {
		int pickedCard;	Scanner scan = new Scanner(System.in);
		try {
			System.out.print("Enter the number of the opponent's card you want to pick: ");
			pickedCard = scan.nextInt();
			pickedCard = pickedCard-1;	
			return pickedCard;
		} catch (InputMismatchException e) {
			errorInputInvalidValue();
		
			pickedCard = pickACard();
			pickedCard = pickedCard-1;
			return pickedCard;
		}
	} /* WORKING */
	
	
	/*  THE PLAYER PICKS CARD FROM EITHER AI1 OR AI2 DEPENDING ON THE OUTCOME OF TOSS COIN */
	private static int playerChooseCard(int choosenDeckPlayer, String[] playAI1Deck, String[] playAI2Deck, String[] playPlayerDeck) {
		String greenColor = "\u001B[32m"; String redColor = "\u001B[31m"; String resetColor = "\u001B[0m"; int pickedCard = 0;

		/* IF PLAYER PICKED AI 1 */
		if (choosenDeckPlayer == 1) {
			whiteLine();
			System.out.println(greenColor + "\nYou get to pick a card from [AI 1]!!!\n" + resetColor);
			System.out.print("Your current deck of cards: \n"); printTheArray(playPlayerDeck);
			System.out.println("\nAI 1 Deck of Cards:");
			hidesOpponentCards(playAI1Deck);	
			System.out.println(Arrays.toString(playAI1Deck));						
			try {
				pickedCard = pickACard();
				if (pickedCard > playAI1Deck.length){
					errorInputInvalidValue();
					playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck, playPlayerDeck);			
				}
				return pickedCard;
			} catch (InputMismatchException e) {
				errorInputInvalidValue();
				choosenDeckPlayer = 1;
				pickedCard = playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck,playPlayerDeck);
				return pickedCard;			
			} catch (Exception e) {
				errorInputInvalidValue();
				choosenDeckPlayer = 1;
				pickedCard = playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck,playPlayerDeck);					
				return pickedCard;
			}		
		}
	
		/* IF PLAYER PICKED AI 2 */
		if(choosenDeckPlayer == 2) {
			whiteLine();
			System.out.println(greenColor + "\nYou get to pick a card from [AI 2]!!!\n" + resetColor);
			System.out.print("Your current deck of cards: \n"); printTheArray(playPlayerDeck);
			System.out.println("\nAI 2 Deck of Cards:");
			hidesOpponentCards(playAI2Deck);	
			System.out.println(Arrays.toString(playAI2Deck));						
			try {
				pickedCard = pickACard();
				if (pickedCard > playAI2Deck.length){
					errorInputInvalidValue();
					playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck, playPlayerDeck);			
				}	
				return pickedCard;
			} catch (InputMismatchException e) {
				System.out.println(redColor + "\nPlease enter a number value...\n" + resetColor);
				pickedCard = pickACard();
				if (pickedCard > playAI2Deck.length){
					errorInputInvalidValue();
					choosenDeckPlayer = 2;
					playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck,playPlayerDeck);							
				}	
				return pickedCard;
			} catch (Exception e) {
				pickedCard = pickACard();
				if(pickedCard > playAI2Deck.length){
					errorInputInvalidValue();
					choosenDeckPlayer = 2;
					playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck,playPlayerDeck);						
				}	
				return pickedCard;
			}		
		}
		return pickedCard;	
	}


	/* GETS THE PLAYER'S SIDE OF THE COIN FOR COIN TOSS  */
	private static int getCoinSideChoice () {
		try {
			String redColor = "\u001B[31m";
			String resetColor = "\u001B[0m";


			Scanner scan = new Scanner(System.in);
			System.out.println("\n________________________________________________________________________________________________________________________________\n");
			System.out.print("Sides of the coin (1-Tails, 2-Heads)\n"); timer700();
			System.out.print("Choose the side of the coin you want: "); 
			int sideChoice = scan.nextInt();
			System.out.println("________________________________________________________________________________________________________________________________");
			sideChoice = sideChoice - 1;
			int newsideChoice;

			if (sideChoice < 0 || sideChoice > 1 ) {
				sideChoice = sideChoice + 1;
				System.out.println(redColor + "\nYour Choice of " + sideChoice + " is Invalid\nPlease Enter a Valid Number Ranging From(1-2)\n" + resetColor);
				newsideChoice = getCoinSideChoice();
			} else {newsideChoice = sideChoice;}

			return newsideChoice;

		} catch (InputMismatchException e) {
			String redColor = "\u001B[31m";
			String resetColor = "\u001B[0m";
			System.out.println(redColor + "\n\nPlease enter the \"number\" not a letter/word.....\n" + resetColor); timer900();
			int newsideChoice = getCoinSideChoice();
		
			return newsideChoice;
		} /* WORKING */ /*  FULLY FUNCTIONAL METHOD AS INTENDED  */
	} /* WORKING??? i don't really know how it works, might be a miracle */ /* re-wrote the program, was not a miracle :< */

	/* ONLY DISPLAYS THE SIDE CHOOSEN BY PLAYER */
	private static String checkCoinSide(int coinChoice) {
		String coinSides[] = {"Tails","Heads"};
		String coinSide = coinSides[coinChoice];

		return coinSide;
	} /* WORKING */

	private static void firstTossCoinMessage() {
		String yellowColor = "\u001B[33m"; String resetColor = "\u001B[0m";
		
		timer400(); 
			System.out.println(yellowColor +"________________________________________________________________________________________________________________________________");
			System.out.println("\n\n\t\t\t****First Toss Coin****");
			System.out.println("\n________________________________________________________________________________________________________________________________\n" + resetColor);
		timer2900();
	
	}

	private static void secondTossCoinMessage() {
		String yellowColor = "\u001B[33m"; String resetColor = "\u001B[0m";
		timer400(); 
			System.out.println(yellowColor + "\n________________________________________________________________________________________________________________________________\n");
			System.out.println("\n\t\t\t****Second Toss Coin****");
			System.out.println("\n________________________________________________________________________________________________________________________________\n" + resetColor);
		timer2900(); 
	
	}



	/*  STARTS THE TOSS COIN  */
	private static String[] tossCoin(int userCoinChoice) {
		String[] winners = new String[3];
		Random dice = new Random();
		String lightGreenColor = "\u001B[92m";
		String greenColor = "\u001B[32m";
		String yellowColor = "\u001B[33m";
		String redColor = "\u001B[31m";
		String resetColor = "\u001B[0m";
		
	
		int winningSide = dice.nextInt(2);		
		if (userCoinChoice == winningSide) { /* PLAYEr WON */
			firstTossCoinMessage();
			System.out.println("\nYou picked the side of " + checkCoinSide(userCoinChoice));
			timer1800(); System.out.println("\nThe Coin if Flipping...\n"); timer1800();
			System.out.println("\nThe winning side was " + checkCoinSide(winningSide) + "," + lightGreenColor +" You won!\n" + resetColor);
			System.out.println(redColor + "The AI1 pick Last.\n" + resetColor);
			winners[2] = "AI1";
			winningSide = dice.nextInt(2);
			
			userCoinChoice = getCoinSideChoice();
			if (userCoinChoice == winningSide) { /* PLAYER WON */
				secondTossCoinMessage();
				System.out.println("\nYou picked the side of " + checkCoinSide(userCoinChoice));
				timer1800(); System.out.println("\nThe Coin if Flipping...\n"); timer1800();
				System.out.println("The winning side was " + checkCoinSide(winningSide) + "," + greenColor + " Player won!\n" +resetColor);
				System.out.println(greenColor + "The Player goes first!," +resetColor + yellowColor + " while AI2 goes second.\n" + resetColor);
				whiteLine();
				winners[0] = "Player";
				winners[1] = "AI2";

			} else { /* PLAYER LOST */
				secondTossCoinMessage();
				System.out.println("\nYou picked the side of " + checkCoinSide(userCoinChoice));
				timer1800(); System.out.println("\nThe Coin if Flipping...\n"); timer1800();
				System.out.println("The winning side was " + checkCoinSide(winningSide) + "," + redColor +" You lost.\n" + resetColor);
				System.out.println(greenColor + "The AI2 pick first!," + resetColor + yellowColor + " while You pick second.\n" + resetColor);
				whiteLine();
				winners[0] = "AI2";
				winners[1] = "Player";
			} 

			return winners;

		} else { /* PLAYER LOST */
			firstTossCoinMessage();
			timer2900(); System.out.println("\nYou picked the side of " + checkCoinSide(userCoinChoice));
			timer1800(); System.out.println("\nThe Coin if Flipping...\n"); timer1800();
			System.out.println("The winning side was " + checkCoinSide(winningSide) + "," + redColor + " You lost.\n" + resetColor); timer700();
			System.out.println(redColor + "The Player pick last.\n" + resetColor);
			winners[2] = "Player";
			winningSide = dice.nextInt(2);
			
			int AI1 = dice.nextInt(2);
			if (AI1 == winningSide) {
				secondTossCoinMessage();
				timer2900(); System.out.println("\nThe AI1 picked the side of " + checkCoinSide(AI1));
				timer1800(); System.out.println("\nThe Coin if Flipping...\n"); timer1800();
				System.out.println("The winning side was " + checkCoinSide(winningSide) + "," + lightGreenColor +" The AI1 won!\n" + resetColor); timer700();
				System.out.println(greenColor + "The AI1 pick first!," + resetColor + yellowColor + " while AI2 pick second.\n" + resetColor);
				whiteLine();
				winners[0] = "AI1";
				winners[1] = "AI2";

			} else {
				secondTossCoinMessage();
				timer2900(); System.out.println("\nThe AI1 picked the side of " + checkCoinSide(AI1));
				timer1800(); System.out.println("\nThe Coin if Flipping...\n"); timer1800();
				System.out.println("The winning side was " + checkCoinSide(winningSide) + "," + redColor +" The AI1 lost.\n" + resetColor); timer700();
				System.out.println(greenColor + "The AI2 pick first," + resetColor + redColor + " while the AI1 pick second.\n" + resetColor);
				whiteLine();
				winners[0] = "AI2";
				winners[1] = "AI1";
			}
			// Testing purposes
			//System.out.println("The order of picking for cards is:\n" + Arrays.toString(winners));

			return winners;
		}
	} /* WORKING */ /*  FULLY FUNCTIONAL METHOD AS INTENDED  */

	private static void startTossCoin() {
		tossCoin(getCoinSideChoice());
	}

	private static String[] addTakenCard(String[] playDeck, String takenCard) {
		// Create a deck with bigger size for the additional card
		String[] newPlayDeck = new String[playDeck.length + 1];
		
		System.arraycopy(playDeck, 0, newPlayDeck, 0, playDeck.length);
		
		// Add the new card to the last index of the new array
		newPlayDeck[newPlayDeck.length - 1] = takenCard;
		
		
		return newPlayDeck;
	}


		/*  CHECKS IF THERE IS A PAIR (PLAYER) */
		private static int checksIfThereIsPairPlayer(String[] playPlayerDeck, String[] theCard) {
			int noPair = 99;

			for (int i = 0; i < playPlayerDeck.length; i++) {
				if (theCard[0].charAt(0) == playPlayerDeck[i].charAt(0)) {
					return i; // if there is a pair, return the index where the pair is found in playPlayerDeck.
				}
			}
			return noPair; // if there is no pair, return 99 which means no pair.
		}
	
	
		/*  CHECKS IF THE PICKED CARD PAIRED WITH ON HAND CARD (AI1) */
		private static int checksIfThereIsPairAI1 (String[] playAI1Deck, String[] choosenCard1) {
			int noPair = 99;
				// Checks if there is a pair
			for (int i = 0; i<playAI1Deck.length; i++) {
				if (choosenCard1[0].charAt(0) == playAI1Deck[i].charAt(0)) {
					return i; // if there is a pair, return the index where the pair is found in playAI1Deck.
				} 
			}
	
			return noPair; // if there is no pair, return 99 which means no pair.
		}
	
		/*  CHECKS IF THE PICKED CARD PAIRED WITH ON HAND CARD (AI2) */
		private static int checksIfThereIsPairAI2 (String[] playAI2Deck, String[] choosenCard2) {
			int noPair = 99;
			
			for (int i = 0; i<playAI2Deck.length; i++) {
				if (choosenCard2[0].charAt(0) == playAI2Deck[i].charAt(0)) {
					return i;  // if there is a pair, return the index where the pair is found in playAI2Deck.
				} 
			}
			
			return noPair; // if there is no pair, return 99 which means no pair.
		}


	private static String[] playPlayerDeckPairAI(String[] playPlayerDeck, int confirmPairPlayer, String[] playAI1Deck, int choosenCardPlayer, String[] pairedCards) {
		
		if (confirmPairPlayer == 99) {
			System.out.println("There is no pair found.");
			return playPlayerDeck;
		} else {
			boolean pairFound = false;
			for (int i = 0; i < pairedCards.length; i++) {
				if (pairedCards[i] == null) {
					pairedCards[i] = playAI1Deck[choosenCardPlayer]; // saves the pair from the oppponent deck to paired array
					pairedCards[i + 1] = playPlayerDeck[confirmPairPlayer]; // save the pair from your own deck to paired array
					pairFound = true;
					break;
				}
			}
			if (!pairFound) {
				System.out.println("No space to store more pairs.");
				return playPlayerDeck;
			}
			playPlayerDeck = removeCard(playPlayerDeck, confirmPairPlayer); // removes the card from the Player deck, the stealer
			playAI1Deck = removeCard(playAI1Deck, choosenCardPlayer); // removes the stolen card // not implemented since not returned
		}
		return playPlayerDeck;
	}


	private static String[] playAI1DeckPairOpponent(String[] playAI1Deck, int confirmPairAI1, String[] opponentDeck, int choosenCardIndexAI1, String[] pairedCards) {
		
		if (confirmPairAI1 == 99) {
			System.out.println("There is no pair found.");
			return playAI1Deck;
		} else {
			boolean pairFound = false;
			for (int i = 0; i < pairedCards.length; i++) {
				if (pairedCards[i] == null) {
					pairedCards[i] = opponentDeck[choosenCardIndexAI1]; // saves the pair from the oppponent deck to paired array
					pairedCards[i + 1] = playAI1Deck[confirmPairAI1];  // save the pair from AI 1 own deck to paired array
					pairFound = true;
					break;
				}
			}
			if (!pairFound) {
				System.out.println("No space to store more pairs.");
				return playAI1Deck;
			}
			playAI1Deck = removeCard(playAI1Deck, confirmPairAI1); // removes the card from the AI 1 deck, the stealer
			opponentDeck = removeCard(opponentDeck, choosenCardIndexAI1); // removes the stolen card
		}
		return playAI1Deck;
	}


	private static String[] playAI2DeckPairOpponent(String[] playAI2Deck, int confirmPairAI2, String[] opponentDeck, int choosenCardIndexAI2, String[] pairedCards) {
		
		if (confirmPairAI2 == 99) {
			System.out.println("There is no pair found.");
			return playAI2Deck;
		} else {
			boolean pairFound = false;
			for (int i = 0; i < pairedCards.length; i++) {
				if (pairedCards[i] == null) {
					pairedCards[i] = opponentDeck[choosenCardIndexAI2]; // saves the pair from the oppponent deck to paired array
					pairedCards[i + 1] = playAI2Deck[confirmPairAI2];  // save the pair from AI 1 own deck to paired array
					pairFound = true;
					break;
				}
			}
			if (!pairFound) {
				System.out.println("No space to store more pairs.");
				return playAI2Deck;
			}
			playAI2Deck = removeCard(playAI2Deck, confirmPairAI2); // removes the card from the AI 2 deck, the stealer
			opponentDeck = removeCard(opponentDeck, choosenCardIndexAI2); // removes the stolen card
		}
		return playAI2Deck;
	}

	private static void printTheArray(String[] theArray) {
		//for (int i = 0; i < theArray.length; i++) {
			//if (i/4==0) {
			//	System.out.println(theArray[i]);
			//} else {
			//System.out.print(theArray[i] + "\t");
			//}
			//System.out.print(theArray[i] + ",\t");
			System.out.println(Arrays.toString(theArray));
		//}
	}



	/*  CLEAN PANEL WITH LOADING TEXTS  */
	private static void scuffedPanelCleaner() {
		timer400(); System.out.print("\n\n\n\n\n\n\n L\tL\tL\tL\tL");  timer400(); System.out.print("\n O\tO\tO\tO\tO");  timer400(); System.out.print("\n A\tA\tA\tA\tA");  timer400(); System.out.print("\n D\tD\tD\tD\tD"); timer400(); System.out.print("\n I\tI\tI\tI\tI"); timer400(); System.out.print("\n N\tN\tN\tN\tN"); timer400(); System.out.print("\n G\tG\tG\tG\tG\n"); 
		timer400(); System.out.println("\n P\tP\tP\tP\tP"); timer400(); System.out.println(" L\tL\tL\tL\tL"); timer700(); System.out.println(" E\tE\tE\tE\tE"); timer700(); System.out.println(" A\tA\tA\tA\tA"); timer700(); System.out.println(" S\tS\tS\tS\tS"); timer700(); System.out.println(" E\tE\tE\tE\tE");
		timer700(); System.out.println("\n W\tW\tW\tW\tW"); timer700(); System.out.println(" A\tA\tA\tA\tA"); timer900(); System.out.println(" I\tI\tI\tI\tI");  timer900(); System.out.println(" T\tT\tT\tT\tT"); 
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); timer400(); System.out.println("\n\n\n\n\n"); timer400();
	} /* WORKING */

	private static void cleanPanel() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	/*  ERROR MESSAGE REGARDING INVALID INPUT VALUE  */
	private static void errorInputInvalidValue() {
		String redColor = "\u001B[31m";		String resetColor = "\u001B[0m";
		System.out.println(redColor + "\n\n\n____________________________________________\n\n");
		System.out.println("\tPlease enter a valid value...");
		System.out.println("\n____________________________________________\n\n" + resetColor);
	} /* WORKING */

	/*  TIMERS  */
	/* BAWaS 2 ZERO so kulang dlwa */
	private static void timer400() {
		/* Timer for 0.4 seconds */
		try {
			Thread.sleep(000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void timer700() {
		/* Timer for 0.7 seconds */
		try {
			Thread.sleep(000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void timer900() {
		/* Timer for 0.9 seconds */
		try {
			Thread.sleep(000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void timer1500() {
		    /* Timer for 1.5 seconds */
		try {
            Thread.sleep(000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	private static void timer1800() {
		/* Timer for 1.8 seconds */
		try {
			Thread.sleep(0000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void timer2900() {
		/* Timer for 2.9 seconds */
		try {
			Thread.sleep(0000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void timer3400() {
		/* Timer for 3.4 seconds */
		try {
			Thread.sleep(0000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void timer5000() {
		/* Timer for 5.0 seconds */
		try {
			Thread.sleep(0000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void whiteLine() {
		System.out.println("________________________________________________________________________________________________________________________________\n\n");
	}

	private static void announceOrderOfPlayers(String[] order){
		String greenColor = "\u001B[32m"; String yellowColor = "\u001B[33m"; String redColor = "\u001B[31m"; String resetColor = "\u001B[0m";
		System.out.println("The order of picking is:");
		System.out.println(greenColor + order[0] + resetColor + " Goes First\n" + yellowColor + order[1] + resetColor + " is Next\n" + redColor + order[2] + resetColor + " is Last\n");
		System.out.println("________________________________________________________________________________________________________________________________\n\n");
	}

	   public static void promptEnterKey(){
        System.out.print("\nPress \"ENTER\" to Continue:");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void promptToStart(){
        System.out.print("Enter any keys to start the game: ");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	public static void main(String[] args) {
		Random dice = new Random();
		Scanner scan = new Scanner(System.in);
		String greenColor = "\u001B[32m"; String lightGreenColor = "\u001B[92m"; String yellowColor = "\u001B[33m"; String redColor = "\u001B[31m"; String resetColor = "\u001B[0m";
		File coinDropping = new File("soundEffects/coinDropping.wav");




		timer1500();

		System.out.println("\n\n\n \t Welcome to Monkey Game \n\n");

		timer1500();
		
		//startMonkey();

		/* Problem B or 2 */
			/* Array's of the contents of the Deck of Cards */
		String spades = "\u2660"; // ♠
		String hearts = "\u2665"; // ♥
		String diamonds = "\u2666"; // ♦
		String clubs = "\u2663"; // ♣
		String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

			/* Generate Original Deck of Cards */
		String[] unOfficialPlayDeck = generateCards(suits, ranks);
			/* WORKING */
		/* Problem B or 2 */


		/*  Problem C or 3 */
			/* Shuffles the originaly created deck of cards */
		System.out.println("\nShuffled array\n");
		MonkeyGame.shuffle(unOfficialPlayDeck);
		//timer400();
		System.out.println(Arrays.toString(unOfficialPlayDeck));
		//timer1500();
			/* Shows Amount of Cards in the deck */
		System.out.println("\nCurrent Number of Cards " + unOfficialPlayDeck.length + "\n");
			/* CHECKS IF THERE IS A DUPLICATE IN THE SHUFFLED ARRAY */
		checkDuplicatesGenertedDeck(unOfficialPlayDeck);
			/* WORKING */
		/* Problem C or 3 */

		timer3400();

		/* Problem D or 4 */
			/* Generate the card to be removed */
		int cardIndexToBeRemoved = cardIndexToBeRemoved();
		String[] cardRemovedFromDeck = new String[1];
		cardRemovedFromDeck[0] = unOfficialPlayDeck[cardIndexToBeRemoved];
			/* WORKING */
		/* Problem D or 4 */
	
		//cleans the panel
		cleanPanel();
		/*  Problem E or 5 */
			/* Removes the randomly picked card from the deck of cards */
			/* UNSHUFFLED OFFICIAL DECK  */
		System.out.println("\nUNSHUFFLED OFFICIAL DECK \n");
		timer400();
		String[] officialPlayDeck = (randomlyRemoveACard(unOfficialPlayDeck,cardIndexToBeRemoved));
		System.out.println(Arrays.toString(officialPlayDeck));

		timer1800();

		System.out.println("\nCurrent Number of Cards " + officialPlayDeck.length);

		timer2900();

		System.out.println("\nPicking a random card to hide..........");

		timer2900();

		System.out.println("\nThe card " + cardRemovedFromDeck[0] + " was removed from the deck with the index of " + cardIndexToBeRemoved);
		checkDuplicatesPlayDeck(officialPlayDeck); timer1500();
			/* WORKING */
		/* Problem E or 5 */


		/* Problem F or 6 */
			/* SHUFFLED OFFICIAL DECK  */
		System.out.println("\nSHUFFLED OFFICIAL DECK \n");
		timer900();
		MonkeyGame.shuffle(officialPlayDeck);
		System.out.println(Arrays.toString(officialPlayDeck));

		timer1800();

		System.out.println("\nCurrent Number of Cards " + officialPlayDeck.length);
		checkDuplicatesPlayDeck(officialPlayDeck);
			/* WORKING */
		/* Problem F or 6 */



		/* Problem G or 7 */
			
		    /* PLAYER DECK */
        String[] playerDeck = distributeCardsToPlayer(officialPlayDeck);
        System.out.println("\nThis is the player deck of cards containing " + playerDeck.length + " cards.");
        System.out.println(Arrays.toString(playerDeck)); // Display the deck

        	/* AI 1 DECK */
        String[] firstAIDeck = distributeCardsToFirstAI(officialPlayDeck);
        System.out.println("\nThis is the first ai deck of cards containing " + firstAIDeck.length + " cards.");
        System.out.println(Arrays.toString(firstAIDeck)); // Display the deck

       		/* AI 2 DECK */
        String[] secondAIDeck = distributeCardsToSecondAI(officialPlayDeck);
        System.out.println("\nThis is the second ai deck of cards containing " + secondAIDeck.length + " cards.");
        System.out.println(Arrays.toString(secondAIDeck)); // Display the deck

		/* Problem G or 7 */

		
		

	
		/* Problem H or 8 */

		String[] playerDeckPaired = pairCards(playerDeck); System.out.println("\n\nThe following cards was paired from the Player Deck: " + Arrays.toString(playerDeckPaired)); timer3400();
		String[] firstAIDeckPaired = pairCards(firstAIDeck); System.out.println("\n\nThe following cards was paired from the AI1 Deck: " + Arrays.toString(firstAIDeckPaired)); timer3400();
		String[] secondAIDeckPaired = pairCards(secondAIDeck); System.out.println("\n\nThe following cards was paired from the AI2 Deck: " + Arrays.toString(secondAIDeckPaired)); timer3400();
		int triDeck = playerDeckPaired.length + firstAIDeckPaired.length + secondAIDeckPaired.length;
		String[] pairedInitialDeck = new String[triDeck];
		pairedInitialDeck = storesTheInitialPairedCards(playerDeckPaired,firstAIDeckPaired,secondAIDeckPaired);

		System.out.println("\n\n\nThis is the deck of all the paired cards: " + Arrays.toString(pairedInitialDeck));
			
		/* Problem H or 8 */

		

		

		/* Problem I or 9 */
		String[] playPlayerDeck = removesTheInitialPairedCards(playerDeckPaired, playerDeck); 
		String[] playAI1Deck = removesTheInitialPairedCards(firstAIDeckPaired, firstAIDeck);
		String[] playAI2Deck = removesTheInitialPairedCards(secondAIDeckPaired, secondAIDeck);
		
		System.out.println("\n\n This is the cleaned play deck of Player: " + Arrays.toString(playPlayerDeck)); timer3400();
		System.out.println("\n\n This is the cleaned play deck of AI1: " + Arrays.toString(playAI1Deck)); timer3400();
		System.out.println("\n\n This is the cleaned play deck of AI2: " + Arrays.toString(playAI2Deck)); timer3400();

		/* Problem I or 9 */

		/* CLEANS THE TERMNAL */
		cleanPanel();


		//System.out.println("\n Hides the card \n" + Arrays.toString(hidesOpponentCards(secondAIDeck)));

		/* GAME */
		
	 
		boolean gamePlaying = true;
		//timer2900();
		int counter = 0;
		String[] pairedCards = new String[50]; // array of pairedCaards
		
		/*  MOVES THE PAIRED CARDS TO OFFICIAL PAIRED DECK  */
		for (int i = 0; i < pairedInitialDeck.length; i++) {
			pairedCards[i] = pairedInitialDeck[i];
		}
		
		// testing
		//for (int i = 0; i < pairedCards.length;  i++) {
		//	if (pairedCards[i] == null ) {
		//		System.out.println("The Index " + i + " is emmpty");
		//	} else {
		//		System.out.println("Not Empty" + pairedCards[i]);
		//	}
		//}

		whiteLine();

		/*  END THE GAME  */
		if (pairedCards[49] != null){
			gamePlaying = false; // stops the game after all cards is paired up,
		} else {				 // except the last card and the hidden card.
			gamePlaying = true;
		}


		String [] order = tossCoin(getCoinSideChoice());
		/*  FOR DEBUGGING THE GAME FLOW  */
			/* PLAYER FIRST */
		//String[] order = {"Player", "AI1", "AI2"}; 
		//String[] order = {"Player", "AI2", "AI1"}; 
			/* AI 1 FIRST */
		//String[] order = {"AI1", "Player", "AI2"}; 
		//String[] order = {"AI1", "AI2", "Player"};
			/* AI 2 FIRST */
		//String[] order = {"AI2","Player","AI1"}; 	
		//String[] order = {"AI2","Player","AI1"}; 



		announceOrderOfPlayers(order);
		
		startMonkey();

		/*  LOOPS THE GAME UNTIL ONLY CARD REMAIN  */
		while (gamePlaying) {
			String[] overAllWinner = new String[1];

			/*  PLAYER FIRST  */ 
			if (order[0].equals("Player")) { 
				

				/*  PLAYER FIRST  */ /* First Condition */
				if (order[1].equals("AI1")) { 
					
					while (gamePlaying) { /* PLAYER-AI1-AI2 */
						int choosenCardPlayer = 0,choosenCardIndexAI1,choosenCardIndexAI2,noPair; // initialize

						
						/* Player Wins */ /* Start of Player Turn */
						overAllWinner = isPlayerWinner(playPlayerDeck,overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}
						
						// PLAYER STEALS FROM AI 1
						int choosenDeckPlayer = 1; // PICKS FROM AI 1						
						String[] playerTakenCard = new String[1];

						try {
								// stores the number of the picked card
							choosenCardPlayer = playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck, playPlayerDeck);
								// makes sures it is not more than the max lenght of the array
							if (choosenCardPlayer < playAI1Deck.length) {
								playerTakenCard[0] = playAI1Deck[choosenCardPlayer];
							} else {
								errorInputInvalidValue();
								// kinuha uli kuha ng user idk, time check 1:21 am 
								choosenCardPlayer = playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck, playPlayerDeck);
								// Check again if valid na 
								if (choosenCardPlayer < playAI1Deck.length) {
									playerTakenCard[0] = playAI1Deck[choosenCardPlayer]; // Player Steal from AI 1
								}
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							errorInputInvalidValue(); 
						}
						timer900(); whiteLine(); timer400();
						System.out.println("The card taken from AI 1 was " + playerTakenCard[0]);
						
						int confirmPairPlayer = 99; // default value for no pair
						
							// check if paired or nahh
						confirmPairPlayer = checksIfThereIsPairPlayer(playPlayerDeck, playerTakenCard);
						
						
						noPair = 99; // to check if no pair or yea
						
						if (confirmPairPlayer != noPair) { // if yes pair
							timer1500();
							System.out.println("\nThe card " + playerTakenCard[0] + " was taken, and it paired with the " + playPlayerDeck[confirmPairPlayer] + " in Player current deck.\n");
							playPlayerDeck = playPlayerDeckPairAI(playPlayerDeck, confirmPairPlayer, playAI1Deck, choosenCardPlayer, pairedCards);
							whiteLine(); promptEnterKey();

							yellowLineT();
							playAI1Deck = removeCard(playAI1Deck, choosenCardPlayer);// remove the stolen card
							System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
							yellowLineB(); promptEnterKey();System.out.println("\n");


						} else { // if no pair
							playPlayerDeck = addTakenCard(playPlayerDeck, playerTakenCard[0]); // Add the card to picker deck
							playAI1Deck = removeCard(playAI1Deck,choosenCardPlayer); // Remove the card from the picked  deck
							System.out.println("\nThere was no pair found. :<\n"); whiteLine(); 
							System.out.print("Your Current cards: "); printTheArray(playPlayerDeck); promptEnterKey(); System.out.println(""); whiteLine(); 
						} // END OF PLAYER TURN

						/* Player Wins */
						overAllWinner = isPlayerWinner(playPlayerDeck,overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						/* AI 1 Wins */ /* Start of AI 1 Turn */
						overAllWinner = isAI1Winner(playAI1Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}
						
						turnAI1();

						// AI1 STEALS AI2
						choosenCardIndexAI1 = dice.nextInt(playAI2Deck.length);

						whiteLine();
						System.out.println("\nThis is the AI 1 current deck: "); hidesOpponentCards(playAI1Deck); // Prints the current deck of picker

						System.out.println("\nThis is the AI 2 current deck: "); hidesOpponentCards(playAI1Deck); // Prints the current deck of picked from
						
							// the card taken
						String[] choosenCardAI1 = new String[1];
						choosenCardAI1[0] = playAI2Deck[choosenCardIndexAI1]; // AI 1 Steals from opponent
						whiteLine();
						System.out.println("The card taken from AI 2 was " + choosenCardAI1[0] + "\n"); // Displays the card taken
						int confirmPairAI1 = 99; whiteLine();
						System.out.print("\nAI 1 current cards: \n"); hidesOpponentCards(playAI1Deck); timer900();
						confirmPairAI1 = checksIfThereIsPairAI1(playAI1Deck, choosenCardAI1);

						noPair = 99; // to check if no pair or yea

						if (confirmPairAI1 != noPair) { // if yes pair
							System.out.println("There was a pair!!!"); timer900(); // the card taken from opponent paired with AI1
							System.out.println("\nThe card " + choosenCardAI1[0] + " and it paired with the " + playAI1Deck[confirmPairAI1] + " in AI 1 current deck.\n\n");
							whiteLine(); timer1500();
							playAI1Deck = playAI1DeckPairOpponent(playAI1Deck, confirmPairAI1, playAI2Deck, choosenCardIndexAI1, pairedCards);
							playAI2Deck = removeCard(playAI2Deck, choosenCardIndexAI1); // removes the stolen card
							System.out.println("Current cards of AI 1 after pairing: \n" ); hidesOpponentCards(playAI1Deck); whiteLine();
							timer1500();

							timer1500(); yellowLineT(); // Prints the current paired card
							System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
							yellowLineB(); timer900();

						} else { // if no pair
							playAI1Deck = addTakenCard(playAI1Deck, choosenCardAI1[0]); // Add the card to AI 1 deck
							playAI2Deck = removeCard(playAI2Deck,choosenCardIndexAI1); // Remove the card from the deck it is picked from
							System.out.println("\nThere was no pair found. :<\n"); whiteLine();
							yellowLineT();
							System.out.print("AI 1 Current cards: \n"); hidesOpponentCards(playAI1Deck); // hide the cards after debug
							yellowLineB();
						} // END OF AI 1 TURN					

						/* AI 1 Wins */
						overAllWinner = isAI1Winner(playAI1Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						/* AI 2 Wins */ /* Start of AI 2 Turn */
						overAllWinner = isAI2Winner(playAI2Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						turnAI2();

						whiteLine();
						System.out.println("\nThis is the AI 2 current deck: "); hidesOpponentCards(playAI2Deck); // Prints the current deck picker
						System.out.println("This is Player current deck: "  + Arrays.toString(playPlayerDeck)); // Prints the deck of picked
						
						/*  AI 2 STEALS FROM PLAYER  */
						choosenCardIndexAI2 = dice.nextInt(playPlayerDeck.length);
  
						// the card taken
						String[] choosenCardAI2 = new String[1];
						choosenCardAI2[0] = playPlayerDeck[choosenCardIndexAI2]; // ninakawan ng card
						System.out.println("\nThe card taken from Player was " + choosenCardAI2[0] + "\n"); // Displays the card taken
						int confirmPairAI2 = 99; whiteLine();
						System.out.print("\nAI 2 current cards: \n"); hidesOpponentCards(playAI2Deck); timer900();
						confirmPairAI2 = checksIfThereIsPairAI2(playAI2Deck, choosenCardAI2); // check if there is a pair in the card

						noPair = 99; // to check if no pair or yea
						
						if (confirmPairAI2 != noPair) { // if yes pair
							System.out.println("\nThere was a pair found!!!"); // informs that there is a pair
							System.out.println("\nThe card " + choosenCardAI2[0] + " and it paired with " + playAI2Deck[confirmPairAI2] + " in AI 2 current deck.");
							playAI2Deck = playAI2DeckPairOpponent(playAI2Deck, confirmPairAI2, playPlayerDeck, choosenCardIndexAI2, pairedCards);
							playPlayerDeck = removeCard(playPlayerDeck, choosenCardIndexAI2); // removes the stolen card
							System.out.println("\nCurrent cards of AI 2 are : " ); hidesOpponentCards(playAI2Deck); whiteLine();
							yellowLineT();
							System.out.println("This is the current paired cards \n"); printTheArray(pairedCards); 
							yellowLineB();

						} else { // if no pair
							Arrays.toString(playAI2Deck);
							playAI2Deck = addTakenCard(playAI2Deck, choosenCardAI2[0]); // Add the card to AI 2 deck
							playPlayerDeck = removeCard(playPlayerDeck,choosenCardIndexAI2); // Remove the card from the deck it is picked
							System.out.println("\nThere was no pair found. :<\n"); whiteLine();
							System.out.print("AI 2 Current cards: "); hidesOpponentCards(playAI2Deck); System.out.println("\n\n\n");
						} //END OF AI 2 TURN

						/* AI 2 Wins */ 
						overAllWinner = isAI2Winner(playAI2Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						// WHILE LOOP/GAME END
					}						
					System.out.println("The winner was " + overAllWinner[0] + ". Since they were the first one to have no cards left");
					// 1st Condition end // Player 1st


				/* PLAYER FIRST */ /* Second Condition */
				} else if (order[1].equals("AI2")) { 
				
					while (gamePlaying) { /* PLAYER, AI2, AI1 */
						int choosenCardPlayer = 0,choosenCardIndexAI1,choosenCardIndexAI2,noPair; // initialize
						
						/* Player Wins */ /* Start of Player Turn */
						overAllWinner = isPlayerWinner(playPlayerDeck,overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}
						
						// PLAYER STEALS FROM AI 2
						int choosenDeckPlayer = 2; // PICKS FROM AI 2						
						String[] playerTakenCard = new String[1];

						try {
								// stores the number of the picked card
							choosenCardPlayer = playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck, playPlayerDeck);
								// makes sures it is not more than the max lenght of the array
							if (choosenCardPlayer < playAI2Deck.length) {
								playerTakenCard[0] = playAI2Deck[choosenCardPlayer];
							} else {
								errorInputInvalidValue();
								// kinuha uli kuha ng user idk, time check 1:21 am 
								choosenCardPlayer = playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck, playPlayerDeck);
								// Check again if valid na 
								if (choosenCardPlayer < playAI2Deck.length) {
									playerTakenCard[0] = playAI2Deck[choosenCardPlayer]; // Player Steal from AI 2
								}
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							errorInputInvalidValue(); 
						}
						timer900(); whiteLine(); timer400();
						System.out.println("The card taken from AI 2 was " + playerTakenCard[0]);
						
						int confirmPairPlayer = 99; // default value for no pair
						
							// check if paired or nahh
						confirmPairPlayer = checksIfThereIsPairPlayer(playPlayerDeck, playerTakenCard);
						
						
						noPair = 99; // to check if no pair or yea
						
						if (confirmPairPlayer != noPair) { // if yes pair
							timer1500();
							System.out.println("\nThe card " + playerTakenCard[0] + " was taken, and it paired with the " + playPlayerDeck[confirmPairPlayer] + " in Player current deck.\n");
							playPlayerDeck = playPlayerDeckPairAI(playPlayerDeck, confirmPairPlayer, playAI2Deck, choosenCardPlayer, pairedCards);
							whiteLine(); promptEnterKey();

							yellowLineT();
							playAI2Deck = removeCard(playAI2Deck, choosenCardPlayer);// remove the stolen card
							System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
							yellowLineB(); promptEnterKey();System.out.println("\n");


						} else { // if no pair
							playPlayerDeck = addTakenCard(playPlayerDeck, playerTakenCard[0]); // Add the card to picker deck
							playAI2Deck = removeCard(playAI2Deck,choosenCardPlayer); // Remove the card from the picked  deck
							System.out.println("\nThere was no pair found. :<\n"); whiteLine(); 
							System.out.print("Your Current cards: "); printTheArray(playPlayerDeck); promptEnterKey(); System.out.println(""); whiteLine(); 
						} // END OF PLAYER TURN

						/* Player Wins */
						overAllWinner = isPlayerWinner(playPlayerDeck,overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}
						
						/* AI 2 Wins */ /* Start of AI 2 Turn */
						overAllWinner = isAI2Winner(playAI2Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						turnAI2();

						whiteLine();
						System.out.println("\nThis is the AI 2 current deck: "); hidesOpponentCards(playAI2Deck); // Prints the current deck picker
						System.out.println("This is AI 1 current deck: \n" ); hidesOpponentCards(playAI1Deck); // Prints the deck of picked // hide this after
						
						/*  AI 2 STEALS FROM AI 1  */
						choosenCardIndexAI2 = dice.nextInt(playAI1Deck.length);
  
						// the card taken
						String[] choosenCardAI2 = new String[1];
						choosenCardAI2[0] = playAI1Deck[choosenCardIndexAI2]; // ninakawan ng card
						System.out.println("\nThe card taken from AI 1 was " + choosenCardAI2[0] + "\n"); // Displays the card taken
						int confirmPairAI2 = 99; whiteLine();
						System.out.print("\nAI 2 current cards: \n"); hidesOpponentCards(playAI2Deck); timer900();
						confirmPairAI2 = checksIfThereIsPairAI2(playAI2Deck, choosenCardAI2); // check if there is a pair in the card

						noPair = 99; // to check if no pair or yea
						
						if (confirmPairAI2 != noPair) { // if yes pair
							System.out.println("\nThere was a pair found!!!"); // informs that there is a pair
							System.out.println("\nThe card " + choosenCardAI2[0] + " and it paired with " + playAI2Deck[confirmPairAI2] + " in AI 2 current deck.");
							playAI2Deck = playAI2DeckPairOpponent(playAI2Deck, confirmPairAI2, playAI1Deck, choosenCardIndexAI2, pairedCards);
							playAI1Deck = removeCard(playAI1Deck, choosenCardIndexAI2); // removes the stolen card
							System.out.println("\nCurrent cards of AI 2 are : " ); hidesOpponentCards(playAI2Deck); whiteLine();
							yellowLineT();
							System.out.println("This is the current paired cards \n"); printTheArray(pairedCards); 
							yellowLineB();

						} else { // if no pair
							Arrays.toString(playAI2Deck);
							playAI2Deck = addTakenCard(playAI2Deck, choosenCardAI2[0]); // Add the card to AI 2 deck
							playAI1Deck = removeCard(playAI1Deck,choosenCardIndexAI2); // Remove the card from the deck it is picked
							System.out.println("\nThere was no pair found. :<\n"); whiteLine();
							System.out.print("AI 2 Current cards: "); hidesOpponentCards(playAI2Deck); System.out.println("\n\n\n");
						} //END OF AI 2 TURN

						/* AI 2 Wins */
						overAllWinner = isAI2Winner(playAI2Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						/* AI 1 Wins */ /* Start of AI 1 Turn */
						overAllWinner = isAI1Winner(playAI1Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}
						
						turnAI1();

						// AI1 STEALS PLAYER
						choosenCardIndexAI1 = dice.nextInt(playPlayerDeck.length);

						whiteLine();
						System.out.println("\nThis is the AI 1 current deck: "); hidesOpponentCards(playAI1Deck); // Prints the current deck of picker

						System.out.println("\nThis is the Player current deck: "); printTheArray(playPlayerDeck); // Prints the current deck of picked from
						
							// the card taken
						String[] choosenCardAI1 = new String[1];
						choosenCardAI1[0] = playPlayerDeck[choosenCardIndexAI1]; // AI 1 Steals from opponent
						whiteLine();
						System.out.println("The card taken from Player was " + choosenCardAI1[0] + "\n"); // Displays the card taken
						int confirmPairAI1 = 99; whiteLine();
						System.out.print("\nAI 1 current cards: \n"); hidesOpponentCards(playAI1Deck); timer900();
						confirmPairAI1 = checksIfThereIsPairAI1(playAI1Deck, choosenCardAI1);

						noPair = 99; // to check if no pair or yea

						if (confirmPairAI1 != noPair) { // if yes pair
							System.out.println("There was a pair!!!"); timer900(); // the card taken from opponent paired with AI1
							System.out.println("\nThe card " + choosenCardAI1[0] + " and it paired with the " + playAI1Deck[confirmPairAI1] + " in AI 1 current deck.\n\n");
							whiteLine(); timer1500();
							playAI1Deck = playAI1DeckPairOpponent(playAI1Deck, confirmPairAI1, playPlayerDeck, choosenCardIndexAI1, pairedCards);
							playPlayerDeck = removeCard(playPlayerDeck, choosenCardIndexAI1); // removes the stolen card
							System.out.println("Current cards of AI 1 after pairing: " ); hidesOpponentCards(playAI1Deck); whiteLine();
							timer1500();

							timer1500(); yellowLineT(); // Prints the current paired card
							System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
							yellowLineB(); timer900();

						} else { // if no pair
							playAI1Deck = addTakenCard(playAI1Deck, choosenCardAI1[0]); // Add the card to AI 1 deck
							playPlayerDeck = removeCard(playPlayerDeck,choosenCardIndexAI1); // Remove the card from the deck it is picked from
							System.out.println("\nThere was no pair found. :<\n"); whiteLine();
							yellowLineT();
							System.out.print("AI 1 Current cards: \n"); hidesOpponentCards(playAI1Deck); // hide the cards after debug
							yellowLineB();
						} // END OF AI 1 TURN
						
						/* AI 1 Wins */
						overAllWinner = isAI1Winner(playAI1Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						// WHILE LOOP/GAME END
					}		
					
					// 2nd condition end // The player is 1st end
				}

				/*  AI 1 FIRST  */
			} else if (order[0].equals("AI1")) {
				

				/*  AI 1 FIRST  */ /* First Condition */
				if(order[1].equals("Player")) { /* AI1, PLAYER, AI2 */
					while (gamePlaying) {
						int choosenCardPlayer = 0,choosenCardIndexAI1,choosenCardIndexAI2,noPair; // initialize

						/* AI 1 Wins */ /* Start of AI 1 Turn */
						overAllWinner = isAI1Winner(playAI1Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}
						
						turnAI1();

						// AI1 STEALS PLAYER
						choosenCardIndexAI1 = dice.nextInt(playPlayerDeck.length);

						whiteLine();
						System.out.println("\nThis is the AI 1 current deck: "); hidesOpponentCards(playAI1Deck); // Prints the current deck of picker

						System.out.println("\nThis is the Player current deck: "); printTheArray(playPlayerDeck); // Prints the current deck of picked from
						
							// the card taken
						String[] choosenCardAI1 = new String[1];
						choosenCardAI1[0] = playPlayerDeck[choosenCardIndexAI1]; // AI 1 Steals from opponent
						whiteLine();
						System.out.println("The card taken from Player was " + choosenCardAI1[0] + "\n"); // Displays the card taken
						int confirmPairAI1 = 99; whiteLine();
						System.out.print("\nAI 1 current cards: \n"); hidesOpponentCards(playAI1Deck); timer900();
						confirmPairAI1 = checksIfThereIsPairAI1(playAI1Deck, choosenCardAI1);

						noPair = 99; // to check if no pair or yea

						if (confirmPairAI1 != noPair) { // if yes pair
							System.out.println("There was a pair!!!"); timer900(); // the card taken from opponent paired with AI1
							System.out.println("\nThe card " + choosenCardAI1[0] + " and it paired with the " + playAI1Deck[confirmPairAI1] + " in AI 1 current deck.\n\n");
							whiteLine(); timer1500();
							playAI1Deck = playAI1DeckPairOpponent(playAI1Deck, confirmPairAI1, playPlayerDeck, choosenCardIndexAI1, pairedCards);
							playPlayerDeck = removeCard(playPlayerDeck, choosenCardIndexAI1); // removes the stolen card
							System.out.println("Current cards of AI 1 after pairing: " ); hidesOpponentCards(playAI1Deck); whiteLine();
							timer1500();

							timer1500(); yellowLineT(); // Prints the current paired card
							System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
							yellowLineB(); timer900();

						} else { // if no pair
							playAI1Deck = addTakenCard(playAI1Deck, choosenCardAI1[0]); // Add the card to AI 1 deck
							playPlayerDeck = removeCard(playPlayerDeck,choosenCardIndexAI1); // Remove the card from the deck it is picked from
							System.out.println("\nThere was no pair found. :<\n"); whiteLine();
							yellowLineT();
							System.out.print("AI 1 Current cards: \n"); hidesOpponentCards(playAI1Deck); // hide the cards after debug
							yellowLineB();
						} // END OF AI 1 TURN
						
						/* AI 1 Wins */
						overAllWinner = isAI1Winner(playAI1Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						/* Player Wins */ /* Start of Player Turn */
						overAllWinner = isPlayerWinner(playPlayerDeck,overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}
						
						// PLAYER STEALS FROM AI 2
						int choosenDeckPlayer = 2; // PICKS FROM AI 2						
						String[] playerTakenCard = new String[1];

						try {
								// stores the number of the picked card
							choosenCardPlayer = playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck, playPlayerDeck);
								// makes sures it is not more than the max lenght of the array
							if (choosenCardPlayer < playAI2Deck.length) {
								playerTakenCard[0] = playAI2Deck[choosenCardPlayer];
							} else {
								errorInputInvalidValue();
								// kinuha uli kuha ng user idk, time check 1:21 am 
								choosenCardPlayer = playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck, playPlayerDeck);
								// Check again if valid na 
								if (choosenCardPlayer < playAI2Deck.length) {
									playerTakenCard[0] = playAI2Deck[choosenCardPlayer]; // Player Steal from AI 2
								}
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							errorInputInvalidValue(); 
						}
						timer900(); whiteLine(); timer400();
						System.out.println("The card taken from AI 2 was " + playerTakenCard[0]);
						
						int confirmPairPlayer = 99; // default value for no pair
						
							// check if paired or nahh
						confirmPairPlayer = checksIfThereIsPairPlayer(playPlayerDeck, playerTakenCard);
						
						
						noPair = 99; // to check if no pair or yea
						
						if (confirmPairPlayer != noPair) { // if yes pair
							timer1500();
							System.out.println("\nThe card " + playerTakenCard[0] + " was taken, and it paired with the " + playPlayerDeck[confirmPairPlayer] + " in Player current deck.\n");
							playPlayerDeck = playPlayerDeckPairAI(playPlayerDeck, confirmPairPlayer, playAI2Deck, choosenCardPlayer, pairedCards);
							whiteLine(); promptEnterKey();

							yellowLineT();
							playAI2Deck = removeCard(playAI2Deck, choosenCardPlayer);// remove the stolen card
							System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
							yellowLineB(); promptEnterKey();System.out.println("\n");


						} else { // if no pair
							playPlayerDeck = addTakenCard(playPlayerDeck, playerTakenCard[0]); // Add the card to picker deck
							playAI2Deck = removeCard(playAI2Deck,choosenCardPlayer); // Remove the card from the picked  deck
							System.out.println("\nThere was no pair found. :<\n"); whiteLine(); 
							System.out.print("Your Current cards: "); printTheArray(playPlayerDeck); promptEnterKey(); System.out.println(""); whiteLine(); 
						} // END OF PLAYER TURN

						/* Player Wins */
						overAllWinner = isPlayerWinner(playPlayerDeck,overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						/* AI 2 Wins */ /* Start of AI 2 Turn */
						overAllWinner = isAI2Winner(playAI2Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						turnAI2();

						whiteLine();
						System.out.println("\nThis is the AI 2 current deck: "); hidesOpponentCards(playAI2Deck); // Prints the current deck picker
						System.out.println("This is AI 1 current deck: \n" ); hidesOpponentCards(playAI1Deck); // Prints the deck of picked // hide this after
						
						/*  AI 2 STEALS FROM AI 1  */
						choosenCardIndexAI2 = dice.nextInt(playAI1Deck.length);
  
						// the card taken
						String[] choosenCardAI2 = new String[1];
						choosenCardAI2[0] = playAI1Deck[choosenCardIndexAI2]; // ninakawan ng card
						System.out.println("\nThe card taken from AI 1 was " + choosenCardAI2[0] + "\n"); // Displays the card taken
						int confirmPairAI2 = 99; whiteLine();
						System.out.print("\nAI 2 current cards: \n"); hidesOpponentCards(playAI2Deck); timer900();
						confirmPairAI2 = checksIfThereIsPairAI2(playAI2Deck, choosenCardAI2); // check if there is a pair in the card

						noPair = 99; // to check if no pair or yea
						
						if (confirmPairAI2 != noPair) { // if yes pair
							System.out.println("\nThere was a pair found!!!"); // informs that there is a pair
							System.out.println("\nThe card " + choosenCardAI2[0] + " and it paired with " + playAI2Deck[confirmPairAI2] + " in AI 2 current deck.");
							playAI2Deck = playAI2DeckPairOpponent(playAI2Deck, confirmPairAI2, playAI1Deck, choosenCardIndexAI2, pairedCards);
							playAI1Deck = removeCard(playAI1Deck, choosenCardIndexAI2); // removes the stolen card
							System.out.println("\nCurrent cards of AI 2 are : " ); hidesOpponentCards(playAI2Deck); whiteLine();
							yellowLineT();
							System.out.println("This is the current paired cards \n"); printTheArray(pairedCards); 
							yellowLineB();

						} else { // if no pair
							Arrays.toString(playAI2Deck);
							playAI2Deck = addTakenCard(playAI2Deck, choosenCardAI2[0]); // Add the card to AI 2 deck
							playAI1Deck = removeCard(playAI1Deck,choosenCardIndexAI2); // Remove the card from the deck it is picked
							System.out.println("\nThere was no pair found. :<\n"); whiteLine();
							System.out.print("AI 2 Current cards: "); hidesOpponentCards(playAI2Deck); System.out.println("\n\n\n");
						} //END OF AI 2 TURN

						/* AI 2 Wins */
						overAllWinner = isAI2Winner(playAI2Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}
					}
					// end of while

				/*  AI 1 FIRST  */ /* Second Condition */
				} else if (order[1].equals("AI2")) { // AI 1, AI 2, PLAYER
						
						
					while (gamePlaying) {
						int choosenCardPlayer = 0,choosenCardIndexAI1,choosenCardIndexAI2,noPair; // initialize


						/* AI 1 Wins */ /* Start of AI 1 Turn */
						overAllWinner = isAI1Winner(playAI1Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}
						
						turnAI1();

						// AI1 STEALS AI2
						choosenCardIndexAI1 = dice.nextInt(playAI2Deck.length);

						whiteLine();
						System.out.println("\nThis is the AI 1 current deck: "); hidesOpponentCards(playAI1Deck); // Prints the current deck of picker

						System.out.println("\nThis is the AI 2 current deck: "); hidesOpponentCards(playAI1Deck); // Prints the current deck of picked from
						
							// the card taken
						String[] choosenCardAI1 = new String[1];
						choosenCardAI1[0] = playAI2Deck[choosenCardIndexAI1]; // AI 1 Steals from opponent
						whiteLine();
						System.out.println("The card taken from AI 2 was " + choosenCardAI1[0] + "\n"); // Displays the card taken
						int confirmPairAI1 = 99; whiteLine();
						System.out.print("\nAI 1 current cards: \n"); hidesOpponentCards(playAI1Deck); timer900();
						confirmPairAI1 = checksIfThereIsPairAI1(playAI1Deck, choosenCardAI1);

						noPair = 99; // to check if no pair or yea

						if (confirmPairAI1 != noPair) { // if yes pair
							System.out.println("There was a pair!!!"); timer900(); // the card taken from opponent paired with AI1
							System.out.println("\nThe card " + choosenCardAI1[0] + " and it paired with the " + playAI1Deck[confirmPairAI1] + " in AI 1 current deck.\n\n");
							whiteLine(); timer1500();
							playAI1Deck = playAI1DeckPairOpponent(playAI1Deck, confirmPairAI1, playAI2Deck, choosenCardIndexAI1, pairedCards);
							playAI2Deck = removeCard(playAI2Deck, choosenCardIndexAI1); // removes the stolen card
							System.out.println("Current cards of AI 1 after pairing: \n" ); hidesOpponentCards(playAI1Deck); whiteLine();
							timer1500();

							timer1500(); yellowLineT(); // Prints the current paired card
							System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
							yellowLineB(); timer900();

						} else { // if no pair
							playAI1Deck = addTakenCard(playAI1Deck, choosenCardAI1[0]); // Add the card to AI 1 deck
							playAI2Deck = removeCard(playAI2Deck,choosenCardIndexAI1); // Remove the card from the deck it is picked from
							System.out.println("\nThere was no pair found. :<\n"); whiteLine();
							yellowLineT();
							System.out.print("AI 1 Current cards: \n"); hidesOpponentCards(playAI1Deck); // hide the cards after debug
							yellowLineB();
						} // END OF AI 1 TURN					

						/* AI 1 Wins */
						overAllWinner = isAI1Winner(playAI1Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						/*  AI 2 STEALS FROM PLAYER  */
						choosenCardIndexAI2 = dice.nextInt(playPlayerDeck.length);
  
						// the card taken
						String[] choosenCardAI2 = new String[1];
						choosenCardAI2[0] = playPlayerDeck[choosenCardIndexAI2]; // ninakawan ng card
						System.out.println("\nThe card taken from Player was " + choosenCardAI2[0] + "\n"); // Displays the card taken
						int confirmPairAI2 = 99; whiteLine();
						System.out.print("\nAI 2 current cards: \n"); hidesOpponentCards(playAI2Deck); timer900();
						confirmPairAI2 = checksIfThereIsPairAI2(playAI2Deck, choosenCardAI2); // check if there is a pair in the card

						noPair = 99; // to check if no pair or yea
						
						if (confirmPairAI2 != noPair) { // if yes pair
							System.out.println("\nThere was a pair found!!!"); // informs that there is a pair
							System.out.println("\nThe card " + choosenCardAI2[0] + " and it paired with " + playAI2Deck[confirmPairAI2] + " in AI 2 current deck.");
							playAI2Deck = playAI2DeckPairOpponent(playAI2Deck, confirmPairAI2, playPlayerDeck, choosenCardIndexAI2, pairedCards);
							playPlayerDeck = removeCard(playPlayerDeck, choosenCardIndexAI2); // removes the stolen card
							System.out.println("\nCurrent cards of AI 2 are : " ); hidesOpponentCards(playAI2Deck); whiteLine();
							yellowLineT();
							System.out.println("This is the current paired cards \n"); printTheArray(pairedCards); 
							yellowLineB();

						} else { // if no pair
							Arrays.toString(playAI2Deck);
							playAI2Deck = addTakenCard(playAI2Deck, choosenCardAI2[0]); // Add the card to AI 2 deck
							playPlayerDeck = removeCard(playPlayerDeck,choosenCardIndexAI2); // Remove the card from the deck it is picked
							System.out.println("\nThere was no pair found. :<\n"); whiteLine();
							System.out.print("AI 2 Current cards: "); hidesOpponentCards(playAI2Deck); System.out.println("\n\n\n");
						} //END OF AI 2 TURN


						/* Player Wins */ /* Start of Player Turn */
						overAllWinner = isPlayerWinner(playPlayerDeck,overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}
						
						// PLAYER STEALS FROM AI 1
						int choosenDeckPlayer = 1; // PICKS FROM AI 1						
						String[] playerTakenCard = new String[1];

						try {
								// stores the number of the picked card
							choosenCardPlayer = playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck, playPlayerDeck);
								// makes sures it is not more than the max lenght of the array
							if (choosenCardPlayer < playAI1Deck.length) {
								playerTakenCard[0] = playAI1Deck[choosenCardPlayer];
							} else {
								errorInputInvalidValue();
								// kinuha uli kuha ng user idk, time check 1:21 am 
								choosenCardPlayer = playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck, playPlayerDeck);
								// Check again if valid na 
								if (choosenCardPlayer < playAI1Deck.length) {
									playerTakenCard[0] = playAI1Deck[choosenCardPlayer]; // Player Steal from AI 1
								}
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							errorInputInvalidValue(); 
						}
						timer900(); whiteLine(); timer400();
						System.out.println("The card taken from AI 1 was " + playerTakenCard[0]);
						
						int confirmPairPlayer = 99; // default value for no pair
						
							// check if paired or nahh
						confirmPairPlayer = checksIfThereIsPairPlayer(playPlayerDeck, playerTakenCard);
						
						/* Player Wins */
						overAllWinner = isPlayerWinner(playPlayerDeck,overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						noPair = 99; // to check if no pair or yea
						
						if (confirmPairPlayer != noPair) { // if yes pair
							timer1500();
							System.out.println("\nThe card " + playerTakenCard[0] + " was taken, and it paired with the " + playPlayerDeck[confirmPairPlayer] + " in Player current deck.\n");
							playPlayerDeck = playPlayerDeckPairAI(playPlayerDeck, confirmPairPlayer, playAI1Deck, choosenCardPlayer, pairedCards);
							whiteLine(); promptEnterKey();

							yellowLineT();
							playAI1Deck = removeCard(playAI1Deck, choosenCardPlayer);// remove the stolen card
							System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
							yellowLineB(); promptEnterKey();System.out.println("\n");


						} else { // if no pair
							playPlayerDeck = addTakenCard(playPlayerDeck, playerTakenCard[0]); // Add the card to picker deck
							playAI1Deck = removeCard(playAI1Deck,choosenCardPlayer); // Remove the card from the picked  deck
							System.out.println("\nThere was no pair found. :<\n"); whiteLine(); 
							System.out.print("Your Current cards: "); printTheArray(playPlayerDeck); promptEnterKey(); System.out.println(""); whiteLine(); 
						} // END OF PLAYER TURN

						/* Player Wins */
						overAllWinner = isPlayerWinner(playPlayerDeck,overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */
						} 
						// end of while
				}
					/* 2ND AI */
			}else if (order[0].equals("AI2")) {
				System.out.println("ai2 goes first\n");// test

				if (order[1].equals("Player")) { // AI 2, PLAYER, AI 1
					
					while (gamePlaying) {
						int choosenCardPlayer = 0,choosenCardIndexAI1,choosenCardIndexAI2,noPair; // initialize


						/* AI 2 Wins */ /* Start of AI 2 Turn */
						overAllWinner = isAI2Winner(playAI2Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						turnAI2();

						whiteLine();
						System.out.println("\nThis is the AI 2 current deck: "); hidesOpponentCards(playAI2Deck); // Prints the current deck picker
						System.out.println("This is Player current deck: "  + Arrays.toString(playPlayerDeck)); // Prints the deck of picked
						
						/*  AI 2 STEALS FROM PLAYER  */
						choosenCardIndexAI2 = dice.nextInt(playPlayerDeck.length);
  
						// the card taken
						String[] choosenCardAI2 = new String[1];
						choosenCardAI2[0] = playPlayerDeck[choosenCardIndexAI2]; // ninakawan ng card
						System.out.println("\nThe card taken from Player was " + choosenCardAI2[0] + "\n"); // Displays the card taken
						int confirmPairAI2 = 99; whiteLine();
						System.out.print("\nAI 2 current cards: \n"); hidesOpponentCards(playAI2Deck); timer900();
						confirmPairAI2 = checksIfThereIsPairAI2(playAI2Deck, choosenCardAI2); // check if there is a pair in the card

						noPair = 99; // to check if no pair or yea
						
						if (confirmPairAI2 != noPair) { // if yes pair
							System.out.println("\nThere was a pair found!!!"); // informs that there is a pair
							System.out.println("\nThe card " + choosenCardAI2[0] + " and it paired with " + playAI2Deck[confirmPairAI2] + " in AI 2 current deck.");
							playAI2Deck = playAI2DeckPairOpponent(playAI2Deck, confirmPairAI2, playPlayerDeck, choosenCardIndexAI2, pairedCards);
							playPlayerDeck = removeCard(playPlayerDeck, choosenCardIndexAI2); // removes the stolen card
							System.out.println("\nCurrent cards of AI 2 are : " ); hidesOpponentCards(playAI2Deck); whiteLine();
							yellowLineT();
							System.out.println("This is the current paired cards \n"); printTheArray(pairedCards); 
							yellowLineB();

						} else { // if no pair
							Arrays.toString(playAI2Deck);
							playAI2Deck = addTakenCard(playAI2Deck, choosenCardAI2[0]); // Add the card to AI 2 deck
							playPlayerDeck = removeCard(playPlayerDeck,choosenCardIndexAI2); // Remove the card from the deck it is picked
							System.out.println("\nThere was no pair found. :<\n"); whiteLine();
							System.out.print("AI 2 Current cards: "); hidesOpponentCards(playAI2Deck); System.out.println("\n\n\n");
						} //END OF AI 2 TURN

						/* AI 2 Wins */ 
						overAllWinner = isAI2Winner(playAI2Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						/* Player Wins */ /* Start of Player Turn */
						overAllWinner = isPlayerWinner(playPlayerDeck,overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}
						
						// PLAYER STEALS FROM AI 1
						int choosenDeckPlayer = 1; // PICKS FROM AI 1						
						String[] playerTakenCard = new String[1];

						try {
								// stores the number of the picked card
							choosenCardPlayer = playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck, playPlayerDeck);
								// makes sures it is not more than the max lenght of the array
							if (choosenCardPlayer < playAI1Deck.length) {
								playerTakenCard[0] = playAI1Deck[choosenCardPlayer];
							} else {
								errorInputInvalidValue();
								// kinuha uli kuha ng user idk, time check 1:21 am 
								choosenCardPlayer = playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck, playPlayerDeck);
								// Check again if valid na 
								if (choosenCardPlayer < playAI1Deck.length) {
									playerTakenCard[0] = playAI1Deck[choosenCardPlayer]; // Player Steal from AI 1
								}
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							errorInputInvalidValue(); 
						}
						timer900(); whiteLine(); timer400();
						System.out.println("The card taken from AI 1 was " + playerTakenCard[0]);
						
						int confirmPairPlayer = 99; // default value for no pair
						
							// check if paired or nahh
						confirmPairPlayer = checksIfThereIsPairPlayer(playPlayerDeck, playerTakenCard);
						
						
						noPair = 99; // to check if no pair or yea
						
						if (confirmPairPlayer != noPair) { // if yes pair
							timer1500();
							System.out.println("\nThe card " + playerTakenCard[0] + " was taken, and it paired with the " + playPlayerDeck[confirmPairPlayer] + " in Player current deck.\n");
							playPlayerDeck = playPlayerDeckPairAI(playPlayerDeck, confirmPairPlayer, playAI1Deck, choosenCardPlayer, pairedCards);
							whiteLine(); promptEnterKey();

							yellowLineT();
							playAI1Deck = removeCard(playAI1Deck, choosenCardPlayer);// remove the stolen card
							System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
							yellowLineB(); promptEnterKey();System.out.println("\n");


						} else { // if no pair
							playPlayerDeck = addTakenCard(playPlayerDeck, playerTakenCard[0]); // Add the card to picker deck
							playAI1Deck = removeCard(playAI1Deck,choosenCardPlayer); // Remove the card from the picked  deck
							System.out.println("\nThere was no pair found. :<\n"); whiteLine(); 
							System.out.print("Your Current cards: "); printTheArray(playPlayerDeck); promptEnterKey(); System.out.println(""); whiteLine(); 
						} // END OF PLAYER TURN

						/* Player Wins */
						overAllWinner = isPlayerWinner(playPlayerDeck,overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}

						/* AI 1 Wins */ /* Start of AI 1 Turn */
						overAllWinner = isAI1Winner(playAI1Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */
						
						if (overAllWinner[0] != null) {
							break;
						}

						turnAI1();

						// AI1 STEALS AI2
						choosenCardIndexAI1 = dice.nextInt(playAI2Deck.length);

						whiteLine();
						System.out.println("\nThis is the AI 1 current deck: "); hidesOpponentCards(playAI1Deck); // Prints the current deck of picker

						System.out.println("\nThis is the AI 2 current deck: "); hidesOpponentCards(playAI1Deck); // Prints the current deck of picked from
						
							// the card taken
						String[] choosenCardAI1 = new String[1];
						choosenCardAI1[0] = playAI2Deck[choosenCardIndexAI1]; // AI 1 Steals from opponent
						whiteLine();
						System.out.println("The card taken from AI 2 was " + choosenCardAI1[0] + "\n"); // Displays the card taken
						int confirmPairAI1 = 99; whiteLine();
						System.out.print("\nAI 1 current cards: \n"); hidesOpponentCards(playAI1Deck); timer900();
						confirmPairAI1 = checksIfThereIsPairAI1(playAI1Deck, choosenCardAI1);

						noPair = 99; // to check if no pair or yea

						if (confirmPairAI1 != noPair) { // if yes pair
							System.out.println("There was a pair!!!"); timer900(); // the card taken from opponent paired with AI1
							System.out.println("\nThe card " + choosenCardAI1[0] + " and it paired with the " + playAI1Deck[confirmPairAI1] + " in AI 1 current deck.\n\n");
							whiteLine(); timer1500();
							playAI1Deck = playAI1DeckPairOpponent(playAI1Deck, confirmPairAI1, playAI2Deck, choosenCardIndexAI1, pairedCards);
							playAI2Deck = removeCard(playAI2Deck, choosenCardIndexAI1); // removes the stolen card
							System.out.println("Current cards of AI 1 after pairing: \n" ); hidesOpponentCards(playAI1Deck); whiteLine();
							timer1500();

							timer1500(); yellowLineT(); // Prints the current paired card
							System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
							yellowLineB(); timer900();

						} else { // if no pair
							playAI1Deck = addTakenCard(playAI1Deck, choosenCardAI1[0]); // Add the card to AI 1 deck
							playAI2Deck = removeCard(playAI2Deck,choosenCardIndexAI1); // Remove the card from the deck it is picked from
							System.out.println("\nThere was no pair found. :<\n"); whiteLine();
							yellowLineT();
							System.out.print("AI 1 Current cards: \n"); hidesOpponentCards(playAI1Deck); // hide the cards after debug
							yellowLineB();
						} // END OF AI 1 TURN					

						

						/* AI 1 Wins */
						overAllWinner = isAI1Winner(playAI1Deck, overAllWinner);
						gamePlaying = isGameDone(overAllWinner, gamePlaying);
						/* Game Ends */

						if (overAllWinner[0] != null) {
							break;
						}
					}	
					// end of while

					/* AI 2 */ /* 2nd Condition */
				} else if (order[1].equals("AI1")) { /* AI2, AI1, PLAYER */
						while (gamePlaying) {	
							int choosenCardPlayer = 0,choosenCardIndexAI1,choosenCardIndexAI2,noPair; // initialize


								/* AI 2 Wins */ /* Start of AI 2 Turn */
							overAllWinner = isAI2Winner(playAI2Deck, overAllWinner);
							gamePlaying = isGameDone(overAllWinner, gamePlaying);
							/* Game Ends */

							if (overAllWinner[0] != null) {
								break;
							}

							turnAI2();

							whiteLine();
							System.out.println("\nThis is the AI 2 current deck: "); hidesOpponentCards(playAI2Deck); // Prints the current deck picker
							System.out.println("This is AI 1 current deck: \n" ); hidesOpponentCards(playAI1Deck); // Prints the deck of picked // hide this after
							
							/*  AI 2 STEALS FROM AI 1  */
							choosenCardIndexAI2 = dice.nextInt(playAI1Deck.length);
	
							// the card taken
							String[] choosenCardAI2 = new String[1];
							choosenCardAI2[0] = playAI1Deck[choosenCardIndexAI2]; // ninakawan ng card
							System.out.println("\nThe card taken from AI 1 was " + choosenCardAI2[0] + "\n"); // Displays the card taken
							int confirmPairAI2 = 99; whiteLine();
							System.out.print("\nAI 2 current cards: \n"); hidesOpponentCards(playAI2Deck); timer900();
							confirmPairAI2 = checksIfThereIsPairAI2(playAI2Deck, choosenCardAI2); // check if there is a pair in the card

							noPair = 99; // to check if no pair or yea
							
							if (confirmPairAI2 != noPair) { // if yes pair
								System.out.println("\nThere was a pair found!!!"); // informs that there is a pair
								System.out.println("\nThe card " + choosenCardAI2[0] + " and it paired with " + playAI2Deck[confirmPairAI2] + " in AI 2 current deck.");
								playAI2Deck = playAI2DeckPairOpponent(playAI2Deck, confirmPairAI2, playAI1Deck, choosenCardIndexAI2, pairedCards);
								playAI1Deck = removeCard(playAI1Deck, choosenCardIndexAI2); // removes the stolen card
								System.out.println("\nCurrent cards of AI 2 are : " ); hidesOpponentCards(playAI2Deck); whiteLine();
								yellowLineT();
								System.out.println("This is the current paired cards \n"); printTheArray(pairedCards); 
								yellowLineB();

							} else { // if no pair
								Arrays.toString(playAI2Deck);
								playAI2Deck = addTakenCard(playAI2Deck, choosenCardAI2[0]); // Add the card to AI 2 deck
								playAI1Deck = removeCard(playAI1Deck,choosenCardIndexAI2); // Remove the card from the deck it is picked
								System.out.println("\nThere was no pair found. :<\n"); whiteLine();
								System.out.print("AI 2 Current cards: "); hidesOpponentCards(playAI2Deck); System.out.println("\n\n\n");
							} //END OF AI 2 TURN

							/* AI 2 Wins */
							overAllWinner = isAI2Winner(playAI2Deck, overAllWinner);
							gamePlaying = isGameDone(overAllWinner, gamePlaying);
							/* Game Ends */

							if (overAllWinner[0] != null) {
								break;
							}

							/* AI 1 Wins */ /* Start of AI 1 Turn */
							overAllWinner = isAI1Winner(playAI1Deck, overAllWinner);
							gamePlaying = isGameDone(overAllWinner, gamePlaying);
							/* Game Ends */
							
							if (overAllWinner[0] != null) {
								break;
							}
							
							turnAI1();

							// AI1 STEALS PLAYER
							choosenCardIndexAI1 = dice.nextInt(playPlayerDeck.length);

							whiteLine();
							System.out.println("\nThis is the AI 1 current deck: "); hidesOpponentCards(playAI1Deck); // Prints the current deck of picker

							System.out.println("\nThis is the Player current deck: "); printTheArray(playPlayerDeck); // Prints the current deck of picked from
							
								// the card taken
							String[] choosenCardAI1 = new String[1];
							choosenCardAI1[0] = playPlayerDeck[choosenCardIndexAI1]; // AI 1 Steals from opponent
							whiteLine();
							System.out.println("The card taken from Player was " + choosenCardAI1[0] + "\n"); // Displays the card taken
							int confirmPairAI1 = 99; whiteLine();
							System.out.print("\nAI 1 current cards: \n"); hidesOpponentCards(playAI1Deck); timer900();
							confirmPairAI1 = checksIfThereIsPairAI1(playAI1Deck, choosenCardAI1);

							noPair = 99; // to check if no pair or yea

							if (confirmPairAI1 != noPair) { // if yes pair
								System.out.println("There was a pair!!!"); timer900(); // the card taken from opponent paired with AI1
								System.out.println("\nThe card " + choosenCardAI1[0] + " and it paired with the " + playAI1Deck[confirmPairAI1] + " in AI 1 current deck.\n\n");
								whiteLine(); timer1500();
								playAI1Deck = playAI1DeckPairOpponent(playAI1Deck, confirmPairAI1, playPlayerDeck, choosenCardIndexAI1, pairedCards);
								playPlayerDeck = removeCard(playPlayerDeck, choosenCardIndexAI1); // removes the stolen card
								System.out.println("Current cards of AI 1 after pairing: " ); hidesOpponentCards(playAI1Deck); whiteLine();
								timer1500();

								timer1500(); yellowLineT(); // Prints the current paired card
								System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
								yellowLineB(); timer900();

							} else { // if no pair
								playAI1Deck = addTakenCard(playAI1Deck, choosenCardAI1[0]); // Add the card to AI 1 deck
								playPlayerDeck = removeCard(playPlayerDeck,choosenCardIndexAI1); // Remove the card from the deck it is picked from
								System.out.println("\nThere was no pair found. :<\n"); whiteLine();
								yellowLineT();
								System.out.print("AI 1 Current cards: \n"); hidesOpponentCards(playAI1Deck); // hide the cards after debug
								yellowLineB();
							} // END OF AI 1 TURN
							
							/* AI 1 Wins */
							overAllWinner = isAI1Winner(playAI1Deck, overAllWinner);
							gamePlaying = isGameDone(overAllWinner, gamePlaying);
							/* Game Ends */

							if (overAllWinner[0] != null) {
								break;
							}

							/* Player Wins */ /* Start of Player Turn */
							overAllWinner = isPlayerWinner(playPlayerDeck,overAllWinner);
							gamePlaying = isGameDone(overAllWinner, gamePlaying);
							/* Game Ends */
							
							if (overAllWinner[0] != null) {
								break;
							}

							// PLAYER STEALS FROM AI 2
							int choosenDeckPlayer = 2; // PICKS FROM AI 2						
							String[] playerTakenCard = new String[1];

							try {
									// stores the number of the picked card
								choosenCardPlayer = playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck, playPlayerDeck);
									// makes sures it is not more than the max lenght of the array
								if (choosenCardPlayer < playAI2Deck.length) {
									playerTakenCard[0] = playAI2Deck[choosenCardPlayer];
								} else {
									errorInputInvalidValue();
									// kinuha uli kuha ng user idk, time check 1:21 am 
									choosenCardPlayer = playerChooseCard(choosenDeckPlayer, playAI1Deck, playAI2Deck, playPlayerDeck);
									// Check again if valid na 
									if (choosenCardPlayer < playAI2Deck.length) {
										playerTakenCard[0] = playAI2Deck[choosenCardPlayer]; // Player Steal from AI 2
									}
								}
							} catch (ArrayIndexOutOfBoundsException e) {
								errorInputInvalidValue(); 
							}
							timer900(); whiteLine(); timer400();
							System.out.println("The card taken from AI 2 was " + playerTakenCard[0]);
							
							int confirmPairPlayer = 99; // default value for no pair
							
								// check if paired or nahh
							confirmPairPlayer = checksIfThereIsPairPlayer(playPlayerDeck, playerTakenCard);
							
							
							noPair = 99; // to check if no pair or yea
							
							if (confirmPairPlayer != noPair) { // if yes pair
								timer1500();
								System.out.println("\nThe card " + playerTakenCard[0] + " was taken, and it paired with the " + playPlayerDeck[confirmPairPlayer] + " in Player current deck.\n");
								playPlayerDeck = playPlayerDeckPairAI(playPlayerDeck, confirmPairPlayer, playAI2Deck, choosenCardPlayer, pairedCards);
								whiteLine(); promptEnterKey();

								yellowLineT();
								playAI2Deck = removeCard(playAI2Deck, choosenCardPlayer);// remove the stolen card
								System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
								yellowLineB(); promptEnterKey();System.out.println("\n");


							} else { // if no pair
								playPlayerDeck = addTakenCard(playPlayerDeck, playerTakenCard[0]); // Add the card to picker deck
								playAI2Deck = removeCard(playAI2Deck,choosenCardPlayer); // Remove the card from the picked  deck
								System.out.println("\nThere was no pair found. :<\n"); whiteLine(); 
								System.out.print("Your Current cards: "); printTheArray(playPlayerDeck); promptEnterKey(); System.out.println(""); whiteLine(); 
							} // END OF PLAYER TURN

							/* Player Wins */
							overAllWinner = isPlayerWinner(playPlayerDeck,overAllWinner);
							gamePlaying = isGameDone(overAllWinner, gamePlaying);
							/* Game Ends */

							
						

					}
				}				
			}
				
			String[] monkeyNa = new String[1];
			//monkeyNa = theMonkey(overAllWinner, playPlayerDeck, playAI1Deck, playAI2Deck);
			//isTheMonkey = theMonkey;






			System.out.println("The winner was " + overAllWinner[0] + ". Since they were the first one to have no cards left"); 
			System.out.println("\n\n Want to know who is the monkey? \n"); promptEnterKey();

			theMonkey(overAllWinner, playPlayerDeck, playAI1Deck, playAI2Deck, pairedCards);
		}
		
	}
	// Monkey class


	private static String[] theMonkey(String[] overAllWinner, String[] playPlayerDeck, String[] playAI1Deck, String[] playAI2Deck, String[] pairedCards) {
		String[] monkeyNa = new String[1];
		int choosen1,choosen2,choosenPlayer,check1,check2,noPair = 99;
		Random dice = new Random();
		boolean monkey = true;

		if (overAllWinner[0] == "Player") {
			
			if (playAI1Deck.length < playAI2Deck.length) {
				while (monkey) {
					String[] card1 = new String[1]; String[] card2 = new String[1];
					
					choosen1 = dice.nextInt(playAI2Deck.length); 
					card1[0] = playAI2Deck[choosen1];
					check1 = checksIfThereIsPairAI1(playAI1Deck, card1);
					

					playAI1Deck = playAI1DeckPairOpponent(playAI1Deck, check1, playAI2Deck, choosen1, card1);
					System.out.println("The card taken from AI 2 was " + card1[0] + "\n"); // Displays the card taken
					whiteLine();
					System.out.print("\nAI 1 current cards: \n"); hidesOpponentCards(playAI1Deck); timer900();

					if (check1 != noPair) { // if yes pair
						System.out.println("There was a pair!!!"); timer900(); // the card taken from opponent paired with AI1
						System.out.println("\nThe card " + card1[0] + " and it paired with the " + playAI1Deck[check1] + " in AI 1 current deck.\n\n");
						whiteLine(); timer1500();
						playAI1Deck = playAI1DeckPairOpponent(playAI1Deck, check1, playAI2Deck, choosen1, pairedCards);
						playAI2Deck = removeCard(playAI2Deck, check1); // removes the stolen card
						System.out.println("Current cards of AI 1 after pairing: " ); hidesOpponentCards(playAI1Deck); whiteLine();
						timer1500();

						timer1500(); yellowLineT(); // Prints the current paired card
						System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
						yellowLineB(); timer900();

					} else { // if no pair
						playAI1Deck = addTakenCard(playAI1Deck, card1[0]); // Add the card to AI 1 deck
						playAI2Deck = removeCard(playAI2Deck,choosen1); // Remove the card from the deck it is picked from
						System.out.println("\nThere was no pair found. :<\n"); whiteLine();
						yellowLineT();
						System.out.print("AI 1 Current cards: "); printTheArray(playAI1Deck); // hide the cards after debug
						yellowLineB();
					}

					if (playAI1Deck.length == 0) { // AI 1 Wins
						monkeyNa[0] = "AI2"; 
						monkey = false;
					} // AI 1 not Monkey


					choosen2 = dice.nextInt(playAI1Deck.length);
					card2[0] = playAI1Deck[choosen2];
					check2 = checksIfThereIsPairAI2(playAI2Deck, card2);
					

					playAI2Deck = playAI2DeckPairOpponent(playAI2Deck, check2, playAI1Deck, choosen2, pairedCards);
					System.out.println("The card taken from AI 1 was " + card2[0] + "\n"); // Displays the card taken
					whiteLine();
					System.out.print("\nAI 2 current cards: \n"); hidesOpponentCards(playAI2Deck); timer900();

					if (check2 != noPair) { // if yes pair
						System.out.println("There was a pair!!!"); timer900(); // the card taken from opponent paired with AI2
						System.out.println("\nThe card " + card2[0] + " and it paired with the " + playAI2Deck[check2] + " in AI 2 current deck.\n\n");
						whiteLine(); timer1500();
						playAI2Deck = playAI2DeckPairOpponent(playAI2Deck, check2, playAI2Deck, choosen1, pairedCards);
						playAI1Deck = removeCard(playAI1Deck, check2); // removes the stolen card
						System.out.println("Current cards of AI 2 after pairing: " ); hidesOpponentCards(playAI2Deck); whiteLine();
						timer1500();

						timer1500(); yellowLineT(); // Prints the current paired card
						System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
						yellowLineB(); timer900();

					} else { // if no pair
						playAI2Deck = addTakenCard(playAI2Deck, card2[0]); // Add the card to AI 2 deck
						playAI1Deck = removeCard(playAI1Deck,choosen2); // Remove the card from the deck it is picked from
						System.out.println("\nThere was no pair found. :<\n"); whiteLine();
						yellowLineT();
						System.out.print("AI 2 Current cards: "); printTheArray(playAI2Deck); // hide the cards after debug
						yellowLineB();
					}


					if (playAI2Deck.length == 0) { // AI 2 Wins
						monkeyNa[0] = "AI1";
						monkey = false;
					}// AI 2 not Monkey

				}
			
			} else {
				while (monkey) {
					String[] card2 = new String[1];
					choosen2 = dice.nextInt(playAI1Deck.length);
					card2[0] = playAI1Deck[choosen2];
					check2 = checksIfThereIsPairAI2(playAI2Deck, card2);
					

					playAI2Deck = playAI2DeckPairOpponent(playAI2Deck, check2, playAI1Deck, choosen2, pairedCards);
					System.out.println("The card taken from AI 1 was " + card2[0] + "\n"); // Displays the card taken
					whiteLine();
					System.out.print("\nAI 2 current cards: \n"); hidesOpponentCards(playAI2Deck); timer900();

					if (check2 != noPair) { // if yes pair
						System.out.println("There was a pair!!!"); timer900(); // the card taken from opponent paired with AI2
						System.out.println("\nThe card " + card2[0] + " and it paired with the " + playAI2Deck[check2] + " in AI 2 current deck.\n\n");
						whiteLine(); timer1500();
						playAI2Deck = playAI2DeckPairOpponent(playAI2Deck, check2, playAI2Deck, choosen2, pairedCards);
						playAI1Deck = removeCard(playAI1Deck, check2); // removes the stolen card
						System.out.println("Current cards of AI 2 after pairing: " ); hidesOpponentCards(playAI2Deck); whiteLine();
						timer1500();

						timer1500(); yellowLineT(); // Prints the current paired card
						System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
						yellowLineB(); timer900();

					} else { // if no pair
						playAI2Deck = addTakenCard(playAI2Deck, card2[0]); // Add the card to AI 2 deck
						playAI1Deck = removeCard(playAI1Deck,choosen2); // Remove the card from the deck it is picked from
						System.out.println("\nThere was no pair found. :<\n"); whiteLine();
						yellowLineT();
						System.out.print("AI 2 Current cards: "); printTheArray(playAI2Deck); // hide the cards after debug
						yellowLineB();
					}


					if (playAI2Deck.length == 0) { // AI 2 Wins
						monkeyNa[0] = "AI1";
						monkey = false;
					}// AI 2 not Monkey

					String[] card1 = new String[1];


					choosen1 = dice.nextInt(playAI2Deck.length); 
					card1[0] = playAI2Deck[choosen1];
					check1 = checksIfThereIsPairAI1(playAI1Deck, card1);
					

					playAI1Deck = playAI1DeckPairOpponent(playAI1Deck, check1, playAI2Deck, choosen1, card1);
					System.out.println("The card taken from AI 2 was " + card1[0] + "\n"); // Displays the card taken
					whiteLine();
					System.out.print("\nAI 1 current cards: \n"); hidesOpponentCards(playAI1Deck); timer900();

					if (check1 != noPair) { // if yes pair
						System.out.println("There was a pair!!!"); timer900(); // the card taken from opponent paired with AI1
						System.out.println("\nThe card " + card1[0] + " and it paired with the " + playAI1Deck[check1] + " in AI 1 current deck.\n\n");
						whiteLine(); timer1500();
						playAI1Deck = playAI1DeckPairOpponent(playAI1Deck, check1, playAI2Deck, choosen1, pairedCards);
						playAI2Deck = removeCard(playAI2Deck, check1); // removes the stolen card
						System.out.println("Current cards of AI 1 after pairing: " ); hidesOpponentCards(playAI1Deck); whiteLine();
						timer1500();

						timer1500(); yellowLineT(); // Prints the current paired card
						System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
						yellowLineB(); timer900();

					} else { // if no pair
						playAI1Deck = addTakenCard(playAI1Deck, card1[0]); // Add the card to AI 1 deck
						playAI2Deck = removeCard(playAI2Deck,choosen1); // Remove the card from the deck it is picked from
						System.out.println("\nThere was no pair found. :<\n"); whiteLine();
						yellowLineT();
						System.out.print("AI 1 Current cards: "); printTheArray(playAI1Deck); // hide the cards after debug
						yellowLineB();
					}

					if (playAI1Deck.length == 0) { // AI 1 Wins
						monkeyNa[0] = "AI2"; 
						monkey = false;
					} // AI 1 not Monkey
				}

			}


		} else if (overAllWinner[0] == "AI1") {
			if (playAI1Deck.lenght<playPlayerDeck.lenght) {
				while(monkey) {
					String[] card1 = new String[1];

					System.out.println("This is the current deck of player:")
					printTheArray(playPlayerDeck); // Steals from Player

					choosen1 = dice.nextInt(playPlayerDeck.length); 
					card1[0] = playPlayerDeck[choosen1];
					check1 = checksIfThereIsPairAI1(playAI1Deck, card1);
					

					playAI1Deck = playAI1DeckPairOpponent(playAI1Deck, check1, playAI2Deck, choosen1, card1);
					System.out.println("The card taken from Player was " + card1[0] + "\n"); // Displays the card taken
					whiteLine();
					System.out.print("\nAI 1 current cards: \n"); hidesOpponentCards(playAI1Deck); timer900();

					if (check1 != noPair) { // if yes pair
						System.out.println("There was a pair!!!"); timer900(); // the card taken from opponent paired with AI1
						System.out.println("\nThe card " + card1[0] + " and it paired with the " + playAI1Deck[check1] + " in AI 1 current deck.\n\n");
						whiteLine(); timer1500();
						playAI1Deck = playAI1DeckPairOpponent(playAI1Deck, check1, playPlayerDeck, choosen1, pairedCards);
						playPlayerDeck = removeCard(playPlayerDeck, check1); // removes the stolen card
						System.out.println("Current cards of AI 1 after pairing: " ); hidesOpponentCards(playAI1Deck); whiteLine();
						timer1500();

						timer1500(); yellowLineT(); // Prints the current paired card
						System.out.print("This is the current paired cards: \n"); printTheArray(pairedCards); 
						yellowLineB(); timer900();

					} else { // if no pair
						playAI1Deck = addTakenCard(playAI1Deck, card1[0]); // Add the card to AI 1 deck
						playPlayerDeck = removeCard(playPlayerDeck,choosen1); // Remove the card from the deck it is picked from
						System.out.println("\nThere was no pair found. :<\n"); whiteLine();
						yellowLineT();
						System.out.print("AI 1 Current cards: "); printTheArray(playAI1Deck); // hide the cards after debug
						yellowLineB();
					}

					if (playAI1Deck.length == 0) { // AI 1 Wins
						monkeyNa[0] = "Player"; 
						monkey = false;
					} // AI 1 not Monkey
					
					
				}
			} else {
				
			}
				
			// End of Ai 1 winner
		} else if (overAllWinner[0] == "AI2") {
			while (monkey) {
				


				
			}
		}

		

		return monkeyNa;
	}

	
		
	
	

	private static void greenLineB() {
		String greenColor = "\u001B[32m"; String resetColor = "\u001B[0m";
		System.out.println(greenColor + "\n________________________________________________________________________________________________________________________________\n\n" + resetColor);
	}

	private static void greenLineT() {
		String greenColor = "\u001B[32m"; String resetColor = "\u001B[0m";
		System.out.println(greenColor + "\n________________________________________________________________________________________________________________________________\n\n" + resetColor);

	}

	private static void yellowLineT() {
		String yellowColor = "\u001B[33m"; String resetColor = "\u001B[0m";
		System.out.println(yellowColor + "\n\n________________________________________________________________________________________________________________________________\n\n" + resetColor);
	}

	private static void yellowLineB() {
		String yellowColor = "\u001B[33m"; String resetColor = "\u001B[0m";
		System.out.println(yellowColor + "\n________________________________________________________________________________________________________________________________\n\n" + resetColor);
	}

	private static void redLineT() {
		String redColor = "\u001B[31m"; String resetColor = "\u001B[0m";

	}

	private static void redLineB() {
		String redColor = "\u001B[31m"; String resetColor = "\u001B[0m";

	}

	private static void turnAI1() {
		greenLineT();
		System.out.println("\t\tAI 1 Turn to pick cards.");
		greenLineB();
	}

	private static void turnAI2() {
		greenLineT();
		System.out.println("\t\tAI 2 Turn to pick cards.");
		greenLineB();
	}

	private static String[] isPlayerWinner(String[]playPlayerDeck, String[] overAllWinner) {
		if (playPlayerDeck.length == 0) {
			overAllWinner[0] = "Player";
			return overAllWinner; // The Player won
			
		} else {
			return overAllWinner; // Did not win yet
		}
	} /* WORKING */

	private static String[] isAI1Winner(String[]playAI1Deck, String[] overAllWinner) {
		if (playAI1Deck.length == 0) {
			overAllWinner[0] = "AI1";
			return overAllWinner; // The AI 1 won
		} else {
			return overAllWinner; // Did not win yet
		}
	} /* WORKING */

	private static String[] isAI2Winner(String[]playAI2Deck, String[] overAllWinner) {
		if (playAI2Deck.length == 0) {
			overAllWinner[0] = "AI2";
			return overAllWinner; // The AI 2 won
		} else {
			return overAllWinner; // Did not win yet
		}
	} /* WORKING */

	private static boolean isGameDone(String[] overAllWinner, boolean gamePlaying) {
		if (overAllWinner[0] != null) {
			gamePlaying = false; // Ends the game
		} else {
			gamePlaying = true; // Continue the game
		}

		return gamePlaying;
	} /* WORKING */


	private static void startMonkey() {
        for (int i = 0; i < 1; i++) {
            timerAnimation(); spacingMonkeyAnimation();  monkey1Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey2Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey3Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey4Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey5Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey6Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey7Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey8Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey9Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey10Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey11Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey12Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey13Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey14Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey15Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey16Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey17Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey18Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey19Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey20Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey21Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey22Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey23Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey24Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey25Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey26Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey27Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey28Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey29Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey30Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey31Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey32Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey33Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey34Frame(); 
            timerAnimation(); spacingMonkeyAnimation();  monkey35Frame(); 
        }  timerAnimation(); spacingMonkeyAnimation(); spacingMonkeyAnimation(); monkey0Frame(); timer5000();
    }
    private static void timerAnimation() {
		/* Timer for 0.7 seconds */
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    private static void monkey0Frame() {
        String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey1Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        midGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        midGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        darkGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        darkGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        darkGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey2Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        darkGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        midGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        midGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        darkGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        darkGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey3Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        darkGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        darkGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        midGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        midGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        darkGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey4Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        darkGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        darkGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        darkGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        midGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        midGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey5Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        midGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        darkGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        darkGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        darkGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        midGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey6Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        midGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        midGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        darkGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        darkGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        darkGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey7Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        midGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        midGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        darkGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        darkGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        darkGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey8Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        midGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        midGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        darkGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        darkGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey9Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        darkGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        midGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        midGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        darkGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey10Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        darkGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        darkGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        midGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        midGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey11Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        midGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        darkGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        darkGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        midGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey12Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        midGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        midGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        darkGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        darkGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey13Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        midGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        midGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        darkGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        darkGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey14Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        midGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        midGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        darkGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        darkGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey15Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        midGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        midGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        darkGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey16Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        darkGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        midGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        midGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey17Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        midGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        darkGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        midGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey18Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        midGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        midGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        darkGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey19Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        midGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        midGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        darkGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey20Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        midGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        midGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        darkGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey21Frame() {
        String darkGreyColor = "\u001B[90m"; String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        midGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        midGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        darkGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey22Frame() {
        String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        midGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        midGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey23Frame() {
        String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        midGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        midGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey24Frame() {
        String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        midGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        midGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey25Frame() {
        String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        midGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        midGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey26Frame() {
        String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        midGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        midGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey27Frame() {
        String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        midGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        midGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey28Frame() {
        String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        midGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        midGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey29Frame() {
        String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        midGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey30Frame() {
        String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        midGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey31Frame() {
        String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        midGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey32Frame() {
		String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        midGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey33Frame() {
        String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        midGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey34Frame() {
        String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        midGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        lightGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void monkey35Frame() {
        String midGreyColor = " \u001B[38;5;240m"; String lightGreyColor = "\u001B[37m"; String resetColor = "\u001B[0m"; 
        System.out.println(
        lightGreyColor + "\t    ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗            ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    \r\n" + resetColor + //
        lightGreyColor  + "\t    ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝            ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    \r\n" + resetColor + //
        lightGreyColor  + "\t    ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝             ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     \r\n" + resetColor + //
        lightGreyColor  + "\t    ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝     █████╗   ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      \r\n" + resetColor + //
        lightGreyColor + "\t    ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║      ╚════╝   ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       \r\n" + resetColor + //
        midGreyColor  + "\t    ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝               ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       \r\n" + resetColor +//
       "" + resetColor);
    }

    private static void spacingMonkeyAnimation() {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

	
	
}


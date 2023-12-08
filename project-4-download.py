# An armwrestling wordle in which you may only guess the names of armwrestlers to win. NOTE: While I did not take any code directly from https://www.freecodecamp.org/news/how-to-build-a-wordle-clone-using-python-and-rich/
# I did view the video to determine how the game should be structured (What the user sees) such as the guesses being presented afterward. So I only used it as a structure reference for the componenets
# presented to the user not for the actual code. If that is confusing please let me know, I did not plagiarize and my code should present that, but again if their are concerns
# please let me know. Thank you.
# NOTE: Most armwrestling names came from https://www.walunderground.com/competitors but not all.
# NOTE: Used https://pypi.org/project/colorama/ as new library/module.
# Carson J. King
import random
#Imports random module for later use.
from colorama import Fore, Back, Style
#Imports colorama for future use.

FOUR_LETTER = ("TODD", "MASK", "FURY", "FORD", "SILL", "WEST", "HALE", "SHAW", "ROSE", "KING",
               "BATH", "GABE", "TATI", "DALE", "LINN", "JOHN", "BLUE", "KRIS", "JEDI", "JACK",
               "GOAT", "DABE", "LABA", "SPUR", "WYNN")
#Four letter names within a tuple.
FIVE_LETTER = ("SUCKA", "BARUP", "ZINNA", "COYLE", "COBRA", "GREEN", "KINDT", "HUDIK", "STORM",
               "HIRST", "MITTS", "SINKS", "LEWIS", "BLIND", "TOPIE", "BEACH", "WOLFE", "FARIA"
               "STEEL", "BICCY", "SMITH", "ESPEY", "GOULD", "LOCKE", "TERZI", "BOWEN", "MILNE",
               "TESCH", "ZHOKH", "ALLEN", "CRAZY", "FREAK", "SEDIS", "GIVEN", "ROBBY")
#Five letter names within a tuple.
SIX_LETTER = ("KLEMBA", "ABBOTT", "MENDEZ", "ARNOLD", "RHODES", "BAGENT", "BISHOP", "BINNIE",
              "NELSON", "BRZENK", "THRILL", "POPEYE", "HARRIS", "MUTANT", "HUGHES", "LOVELY",
              "LUPKES", "MCGRAW", "DOUGAN", "ZOLOEV", "MORRIS", "TAUBIN", "SILEAV", "MOSIER",
              "AYELLO", "HITMAN", "PERRON", "LEGEND", "TSONEV", "ANCHOR", "DAMIAN", "GRONOV",
              "GUERRA", "TUPPER", "MILLER", "DRAGON", "SKYLAR", "PICKUP", "REISEK", "GEORGE",
              "PHENOM", "ELRICH", "NELSON", "ANIMAL", "FISHER", "WILMOT")
#Six letter names within a tuple.
GAMES = (FOUR_LETTER, FIVE_LETTER, SIX_LETTER)
#Global tuple to choose a game from.

def game(lst):
    """Handles all the aspects of the game of Awordle, from the number of guesses to the coloring of the guess and whether they won or not."""
    print(f"\n The armwrestling name is a {len(lst[0])} letter name")
    AWORDLE = random.choice(lst)
    #Chooses our Awordle from the given list of armwrestling names.
    guess = "N/A"
    #Sets guess to a unimportant value to begin a while loop later on.
    guesses = 0
    #Begins our user's guesses at zero, and is used to determine if the user is out of guesses later on.
    game_info = []
    #A list to add whether the user won and what the Awordle was to report back to main.
    game_info.append(AWORDLE)
    #Appends Awordle to our game_info list.
    guesses_allowed = len(lst[0]) + 1
    #Gives a variable guesses_allowed a value of 1 + the length of the name so the user has a limited number of allowed guesses.
    print(f" You have {guesses_allowed} guesses")
    #Informs the user the number of guesses they have until they lose.
    shown_guesses = []
    #List to add users guesses to so they can be reported back each turn.
    while guess != AWORDLE and guesses != guesses_allowed:
    #While loop to determine whether the user has guessed the Awordle or has run out of turns.
        shown_guess = ""
        #Used to individually add characters of a guess to so that they can be attributed the correct color for the game.
        whats_left = AWORDLE + ""
        #Used to determine how many of a certain letter are left in the Awordle during a singular guess. Prevents for instance double coloring two
        #O's when there is in fact only 1 in the Awordle.
        guess = (str(input(" "))).upper()
        #Turns our users inputed guess into all upercase so that it can be compared with our list of names above accurately.
        while guess not in lst:
        #While loop that prevents user from guessing a non armwrestling name (This includes numbers, too long of names, and anything that is not within the provided list.
            print(f" Your guess must be  {len(lst[0])} letters and be the last name or nickname of an armwrestler")
            #Informs the user that they must guess a 4 letter name of an armwrestler.
            guess = (str(input(" "))).upper()
            #Provides the user with another chance to input a guess.
        for i in range(len(AWORDLE)):
        #Gives us indexes for the length of the Awordle to use.
            if guess[i] == AWORDLE[i] and guess[i] in whats_left:
            #Determines whether the guesses index matches the Awordle's index. (To determine if the character is in the right place and in the Awordle)
                shown_guess += (Back.GREEN + Fore.WHITE + f"{guess[i]}" + Style.RESET_ALL)
                #Gives the character the correct color of green and adds it to the shown_guess variable.
                remove = whats_left.index(guess[i])
                #Gives us the index of the character we want to remove.
                whats_left = whats_left[:remove] + whats_left[remove + 1:]
                #Removes the letter from whats_left to again prevent double characters as mentioned above.
            elif guess[i] in AWORDLE and guess[i] in whats_left:
            #Determines whether a character of the given guess is in the Awordle.
                shown_guess += (Back.YELLOW + Fore.WHITE + f"{guess[i]}" + Style.RESET_ALL)
                #Gives the character the correct color of yellow and adds it to the shown_guess variable.
                remove = whats_left.index(guess[i])
                #Gives us the index of the character we want to remove.
                whats_left = whats_left[:remove] + whats_left[remove + 1:]
                #Removes the letter from whats_left to again prevent double characters as mentioned above.
            else:
            #If both the previous conditions are not met then the character being evaluated
            #must not be in the Awordle.
                shown_guess += (Back.BLACK + Fore.WHITE + f"{guess[i]}" + Style.RESET_ALL)
                #Gives the character the correct color of yellow and adds it to the shown_guess variable.
        shown_guesses.append(shown_guess)
        #Appends the completed shown_guess to a list of guesses mentioned above.
        for i in shown_guesses:
        #For loop to print each guess after one another.
            print(f" {i}")
            #Prints the user's guesses so they can reference them as they progress.
        guesses += 1
        #Counts up the number of guesses so the user doesn't get more guesses than is allowed.
    if guess == AWORDLE:
    #For if the user guessed the Awordle.
        game_info.append(True)
        #Reports back a value that represents a win for the user.
    else:
    #For if the user did not guess the Awordle.
        game_info.append(False)
        #Reports back a value that represents a loss for the user.
    return game_info
    #Returns the game_info to main
    
def main():
    input(" Welcome to the first ever armwrestling worlde! (Enter Anything To Progress Through The Introduction)")
    input(" Let me explain how it works.")
    input(" You will be asked to submit a 4, 5, or 6 letter name of an armwrestler.")
    input(" This may be their last name or nickname.")
    input(" After giving a guess you will be shown (via colors) if the letter is in the name or whether it is within the name and in the correct space.")
    input(" The number of guesses before you lose is based upon the length of the name.")
    input(" The number of letters is random each time you play.")
    input(" Ready to play? (Enter Anything)")
    #Introduction to the game, uses input so that the user can read it line by line at their own pace. 
    keep_going = ""
    #Begins while loop for playing a new game.
    while keep_going == "":
        round_ = random.choice(GAMES)
        #Chooses a random game from the global tuple so that you are always playing a 4, 5, or 6 letter name game.
        result = game(round_)
        #Calls to game function, while sending the tuple of names that was selected above.
        if result[1] == False:
        #Uses indexing to check the first value of game_info/result, which is whether the user won or not. (In this case checking if they won)
            print(f" Nice try! The name was {result[0][0] + result[0][1:].lower()}.")
            #Tells the user they won and the Awordle.
        if result[1] == True:
        #Uses indexing to check the first value of game_info/result, which is whether the user won or not. (In this case checking if they lost)
            print(f" Congrats! You got it right! The name was {result[0][0] + result[0][1:].lower()}.")
            #Tells the user they lost and the Awordle
        keep_going = input('\n Would you like to play again?\n\n If so hit the "Enter" key, if not enter any other key.')
        #Asks the user if they want to keep going or not.

if __name__ == '__main__':
    main()
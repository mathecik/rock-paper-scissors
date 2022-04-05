*/+Rock-Paper-Scissors Game*/+


Player
*name:String
*savedGameId: Game

Game
*gameId:int
*firstPlayer:Player
*firstPlayerScore:int
*secondPlayer:Player
*secondPlayerScore:int
*roundCount:int

Choice
*ROCK
*PAPER
*SCISSORS

Rules:
*Rock beats scissors
*Scissors beat paper
*Paper beats rock

*Each turn player who choose the superior gains 1 point 
*In case of draw nobody gains point
*After each turn, notify the players score and the winner.


Scenarios:

Player -> Enter Name-> Press Start button

<Already Player>
	-><Player has saved game>-><Retrieve the save game> -> Continue saved game						  	  <Start a new game>	-> Create new game with score 0

<New Player>->Create new player->Create new game with score 0


Player -> Press the Play Button: Rock,Paper,Scissors -> Calculate the Computer’s move -> Calculate the winner -> show the winner

Player-> Press the New Game Bottun -> create a new game for the player

Player->Pres the Save Button -> set the players played game with the game id.


start(String name)
newGame()
choose(Choice choice)
saveGame()

Notes:
The game is designed one real player and one computer player.
Tried to implement best practices of REST api design
Used version (in URL) for improve the game in the future.
This is only back end part of the game.
If I had more time, I would add db design and DAO implementations, unit test of http request and maybe a simple UI. Also I could add some different types of responses.  In Game.java there is a field roundCount is never used. I would use it to show which round is played to player. For the future, I will review my design and change for multiple player. (But still can be played against the computer), maybe add more feature to the player (id, played games, total point etc.) and really atractive UI:)












 

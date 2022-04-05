# rock-paper-scissors
rock-paper-scissors game


Rock-Paper-Scissors Game


Player
*playerId:int
*name:String
*savedGameId: Game


Game
*gameId:int
*firstPlayer:Player
*firstPlayerScore:int
*secondPlayer:Player
*secondPlayerScore:int
*roundCount

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



Player -> Enter Name-> Press Start button

<Already Player>
	-><Player has saved game>-><Retrieve the save game> -> Continue saved game						  	  <Start a new game>	-> Create new game with score 0

<New Player>->Create new player->Create new game with score 0


Player -> Press the Choice: Rock,Paper,Scissors -> Calculate the Computer’s move -> Calculate the winner -> show the winner


start(String name)
newGame()
choose(Choice choice)
saveGame()














 

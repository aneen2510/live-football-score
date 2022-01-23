# live-football-score
Live score calculator for a Football match

Java Module contains FootballScoreBoardCalculator class which works as a Sports Dashboard 
which have options to Record live football matches and overview of history of matches completed.

#Implementation Details

Implementation consist of 5 methods for starting a game, update the game scores when there is goal scored
by any of the team, method to notify the end of game and method for displaying the history of matches.

FootballMatchDetails.java -> Object represents a match, it have fields for a game
            private String homeTeam;
            private String awayTeam;
            private int homeTeamScore = 0;
            private int awayTeamScore = 0;

liveMatchDetails -> HashMap which holds the details of matches which is going live, implementation handles multiple live matches at a time if there are more matches going live.
                    Key contains the combination of HOME TEAM and AWAY TEAM names.
historyOfMatches -> ArrayList of FootballMatch Details which holds the details of matches which is over. Once the end game method get called it will add into history list.


#Method Details

#startGame

Method accepts home team and away team names. 
1. Check names are valid
2. Checks if there is already a live match going on if yes return error string
3. add into liveMatchDetails hash map for reference

#updateScore

Method used to update the score when there is a goal scored by any of the team. Accepts 4 parameters
homeTeamScore and awayTeamScore expects like when there is any goal scored by any of the team like 1-0 format(it can handle more than one score also, but it will add with the existing scores present)
which team scores then the team will have 1, and it will add with the existing score stored in the hash map
1. Verify the team names and scores inputs
2. Verify there is already a live match is going on for the team
3. Updates the score by adding existing scores stored in the map along with current input scores of the team.

#endGame

Method accepts two parameters and get called when match is over for a pair of teams.
1. Verify if there is an existing match going on for the team
2. Add FootballMatchDetails into historyOfMatch list
3. Remove FootballMatchDetails from liveMatchDetails hash map

#historyOfMatches

Method returns historyOfMatches list which is completed in that period.
1. Get the historyOfMatches list which holds the details of matches which is completed
2. Reverse the list, since we need last completed match should come in the list first
3. Sort the list based on the total goal scored in the match
4. Return the list.


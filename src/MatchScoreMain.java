import football.FootballScoreBoardCalculator;

public class MatchScoreMain {

    public static void main(String args[]) {

        FootballScoreBoardCalculator.startGame("Mexico", "Canada");
        FootballScoreBoardCalculator.updateScore("Mexico", 1, "Canada", 0);
        FootballScoreBoardCalculator.updateScore("Mexico", 1, "Canada", 1);
        FootballScoreBoardCalculator.endGame("Mexico", "Canada");

        FootballScoreBoardCalculator.startGame("Spain", "Brazil");
        FootballScoreBoardCalculator.updateScore("Spain", 1, "Brazil", 1);
        FootballScoreBoardCalculator.updateScore("Spain", 3, "Brazil", 0);
        FootballScoreBoardCalculator.startGame("Germany", "France");
        FootballScoreBoardCalculator.updateScore("Germany", 1, "France", 1);
        FootballScoreBoardCalculator.endGame("Spain", "Brazil");
        FootballScoreBoardCalculator.updateScore("Germany", 3, "France", 2);
        FootballScoreBoardCalculator.endGame("Germany", "France");

        FootballScoreBoardCalculator.historyOfMatches();

    }
}


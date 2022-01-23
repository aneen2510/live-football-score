package football;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FootballScoreBoardCalculatorTest {

    @Test
    public void testStartGame_Success() {
        String result = FootballScoreBoardCalculator.startGame("GER", "AUS");
        Assert.assertEquals("GER vs AUS Match Started", result);
        FootballScoreBoardCalculator.clearHistory();
    }

    //Method verifies all error scenarios in start game method are working properly
    @Test
    public void testStartGame_errors() {
        String result = FootballScoreBoardCalculator.startGame("AUS", "");
        Assert.assertEquals("Team names are not valid", result);
        result = FootballScoreBoardCalculator.startGame("AUS", null);
        Assert.assertEquals("Team names are not valid", result);
        result = FootballScoreBoardCalculator.startGame("AUS", "AUS");
        Assert.assertEquals("Team names are not valid", result);
        FootballScoreBoardCalculator.startGame("GER", "FRA");

        //Checking whether there is another live match going on for the entered teams.
        result = FootballScoreBoardCalculator.startGame("GER", "AUS");
        Assert.assertEquals("Existing live match not over for the team", result);
    }

    @Test
    public void testUpdateGame_success() {
        FootballScoreBoardCalculator.startGame("Canada", "Mexico");
        String result = FootballScoreBoardCalculator.updateScore("Canada", 1, "Mexico", 0);
        Assert.assertEquals("Score : Canada-1:Mexico-0", result);

        //Checking whether the scores are getting added up
        FootballScoreBoardCalculator.updateScore("Canada", 1, "Mexico", 0);
        result = FootballScoreBoardCalculator.updateScore("Canada", 0, "Mexico", 1);
        Assert.assertEquals("Score : Canada-2:Mexico-1", result);
        FootballScoreBoardCalculator.clearHistory();
    }

    //Method verifies all error scenarios in update game method are working properly
    @Test
    public void testUpdateGame_errors() {
        FootballScoreBoardCalculator.startGame("Canada", "Mexico");

        //Checking if there is a live match for the entered Team names.
        String result = FootballScoreBoardCalculator.updateScore("Spain", 1, "Mexico", 1);
        Assert.assertEquals("There is no live match for the teams", result);

        //Checking whether the team names are entered properly.
        result = FootballScoreBoardCalculator.updateScore("", 1, "Mexico", 1);
        Assert.assertEquals("Team names are not valid", result);
        result = FootballScoreBoardCalculator.updateScore("Mexico", 1, "Mexico", 1);
        Assert.assertEquals("Team names are not valid", result);

        //Checking whether the scores are entered properly.
        result = FootballScoreBoardCalculator.updateScore("Canada", null, "Mexico", 1);
        Assert.assertEquals("Score values must be valid", result);


    }

    @Test
    public void testEndGame_success() {
        FootballScoreBoardCalculator.startGame("Sweden", "Argentina");
        FootballScoreBoardCalculator.updateScore("Sweden", 1, "Argentina", 2);
        String result = FootballScoreBoardCalculator.endGame("Sweden", "Argentina");
        Assert.assertEquals("Sweden vs Argentina Match has ended with score 1-2", result);
        FootballScoreBoardCalculator.clearHistory();
    }

    //Method verifies all error scenarios in update game method are working properly
    @Test
    public void testEndGame_error() {
        String result = FootballScoreBoardCalculator.endGame("Sweden", "Denmark");
        Assert.assertEquals("There is no live match for the teams", result);
    }

    //Test method creates in the same order as requested in the challenge and expecting result list is based
    // on total count of scores and also in the order of last end will displays first
    @Test
    public void testHistoryOfMatches() {
        FootballScoreBoardCalculator.startGame("Mexico", "Canada");
        FootballScoreBoardCalculator.updateScore("Mexico", 0, "Canada", 2);
        FootballScoreBoardCalculator.updateScore("Mexico", 0, "Canada", 1);
        FootballScoreBoardCalculator.updateScore("Mexico", 0, "Canada", 2);
        FootballScoreBoardCalculator.endGame("Mexico", "Canada");

        FootballScoreBoardCalculator.startGame("Spain", "Brazil");
        FootballScoreBoardCalculator.updateScore("Spain", 2, "Brazil", 0);
        FootballScoreBoardCalculator.updateScore("Spain", 3, "Brazil", 1);
        FootballScoreBoardCalculator.updateScore("Spain", 4, "Brazil", 1);
        FootballScoreBoardCalculator.updateScore("Spain", 1, "Brazil", 0);
        FootballScoreBoardCalculator.endGame("Spain", "Brazil");

        FootballScoreBoardCalculator.startGame("Germany", "France");
        FootballScoreBoardCalculator.updateScore("Germany", 1, "France", 2);
        FootballScoreBoardCalculator.updateScore("Germany", 1, "France", 0);
        FootballScoreBoardCalculator.endGame("Germany", "France");

        FootballScoreBoardCalculator.startGame("Uruguay", "Italy");
        FootballScoreBoardCalculator.updateScore("Uruguay", 2, "Italy", 1);
        FootballScoreBoardCalculator.updateScore("Uruguay", 1, "Italy", 2);
        FootballScoreBoardCalculator.updateScore("Uruguay", 2, "Italy", 1);
        FootballScoreBoardCalculator.updateScore("Uruguay", 1, "Italy", 2);
        FootballScoreBoardCalculator.endGame("Uruguay", "Italy");

        FootballScoreBoardCalculator.startGame("Argentina", "Australia");
        FootballScoreBoardCalculator.updateScore("Argentina", 1, "Australia", 2);
        FootballScoreBoardCalculator.updateScore("Argentina", 1, "Australia", 0);
        FootballScoreBoardCalculator.endGame("Argentina", "Australia");


        List<FootballMatchDetails> matchDetailsList = FootballScoreBoardCalculator.historyOfMatches();
        Assert.assertEquals(5, matchDetailsList.size());
        Assert.assertEquals("Uruguay", matchDetailsList.get(0).getHomeTeam());
        Assert.assertEquals(6, matchDetailsList.get(0).getHomeTeamScore());
        Assert.assertEquals("Spain", matchDetailsList.get(1).getHomeTeam());
        Assert.assertEquals("Mexico", matchDetailsList.get(2).getHomeTeam());
        Assert.assertEquals("Argentina", matchDetailsList.get(3).getHomeTeam());
        Assert.assertEquals("Germany", matchDetailsList.get(4).getHomeTeam());
        FootballScoreBoardCalculator.clearHistory();

    }
}

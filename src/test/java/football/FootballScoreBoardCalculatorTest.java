package football;

import org.junit.Assert;
import org.junit.Test;

public class FootballScoreBoardCalculatorTest {

    @Test
    public void testStartGame_Success() {
        String result = FootballScoreBoardCalculator.startGame("GER", "AUS");
        Assert.assertEquals("GER vs AUS Match Started", result);
    }

    public void testUpdateScore() {
    }

    public void testEndGame() {
    }

    public void testHistoryOfMatches() {
    }
}
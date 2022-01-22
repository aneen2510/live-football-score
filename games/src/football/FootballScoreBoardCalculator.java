package football;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FootballScoreBoardCalculator {

    private static ArrayList<FootballMatchDetails> historyOfMatches = new ArrayList<>();
    private static HashMap<String,FootballMatchDetails>liveMatchDetails = new HashMap<>();

    public static String startGame(String homeTeam,String awayTeam) {

        if (homeTeam == null || awayTeam == null) {
            return "Please enter Team name";
        }
        FootballMatchDetails footballMatch = new FootballMatchDetails();
        footballMatch.setHomeTeam(homeTeam);
        footballMatch.setAwayTeam(awayTeam);
        liveMatchDetails.put(homeTeam, footballMatch);

        String output = homeTeam + " vs " + awayTeam + " Match Started ";
        System.out.println(output);
        return output;
    }

    public static String updateScore(String homeTeam,int homeTeamScore,String awayTeam,int awayTeamScore){

        FootballMatchDetails footballMatch = liveMatchDetails.get(homeTeam);
        footballMatch.setHomeTeamScore(footballMatch.getHomeTeamScore()+homeTeamScore);
        footballMatch.setAwayTeamScore(footballMatch.getAwayTeamScore()+awayTeamScore);
        liveMatchDetails.replace(homeTeam,footballMatch);
        String output = "Score : "+homeTeam + "="+footballMatch.getHomeTeamScore()+ ":" + awayTeam + "="+footballMatch.getAwayTeamScore();
        System.out.println(output);
        return output;
    }

    public static String endGame(String homeTeam,String awayTeam){

        FootballMatchDetails footballMatch = liveMatchDetails.get(homeTeam);
        historyOfMatches.add(footballMatch);

        liveMatchDetails.remove(homeTeam);
        String output = homeTeam + " vs "+ awayTeam + " Match has ended with score "+footballMatch.getHomeTeamScore()+ ":"+ footballMatch.getAwayTeamScore() ;
        System.out.println(output);
        return output;

    }

    public static List<FootballMatchDetails> historyOfMatches(){


        historyOfMatches.forEach(match ->
                System.out.println(match.getHomeTeam()+" - "+match.getAwayTeam() +" : " +match.getHomeTeamScore()
                        +" - "+match.getAwayTeamScore()));

        return historyOfMatches;

    }



}

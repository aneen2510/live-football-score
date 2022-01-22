package football;

import java.util.*;

public class FootballScoreBoardCalculator {

    private static ArrayList<FootballMatchDetails> historyOfMatches = new ArrayList<>();
    private static HashMap<String, FootballMatchDetails> liveMatchDetails = new HashMap<>();
    private static FootballMatchDetails footballMatch;
    private static final Comparator<FootballMatchDetails> scoreComparator = getScoreComparator();


    /**
     * Method to start game.
     * After verifying it will create new entry in liveMatchDetails HashMap
     * @param homeTeam Home Team name.
     * @param awayTeam Away Team name.
     * @return
     */
    public static String startGame(String homeTeam, String awayTeam) {

        //Verifying Team names are valid
        if (!isValidTeamName(homeTeam, awayTeam)) {
            return "Team names are not valid";
        }

        //Verifying given names have any match in existing live match team names
        //using keySet of live match hashmap
        boolean isLiveMatchExist = liveMatchDetails.keySet().stream().anyMatch(keyValue
                -> keyValue.contains(homeTeam) || keyValue.contains(awayTeam));
        if (isLiveMatchExist) {
            return "Existing live match not over for the team";
        }

        footballMatch = new FootballMatchDetails();
        footballMatch.setHomeTeam(homeTeam);
        footballMatch.setAwayTeam(awayTeam);
        liveMatchDetails.put((homeTeam + awayTeam), footballMatch);

        String output = homeTeam + " vs " + awayTeam + " Match Started";
        System.out.println(output);
        return output;
    }

    /**
     * Method to update the existing live match scores when there is any of the team scores
     * When there is a goal scored it will add to the existing score and update the liveMatchDetail HashMap
     * @param homeTeam
     * @param homeTeamScore
     * @param awayTeam
     * @param awayTeamScore
     * @return
     */
    public static String updateScore(String homeTeam, Integer homeTeamScore, String awayTeam, Integer awayTeamScore) {
        if (!isValidTeamName(homeTeam, awayTeam)) {
            return "Team names are not valid";
        }
        if (homeTeamScore == null || awayTeamScore == null) {
            return "Score values must be valid";
        }

        String keyValue = homeTeam + awayTeam;

        //Checking whether there is already a live match is going on
        if (liveMatchDetails.containsKey(keyValue)) {

            footballMatch = liveMatchDetails.get(keyValue);
            footballMatch.setHomeTeamScore(footballMatch.getHomeTeamScore() + homeTeamScore);
            footballMatch.setAwayTeamScore(footballMatch.getAwayTeamScore() + awayTeamScore);
            liveMatchDetails.replace(keyValue, footballMatch);
            String output = "Score : " + homeTeam + "=" + footballMatch.getHomeTeamScore() + ":" + awayTeam + "=" + footballMatch.getAwayTeamScore();
            System.out.println(output);
            return output;
        }
        return "There is no live match for the teams";
    }

    /**
     * Method for end game.
     * When the game ends it will remove the match from liveMatchDetail hash map
     * and adds into historyOfMatches arraylist for Match Summary usage
     * @param homeTeam
     * @param awayTeam
     * @return
     */
    public static String endGame(String homeTeam, String awayTeam) {

        String keyValue = homeTeam + awayTeam;
        if (liveMatchDetails.containsKey(keyValue)) {
            FootballMatchDetails footballMatch = liveMatchDetails.get(keyValue);
            historyOfMatches.add(footballMatch);

            liveMatchDetails.remove(keyValue);
            String output = homeTeam + " vs " + awayTeam + " Match has ended with score " + footballMatch.getHomeTeamScore() + ":" + footballMatch.getAwayTeamScore();
            System.out.println(output);
            return output;
        }
        return "There is no live match for the teams";
    }

    /**
     * Method to display historyOfMatches.
     * Steps
     * 1. reverse the history of matches arraylist to have order of LIFO (Because Arraylist keeps Insertion order in sorting)
     * 2. Sort the list with a comparator which sorts based on the total score in a game
     * 3. print and return the list of MatchDetails
     * @return
     */
    public static List<FootballMatchDetails> historyOfMatches() {

        List<FootballMatchDetails> matchHistory = historyOfMatches;

        Collections.reverse(matchHistory);

        Collections.sort(matchHistory, scoreComparator);

        matchHistory.forEach(match ->
                System.out.println(match.getHomeTeam() + " - " + match.getAwayTeam() + " : " + match.getHomeTeamScore()
                        + " - " + match.getAwayTeamScore()));

        return matchHistory;

    }

    /**
     * Comparator created for sorting based on the total score
     * @return
     */
    private static Comparator<FootballMatchDetails> getScoreComparator() {
        Comparator<FootballMatchDetails> scoreComparator = new Comparator<FootballMatchDetails>() {
            @Override
            public int compare(FootballMatchDetails o1, FootballMatchDetails o2) {
                if ((o1.getHomeTeamScore() + o1.getAwayTeamScore()) < (o2.getHomeTeamScore() + o2.getAwayTeamScore())) {
                    return 1;
                } else if ((o1.getHomeTeamScore() + o1.getAwayTeamScore()) > (o2.getHomeTeamScore() + o2.getAwayTeamScore())) {
                    return -1;
                }
                return 0;
            }

        };
        return scoreComparator;
    }

    private static boolean isValidTeamName(String homeTeam, String awayTeam) {
        if (homeTeam == null || awayTeam == null) {
            return false;
        }
        if (homeTeam.isEmpty() || awayTeam.isEmpty()) {
            return false;
        }
        if (homeTeam.equals(awayTeam)) {
            return false;
        }
        return true;
    }


}

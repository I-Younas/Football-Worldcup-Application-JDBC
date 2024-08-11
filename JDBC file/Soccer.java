package project3;
import javax.xml.transform.Result;
import java.sql.* ;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;



public class Soccer {

    //TO TEST ****************
    final static String mydate = "'" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";



    public static void main(String[] args) throws SQLException {


        // Unique table names.  Either the user supplies a unique identifier as a command line argument, or the program makes one up.
        String tableName = "";
        int sqlCode = 0;      // Variable to hold SQLCODE
        String sqlState = "00000";  // Variable to hold SQLSTATE

        if (args.length > 0)
            tableName += args[0];
        else
            tableName += "exampletbl";

        // Register the driver.  You must register the driver before you can use it.
        try {
            DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());
        } catch (Exception cnfe) {
            System.out.println("Class not found");
        }

        // This is the url you must use for DB2.
        //Note: This url may not valid now ! Check for the correct year and semester and server name.
        String url = "jdbc:db2://winter2023-comp421.cs.mcgill.ca:50000/cs421";

        //REMEMBER to remove your user id and password before submitting your code!!
        String your_userid = "cs421g210";
        String your_password = "Z4VbUw3pq210";
        //AS AN ALTERNATIVE, you can just set your password in the shell environment in the Unix (as shown below) and read it from there.
        //$  export SOCSPASSWD=yoursocspasswd
        if (your_userid == null && (your_userid = System.getenv("SOCSUSER")) == null) {
            System.err.println("Error!! do not have a password to connect to the database!");
            System.exit(1);
        }
        if (your_password == null && (your_password = System.getenv("SOCSPASSWD")) == null) {
            System.err.println("Error!! do not have a password to connect to the database!");
            System.exit(1);
        }
        Connection con = DriverManager.getConnection(url, your_userid, your_password);
        Statement statement = con.createStatement();


        //start menu
        MainMenu(con, statement);


    }

    public static void MainMenu(Connection con, Statement statement){
        String mainMenu =
                "Soccer Main Menu \n" +
                        "     1. List information of matches of a country \n" +
                        "     2. Insert initial player information for a match \n" +
                        "     3. Find your favorite player's most watched performance \n" +
                        "     4. Exit Application \n" +
                        "Please Enter Your Option:";

        System.out.println(mainMenu);
        boolean loopVar = true;
        int userInput = 0;
        Scanner input = new Scanner(System.in);

        while (loopVar == true) {
            String temp;
            temp = input.nextLine();
            try {
                userInput = Integer.valueOf(temp);
                loopVar = false;
                if (userInput < 1 || userInput > 4) {
                    loopVar = true;
                    System.out.println("Invalid Input");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input");
            }
        }
        //FOR TESTING
//        System.out.println("Input value : " + userInput);

        switch(userInput){
            case(1): command1(con, statement); break;
            case(2): command2(con, statement); break;
            case(3): command3(con, statement); break;
            case(4):
                try{
                    statement.close ( ) ;
                    con.close ( ) ;
                    System.exit(0);
                }catch(SQLException e){
                    System.out.println(e);
                }

        }

    }

//TODO
    //CHANGE THE ZEROS TO NULL WHERE IT APPLIES
    public static void command1(Connection con, Statement statement){

        System.out.println("Select the Country: ");
        Scanner input = new Scanner(System.in);
        String country = "'" + input.nextLine() + "'";
        System.out.println("country inputed : " + country);
        String stmt1 = "with matchinfo as\n" +
                "--gets the corresponding country1 country2 and date\n" +
                "(select mid, country1, country2, dateandtime, round from matches\n" +
                "where country1 = "+country+" or country2 = "+country+"),\n" +
                "    --get goals scored by each country for each match selected\n" +
                "    goalinfo as(SELECT t1.mid, team1goals, team2goals\n" +
                "                FROM (\n" +
                "                    --matches that have been played before 3 days later upper bound\n" +
                "                    SELECT matches.mid, count(Occurrence) as team1goals\n" +
                "                      FROM matches\n" +
                "                             JOIN scoredfor ON matches.mid = scoredfor.mid AND matches.Country1 = scoredfor.Country\n" +
                "                      and matches.DATEANDTIME < "+mydate+"\n" +
                "                    GROUP BY matches.mid, COUNTRY\n" +
                "\n" +
                "                    union\n" +
                "                    --matches that havent been played yet\n" +
                "                    (select matches.mid, null as \"team1goals\"\n" +
                "                     from matches where matches.DATEANDTIME > "+mydate+"\n" +
                "                                    and matches.DATEANDTIME <= ADD_DAYS("+mydate+", 3)\n" +
                "                    )\n" +
                "\n" +
                "                    --matches that have been played but no goals\n" +
                "                    union\n" +
                "                    (select matches.mid, 0 as \"team1goals\" from MATCHES\n" +
                "                        where matches.DATEANDTIME < "+mydate+" and matches.mid not in (SELECT matches.mid\n" +
                "                      FROM matches\n" +
                "                             JOIN scoredfor ON matches.mid = scoredfor.mid AND matches.Country1 = scoredfor.Country\n" +
                "                      and matches.DATEANDTIME < "+mydate+"\n" +
                "                    GROUP BY matches.mid, COUNTRY))\n" +
                "                      ) as t1\n" +
                "\n" +
                "                         JOIN\n" +
                "\n" +
                "                     (--matches that have been played before 3 days later upper bound\n" +
                "                         SELECT matches.mid, count(Occurrence) as team2goals\n" +
                "                         FROM matches\n" +
                "                                  JOIN scoredfor ON matches.mid = scoredfor.mid AND matches.Country2 = scoredfor.Country\n" +
                "                             and matches.DATEANDTIME < "+mydate+"\n" +
                "                         GROUP BY matches.mid, COUNTRY\n" +
                "\n" +
                "                         union\n" +
                "                         --matches that havent been played yet\n" +
                "                         (select matches.mid, null as \"team2goals\"\n" +
                "                          from matches where matches.DATEANDTIME > "+mydate+"\n" +
                "                                         and matches.DATEANDTIME <= ADD_DAYS("+mydate+", 3)\n" +
                "                         )\n" +
                "                         --matches that have been played but no goals\n" +
                "                         union\n" +
                "                         (select matches.mid, 0 as \"team2goals\" from MATCHES\n" +
                "                          where matches.DATEANDTIME < "+mydate+" and matches.mid not in\n" +
                "                                (SELECT matches.mid\n" +
                "                               FROM matches\n" +
                "                                        JOIN scoredfor ON matches.mid = scoredfor.mid\n" +
                "                                        AND matches.Country2 = scoredfor.Country\n" +
                "                                   and matches.DATEANDTIME < "+mydate+"\n" +
                "                               GROUP BY matches.mid, COUNTRY))) as t2\n" +
                "                     ON t1.mid = t2.mid\n" +
                "                ),\n" +
                "\n" +
                "    --get seats sold for corresponding match\n" +
                "    ticketinfo as ((select TICKETS.MID, count(*) as \"soldSeats\"\n" +
                "                 from TICKETS, RESERVE\n" +
                "                 where reserve.tid = tickets.TID\n" +
                "                 group by tickets.mid)\n" +
                "\n" +
                "                 union\n" +
                "                 (select tickets.mid, 0 as \"soldSeats\"\n" +
                "                  from tickets\n" +
                "                  where tickets.MID not in\n" +
                "                        (select TICKETS.MID\n" +
                "                         from TICKETS, RESERVE\n" +
                "                         where reserve.tid = tickets.TID\n" +
                "                         group by tickets.mid)\n" +
                "                  group by tickets.mid\n" +
                "                  )\n" +
                "                 )\n" +
                "\n" +
                "select matchinfo.mid, matchinfo.COUNTRY1, matchinfo.COUNTRY2, matchinfo.dateandtime,\n" +
                "       matchinfo.ROUND, goalinfo.team1goals, goalinfo.team2goals, ticketinfo.\"soldSeats\"\n" +
                "           from matchinfo, goalinfo, ticketinfo\n" +
                "            where matchinfo.mid = goalinfo.MID and matchinfo.mid = ticketinfo.mid;";

        try {
            ResultSet rs = statement.executeQuery(stmt1);
//                    System.out.println("prepCountry : "+prepareCountry.);

            while (rs.next()) {
//                        String MID = rs.getString("MID");
                String COUNTRY1 = rs.getString("COUNTRY1");
                String COUNTRY2 = rs.getString("COUNTRY2");
                Date DATEANDTIME = rs.getDate("DATEANDTIME");
                String ROUND = String.format("%-20s",rs.getString("ROUND"));
                Integer SOLDSEATS = rs.getInt("SOLDSEATS");

                //check for null?
                Integer TEAM1GOALS = rs.getInt("TEAM1GOALS");
                StringBuilder teamgoal1 = new StringBuilder();
                if (rs.getObject("TEAM1GOALS") == null) {
                    TEAM1GOALS = null;
                    String.format("%-20s", teamgoal1.append("null"));

                }
                else String.format("%-20s", teamgoal1.append(TEAM1GOALS.toString()));

                Integer TEAM2GOALS = rs.getInt("TEAM2GOALS");
                StringBuilder teamgoal2 = new StringBuilder();
                if (rs.getObject("TEAM2GOALS") == null) {
                    TEAM2GOALS = null;
                    String.format("%-20s", teamgoal2.append("null"));
                } else String.format("%-20s", teamgoal2.append(TEAM2GOALS.toString()));


                System.out.println(String.format("%-10s",COUNTRY1) + "\t"  + String.format("%-10s",COUNTRY2) + "\t" + DATEANDTIME + "\t"
                        + ROUND + "\t" + String.format("%-10s",teamgoal1) +
                        "\t" + String.format("%-10s",teamgoal2) + "\t" + String.format("%-20s", SOLDSEATS));

            }
            rs.close();

            while(true) {
                //check for next user input
                System.out.println("\n Enter [A] to find matches of another country, [P] to go to the previous menu");
                String temp = input.nextLine();
                if (temp.equals("A")) command1(con, statement);
                else if (temp.equals("P")) MainMenu(con, statement);
                else {
                    System.out.println("Invalid Output, try again");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //displays the matches that are scheduled for the next 3 days
    public static void command2(Connection con, Statement statement){

        System.out.println("Matches for the next three days : \n");
        String next3daysMatches = "SELECT matches.mid, matches.country1, matches.country2, matches.dateandtime,matches.round\n" +
                "from matches where matches.dateandtime < ADD_DAYS(" + mydate +", 3) AND matches.dateandtime >= "+ mydate ;
        ArrayList<String> mIDList = new ArrayList<>();
        try{
            ResultSet rs = statement.executeQuery(next3daysMatches);
//                    System.out.println("prepCountry : "+prepareCountry.);

            while (rs.next()) {
                String MID = String.format( rs.getString("MID"));
                mIDList.add(MID);
                String COUNTRY1 = String.format("%-15s", rs.getString("COUNTRY1"));
                String COUNTRY2 = String.format("%-15s", rs.getString("COUNTRY2"));
                Date DATEANDTIME = rs.getDate("DATEANDTIME");
                String ROUND = String.format("%-20s",rs.getString("ROUND"));

                System.out.println(MID + "\t" + COUNTRY1 + "\t"  + COUNTRY2 + "\t" + DATEANDTIME + "\t"
                        + ROUND );

            }
            rs.close();

        }catch(SQLException e){
            System.out.println(e);
        }
//        System.out.println(mIDList);
        while(true){
            System.out.println("Enter an mID in which a player is going to be added, or  \n Enter [P] to return to Main Menu: ");
            Scanner input = new Scanner(System.in);
            String tmp = input.nextLine();

            //if input mid is valid
            if (mIDList.contains(tmp)) {
                System.out.println("Enter one of the corresponding Countries playing in the selected Match :");
                String tmpCountry = "'" + input.nextLine() + "'";
                //TODO
                //handle bad country
                command2Players(con, statement, tmp, tmpCountry);
            }
            else if (tmp.equals("P")) MainMenu(con, statement);
            else System.out.println("Invalid mID input, try again");
        }
    }

    //displays the players selected/not selected for the match given by user
    public static void command2Players(Connection con, Statement statement, String mID, String country){
        System.out.println("The following players from " + country + " are already entered for match "+mID+":");
        String alreadyPlay =
                "SELECT participant.name, participant.pid, playerstats.EXACTPOSITION,\n" +
                        "       playerstats.STARTINGTIME, PLAYERSTATS.LEAVINGTIME,\n" +
                        "players.shirtnumber \n"+
                        "FROM teams, players, playerstats, matches, participant\n" +
                        "WHERE teams.country=players.country AND\n" +
                        "      playerstats.mid = " + mID +" and\n" +
                        "        playerstats.pid = players.pid AND\n" +
                        "        playerstats.mid = matches.mid AND\n" +
                        "        participant.pid=players.pid AND\n" +
                        "        players.country = " + country +";";
//        System.out.println(alreadyPlay);

        //stores the number of players already registered, because have to check for a max of 11
        int numbEntered = 0;
        try{
            ResultSet rs = statement.executeQuery(alreadyPlay);
//                    System.out.println("prepCountry : "+prepareCountry.);

            while (rs.next()) {
                String NAME = String.format("%-20s", rs.getString("NAME"));
                String PID = String.format("%-10s", rs.getString("PID"));
                String EXACTPOSITION = String.format("%-30s", rs.getString("EXACTPOSITION"));
                String STARTINGTIME = String.format("%-10s", rs.getString("STARTINGTIME"));
                String LEAVINGTIME = String.format("%-10s",rs.getString("LEAVINGTIME"));
                String SHIRTNUMBER = String.format("%-10s",rs.getString("SHIRTNUMBER"));

                System.out.println(NAME + "\t" + SHIRTNUMBER + "\t"  + String.format("%-10s",EXACTPOSITION) +
                        "\t" + STARTINGTIME + "\t" + LEAVINGTIME );
                numbEntered ++;

            }
            rs.close();

        }catch(SQLException e){
            System.out.println(e);
        }
        System.out.println();
        //start player insert menu
        command2PlayerInsert(con, statement, mID, country, numbEntered);

    }

    public static void command2PlayerInsert(Connection con, Statement statement, String mID, String country, int numbEntered){

        System.out.println("Possible Players from "+country+" not yet selected :\n");
        String playersNoSelect =
                "select distinct participant.name, players.pid, players.position, players.shirtnumber\n" +
                        "from matches, participant, players, PLAYERSTATS\n" +
                        "where (players.country = matches.COUNTRY1 or players.country = matches.COUNTRY2)\n" +
                        "  and participant.pid = players.pid\n" +
                        "  and matches.mid = " + mID + " and players.country = " +country +"\n" +
                        "  and players.pid not in\n" +
                        "\n" +
                        "(select  distinct playerstats.pid from PLAYERSTATS, matches, players\n" +
                        "            where matches.mid = " + mID +" and players.pid = playerstats.PID\n" +
                        "              and matches.mid = playerstats.mid\n" +
                        "              and (players.country = matches.COUNTRY1 or players.country = matches.COUNTRY2))";

        int index = 0;

        ArrayList<PlayerStat> playerAvailable = new ArrayList<>();

        try{
            ResultSet rs = statement.executeQuery(playersNoSelect);
//                    System.out.println("prepCountry : "+prepareCountry.);

            while (rs.next()) {
                index++;
                String NAME = rs.getString("NAME");
                String PID = rs.getString("PID");
                String POSITION = rs.getString("POSITION");
                String SHIRTNUMBER = rs.getString("SHIRTNUMBER");

                System.out.println(index + ". " +String.format("%-25s",NAME) + "\t"
                        + String.format("%-15s",SHIRTNUMBER)
                        + "\t"  + String.format("%-20s",POSITION));
                playerAvailable.add(new PlayerStat(PID, POSITION));

            }
            rs.close();

        }catch(SQLException e){
            System.out.println(e);
        }

//        System.out.println("index : "+index);
        while(true){
            System.out.println("Enter the number of the player you want to insert or " +
                    "\n [P] to go to  Main Menu");
            Scanner input = new Scanner(System.in);
            String tmp = input.nextLine();
            if (tmp.equals("P")) MainMenu(con, statement);

            else if(Integer.valueOf(tmp) >=1 && Integer.valueOf(tmp) <=index){
//                System.out.println("PID insert : "+playerAvailable.get(Integer.valueOf(tmp) -1).getPID() +
//                        "\n Pos insert : "+playerAvailable.get(Integer.valueOf(tmp)).getPos());
                String playerInsert = "insert into playerstats values(" +mID +
                        "," +playerAvailable.get(Integer.valueOf(tmp) -1).getPID()
                        +",0,NULL," + "'" +playerAvailable.get(Integer.valueOf(tmp) -1).getPos() + "'" +",null);";

//                System.out.println("Insert statement : \n "+playerInsert);

                //check for max of 11 players
                if (numbEntered == 11){
                    System.out.println("A maximum of 11 players are already registered for the match, \n" +
                            "return to match selection \n");
                    command2(con, statement);
                }

                //Now insert the player into playerstats
                try{
                    statement.executeUpdate ( playerInsert ) ;
                    System.out.println("\n Player Successfully Added! \n");
                }catch(SQLException e){
                    System.out.println(e);
                }
                command2Players(con, statement, mID, country);

            }
            else System.out.println("Invalid Input, try again");
        }
    }


    //user enters favorite player country, shirt number
    // and we provide the goal scored by the correspoding player that had the most
    // attendance during the match
    public static void command3(Connection con, Statement statement){
        System.out.println("Find out the number of goals scored by your favorite " +
                "player during their most viewed match");
        System.out.println("\n Enter the player's country :");
        Scanner input = new Scanner(System.in);
        String country = "'" + input.nextLine() + "'";
        System.out.println("Enter the player's shirt number :");
        String shirtNum = input.nextLine();

        String getPerformance =
                "with tmp as\n" +
                        "(select bestmatch.mid, max(bestMatch.\"ticketSold\") as \"maxSold\" from (\n" +
                        "    (select matches.mid, count(*) as \"ticketSold\" from matches, TICKETS, reserve\n" +
                        "     where TICKETS.MID=MATCHES.MID and matches.mid in\n" +
                        "       (select matches.mid from matches where matches.mid in\n" +
                        "      (select SCOREDBY.MID from SCOREDBY,\n" +
                        "        players  where SCOREDBY.PID = players.pid\n" +
                        "        and players.SHIRTNUMBER = "+shirtNum+" and players.COUNTRY = "+country+"))\n" +
                        "       and tickets.TID in (select reserve.TID from reserve)\n" +
                        "       and TICKETS.TID = RESERVE.TID\n" +
                        "     group by matches.mid)\n" +
                        ") as bestMatch group by bestmatch.mid)\n" +
                        "\n" +
                        "select tmp.MID, matches.COUNTRY1, matches.COUNTRY2, matches.DATEANDTIME,\n" +
                        "       matches.ROUND, count(*) as \"NUMBEROFGOALS\", tmp.\"maxSold\" as \"ATTENDANCE\", participant.NAME\n" +
                        "from tmp, SCOREDBY, matches, PARTICIPANT\n" +
                        "    where SCOREDBY.MID = tmp.MID and\n" +
                        "          tmp.mid = matches.mid and\n" +
                        "          SCOREDBY.PID = PARTICIPANT.PID and\n" +
                        "          SCOREDBY.PID in (select players.pid from players\n" +
                        "                            where players.SHIRTNUMBER = "+shirtNum+
                        " and players.COUNTRY = "+country+")\n" +
                        "--         group by tmp.mid\n" +
                        "        group by tmp.MID,tmp.\"maxSold\", matches.COUNTRY1,\n" +
                        "                 matches.COUNTRY2, matches.DATEANDTIME,matches.ROUND,participant.name;";

        try{
            ResultSet rs = statement.executeQuery(getPerformance);
//                    System.out.println("prepCountry : "+prepareCountry.);

            while (rs.next()) {
                String MID = rs.getString("MID");
                String COUNTRY1 = rs.getString("COUNTRY1");
                String COUNTRY2 = rs.getString("COUNTRY2");
                Date DATEANDTIME = rs.getDate("DATEANDTIME");
                String ROUND = rs.getString("ROUND");
                Integer NUMBEROFGOALS = rs.getInt("NUMBEROFGOALS");
                Integer ATTENDANCE = rs.getInt("ATTENDANCE");
                String NAME = rs.getString("NAME");

                System.out.println("Match : "+COUNTRY1+" vs "+COUNTRY2+
                        "\nDate : "+DATEANDTIME+"\t"+"Round : "+ROUND+
                        "\nNumber of goals scored by "+NAME+": "+NUMBEROFGOALS+
                        "\nAttendance during the match : "+ATTENDANCE);

            }
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }

        //user can choose another player or go back to main menu
        while(true){
            System.out.println("Enter [A] to find another player's most watched performance, or" +
                    "\nEnter [P] to return to Main Menu");
            String tmp = input.nextLine();

            if (tmp.equals("A")) command3(con, statement);
            else if (tmp.equals("P")) MainMenu(con, statement);
            else System.out.println("Invalid Input, try again");
        }




    }

}



//PlayerStat class used to store the useful information when inserting a player
class PlayerStat{
    private String pID;
    private String pos;

    public PlayerStat(String aID, String aPos){
        pID = aID;
        pos = aPos;
    }

    public void setPID(String aID){
        pID = aID;
    }
    public void setPos(String aPos){
        pos = aPos;
    }
    public String getPID(){
        return pID;
    }
    public String getPos(){
        return pos;
    }
}

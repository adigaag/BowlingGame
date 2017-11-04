# BowlingGame
Bowling Game

1) Setup Java Environment 1.8. Set JAVA_HOME environment variable to the JDK home location and JRE and JDK bin add in Path
2) Setup Maven Environment. Follow the steps here https://maven.apache.org/download.cgi download the binary, unzip in C drve. Setup M2_HOME and path variable to the bin folder.
3) Using github client clone from the URL - https://github.com/adigaag/BowlingGame.git to C:\BowlingGame
4) open command line and navigate to C:\BowlingGame and type mvn clean install. Junit Test classes will run too. Target folder should be created
5) Navigate to C:\BowlingGame\target on command line and type the below command
java -cp BowlingGame.jar com.bowlinggame.business.BowlingGame <pass input as command line arguments with space representing each attempt as metioned in the pdf>

public class Main {
    public static void main(String[] args) {
        String startTime = "0400-01-01T01:01:00";
        String endTime = "0401-01-01T01:01:0";
        long timeSecond = TimePoint.timeToSeconds(endTime) - TimePoint.timeToSeconds(startTime);
        //System.out.println(timeSecond / (24 * 60 * 60));

        System.out.println(TimePoint.secondsToTime(TimePoint.timeToSeconds("0405-01-31T01:01:01")));

    }
}
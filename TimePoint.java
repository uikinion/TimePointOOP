
public class TimePoint {
    private static final int[] monthDaysCount = new int[]{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public static long timeToSeconds(String time) {
        String[] parts = time.split("[-T:]");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        int hour = Integer.parseInt(parts[3]);
        int minute = Integer.parseInt(parts[4]);
        int second = Integer.parseInt(parts[5]);

        int countVisocosYears = getCountVisocosYears(year - 1);
        day += year * 365 + countVisocosYears;

        for (int i = 0; i < month - 1; i++) {
            day += mountToDays(year, i + 1);
        }

        return (long) day * 24 * 60 * 60 + (long) hour * 60 * 60 + (long) minute * 60 + second;
    }

    public static String secondsToTime(long second) {
        int day = (int) (second / (24 * 60 * 60));

        int year = day / 365;
        int countVisocosYears = getCountVisocosYears(year - 1);
        day = day % 365 - countVisocosYears;

        if (day <= 0) {
            year--;
            day += 365;
            if (yearIsVisocos(year)) {
                day++;
            }
        }

        int i = 0;
        while (true) {
            int mountDays = mountToDays(year, i + 1);
            if (day <= mountDays) {
                break;
            }
            day -=mountToDays(year, i + 1);
            i++;
        }
        int month = i + 1;

        second = second % (24 * 60 * 60);
        int hours = (int) (second / (60 * 60));
        second = second % (60 * 60);
        int minute = (int) (second / 60);
        second = second % 60;

        return String.format("{%d-%02d-%02dT%02d:%02d:%02d}", year, month, day, hours, minute, second);
    }

    private static int getCountVisocosYears(int year) {
        return year / 4 - year / 100 + year / 400;
    }


    private static boolean yearIsVisocos(int year) {
        if (year % 4 != 0) {
            return false;
        }
        else if (year % 100 != 0) {
            return true;
        }
        else if (year % 400 != 0) {
            return false;
        }
        else {
            return true;
        }
    }

    private static int mountToDays(int year, int month) {
        if (month != 2) {
            return monthDaysCount[month - 1];
        }
        else {
            if (yearIsVisocos(year)) {
                return monthDaysCount[1] + 1;
            }
            else {
                return monthDaysCount[1];
            }
        }
    }
}

package suka;


import Entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatsParser {
    public static User parseStats(String stats, String user_nickname){

        Matcher matcher = Pattern.compile("(.+),\\s(.+)\\n\uD83E\uDD1FБанда: (.+)\\n❤️Здоровье: \\d+\\/(.+)\\n☠️Голод: \\d+% \\/myfood\\n⚔️Урон: (\\d+) \uD83D\uDEE1Броня: (\\d+)\\n\\n\uD83D\uDCAAСила: (\\d+) \uD83C\uDFAFМеткость: (\\d+)\\n\uD83D\uDDE3Харизма: (\\d+) \uD83E\uDD38\uD83C\uDFFD\u200D♂️Ловкость: (\\d+)\\n\\n\uD83D\uDD0BВыносливость: \\d+\\/(\\d+)").matcher(stats);
        Matcher matcher2 = Pattern.compile("Пупсы: (\\d+)").matcher(stats);
        Matcher matcher3 = Pattern.compile("\uD83C\uDFF5(\\d+)").matcher(stats);
        Matcher matcher4 = Pattern.compile("ID(\\d+)").matcher(stats);

        User newUser = null;
        if (matcher.find()){
            System.out.println("1 group: " + matcher.group(1) +
                    "2 group: " + matcher.group(2) +
                    "3 group: " + matcher.group(3) +
                    "4 group: " + matcher.group(4) +
                    "5 group: " + matcher.group(5) +
                    "6 group: " + matcher.group(6) +
                    "7 group: " + matcher.group(7) +
                    "8 group: " + matcher.group(8) +
                    "9 group: " + matcher.group(9) +
                    "10 group: " + matcher.group(10));

            String userId = matcher4.find() ? matcher4.group(1) : "sukaaaa";
            Boolean isAdmin = false;
            isAdmin = userId.equals("718909829") || userId.equals("424686091");
            System.out.println("StatsParser, UserId " + userId);

            newUser = new User(
                    userId,
                    matcher.group(1),   // Nickname
                    user_nickname,      // Telegram Nickname
                    isAdmin, // Admin Permission
                    matcher.group(2),   // fraction
                    matcher.group(3),   // Band
                    matcher.group(4),   // Max_health
                    matcher.group(5),   // Damage
                    matcher.group(6),   // Defence
                    matcher.group(7),   // Power
                    matcher.group(8),   // Accuracy
                    matcher.group(9),   // Charisma
                    matcher.group(10),   // Agility
                    matcher.group(11),  // Stamina
                    matcher3.find() ? matcher3.group(1) : "0",  // Dzen
                    matcher2.find() ? matcher2.group(1) : "0",  // Donation Currency
                    String.valueOf(System.currentTimeMillis()));  // Last Update

            System.out.println(newUser.toString());
        }

        return newUser;
    }

    private static String match(String pattern, String stats) {
        String match = "No Matches";

        Matcher matcher = Pattern.compile(pattern)
                            .matcher(stats);

        if (matcher.find()){
            match = matcher.group(1);
        }

        return match;

    }
}

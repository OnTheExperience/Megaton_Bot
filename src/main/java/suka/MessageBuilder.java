package suka;

import Database.DBManager;
import Entity.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageBuilder {
    public static String showUserStats(String user_id) {
        User user = DBManager.getUser(user_id);

        Date result = new Date(Long.valueOf(user.getLast_update()));
        String timeIcon;
        if (result.getHours() > 6 && result.getHours() < 19) {
            timeIcon = "\uD83C\uDF1D";
        } else {
            timeIcon = "🌚";
        }

        DateFormat simple = new SimpleDateFormat("\n`⏰ dd.MM.yyyy HH:mm:ss " + timeIcon + "`");
        String admin = "*\n";
        System.out.println("Админка: " + (user.isAdmin() ? "есть" : "нет"));
        if (user.isAdmin()) {
            admin = "*\n`admin`\n";
        }
        String last_time = simple.format(result);
        System.out.println(simple.format(result));
        String suka ="👤 🎭*" + user.getNickname() + "* (@" + user.getTelegram_nickname() + "" +
                ")\n┌🏷*" + user.getId() + "*\n├" +
                "🤘*Банда*: " + user.getBand() + "\n├🔘" +
                "*Без титула*" + "\n├🏙*Гражданин*\n└🏕*Долбоёб*\n*Боевая мощь*:\n┌*" +
                getBMTitle(user).bmTitle + "*(📯" + getBMTitle(user).bm + ")\n├⚔️ *" +
                user.getDamage() + "* | 🛡 *" +
                user.getDefence() + "*\n├💪 *" +
                user.getPower() + "* 🔫 *" +
                user.getAccuracy() + "* ❤️ *" +
                user.getMax_health() + "*\n├🗣 *" +
                user.getCharisma() + "* 🤸🏽‍♂️ *" +
                user.getAgility() +  "*🔋 *" +
                user.getStamina() + "*\n├🏵 *" +
                user.getDzen() + "*\n└💈 *" +
                user.getDonation_currency() + admin +
                last_time;
        return suka;
    }

    public static BM getBMTitle(User user) {

        String bmTitle = "";
        int bm = Integer.parseInt(user.getAgility()) +
                 Integer.parseInt(user.getAccuracy()) +
                 Integer.parseInt(user.getCharisma()) +
                 Integer.parseInt(user.getMax_health());

        if (bm < 50)
            bmTitle = "\uD83E\uDDEAВоин из пробирки";
        else if (bm >= 50 && bm < 100)
            bmTitle = "\uD83E\uDDEBОпытный образец";
        else if (bm >= 100 && bm < 150)
            bmTitle = "\uD83E\uDDA0Ошибка природы";
        else if (bm >= 150 && bm < 300)
            bmTitle = "\uD83E\uDD90Планктон 150-300";
        else if (bm >= 300 && bm < 600)
            bmTitle = "\uD83C\uDF64Облучённый планктон";
        else if (bm >= 600 && bm < 1000)
            bmTitle = "\uD83D\uDC1BСовсем зелёный";
        else if (bm >= 1000 && bm < 1400)
            bmTitle = "\uD83D\uDC23Как у птенчика";
        else if (bm >= 1400 && bm < 1800)
            bmTitle = "\uD83C\uDF6CБарбариска";
        else if (bm >= 1800 && bm < 2200)
            bmTitle = "\uD83C\uDFA9Опытный выживший";
        else if (bm >= 2200 && bm < 2600)
            bmTitle = "\uD83E\uDD3AВоин Пустоши";
        else if (bm >= 2600 && bm < 3000)
            bmTitle = "\uD83C\uDF2AРадиоактивный ураган";
        else if (bm >= 3000 && bm < 3400)
            bmTitle = "\uD83D\uDC68\uD83C\uDFFF\u200D\uD83D\uDE80«Стармэн»";
        else if (bm >= 3400 && bm < 3800)
            bmTitle = "\uD83D\uDC51Иван-из-Мегатонны";
        else if (bm >= 3800 && bm < 4200)
            bmTitle = "\uD83E\uDD8BЧудотворец";
        else if (bm >= 4200 && bm < 4600)
            bmTitle = "\uD83E\uDD3AВоин Пустоши";
        else if (bm >= 4600 && bm < 5000)
            bmTitle = "⭐️Легендарный герой";
        else if (bm >= 5000 && bm < 5400)
            bmTitle = "\uD83C\uDF45Вождь Помидоров";
        else if (bm >= 5400 && bm < 5800)
            bmTitle = "\uD83E\uDDE8Любитель шахт";
        else if (bm >= 5800 && bm < 6200)
            bmTitle = "\uD83C\uDF30Крепкий орешек";
        else if (bm >= 6200 && bm < 6600)
            bmTitle = "\uD83E\uDD68Тёртый калач";
        else if (bm >= 6600 && bm < 7000)
            bmTitle = "\uD83E\uDDC2Соль земли";
        else if (bm >= 7000 && bm < 7200)
            bmTitle = "\uD83D\uDC7DТварь";
        else if (bm >= 7200)
            bmTitle = "\uD83E\uDDECВысшее существо";

        return new BM(bmTitle, bm);
    }

    private static class BM {
        public String bmTitle;
        public int bm;

        public BM(String bmTitle, int bm) {
            this.bmTitle = bmTitle;
            this.bm = bm;
        }
    }
}

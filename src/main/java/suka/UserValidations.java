package suka;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;

import java.util.Date;

public class UserValidations {
    public static boolean checkStatsMessage(String message, User forwardFrom, Bot bot, String chat_id, Update update) {
        Boolean isReplyFromWasteland = false, isAbout = false, last30minutes = false;
        if (forwardFrom != null) {
            String msg = "";

            isReplyFromWasteland
                    = forwardFrom
                    .getUserName()
                    .equals("WastelandWarsBot");

            isAbout = (message.contains("Пип-бой 3000")
                    && !message.contains("\uD83D\uDCDFПодробности /me"));

            if (!isAbout) {
                msg = "Принимаются только полные \uD83D\uDCDFПип-бои (результат команды /me в игре).";
            }

            Date dt = new Date();
            Long time = dt.getTime();
            Long tm = Long.valueOf(60000 / 2);
            time -= tm;

            String kek = String.valueOf(update.getMessage().getForwardDate());
            kek += "000";
            System.out.println("opa opa " + kek);
            Long shpek = Long.valueOf(kek);
            System.out.println("muda muda " + shpek);

            last30minutes = (shpek > time);
            System.out.println("Время отправленного сообщения " + shpek);
            System.out.println("30 секунд назад " + time);

            if (isAbout && !last30minutes) {
                msg += "\uD83D\uDCDFПип-бой должен быть не старше 30 секунд, получи в игре новый.";
            }

            if (!isAbout || !isReplyFromWasteland || !last30minutes) {
                bot.sendMsg(chat_id, msg, null);
            }

        }
        System.out.println(isAbout + "\n");
        System.out.println(isReplyFromWasteland + "\n");
        System.out.println(last30minutes + "\n");
        return (isAbout && isReplyFromWasteland && last30minutes);
    }
}

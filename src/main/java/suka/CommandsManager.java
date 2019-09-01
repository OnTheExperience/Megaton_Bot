package suka;

import Entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandsManager {

    public static void processCommand(String command, Bot bot, String user_id, User user, String chat_id) {

        switch (command) {
            case "\uD83D\uDCC2Профиль":
                String stats = MessageBuilder.showUserStats(user_id);
                bot.sendMsg(chat_id, stats, ButtonsManager.getButtons(1));
            break;
            case "\uD83D\uDCEBЧаты":
                bot.sendMsg(chat_id, "Вот все доступные чаты", ButtonsManager.getButtons(2));
                break;
            case "\uD83E\uDDFFАколиты Бафомета":
                bot.sendMsg(chat_id, "Обучающий чат для новчиков  \"\uD83E\uDDFFАколиты Бафомета\"\n" +
                        "https://t.me/joinchat/GSchPlLdPoK3XXCpH20ZHQ", ButtonsManager.getButtons(1));
                break;
            case "\uD83C\uDFC6Рейтинги":
                MessageBuilder.showRating(user, 1, bot, chat_id);
                break;
            case "\uD83D\uDD25Пылающий Котел":
                bot.sendMsg(chat_id, "Чат флудильня для токсиков и олдов  \"\uD83D\uDD25Пылающий Котел\"\n" +
                        "https://t.me/joinchat/BVtxVFkHFnuXSBBz2gT8ww", ButtonsManager.getButtons(1));
                break;
            case "💣Фракционный канал":
                bot.sendMsg(chat_id, "Фракционный канал:\n[https://t.me/mmo_public]", ButtonsManager.getButtons(1));
                break;
            case "\uD83D\uDC10Козел":
                bot.sendMsg(chat_id, "Всем желающим разваливать вражеских козлов на рейдовых точках или выполнить финальный квест офоромить заявку в лс @Theghostofpresent и в ней указать:\n" +
                        "1. Ваши ❤️ Здоровье / \uD83D\uDCAA Сила.\n" +
                        "2. Количество Выносливости\uD83D\uDD0B\n" +
                        "Все это под must have хештегом #антихайп\n" +
                        "\n" +
                        "Минимальные требования для вступления в Козла\n" +
                        "- ❤️ Здоровье /\uD83D\uDCAA Сила не меньше 150/150\n" +
                        "-  Желание рвать на рейдах\uD83C\uDF2A\n" +
                        "\n" +
                        "От нас: \n" +
                        "✅ Доступ к функциональным ботам козла\n" +
                        "✅ Организация захватов\n" +
                        "✅ Помощь в прокачке\n" +
                        "✅ Постоянная рейдовая активность\n" +
                        "✅ Различные внутренние конкурсы", ButtonsManager.getButtons(1));
                break;
            case "\uD83C\uDFADФракционные события":
                bot.sendMsg(chat_id, "Скоро..", ButtonsManager.getButtons(1));
                break;
            case "✖️Назад":
                bot.sendMsg(chat_id, "Главное меню:", ButtonsManager.getButtons(1));
                break;
            default:
                processComplicatedCommand(command, bot, user_id, user, chat_id);

        }

    }

    public static void processComplicatedCommand(String command, Bot bot, String user_id, User user, String chat_id) {

        if ( command.contains("➡️") || command.contains("⬅️") ) {
            Matcher matcher = Pattern.compile("(\\d+)").matcher(command);
            matcher.find();
            int page_number = Integer.parseInt(matcher.group(0));
            System.out.println("PAGE NUMBER IS " + page_number);
            MessageBuilder.showRating(user, page_number, bot, chat_id);

        } else {
            bot.sendMsg(chat_id, "Что ты хочешь?", ButtonsManager.getButtons(1));
        }

    }
}

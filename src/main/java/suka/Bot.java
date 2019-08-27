package suka;

import Database.DBManager;
import Entity.User;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import suka.ButtonsManager;
import suka.MessageBuilder;
import suka.StatsParser;
import suka.UserValidations;

import javax.management.openmbean.SimpleType;
import java.util.*;


public class Bot extends TelegramLongPollingBot {

    public String greeting;
    public Map<String, String> users;
    public static ArrayList<String> shit_buttons = new ArrayList<>();

    public synchronized void setButtons(SendMessage sendMessage,
                                        ArrayList<String> butts) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        int i = 0;

        while ( i != butts.size() ){

            KeyboardRow row = new KeyboardRow();

            row.add(new KeyboardButton(butts.get(i)));
            i++;

            if ( i == butts.size() ){

                keyboard.add(row);
                break;
            }

            row.add(new KeyboardButton(butts.get(i)));
            i++;

            keyboard.add(row);

        }

        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        String chat_id = update.getMessage().getChatId().toString();
        Long user_id = update.getMessage().getChat().getId();
        org.telegram.telegrambots.api.objects.User forwardFrom = update.getMessage().getForwardFrom();
        String userName = update.getMessage().getChat().getUserName();
        /*sendMsg(chat_id, "Здравствуй, странник!\n" +
                "Ты попал в пустошь, здесь ни у кого нет имен, лишь прозвища.\n" +
                "Как тебя величать?", null);*/
        System.out.println(forwardFrom);

        User user = DBManager.getUser(String.valueOf(user_id));

        if (user == null){
            if (UserValidations.checkStatsMessage(message, forwardFrom, this, chat_id, update)) {
                try {
                    if (userName.isEmpty()) {
                        sendMsg(chat_id, "Для регистрации необходим Username в телеграме!", null);
                    } else {
                        user = StatsParser.parseStats(message, userName);
                        if (!String.valueOf(user_id).equals(user.getId())) {
                            sendMsg(chat_id, "Это не твой \uD83D\uDCDFПип-бой, ID не совпадает.", null);
                        } else if (!user.getFraction().equals("💣Мегатонна") && !user.isAdmin()) {
                            sendMsg(chat_id, "Ты не состоишь во фракции \uD83D\uDCA3Мегатонна.", null);
                        } else {
                            DBManager.upsertUser(user);
                            sendMsg(chat_id, "Добро пожаловать в Мегатонну!", ButtonsManager.getButtons(1));
                        }
                    }
                } catch (Exception e) {
                    sendMsg(chat_id, "Ты мне чем в лицо тычешь? Ксиву давай!", ButtonsManager.getButtons(1));
                }
            } else {
                sendMsg(chat_id, "Добро пожаловать в самую не токсичную фракцию в игре.\nДокажи что ты достойный и скинь мне полный свой профиль /me", null);
            }
        } else {
            if (UserValidations.checkStatsMessage(message, forwardFrom, this, chat_id, update)) {
                    user = StatsParser.parseStats(message, userName);
                    DBManager.upsertUser(user);
                    sendMsg(chat_id, "Ваш профиль учтён!", ButtonsManager.getButtons(1));
            }
            CommandsManager.processCommand(message, this, String.valueOf(user_id), chat_id);
            //String suka = MessageBuilder.showUserStats(String.valueOf(user_id));
            //sendMsg(chat_id, suka, shit_buttons);
        }

        //User user = StatsParser.parseStats(message);
        //DBManager.addUser(user);



/*
*/
    }
        /*if (!UserDB.checkUser(user_id)){

            String firstName = update.getMessage().getChat().getFirstName();
            String secondName = update.getMessage().getChat().getLastName();
            String username = update.getMessage().getChat().getUserName();

            UserDB.newUser(user_id, firstName,
                    secondName, username);

        }

        User user = UserDB.getUser(user_id);

        EventProcessor.event(user.getEvent(), chat_id,
                this, user_id, message);





*/



        /*System.out.println("userId: " + user_id +
                "\nuser name: " + firstName +
                "\nmessage: " + message);


        if ( message.equals("/start") ) {

            if (!sdd.containsKey(user_id)) {
                sdd.put(user_id, 0);
            }

            if (sdd.get(user_id) == 0) {
                sendMsg(chat_id, "Здравствуй, странник!\n" +
                        "Ты попал в пустошь, здесь ни у кого нет имен, лишь прозвища.\n" +
                        "Как тебя величать?");
                sdd.put(user_id, 1);
            }

        } else if ( sdd.get(user_id) == 1 ) {

            names.put(user_id, message);
            sendMsg(chat_id, "Рад познакомиться, " + message + "!");
            sdd.put(user_id, 2);

        } else if ( sdd.get(user_id) == 2 ){

            sendMsg(chat_id, "Прости, " + names.get(user_id) + "" +
                    ", у меня сейчас нет времени на тебя.");

        }

    }

    /**
     * Метод для настройки сообщения и его отправки.
     * @param chatId id чата
     * @param s Строка, которую необходимот отправить в качестве сообщения.
     */
    public synchronized void sendMsg(String chatId, String s, ArrayList<String> butts) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);



        if ( butts != null ) {

            setButtons(sendMessage, butts);


        }

        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {

        }
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     * @return имя бота
     */
    public String getBotUsername() {
        return "megatonww_bot";
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     * @return token для бота
     */
    @Override
    public String getBotToken() {
        return "643589988:AAHko1xWJ21HxFEeKjdwIuYI8fsdDg9lKSk";
    }
}

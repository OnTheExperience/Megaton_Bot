package suka;

import java.util.ArrayList;
import java.util.Arrays;

public class ButtonsManager {
    public static ArrayList<String> getButtons(int event_id) {

        switch (event_id) {
            case 0:
                return null;
            case 1:
                return new ArrayList<String>(
                        Arrays.asList(
                                "\uD83D\uDCC2Профиль",
                                "\uD83C\uDFC6Рейтинги",
                                "\uD83D\uDCEBЧаты",
                                "\uD83D\uDC10Козел",
                                "\uD83C\uDFADФракционные события"));
            case 2:
                return new ArrayList<String>(
                        Arrays.asList(
                                "\uD83D\uDD25Пылающий Котел",
                                "\uD83E\uDDFFАколиты Бафомета",
                                "\uD83D\uDCA3Фракционный канал",
                                "✖️Назад"));
        }

        return null;
    }
}

package Database;

import Entity.User;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    public static ArrayList<String> runQuery(String query, String command) {
        ArrayList<String> fields = null;

        try {
            System.out.println("Подключаюсь к БД...");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/megatonna_bot" +
                            "?&useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root",
                    "000000");

            Statement stmt = con.createStatement();

            System.out.println("Команда: " + command);
            switch (command) {
                case "insert" :
                    stmt.executeUpdate(query);
                    break;
                case "select" :
                    ResultSet resultSet = stmt.executeQuery(query);
                    fields = parseResultSet(resultSet);
                    break;
            }

            con.close();
            System.out.println("Подключение к БД закрыто..");
        } catch (SQLException e) {
            System.out.println("Ошибка в подключении" +
                    "\nКоманда: " + command);
            System.out.println(e.getMessage());
        }
        return fields;
    }

    public static void upsertUser(User user) {

        String query = "insert into users(id,nickname,telegram_nickname,admin_permission,fraction,band,max_health,damage,defence,power,accuracy,charisma,agility,stamina,dzen,donation_currency,last_update)\n" +
                "values('" + user.getId() + "', " +
                "'" + user.getNickname() + "', " +
                "'" + user.getTelegram_nickname() + "', " +
                user.isAdmin() + ", " +
                "'" + user.getFraction() + "', " +
                "'" + user.getBand() + "', " +
                "'" + user.getMax_health() + "', " +
                "'" + user.getDamage() + "', " +
                "'" + user.getDefence() + "', " +
                "'" + user.getPower() + "', " +
                "'" + user.getAccuracy() + "', " +
                "'" + user.getCharisma() + "', " +
                "'" + user.getAgility() + "', " +
                "'" + user.getStamina() + "', " +
                "'" + user.getDzen() + "', " +
                "'" + user.getDonation_currency() + "', " +
                "'" + user.getLast_update() + "') " +
                "ON DUPLICATE KEY UPDATE " +
                "band = '" + user.getBand() +
                "', max_health = '" + user.getMax_health() +
                "', damage = '" + user.getDamage() +
                "', defence = '" + user.getDefence() +
                "', power = '" + user.getPower() +
                "', accuracy = '" + user.getAccuracy() +
                "', charisma = '" + user.getCharisma() +
                "', agility = '" + user.getAgility() +
                "', stamina = '" + user.getStamina() +
                "', dzen = '" + user.getDzen() +
                "', donation_currency = '" + user.getDonation_currency() +
                "', last_update = '" + user.getLast_update() + "'";
        System.out.println(query);
        DBManager.runQuery(query, "insert");
    }

    public static ArrayList<String> parseResultSet(ResultSet resultSet) {
        ArrayList<String> fields = new ArrayList<>();
        try {
            System.out.println("Достаю юзера из БД..");
            if (resultSet.next()) {
                for (int i = 1; i < 18; i++) {
                    System.out.println("Поле " + i + ": " + String.valueOf(resultSet.getString(i)));
                    fields.add(resultSet.getString(i));
                }
            } else {
                System.out.println("Юзер не найден");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fields;
    }

    public static User getUser(String user_id) {
        String query = "select * from users where id =\'" + user_id + "\'";
        System.out.println("Команда на выполнение: " + query);
        ArrayList<String> fields = runQuery(query, "select");

        if (!fields.isEmpty()) {
            User user = new User(
                    fields.get(0),
                    fields.get(1),
                    fields.get(2),
                    fields.get(3).equals("1") ? true : false,
                    fields.get(4),
                    fields.get(5),
                    fields.get(6),
                    fields.get(7),
                    fields.get(8),
                    fields.get(9),
                    fields.get(10),
                    fields.get(11),
                    fields.get(12),
                    fields.get(13),
                    fields.get(14),
                    fields.get(15),
                    fields.get(16));
            return user;
        } else {
            System.out.println("Юзер не найден");
        }

        return null;
    }
}

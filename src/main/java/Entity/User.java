package Entity;

public class User {
    private String id;
    private String nickname;
    private String telegram_nickname;
    private boolean adminPermission;
    private String fraction;
    private String band;
    private String max_health;
    private String damage;
    private String defence;
    private String power;
    private String accuracy;
    private String charisma;
    private String agility;
    private String stamina;
    private String dzen;
    private String donation_currency;
    private String last_update;
    public String error;

    public String getTelegram_nickname() { return telegram_nickname; }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isAdmin() {
        return adminPermission;
    }

    public String getFraction() { return fraction; }

    public String getBand() {
        return band;
    }

    public String getMax_health() {
        return max_health;
    }

    public String getDamage() {
        return damage;
    }

    public String getDefence() {
        return defence;
    }

    public String getPower() {
        return power;
    }

    public String getAgility() {
        return agility;
    }

    public String getCharisma() {
        return charisma;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public String getStamina() {
        return stamina;
    }

    public String getDzen() {
        return dzen;
    }

    public String getDonation_currency() {
        return donation_currency;
    }

    public String getLast_update() {
        return last_update;
    }

    public User(String id,
                String nickname,
                String telegram_nickname,
                boolean adminPermission,
                String fraction,
                String band,
                String max_health,
                String damage,
                String defence,
                String power,
                String accuracy,
                String charisma,
                String agility,
                String stamina,
                String dzen,
                String donation_currency,
                String last_update) {

        this.id = id;
        this.telegram_nickname = telegram_nickname;
        this.nickname = nickname;
        this.adminPermission = adminPermission;
        this.fraction = fraction;
        this.band = band;
        this.max_health = max_health;
        this.power = power;
        this.defence = defence;
        this.damage = damage;
        this.agility = agility;
        this.charisma = charisma;
        this.accuracy = accuracy;
        this.stamina = stamina;
        this.dzen = dzen;
        this.donation_currency = donation_currency;
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", telegram_nickname='" + telegram_nickname + '\'' +
                ", adminPermission=" + adminPermission +
                ", band='" + band + '\'' +
                ", max_health='" + max_health + '\'' +
                ", damage='" + damage + '\'' +
                ", defence='" + defence + '\'' +
                ", power='" + power + '\'' +
                ", accuracy='" + accuracy + '\'' +
                ", charisma='" + charisma + '\'' +
                ", agility='" + agility + '\'' +
                ", stamina='" + stamina + '\'' +
                ", dzen='" + dzen + '\'' +
                ", donation_currency='" + donation_currency + '\'' +
                ", last_update='" + last_update + '\'' +
                '}';
    }
}

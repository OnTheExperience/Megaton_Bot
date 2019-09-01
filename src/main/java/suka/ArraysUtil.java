package suka;

import Entity.User;

import java.util.ArrayList;

public class ArraysUtil {
    public static ArrayList<ArrayList<User>> splitArray(ArrayList<User> users) {

        ArrayList<ArrayList<User>> splittedArrays = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < users.size(); i += k) {
            k = (users.size() - 1 - i) >= 10 ? 10 : users.size() - i;
            System.out.println("k ==== " + k);
            ArrayList<User> group = new ArrayList<>(users.subList(i, i + k));
            splittedArrays.add(group);
        }
        System.out.println("RAZMER MASSIVA " + splittedArrays.size());

        for ( ArrayList<User> users1 : splittedArrays) {

            for (User user : users1) {
                System.out.println(user.toString());
            }

            System.out.println("---------------------------------");
        }
        return splittedArrays;
    }

    public static int findUserRating(User user, ArrayList<ArrayList<User>> userPages) {
        int rating = 0;
        for ( int i = 0; i < userPages.size(); i++ ) {
            for ( int j = 0; j < userPages.get(i).size(); j++ ) {
                if ( userPages.get(i).get(j).getId().equals(user.getId()) ) {
                    rating = j + 1 + i*10;
                }
            }
        }

        return rating;
    }

    public static boolean areUserOnPage(User user, ArrayList<ArrayList<User>> userPages, int page) {

        for ( User currentUser : userPages.get(page) ) {
            if ( user.getId().equals(currentUser.getId()) ) {
                return true;
            }
        }
        return false;
    }
}

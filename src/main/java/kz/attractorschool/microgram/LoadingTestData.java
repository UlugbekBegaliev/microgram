package kz.attractorschool.microgram;

import kz.attractorschool.microgram.entity.Comment;
import kz.attractorschool.microgram.entity.Publication;
import kz.attractorschool.microgram.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoadingTestData {

    private List<Publication> getPublication(List<User> users) {
        List<Publication> publication = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 1; i <= users.size(); i++) {
            for (int j = 0; j < rnd.nextInt(9) + 1; j++) {
             }
        }
        return publication;
    }

        private List<Comment> getComments(List<Publication> publications, List<User> users) {
        List<Comment> comments = new ArrayList<>();
        for (Publication publication : publications) {
            for (User user : users) {
//                comments.add(Comment.
//                       makeDescription(), LocalDate.now(), user));
            }
        }
        return comments;
    }
}



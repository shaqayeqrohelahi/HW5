import entity.Article;
import entity.User;
import repository.ArticleRepository;
import repository.DatabaseConnection;
import repository.UserRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseConnection databaseConnection = new DatabaseConnection();
        User user = new User();
        Article article = new Article();
        UserRepository userRepository = new UserRepository();
        ArticleRepository articleRepository = new ArticleRepository();

        System.out.println("Select your choice");
        int articleChoice;
        int choice;
        while (true) {
            System.out.println("1.Enter" + " \n2.sign up" + "\n3.show article" + "\n4.exist");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {

                case 1:
                    System.out.println("Enter your username");
                    user.setUsername(scanner.nextLine());
                    System.out.println("Enter your password");
                    user.setPassword(scanner.nextLine());
                    try {
                        if (userRepository.matchPassword(user)) {
                            articleRepository.showArticle();
                        }
                    } catch (SQLException e) {
                        System.out.println();
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Enter your name : ");
                        user.setUsername(scanner.nextLine());
                        System.out.println("Enter your nationalCode : ");
                        user.setNationalCode(scanner.nextLine());
                        System.out.println("Enter your birthday : ");
                        user.setBirthday(Date.valueOf(scanner.nextLine()));
                        System.out.println("Enter your password : ");
                        user.setPassword(scanner.nextLine());
                        userRepository.signUp();
                    } catch (SQLException e) {
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("1.show article" + " \n2.update and delete article"
                            + "\n3.insert new article");
                    articleChoice = Integer.parseInt(scanner.nextLine());
                    switch (articleChoice) {
                        case 1:
                            try {
                                if (articleRepository.published()) {
                                    articleRepository.showArticle();
                                }
                            } catch (SQLException e) {
                            }
                            break;
                        case 2:
                            try {
                                articleRepository.updateArticle(article);
                            } catch (SQLException e) {
                            }
                            try {
                                articleRepository.delete(article);
                            } catch (SQLException e) {
                            }
                            break;
                        case 3:
                            try {
                                articleRepository.insertArticle(article);
                            } catch (SQLException e) {
                            }
                            break;
                    }
                case 4:
                    System.exit(0);
                    break;
            }

        }
    }
}
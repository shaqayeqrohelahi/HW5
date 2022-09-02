package repository;

import Data.Article;

import java.sql.*;

public class ArticleRepository {
    public void createTable() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "CREATE TABLE Article_tbl (idArticle int primary key not null ," +
                "title varchar(30),brief varchar(100)," +
                "content varchar(150),creatDate date,isPublished int," +
                "user_id int foreign key not null";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
        connection.close();
    }
    public void insertArticle(Article article) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "insert into article_tbl  (title,brief,content,creatDate,isPublished)" +
                "values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, article.getTitle());
        preparedStatement.setString(2, article.getBrief());
        preparedStatement.setString(3, article.getContent());
        preparedStatement.setDate(4, (Date) article.getCreatDate());
        preparedStatement.setInt(5,article.getIsPublished());
        preparedStatement.executeUpdate();
        connection.close();
    }

    public Article showArticle() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "Select title,brief from article_tbl ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        connection.close();
        return buildArticle(resultSet);
    }
    public boolean published() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "Select isPublished from article_tbl where 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        connection.close();
        while (resultSet.next()){
            return true;
        }
        return false;
    }
    public void updateArticle(Article article) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE article_tbl SET article ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, String.valueOf(article.getTitle()));
        preparedStatement.setString(2, String.valueOf(article.getBrief()));
        preparedStatement.setString(3, String.valueOf(article.getContent()));
        preparedStatement.setDate(4, Date.valueOf(String.valueOf(article.
                getCreatDate())));
        preparedStatement.setInt(5,article.getIsPublished());
        preparedStatement.executeUpdate();
        connection.close();
    }
    public void delete(Article article) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM User_tbl WHERE isPublished = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, article.getIsPublished());
        preparedStatement.executeUpdate();
        connection.close();
    }

    private Article buildArticle(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Article article = new Article();
            String title= resultSet.getString("title");
            String brief = resultSet.getString("brief");
            String content = resultSet.getString("content");
            java.util.Date creatDate = resultSet.getDate("creatDate");
            int isPublished = resultSet.getInt("isPublished");

            article.setTitle(title);
            article.setBrief(brief);
            article.setContent(content);
            article.setCreatDate(creatDate);
            article.setIsPublished(isPublished);
            return article;
        }
        return null;
    }
}

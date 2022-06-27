package User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

public class BooksDAO {

    public static List<Books> searchByName(String name) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/atsiskaitymas";
        String querry = "";


            if (name.isEmpty()) {
                querry = "SELECT * FROM `books`";
            } else {
              querry = "SELECT * FROM `books` WHERE `name` LIKE '%" + name + "%'";
            }

        ArrayList<Books> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
   //         if(!name.isEmpty()){
   //             preparedStatement.setString(1, name);
   //       }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { //Kol turime sarase elementus
                list.add(new Books(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("summary"),
                        resultSet.getString("isbn"),
                        resultSet.getString("photo"),
                        resultSet.getInt("pages"),
                        resultSet.getString("category"),
                        resultSet.getInt("user_id")
                ));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List <Books> getBookNames() {
        int user_id;
        String username = UserSingleton.getInstance().getUserName();
        user_id = UserDAO.returnId(username);
        String jdbcUrl = "jdbc:mysql://localhost:3306/atsiskaitymas";
        String querry = "SELECT `id`, `name` FROM books WHERE `user_id` = ?";
        ArrayList<Books> list = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);

            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) { //Kol turime sarase elementus
                list.add(new Books(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
        public static void update(Books book) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/atsiskaitymas";
        String update = "UPDATE `books` SET `name` = ?, `summary` = ?, `isbn` = ?, `photo` = ?, `pages` = ?, `category` = ? WHERE `id` = ?";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getSummary());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setString(4, book.getPhoto());
            preparedStatement.setInt(5, book.getPages());
            preparedStatement.setString(6, book.getCategory());
            preparedStatement.setInt(7, book.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println("Pavyko atnaujinti įrašą");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nepavyko atnaujinti įrašą");
        }
    }

    public static void deleteById(int id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/atsiskaitymas";
        String delete = "DELETE FROM books WHERE id = ?";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");

            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            System.out.println("Pavyko ištrinti įrašą");
        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println("Įrašo ištrinti nepavyko");
        }
    }


    public static int isBooked(int id){
        String jdbcUrl = "jdbc:mysql://localhost:3306/atsiskaitymas";
        String querry2 = "SELECT `user_id` FROM `books` WHERE `id` = ?";
        int x=0;
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry2);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            x = resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nepavyko atnaujinti įrašą");
        }
        if(x>0){
            return -1;
        }
        else return 0;
    }
    public static void book(int id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/atsiskaitymas";
        String querry = "UPDATE `books` SET `user_id` = ? WHERE `id` = ?";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setInt(1,id);

            PreparedStatement preparedStatement2 = connection.prepareStatement(querry);
            String username = UserSingleton.getInstance().getUserName();

            int user_id = UserDAO.returnId(username);
            Books book = new Books();

            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println("Pavyko atnaujinti įrašą");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nepavyko atnaujinti įrašą");
        }
    }
    public static void create(Books book) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/atsiskaitymas";
        String querry = "INSERT INTO `books`(`name`, `summary`, `isbn`, `photo`, `pages`, `category`,`user_id`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");

            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getSummary());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setString(4, book.getPhoto());
            preparedStatement.setInt(5, book.getPages());
            preparedStatement.setString(6, book.getCategory());
            preparedStatement.setInt(7, NULL);
            //preparedStatement.setInt(7, book.getUser_id());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

import java.sql.*;

public class StudentDBConnection {

    static Connection connection = null;
    static String databaseName="studentdatabase";
    static String url = "jdbc:mysql://localhost:3306/"+databaseName;

    static String userName = "root";
    static String password = "Anju162@123";

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        StudentDBConnection studentDB = new StudentDBConnection();
        try {
            studentDB.insertStudent();
            studentDB.selectStudent();
            studentDB.updateStudent();
            studentDB.selectStudent();
            studentDB.deleteStudent();
            studentDB.selectStudent();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void insertStudent() throws SQLException {
        connection = DriverManager.getConnection(url,userName,password);
        String sql = "INSERT INTO student VALUES (2,'Asim')";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        System.out.println("Record inserted successfully");
        closeConnection(connection,statement);
    }

    private void updateStudent() throws SQLException {
        connection = DriverManager.getConnection(url,userName,password);
        String sql = "UPDATE student SET studentID=1 where name='Krish'";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        System.out.println("Record updated successfully");
        closeConnection(connection,statement);
    }

    private void deleteStudent() throws SQLException {
        connection = DriverManager.getConnection(url,userName,password);
        String sql = "DELETE FROM student WHERE studentID='2'";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        System.out.println("Record deleted successfully");
        closeConnection(connection,statement);
    }

    private void selectStudent() throws SQLException {
        connection = DriverManager.getConnection(url,userName,password);
        String sql = "SELECT * FROM student";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery(sql);
        System.out.println("Data on the table are : ");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }
        closeConnection(connection,statement);
    }

    private void closeConnection(Connection con, PreparedStatement stmt) throws SQLException {
        con.close();
        stmt.close();
        System.out.println("Connection is closed..!!");
    }
}

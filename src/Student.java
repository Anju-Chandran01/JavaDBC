import java.sql.*;
import java.util.Scanner;

public class Student {

    static Connection connection = null;
    static String databaseName="studentdatabase";
    static String url = "jdbc:mysql://localhost:3306/"+databaseName;

    static String userName = "root";
    static String password = "Anju162@123";

    static int id;
    static String name;

    public Student(String name, int id) {
        Student.name = name;
        Student.id = id;
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Student student = new Student(name,id);
        try {
            student.getStudentDetailsFromUser();
            student.insertStudent();
            student.getStudentDetailsFromUser();
            student.updateStudent();
            student.deleteStudent();
            student.selectStudent();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void getStudentDetailsFromUser(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name");
        name = input.nextLine();
        System.out.println("Enter ID");
        id = input.nextInt();
    }

    private void insertStudent() throws SQLException {
        connection = DriverManager.getConnection(url,userName,password);
        String sql = "insert into student values(?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);
        statement.setString(2,name);
        statement.execute();
        System.out.println("Record inserted successfully");
        closeConnection(connection,statement);
    }

    private void updateStudent() throws SQLException {
        connection = DriverManager.getConnection(url,userName,password);
        String sql = "update student set studentID=? where name=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);
        statement.setString(2,name);
        statement.execute();
        System.out.println("Record updated successfully");
        closeConnection(connection,statement);
    }

    private void deleteStudent() throws SQLException {
        connection = DriverManager.getConnection(url,userName,password);
        String sql = "delete from student where studentID=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);
        statement.setString(2,name);
        statement.execute();
        System.out.println("Record updated successfully");
        closeConnection(connection,statement);
    }

    private void selectStudent() throws SQLException {
        connection = DriverManager.getConnection(url,userName,password);
        String sql = "delete from student where studentID=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery("SELECT * FROM student");
        System.out.println("Data on the table are : ");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }
    }

    private void closeConnection(Connection con, PreparedStatement stmt) throws SQLException {
        con.close();
        stmt.close();
        System.out.println("Connection is closed..!!");
    }
}
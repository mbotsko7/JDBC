import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author Mimi Opkins with some tweaking from Dave Brown
 */
public class JDBC {
     //  Database credentials
    static String USER;
    static String PASS;
    static String DBNAME;
    static String ENTRY;
    //This is the specification for the printout that I'm doing:.
    //each % denotes the start of a new field.
    //The - denotes left justification.
    //The number indicates how wide to make the field.
    //The "s" denotes that it's a string.  All of our output in this test are
    //strings, but that won't always be the case.
    //static final String displayFormat = "%-5s\n";
// JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/";
//            + "testdb;user=";

    /**
     * Takes the input string and outputs "N/A" if the string is empty or null.
     *
     * @param input The string to be mapped.
     * @return Either the input string or "N/A" as appropriate.
     */
    public static String dispNull(String input) {
        //because of short circuiting, if it's null, it never checks the length.
        if (input == null || input.length() == 0) {
            return "N/A";
        } else {
            return input;
        }
    }

    public static void main(String[] args) {
        //Prompt the user for the database name, and the credentials.
        //If your database has no credentials, you can update this code to
        //remove that from the connection string.
        Scanner in = new Scanner(System.in);
        System.out.print("Name of the database (not the user account): ");
        DBNAME = in.nextLine();
        System.out.print("Database user name: ");
        USER = in.nextLine();
        System.out.print("Database password: ");
        PASS = in.nextLine();
        //Constructing the database URL connection string
        DB_URL = DB_URL + DBNAME + ";user=" + USER + ";password=" + PASS;
        Connection conn = null; //initialize the connection
        Statement stmt = null;  //initialize the statement that we're using

        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //////////////////////////////////////////////////////////
            int choice;
            do {
                System.out.println("Menu");
                System.out.println("1) List Album Titles");
                System.out.println("2) List Album Data");
                System.out.println("3) Insert Album");
                System.out.println("4) Insert Studio and Update Old Studio");
                System.out.println("5) Remove Album");
                System.out.println("Enter -1 to quit...\n");
                System.out.print("Enter choice: ");
                choice = in.nextInt();
                System.out.println();
                String sql;
                switch (choice) {
                    
                    case 1:
                        
                        sql = "SELECT * FROM album";
                        ResultSet rs = stmt.executeQuery(sql);

                        //STEP 5: Extract data from result set
                        System.out.printf("%-5s\n", "Title");
                        while (rs.next()) {
                            //Retrieve by column name
                            String title = rs.getString("title");

                            //Display values
                            System.out.printf("%-5s\n",
                                    dispNull(title));
                        }
                        rs.close();
                        break;
                    case 2:
                        System.out.print("Enter album title: ");
                        in.nextLine();
                        ENTRY = in.nextLine();
                        System.out.println("Entry is " + ENTRY);
            
                        System.out.println("Creating statement...");
                        stmt = conn.createStatement();
                        sql = "SELECT title, gpname, stname, dateRecorded, length, numSongs FROM album WHERE title = '";
                        sql += ENTRY + "'";
                        rs = stmt.executeQuery(sql);

                        //STEP 5: Extract data from result set
                        System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s\n", "title", "gpname", "stname", "dateRecorded", "length", "numSongs");
                        while (rs.next()) {
                            //Retrieve by column name
                            String title = rs.getString("title");
                            String group = rs.getString("gpname");
                            String stname = rs.getString("stname");
                            String dateRecorded = rs.getString("dateRecorded");
                            String length = rs.getString("length");
                            String numSongs = rs.getString("numSongs");
                            //Display values
                            System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s\n", dispNull(group), dispNull(group),dispNull(stname),dispNull(dateRecorded),dispNull(length),dispNull(numSongs));
                        }
                        //STEP 6: Clean-up environment
                        rs.close();
                        break;
                    case 3:
                        /*
                        sql = "INSERT INTO album VALUES ";
                        System.out.println("Enter an album:");
                        String name = in.nextLine();
                        ResultSet rs = stmt.executeQuery(sql);
                        while (rs.next()) {
                            //Retrieve by column name
                            String title = rs.getString("title");

                            //Display values
                            System.out.printf(displayFormat,
                                    dispNull(title));
                        }
                        rs.close();
                        */
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }
                System.out.println();
            } while (choice != -1);
            //STEP 6: Clean-up environment

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end FirstExample}

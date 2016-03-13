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
                        in.nextLine();
                        System.out.print("Enter album title: ");
                        ENTRY = in.nextLine();
            
                        System.out.println("Creating statement...");
                        stmt = conn.createStatement();
                        sql = "SELECT title, gpname, stname, dateRecorded, length, numSongs FROM album WHERE title = '" + ENTRY + "'";
                        rs = stmt.executeQuery(sql);

                        //STEP 5: Extract data from result set
                        System.out.printf("%-30s%-25s%-15s%-15s%-15s%-15s\n", "Title", "Group Name", "Studio Name", "Date Recorded", "Length", "Num Songs");
                        while (rs.next()) {
                            //Retrieve by column name
                            String title = rs.getString("title");
                            String group = rs.getString("gpname");
                            String stname = rs.getString("stname");
                            String dateRecorded = rs.getString("dateRecorded");
                            String length = rs.getString("length");
                            String numSongs = rs.getString("numSongs");
                            //Display values
                            System.out.printf("%-30s%-25s%-15s%-15s%-15s%-15s\n", dispNull(title), dispNull(group),dispNull(stname),dispNull(dateRecorded),dispNull(length),dispNull(numSongs));
                        }
                        //STEP 6: Clean-up environment
                        rs.close();
                        break;
                    case 3:
                        //complications on group and studio
                        boolean good = true;
                        sql = "INSERT INTO album VALUES (title, gpname, stName, dateRecorded, length, numSongs)";
                        String name,
                         group,
                         studio,
                         date,
                         length,
                         numSongs;
                        name = group = studio = date = length = numSongs = "";
                        in.useDelimiter("\n");
                        while (good) {
                            System.out.print("Enter an album name: ");
                            name = in.next();
                            if (name.length() > 40) {
                                System.out.println("Too long of a name. Try again.");

                            } else {
                                good = false;
                            }
                        }
                        good = true;
                        while (good) {
                            System.out.println("Enter an album group:");
                            group = in.next();
                            if (group.length() > 20) {
                                System.out.println("Too long of a group name. Try again.");
                            } else {
                                good = false;
                            }

                        }
                        good = true;
                        while (good) {
                            System.out.println("Enter an album studio:");
                            studio = in.next();
                            if (studio.length() > 20) {
                                System.out.println("Too long of a name. Try again.");
                            } else {
                                good = false;
                            }

                        }
                        good = true;
                        while (good) {
                            System.out.println("Enter an album date:");
                            date = in.next();
                            if (date.length() > 10) {
                                System.out.println("Too long of a date. Try again.");
                            } else {
                                good = false;
                            }

                        }
                        good = true;
                        while (good) {
                            System.out.println("Enter an album length:");
                            length = in.next();
                            if (length.length() > 9) {
                                System.out.println("Too long of a time. Try again.");
                            } else {
                                good = false;
                            }

                        }
                        //good = true;
                        //while (good) {
                        System.out.println("Enter an song count:");
                        numSongs = in.next();
                        //if (date.length() > 10) {
                        //    System.out.println("Too long of a date. Try again.");
                        //    good = false;
                        //}
                        //}
                        
                        PreparedStatement prep = conn.prepareStatement("INSERT INTO album VALUES(?,?,?,?,?,?)");
                        prep.setString(1, name);
                        prep.setString(2, group);
                        prep.setString(3, studio);
                        prep.setString(4, date);
                        prep.setString(5, length);
                        prep.setString(6, numSongs);
                        
                        prep.executeUpdate();
                        //stmt.executeUpdate(sql);
                        //sql = "SELECT * FROM album";
                        //ResultSet rs2 = stmt.executeQuery(sql);
                        //in.useDelimiter(" ");
                        //s2.close();
                        
                        break;
                    case 4:
                        in.nextLine();
                        
                        System.out.print("Enter studio to be replaced: ");
                        String oldStudio = in.nextLine();
                        
                        System.out.print("Enter new studio name: ");
                        ENTRY = in.nextLine();
                        String newStudio = ENTRY;
                        sql = "INSERT INTO studio VALUES ('" + ENTRY + "', '";
                        
                        System.out.print("Enter new studio address: ");
                        ENTRY = in.nextLine();
                        sql += ENTRY + "', '";
                        
                        System.out.print("Enter new studio owner: ");
                        ENTRY = in.nextLine();
                        sql += ENTRY + "', '";
                        
                        System.out.print("Enter new studio phone: ");
                        ENTRY = in.nextLine();
                        sql += ENTRY + "')";
                        
                        System.out.println("Creating statement...");
                        stmt = conn.createStatement();
                        stmt.executeUpdate(sql);
                        
                        // test
                        sql = "UPDATE album SET stname = '" + newStudio + "' WHERE stname='" + oldStudio + "'";
                        stmt = conn.createStatement();
                        stmt.executeUpdate(sql);
                        
                        System.out.println("Updating records...");
                        
                        /*
                        sql = "DELETE FROM studio WHERE stname = '" + oldStudio + "'";
                        stmt = conn.createStatement();
                        stmt.executeUpdate(sql);
                        */
                        
                        sql = "SELECT title, gpname, s.stname, address, owner, phone FROM studio s INNER JOIN album a ON a.stname = s.stname WHERE s.stname = '" + newStudio + "'";
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        
                        //STEP 5: Extract data from result set
                        System.out.printf("%-30s%-25s%-15s%-30s%-15s%-15s\n", "Title", "Group Name", "Studio Name", "Studio Address", "Studio Owner", "Studio Owner");
                        while (rs.next()) {
                            //Retrieve by column name
                            String title = rs.getString("title");
                            String group = rs.getString("gpname");
                            String stname = rs.getString("stname");
                            String address = rs.getString("address");
                            String owner = rs.getString("owner");
                            String phone = rs.getString("phone");
                            //Display values
                            System.out.printf("%-30s%-25s%-15s%-30s%-15s%-15s\n", dispNull(title), dispNull(group),dispNull(stname),dispNull(address),dispNull(owner),dispNull(phone));
                        }
                        
                        //STEP 6: Clean-up environment
                        rs.close();
                        
                        break;
                    case 5:
                        System.out.println("Enter album to delete: ");
                        in.useDelimiter("\n");
                        String text = in.next();
                        System.out.println("."+text+".");
                        sql = "DELETE FROM album WHERE Title = \'" + text+"\'";
                        stmt.executeUpdate(sql);
                        in.useDelimiter(" ");
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

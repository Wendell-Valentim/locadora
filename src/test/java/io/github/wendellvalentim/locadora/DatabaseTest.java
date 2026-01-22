package io.github.wendellvalentim.locadora;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseTest {

    static Connection connection;

    @BeforeAll
    static void setUpDataBase() throws Exception {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");

        connection.createStatement().execute("CREATE TABLE users (id INT, name VARCHAR) ");
    }

    @BeforeEach
    void insertUserTest() throws Exception{
        connection.createStatement().execute("insert into users(id, name) values(1, 'Jose')");
    }

    @Test
    //@Disabled esse teste não sera executado
    void testUserExists() throws Exception {
        var result = connection
                .createStatement()
                .executeQuery("select * from users where id = 1");
        Assertions.assertTrue(result.next());

    }

    @AfterAll
    static void closeDataBase() throws Exception{

        connection.close();
    }
}

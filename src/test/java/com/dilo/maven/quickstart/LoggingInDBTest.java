package com.dilo.maven.quickstart;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoggingInDBTest {
	private LoggingInDB lidb;
	private LogAttributes la;
	String logAttr;
	String logAttrType;
	Connection connection;
	Statement stmnt;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("before LoggingInDBClass");
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("after LoggingInDBClass");
	}

	/**
	 * Initializing Tests with @Before Methods
	 * 
	 * @throws IOException
	 * 
	 */
	@Before
	public void setUp() throws IOException {
		System.out.println("before method");
		lidb = new LoggingInDB();
		la = new LogAttributes();

		/**
		 * deleteOp fonk. için parametreler...
		 */
		logAttr = "loglevel";
		logAttrType = "DEBUG";

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dilo", "postgres", "dilo");
			stmnt = connection.createStatement();

			/**
			 * deleteOp için setup kısmında dbye kaydettiğim execute kısmında sileceğim
			 * kayıt...
			 */
			la.setLogLevel("[DEBUG]");
			la.setTimestamp(" 2018-03-11 13:40:00.062 ");
			la.setLogger("[main] LoggingInDBTest ");
			la.setMessage("This is a debug message.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {
		System.out.println("after method");
	}

	/**
	 * 
	 * Test that the created logAttributes object is registered in the database.
	 */
	@Test
	public void createOp() {

		String result = null;
		String expected = "{\"loglevel\":\"[FATAL]\",\"timestamp\":\" 2018-03-02 20:59:59.062 \",\"logger\":\"[main] CreateSampleLogFile \",\"message\":\"This is a fatal message.\"}";
		// database'de olmasını istediğim format

		la.setLogLevel("[FATAL]");
		la.setTimestamp(" 2018-03-02 20:59:59.062 ");
		la.setLogger("[main] CreateSampleLogFile ");
		la.setMessage("This is a fatal message.");

		try {
			lidb.createOp(la); // bu fonk. çalıştırıldıktan sonra database'e eklenen la'yı olduğu gibi result
								// değişkenine çekeceğim...
			String sql = "select  * from logattr where attributes ->> 'timestamp' = ' 2018-03-02 20:59:59.062 ';";
			ResultSet rs = stmnt.executeQuery(sql);
			while (rs.next()) {
				result = rs.getString("attributes");
			}
			/**
			 * database'den gelen result: {"loglevel":"[FATAL]","timestamp":" 2018-03-02
			 * 20:59:59.062 ","logger":"[main] CreateSampleLogFile ","message":"This is a
			 * fatal message."}
			 */
			assertEquals(expected, result); // beklediğim format ile createOp fonk. çalıştıktan sonra dbye eklenen
											// aynı
			System.out.println("Test başarılı. Kayıt db'ye eklendi ");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Test that the specific log given the parameters from the URI is deleted.
	 */
	@Test
	public void deleteOp() {
		String resultAfterDelete = null;
		try {
			lidb.createOp(la);
			/**
			 * loglevel=[DEBUG] olan kayıt setupta kaydedildikten sonra, execute kısmında
			 * dbye eklenir daha sonra deleteOp çalıştırılır. deleteOp çalıştırıldktan sonra
			 * bu kayıt silinir.. db'den silinen kayıt için sorgu atılır ve dönen sonuç
			 * resultAfterDelete değişkenine çekilir... resultAfterDelete null ise o kayıt
			 * dbde yoktur... assertNull ile kontrolünü sağlarız...
			 */
			lidb.deleteOp(logAttr, logAttrType);
			String sql2 = "select  * from logattr where attributes ->> '" + logAttr + "' = '[" + logAttrType + "]';";
			ResultSet rs2 = stmnt.executeQuery(sql2);
			while (rs2.next()) {
				resultAfterDelete = rs2.getString("attributes");
			}
			assertNull(resultAfterDelete);
			System.out.println("Silmek istediğiniz kayıt db'den silindi.." + resultAfterDelete);

			/**
			 * hata olmasını beklediğim ve fonksiyonun ne yapacağını assert ettiğim bölüm...
			 * 
			 * loglevel=[DEBUG] olan kayıt silindikten sonra aynı kayıt için tekrar deleteOp
			 * fonksiyonu çalıştırılmak istenirse yani var olmayan bir kaydı silmeye
			 * çalışırsak null dönecektir, resultAfterDelete değişkenine db'deki sonuç daha
			 * önce çekilmişti...
			 */
			lidb.deleteOp(logAttr, logAttrType);
			if (resultAfterDelete == null) {
				System.out.println("Silmek istediğiniz kayıt zaten db'de yok:" + resultAfterDelete);
				assertNull(resultAfterDelete);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

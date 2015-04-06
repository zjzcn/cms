package com.zjzcn.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SQL脚本运行类
 * 
 * @author yuqs
 * @since 2.0
 */
public class SqlRunner {

	private static final Logger log = LoggerFactory.getLogger(SqlRunner.class);
	private static final String DEFAULT_DELIMITER = ";";

	public static void runScript(Connection conn, String resource) throws IOException, SQLException {
		Assert.notNull(resource);
		InputStream input = StreamUtils.getStreamFromClasspath(resource);
		Reader reader = new InputStreamReader(input, "UTF-8");
		runScript(conn, reader);
	}

	/**
	 * 根据给定的sql脚本资源、数据库连接对象，执行sql脚本
	 * 
	 * @param conn
	 *            数据库连接对象
	 * @param reader
	 *            sql脚本资源
	 * @throws IOException
	 *             io异常
	 * @throws SQLException
	 *             sql异常
	 */
	public static void runScript(Connection conn, Reader reader) throws IOException, SQLException {
		StringBuffer command = null;
		try {
			LineNumberReader lineReader = new LineNumberReader(reader);
			String line = null;
			while ((line = lineReader.readLine()) != null) {
				if (command == null) {
					command = new StringBuffer();
				}
				String trimmedLine = line.trim();
				if (trimmedLine.startsWith("--") || trimmedLine.startsWith("//")) {
					log.info(trimmedLine);
				} else if (trimmedLine.equals(DEFAULT_DELIMITER) || trimmedLine.endsWith(DEFAULT_DELIMITER)) {
					command.append(line.substring(0, line.lastIndexOf(DEFAULT_DELIMITER)));
					command.append(" ");
					Statement statement = conn.createStatement();

					log.info(command.toString());
					try {
						statement.execute(command.toString());
					} catch (SQLException e) {
						e.fillInStackTrace();
						log.error("Error executing: " + command);
					}

					if (!conn.getAutoCommit()) {
						conn.commit();
					}
					command = null;
					try {
						statement.close();
					} catch (Exception e) {
						// ignore
					}
					Thread.yield();
				} else {
					command.append(line);
					command.append(" ");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Error running script.  Cause: " + e, e);
		}
	}

}

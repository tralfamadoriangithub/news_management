package com.epam.testapp.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.testapp.entity.News;

public class NewsDao implements INewsDao {

	private ConnectionPool connectionPool;

	@Override
	public List<News> getList() {

		Connection connection = connectionPool.getConnection();

		ArrayList<News> newsList = null;

		try {

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM news");
			newsList = new ArrayList<>();

			while (resultSet.next()) {
				newsList.add(getNewsFromResutSet(resultSet));
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			releaseConnection(connection);
		}
		return newsList;
	}

	@Override
	public void save(News news) {

		Connection connection = connectionPool.getConnection();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(
							"INSERT INTO news (news_title, news_date, news_brief, news_content) VALUES (?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, news.getTitle());
			preparedStatement.setDate(2, (Date) news.getDate());
			preparedStatement.setString(3, news.getBrief());
			preparedStatement.setString(4, news.getContent());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			releaseConnection(connection);
		}
	}

	@Override
	public void remove(int... id) {
		
		Connection connection = connectionPool.getConnection();
		try {
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM news WHERE news_id = ?");
			for( int i : id){
				preparedStatement.setInt(1, i);
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			preparedStatement.close();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			releaseConnection(connection);
		}
		
	}

	@Override
	public News fetchById(int... id) {
		// TODO Auto-generated method stub
		return null;
	}

	private News getNewsFromResutSet(ResultSet resultSet) {

		News news = new News();

		try {
			news.setId(resultSet.getInt("news_id"));
			news.setTitle(resultSet.getString("news_title"));
			news.setBrief(resultSet.getString("news_brief"));
			news.setDate(resultSet.getDate("news_date"));
			news.setContent(resultSet.getString("news_content"));
		} catch (SQLException e) {

		}
		return news;
	}
	
	private void releaseConnection(Connection connection){
		connectionPool.releaseConnection(connection);
		connection = null;
	}

	public ConnectionPool getConnectionPool() {
		return connectionPool;
	}

	public void setConnectionPool(ConnectionPool connectionPool) {
		this.connectionPool = connectionPool;
	}

}

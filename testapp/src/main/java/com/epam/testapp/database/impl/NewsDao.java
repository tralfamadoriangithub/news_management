package com.epam.testapp.database.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.testapp.database.ConnectionPool;
import com.epam.testapp.database.ConnectionPoolTestappException;
import com.epam.testapp.database.DaoTestappException;
import com.epam.testapp.database.INewsDao;
import com.epam.testapp.entity.News;

public class NewsDao implements INewsDao {

	private ConnectionPool connectionPool;

	@Override
	public List<News> getList() throws DaoTestappException {

		Connection connection = null;

		ArrayList<News> newsList = null;

		try {
			connection = connectionPool.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery( "SELECT * FROM NEWS ORDER BY NEWS_DATE DESC" );
			newsList = new ArrayList<>();

			while ( resultSet.next() ) {
				newsList.add( getNewsFromResutSet( resultSet ) );
			}
			resultSet.close();
			statement.close();
		} catch ( SQLException e ) {
			throw new DaoTestappException(
					"NewsDao SQL exception in getList method", e );
		} catch ( ConnectionPoolTestappException e ) {
			throw new DaoTestappException( e.getMessage(), e );
		} finally {
			releaseConnection( connection );
		}
		return newsList;
	}

	@Override
	public News save( News news ) throws DaoTestappException {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();
			PreparedStatement preparedStatement;

			if ( news.getId() == 0 ) {
				preparedStatement = connection
						.prepareStatement(
								"INSERT INTO news (news_title, news_date, news_brief, news_content) VALUES (?,?,?,?)",
								new String[] { "NEWS_ID" } );
			} else {
				preparedStatement = connection
						.prepareStatement( "UPDATE news SET news_title=?, news_date=?, news_brief=?, news_content=? WHERE news_id = ?" );
				preparedStatement.setInt( 5, news.getId() );
			}
			java.sql.Date date = new Date( news.getDate().getTime() );
			preparedStatement.setString( 1, news.getTitle() );
			preparedStatement.setDate( 2, date );
			preparedStatement.setString( 3, news.getBrief() );
			preparedStatement.setString( 4, news.getContent() );
			preparedStatement.executeUpdate();

			if ( news.getId() == 0 ) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if ( resultSet.next() ) {
					news.setId( resultSet.getInt( 1 ) );
				}
				resultSet.close();
			}
			preparedStatement.close();
		} catch ( SQLException e ) {
			throw new DaoTestappException(
					"NewsDao SQL exception in save method", e );
		} catch ( ConnectionPoolTestappException e ) {
			throw new DaoTestappException( e.getMessage(), e );
		} finally {
			releaseConnection( connection );
		}
		return news;
	}

	@Override
	public void remove( List<Integer> newsId ) throws DaoTestappException {

		Connection connection = null;
		try {
			connection = connectionPool.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement( "DELETE FROM news WHERE news_id = ?" );
			for ( Integer i : newsId ) {
				preparedStatement.setInt( 1, i );
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			preparedStatement.close();
		} catch ( SQLException e ) {
			throw new DaoTestappException(
					"NewsDao SQL exception in remove method", e );
		} catch ( ConnectionPoolTestappException e ) {
			throw new DaoTestappException( e.getMessage(), e );
		} finally {
			releaseConnection( connection );
		}
	}

	@Override
	public News fetchById( int id ) throws DaoTestappException {

		Connection connection = null;
		News news = null;

		try {
			connection = connectionPool.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement( "SELECT * FROM news WHERE news_id = ?" );
			preparedStatement.setInt( 1, id );
			ResultSet resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				news = getNewsFromResutSet( resultSet );
			}
			resultSet.close();
			preparedStatement.close();
		} catch ( SQLException e ) {
			throw new DaoTestappException(
					"NewsDao SQL exception in fetchById method", e );
		} catch ( ConnectionPoolTestappException e ) {
			throw new DaoTestappException( e.getMessage(), e );
		} finally {
			releaseConnection( connection );
		}
		return news;
	}

	private News getNewsFromResutSet( ResultSet resultSet ) {

		News news = new News();

		try {
			news.setId( resultSet.getInt( "news_id" ) );
			news.setTitle( resultSet.getString( "news_title" ) );
			news.setBrief( resultSet.getString( "news_brief" ) );
			news.setDate( resultSet.getDate( "news_date" ) );
			news.setContent( resultSet.getString( "news_content" ) );
		} catch ( SQLException e ) {

		}
		return news;
	}

	private void releaseConnection( Connection connection )
			throws DaoTestappException {
		try {
			connectionPool.releaseConnection( connection );
		} catch ( ConnectionPoolTestappException e ) {
			throw new DaoTestappException( e.getMessage(), e );
		} finally {
			connection = null;
		}
	}

	public ConnectionPool getConnectionPool() {
		return connectionPool;
	}

	public void setConnectionPool( ConnectionPool connectionPool ) {
		this.connectionPool = connectionPool;
	}

}

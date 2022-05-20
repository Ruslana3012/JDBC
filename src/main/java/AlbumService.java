import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlbumService {
    private Album getAlbum(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        int year = resultSet.getInt("year");
        String singer = resultSet.getString("singer");
        Album album = new Album(name, year, singer);
        return album;
    }

    public ArrayList<Album> getAllAlbums() throws SQLException, ClassNotFoundException {
        ArrayList<Album> albums = new ArrayList<>();

        try (Statement statement = JDBC.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM album");
            while (resultSet.next()) {
                Album album = getAlbum(resultSet);
                albums.add(album);
            }
        }

        return albums;
    }

    public Album getAlbum(String albumName) throws Exception {
        try (Statement statement = JDBC.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM album WHERE name = \'" + albumName + "\'");

            if (resultSet.next()) {
                Album album = getAlbum(resultSet);
                return album;
            } else {
                throw new Exception();
            }
        }
    }

    public ArrayList<String> getAlbumsBySinger(String singer) throws Exception {
        ArrayList<String> albums = new ArrayList<>();

        try (Statement statement = JDBC.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT name FROM album WHERE singer = \'" + singer + "\'");

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                albums.add(name);
            }
        }

        return albums;
    }

    public ArrayList<Album> getAlbumByYear(int year) throws Exception {
        ArrayList<Album> albums = new ArrayList<>();

        try (Statement statement = JDBC.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM album WHERE year = \'" + year + "\'");

            while (resultSet.next()) {
                Album album = getAlbum(resultSet);
                albums.add(album);
            }
        }

        return albums;
    }

    public void addAlbum(Album album) throws Exception {
        try (Statement statement = JDBC.getConnection().createStatement()) {
            statement.executeUpdate("INSERT INTO album(name, year, singer) VALUES (\'" + album.getName() + "\'," + album.getYear() + ",\'" + album.getSinger() + "\')");
        }
    }

    public void deleteAlbum(String album) throws Exception {
        try (Statement statement = JDBC.getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM album WHERE name = \'" + album + "\'");
        }
    }
}

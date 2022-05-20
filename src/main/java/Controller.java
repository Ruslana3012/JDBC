import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private static AlbumService albumService = new AlbumService();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Scanner scanner = new Scanner(System.in);

    public static void printInstruction() {
        System.out.print("Введіть цифру, яка відповідає вашому запиту:\n" +
                "1 - Вивести всі альбоми\n" +
                "2 - Знайти інформацію про альбом по назві\n" +
                "3 - Знайти альбоми виконавця\n" +
                "4 - Знайти альбоми по року випуску\n" +
                "5 - Зберегти новий альбом\n" +
                "6 - Видалити альбом\n");
    }

    public static void processCommand(int number) throws Exception {
        switch (number) {
            case 1:
                printInformationAboutAllAlbums();
                break;
            case 2:
                printInformationAboutAlbumByName();
                break;
            case 3:
                printAlbumsBySinger();
                break;
            case 4:
                printAlbumsByYear();
                break;
            case 5:
                saveAlbum();
                break;
            case 6:
                deleteAlbum();
                break;
        }
    }

    private static void printInformationAboutAllAlbums() throws SQLException, ClassNotFoundException {
        System.out.print(albumService.getAllAlbums());
    }

    private static void printInformationAboutAlbumByName() throws IOException {
        System.out.print("Назва альбому: ");
        String albumName = reader.readLine();
        try {
            System.out.println(albumService.getAlbum(albumName).toString());
        } catch (Exception e) {
            System.out.println("Дані відсутні");
        }
    }

    private static void printAlbumsBySinger() throws IOException {
        System.out.print("Виконавець: ");
        String singer = reader.readLine();
        try {
            ArrayList<String> albumNames = albumService.getAlbumsBySinger(singer);
            if (albumNames.size() != 0) {
                System.out.println("Альбоми: " + albumNames.toString());
            } else {
                System.out.println("Дані відсутні");
            }
        } catch (Exception e) {
            System.out.println("Дані відсутні");
        }
    }

    private static void printAlbumsByYear() {
        System.out.println("Рік випуску: ");
        int year = scanner.nextInt();
        try {
            System.out.println(albumService.getAlbumByYear(year).toString());
        } catch (Exception e) {
            System.out.println("Дані відсутні");
        }
    }

    private static void saveAlbum() throws Exception {
        System.out.print("Введіть наступні дані, щоб додати в базу даних: \n" +
                "Назва альбому: ");
        String name2 = scanner.next();
        System.out.print("Рік: ");
        int year2 = scanner.nextInt();
        System.out.print("Співак/співачка/гурт: ");
        String singer2 = scanner.next();
        Album album = new Album(name2, year2, singer2);
        albumService.addAlbum(album);
        System.out.print("Альбом успішно збережено");
    }

    private static void deleteAlbum() throws Exception {
        System.out.print("Введіть назву альбому, який необхідно видалити з бази даних: \n" +
                "Назва альбому: ");
        albumService.deleteAlbum(reader.readLine());
        System.out.print("Альбом успішно видалено");
    }
}

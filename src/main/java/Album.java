public class Album {
    private String name;
    private int year;
    private String singer;

    public Album(String name, int year, String singer) {
        this.name = name;
        this.year = year;
        this.singer = singer;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getSinger() {
        return singer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "\n" + "Name: " + name + ", Year: " + year + ", Singer: " + singer;
    }
}

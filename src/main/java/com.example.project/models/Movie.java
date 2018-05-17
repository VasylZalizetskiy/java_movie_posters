package com.example.project.models;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

public class Movie implements Serializable{
    @Id
    public String id;
    public String name;
    public int releasedYear;
    public Movie() {}
    public Movie(String name, int releasedYear) {
        this.name = name;
        this.releasedYear = releasedYear;
    }
    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", releasedYear=" + releasedYear +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        if (releasedYear != movie.releasedYear) return false;
        if (id != null ? !id.equals(movie.id) : movie.id != null) return false;
        return name != null ? name.equals(movie.name) : movie.name == null;
    }
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + releasedYear;
        return result;
    }
}
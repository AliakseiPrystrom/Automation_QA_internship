package tables;

import java.sql.Timestamp;
import java.util.Objects;

public class Session {
    private int id;
    private String session_key;
    private Timestamp created_time;
    private int build_number;

    public Session(String session_key, int build_number) {
        this.session_key = session_key;
        this.build_number = build_number;
    }

    public Session() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public Timestamp getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Timestamp created_time) {
        this.created_time = created_time;
    }

    public int getBuild_number() {
        return build_number;
    }

    public void setBuild_number(int build_number) {
        this.build_number = build_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return build_number == session.build_number && Objects.equals(session_key, session.session_key) && Objects.equals(created_time, session.created_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session_key, created_time, build_number);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", session_key='" + session_key + '\'' +
                ", created_time=" + created_time +
                ", build_number=" + build_number +
                '}';
    }
}

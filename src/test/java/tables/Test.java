package tables;

import java.sql.Timestamp;
import java.util.Objects;

public class Test {
    private int author_id;
    private String browser;
    private Timestamp end_time;
    private String env;
    private int id;
    private String method_name;
    private String name;
    private int project_id;
    private int session_id;
    private Timestamp start_time;
    private int status_id;

    public Test() {
    }

    public Test(int author_id, String browser, Timestamp end_time, String env, int id, String method_name, String name, int project_id, int session_id, Timestamp start_time, int status_id) {
        this.author_id = author_id;
        this.browser = browser;
        this.end_time = end_time;
        this.env = env;
        this.id = id;
        this.method_name = method_name;
        this.name = name;
        this.project_id = project_id;
        this.session_id = session_id;
        this.start_time = start_time;
        this.status_id = status_id;
    }

    public Test(String env, int id, String method_name, String name, int project_id, int session_id) {
        this.env = env;
        this.id = id;
        this.method_name = method_name;
        this.name = name;
        this.project_id = project_id;
        this.session_id = session_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethod_name() {
        return method_name;
    }

    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    @Override
    public String toString() {
        return "TestResultInfo{" +
                "author_id=" + author_id +
                ", browser='" + browser + '\'' +
                ", end_time=" + end_time +
                ", env='" + env + '\'' +
                ", id=" + id +
                ", method_name='" + method_name + '\'' +
                ", name='" + name + '\'' +
                ", project_id=" + project_id +
                ", session_id=" + session_id +
                ", start_time=" + start_time +
                ", status_id=" + status_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return author_id == test.author_id && project_id == test.project_id && session_id == test.session_id && status_id == test.status_id && Objects.equals(browser, test.browser) && Objects.equals(end_time, test.end_time) && Objects.equals(env, test.env) && Objects.equals(method_name, test.method_name) && Objects.equals(name, test.name) && Objects.equals(start_time, test.start_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author_id, browser, end_time, env, method_name, name, project_id, session_id, start_time, status_id);
    }
}

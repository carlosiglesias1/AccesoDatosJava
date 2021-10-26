import java.time.LocalDate;

public class Empleado {
    private int empno;
    private int mgr;
    private int deptno;
    private String ename;
    private String job;
    private float sal, comm;
    private LocalDate hiredate;

    public Empleado(int empno, int mgr, int deptno, String ename, String job, float sal, float comm, LocalDate hiredDate) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredDate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public int getEmpno() {
        return empno;
    }

    public String getEname() {
        return ename;
    }

    public String getJob() {
        return job;
    }

    public int getMgr() {
        return mgr;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }

    public float getSal() {
        return sal;
    }

    public float getComm() {
        return comm;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public void setHiredate(LocalDate hiredate) {
        this.hiredate = hiredate;
    }

    public void setSal(float sal) {
        this.sal = sal;
    }

    public void setComm(float comm) {
        this.comm = comm;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }
}

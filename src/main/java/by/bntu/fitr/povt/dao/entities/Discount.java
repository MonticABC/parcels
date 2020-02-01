package by.bntu.fitr.povt.dao.entities;

import javax.persistence.*;

@Entity
@Table(name="discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "month")
    private int month;
    @Column(name = "nameT")
    private String nameT;
    @Column(name = "dis")
    private double dis;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getNameT() {
        return nameT;
    }

    public void setNameT(String nameT) {
        this.nameT = nameT;
    }

    public double getDis() {
        return dis;
    }

    public void setDis(double dis) {
        this.dis = dis;
    }


    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", month='" + month + '\'' +
                ", nameT=" + nameT +
                ", dis=" + dis +
                '}';
    }

}

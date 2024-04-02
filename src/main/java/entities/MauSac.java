package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="MauSac")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="Ma")
    private String ma;

    @Column(name="Ten")
    private String ten;

    @Column(name="TrangThai")
    private int trangThai;

}

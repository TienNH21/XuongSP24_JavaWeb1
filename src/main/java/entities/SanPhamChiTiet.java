package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="SanPhamChiTiet")
public class SanPhamChiTiet {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="IdMauSac")
    private Integer idMauSac;

    @Column(name="IdKichThuoc")
    private Integer idKichThuoc;

    @Column(name="IdSanPham")
    private Integer idSanPham;

    @Column(name="MaSPCT")
    private String maSPCT;

    @Column(name="SoLuong")
    private int soLuong;

    @Column(name="DonGia")
    private double donGia;

    @Column(name="TrangThai")
    private int trangThai;
}

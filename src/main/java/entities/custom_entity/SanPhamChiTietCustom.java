package entities.custom_entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SanPhamChiTietCustom {
    @Id
    private Integer id;
    private String tenMauSac;
    private String tenKichThuoc;
    private String maSPCT;
    private int soLuong;
    private double donGia;
    private int trangThai;
}

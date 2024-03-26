package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamChiTiet {
    private Integer id;
    private Integer idMauSac;
    private Integer idKichThuoc;
    private Integer idSanPham;
    private String maSPCT;
    private int soLuong;
    private double donGia;
    private int trangThai;
}

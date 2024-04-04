package requests.spct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndexRequest {
    private Integer idSanPham;
    private String keyword;
    private Integer trangThai;
    private Integer page;
    private Integer limit;
    private Map<String, String> errors;

    public static IndexRequest make(Map<String, String[]> requestParams)
    {
        String[] s0 = requestParams.get("san_pham_id");
        String[] s1 = requestParams.get("keyword");
        String[] s2 = requestParams.get("trangThai");
        String[] s3 = requestParams.get("page");
        String[] s4 = requestParams.get("limit");

        String keyword = null;
        Integer idSanPham = null, trangThai = null, page = null, limit = null;

        Map<String, String> errors = new HashMap<>();
        if (s0 == null || s0[0].trim().length() == 0) {
            errors.put("idSanPham", "Sản phẩm không hợp lệ.");
        } else {
            try {
                idSanPham = Integer.parseInt(s0[0].trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                errors.put("trangThai", "Sản phẩm không hợp lệ.");
            }
        }

        keyword = (s1 == null || s1[0].trim().length() == 0) ? null : s1[0].trim();
        try {
            trangThai = (s2 == null || s2[0].trim().length() == 0) ? null : Integer.parseInt(s2[0].trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errors.put("trangThai", "Trạng thái không hợp lệ");
        }

        try {
            page = (s3 == null || s3[0].trim().length() == 0) ? null : Integer.parseInt(s3[0].trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errors.put("trangThai", "Số trang không hợp lệ");
        }

        try {
            limit = (s4 == null || s4[0].trim().length() == 0) ? null : Integer.parseInt(s4[0].trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errors.put("trangThai", "Giới hạn trang không hợp lệ");
        }

        return new IndexRequest(idSanPham, keyword, trangThai, page, limit, errors);
    }

    public boolean hasError()
    {
        return !this.errors.isEmpty();
    }
}

package repositories;

import entities.MauSac;

import java.util.ArrayList;
import java.util.List;

public class MauSacRepository {
    private List<MauSac> list;

    public MauSacRepository()
    {
        this.list = new ArrayList<>();
        this.list.add(new MauSac(1, "ede", "Yellow", 1));
        this.list.add(new MauSac(2, "000", "Black", 1));
    }

    public List<MauSac> getList() {
        return list;
    }

    public void setList(List<MauSac> list) {
        this.list = list;
    }

    public void insert(MauSac ms)
    {
        this.list.add(ms);
    }

    public void update(MauSac newMauSac)
    {
        // UPDATE MauSac SET Ma = ?, Ten = ?, TrangThai = ? WHERE ID = ?
        for (int i = 0; i < this.list.size(); i++) {
            MauSac oldMauSac = this.list.get(i);
            if (oldMauSac.getId() == newMauSac.getId()) {
                this.list.set(i, newMauSac);
                return ;
            }
        }
    }

    public void delete(MauSac newMauSac)
    {
        for (int i = 0; i < this.list.size(); i++) {
            MauSac oldMauSac = this.list.get(i);
            if (oldMauSac.getId() == newMauSac.getId()) {
                this.list.remove(i);
                return ;
            }
        }
    }

    public MauSac findById(int id)
    {
        for (int i = 0; i < this.list.size(); i++) {
            MauSac oldMauSac = this.list.get(i);
            if (oldMauSac.getId() == id) {
                return oldMauSac;
            }
        }

        return null;
    }
}

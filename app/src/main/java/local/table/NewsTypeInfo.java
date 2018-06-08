package local.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by rowen on 2018/2/27.
 */
@Entity
public class NewsTypeInfo {
    @Id
    private Long id;
    private String name;
    private String typeId;

    public NewsTypeInfo() {
    }

    @Generated(hash = 1707873593)
    public NewsTypeInfo(Long id, String name, String typeId) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "NewsTypeInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeId='" + typeId + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}

package ru.fortochkin.directoriesandfiles.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.fortochkin.directoriesandfiles.utils.Utils;

@Entity
@Setter @Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name="dir_entry")
public class DirEntryEntity implements Serializable, Comparable<DirEntryEntity>{

    
    public enum Type{
        FILE,DIRECTORY
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;
    
    @ManyToOne
    @JoinColumn(name = "dir_id", nullable=false)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private DirEntity dir;
    
    @Column(name = "size")
    private Long size;
    
    
    @Override
    public int compareTo(DirEntryEntity o) {
        if (this.equals(o)){
            return 0;
        }
        return Utils.compare(this.getType(), this.getName(), o.getType(), o.getName());
    }
}
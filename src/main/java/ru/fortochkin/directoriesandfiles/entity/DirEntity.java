package ru.fortochkin.directoriesandfiles.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.fortochkin.directoriesandfiles.entity.DirEntryEntity.Type;
import ru.fortochkin.directoriesandfiles.utils.Utils;

@Entity
@Setter @Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name="dir")
public class DirEntity implements Serializable,Comparable<DirEntity>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm",timezone="Europe/Moscow")
    private Date date;
    
    @Column(name = "baseDir", nullable = false)
    String baseDir;
    
    @OneToMany(mappedBy="dir", cascade = CascadeType.ALL)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<DirEntryEntity> entries;
    
    @Transient
    private Long dirsCount;
    
    @Transient
    private Long filesCount;
    
    @Transient
    private Long contentSize;
    
        
    @Override
    public int compareTo(DirEntity o) {
        if (this.equals(o)){
            return 0;
        }
        return Utils.compare(Type.DIRECTORY, this.getBaseDir(), Type.DIRECTORY, o.getBaseDir());
    }
    
    
}
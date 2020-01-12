package ru.fortochkin.directoriesandfiles.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="dir")
public class DirEntity implements Serializable,Comparable<DirEntity>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    private Date date;
    
    @Column(name = "baseDir", nullable = false)
    String baseDir;
    
    @OneToMany(mappedBy="dir", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DirEntryEntity> entries;
    
    @Transient
    Long dirsCount;
    
    @Transient
    Long filesCount;
    
    @Transient
    Long contentSize;
    
    public boolean equals(DirEntryEntity e){
        return Objects.equals(this.id, e.id);
    }
        
    @Override
    public int compareTo(DirEntity o) {
        if (this.equals(o)){
            return 0;
        }
        
        Pattern pattern = Pattern.compile("\\d+");
        Matcher thisMatcher = pattern.matcher(this.getBaseDir());
        Matcher oMatcher = pattern.matcher(o.getBaseDir());

        while(thisMatcher.find() && oMatcher.find()){
            if (thisMatcher.start() == oMatcher.start()){
                int thisValue = Integer.valueOf(thisMatcher.group());
                int otherValue = Integer.valueOf(oMatcher.group());
                if (thisValue > otherValue){
                    return -1;
                }else if (thisValue < otherValue){
                    return 1;
                }
            }
        }
        return this.getBaseDir().toLowerCase().compareTo(o.getBaseDir().toLowerCase());
    }
    
    
}
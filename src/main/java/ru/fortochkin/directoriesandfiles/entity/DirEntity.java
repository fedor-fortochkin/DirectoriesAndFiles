package ru.fortochkin.directoriesandfiles.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

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
    private Date date;
    
    @Column(name = "baseDir", nullable = false)
    String baseDir;
    
    @OneToMany(mappedBy="dir", cascade = CascadeType.ALL)
    private Set<DirEntryEntity> entries;
    
    @Transient
    Integer dirsCount;
    
    @Transient
    Integer filesCount;
    
    @Transient
    Integer contentSize;
    
    public boolean equals(DirEntryEntity e){
        return this.id == e.id;
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
                    return 1;
                }else if (thisValue < otherValue){
                    return -1;
                }
            }
        }
        return this.getBaseDir().toLowerCase().compareTo(o.getBaseDir().toLowerCase());
    }
    
    
}
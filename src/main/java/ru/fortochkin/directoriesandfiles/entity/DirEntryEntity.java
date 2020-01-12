package ru.fortochkin.directoriesandfiles.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name="dir_entry")
public class DirEntryEntity implements Serializable, Comparable<DirEntryEntity>{

    
    public enum Type{
        FILE,DIRECTORY
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    
    @Column(name = "name", nullable = false)
    String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    Type type;
    
    @ManyToOne
    @JoinColumn(name = "dir_id", nullable=false)
    @JsonIgnore
    DirEntity dir;
    
    @Column(name = "size")
    Long size;
    
    
    public boolean equals(DirEntryEntity e){
        return this.id == e.id;
    }
    
    @Override
    public int compareTo(DirEntryEntity o) {
        if (this.equals(o)){
            return 0;
        }
        
        if (this.getType() == DirEntryEntity.Type.DIRECTORY && o.getType() == DirEntryEntity.Type.FILE){
            return 1;
        }else if(this.getType() == DirEntryEntity.Type.FILE && o.getType() == DirEntryEntity.Type.DIRECTORY){
            return -1;
        }else{
            Pattern pattern = Pattern.compile("\\d+");
            Matcher thisMatcher = pattern.matcher(this.getName());
            Matcher oMatcher = pattern.matcher(o.getName());
            
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
            return this.getName().toLowerCase().compareTo(o.getName().toLowerCase());
        }
    }
}
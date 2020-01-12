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
@With(AccessLevel.PRIVATE)
@Table(name="dir_entry")
public class DirEntryEntity implements Serializable,Comparable<DirEntryEntity>{
    enum Type{
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
    public int compareTo(DirEntryEntity e) {
        if (this.equals(e)){
            return 0;
        }
        
        if (this.type == Type.DIRECTORY && e.getType() == Type.FILE){
            return 1;
        }else if(this.type == Type.FILE && e.getType() == Type.DIRECTORY){
            return -1;
        }else{
            Pattern pattern = Pattern.compile("^[^0-9]*([0-9]+)[^0-9]*$");
            Matcher thisMatcher = pattern.matcher(this.name);
            Matcher otherMatcher = pattern.matcher(e.getName());
            
            while(!thisMatcher.hitEnd() || !otherMatcher.hitEnd()){
                thisMatcher.find();
                otherMatcher.find();
                if (thisMatcher.start() == otherMatcher.start()){
                    int thisValue = Integer.valueOf(thisMatcher.group());
                    int otherValue = Integer.valueOf(otherMatcher.group());
                    if (thisValue > otherValue){
                        return 1;
                    }else if (thisValue < otherValue){
                        return -1;
                    }
                }
            }
            return this.name.toLowerCase().compareTo(e.getName().toLowerCase());
        }
    }
}
package ru.fortochkin.directoriesandfiles.entity;

import java.util.Date;
import java.util.Set;
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
import lombok.AccessLevel;
import lombok.Data;
import lombok.With;

@Entity
@Data
@With(AccessLevel.PRIVATE)
@Table(name="dir")
public class DirEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;
    
    @Column(name = "baseDir", nullable = false)
    String baseDir;
    
    @OneToMany(mappedBy="dir")
    private Set<DirEntryEntity> entries;
    
    @Transient
    Integer dirsCount;
    
    @Transient
    Integer filesCount;
    
    @Transient
    Integer contentSize;
    
}
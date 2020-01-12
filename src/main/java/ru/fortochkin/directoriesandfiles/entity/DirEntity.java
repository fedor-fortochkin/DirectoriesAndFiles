package ru.fortochkin.directoriesandfiles.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
@With(AccessLevel.PRIVATE)
@Table(name="dir")
public class DirEntity{
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
    
}
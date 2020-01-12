package ru.fortochkin.directoriesandfiles.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.With;

@Entity
@Data
@With(AccessLevel.PRIVATE)
@Table(name="dir_entry")
public class DirEntryEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;
    
    
    @Column(name = "name", nullable = false)
    String name;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entry_type_id", referencedColumnName = "id")
    EntryType type;
    
    @ManyToOne
    @JoinColumn(name="dir_id", nullable=false)
    private DirEntity dir;
    
    @Column(name = "size")
    Long size;
}
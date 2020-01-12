/*
 * Copyright (C) 2020 Pivotal Software, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.fortochkin.directoriesandfiles.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.With;

/**
 *
 * @author Fedor Fortochkin f_fortochkin@inbox.ru
 */
@Entity
@Data
@With(AccessLevel.PRIVATE)
@Table(name="entry_type")
public class EntryType implements Serializable {
    
    enum Type{
        FILE,DIRECTORY
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    Type type;
    
    
    @OneToOne(mappedBy = "type")
    DirEntryEntity dirEntry;
    
}

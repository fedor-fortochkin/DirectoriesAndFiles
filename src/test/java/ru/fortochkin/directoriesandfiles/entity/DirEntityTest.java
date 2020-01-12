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

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Fedor Fortochkin f_fortochkin@inbox.ru
 */
public class DirEntityTest {
    
    @Test
    public void testSortIntegerInString(){        
        DirEntryEntity e1 = new DirEntryEntity(){{
            setType(DirEntryEntity.Type.DIRECTORY);
            setName("bb10056ccc");
            setId(1L);
        }};
        
        DirEntryEntity e2 = new DirEntryEntity(){{
            setType(DirEntryEntity.Type.DIRECTORY);
            setName("bb000576cc");
            setId(2L);
        }};
        
        DirEntryEntity e3 = new DirEntryEntity(){{
            setType(DirEntryEntity.Type.DIRECTORY);
            setName("bbb10056cc");
            setId(3L);
        }};
        
        DirEntryEntity e4 = new DirEntryEntity(){{
            setType(DirEntryEntity.Type.DIRECTORY);
            setName("bbb10056cc123sdfs");
            setId(4L);
        }};
        
        DirEntryEntity e5 = new DirEntryEntity(){{
            setType(DirEntryEntity.Type.DIRECTORY);
            setName("bbb10056cc0124qweq");
            setId(5L);
        }};
        
        DirEntryEntity e6 = new DirEntryEntity(){{
            setType(DirEntryEntity.Type.DIRECTORY);
            setName("bbb10056cc123sdfs");
            setId(6L);
        }};
        
        DirEntryEntity e7 = new DirEntryEntity(){{
            setType(DirEntryEntity.Type.DIRECTORY);
            setName("bbb010056555");
            setId(7L);
        }};
        
        Assert.assertTrue(e1.compareTo(e2) < 0);
        Assert.assertTrue(e2.compareTo(e3) < 0);
        Assert.assertTrue(e4.compareTo(e5) > 0);
        Assert.assertTrue(e6.compareTo(e7) > 0);
    }
    
}

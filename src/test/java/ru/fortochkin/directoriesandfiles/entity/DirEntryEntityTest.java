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

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import ru.fortochkin.directoriesandfiles.entity.DirEntryEntity.Type;

/**
 *
 * @author Fedor Fortochkin f_fortochkin@inbox.ru
 */
public class DirEntryEntityTest {
        
    @Test
    public void testSortIntegerInString(){        
        List<DirEntryEntity> expectation = new ArrayList<DirEntryEntity>(){{
            add(new DirEntryEntity(1L,"innerTemp",Type.DIRECTORY,null,null));
            add(new DirEntryEntity(2L,"X-FILES",Type.DIRECTORY,null,null));
            add(new DirEntryEntity(3L,"f.txt",Type.FILE,null,null));
            add(new DirEntryEntity(4L,"F1.txt",Type.FILE,null,null));
            add(new DirEntryEntity(5L,"f4_99.JPG",Type.FILE,null,null));
            add(new DirEntryEntity(6L,"f4_00127.pdf",Type.FILE,null,null));
            add(new DirEntryEntity(7L,"f0008.doc",Type.FILE,null,null));
            add(new DirEntryEntity(8L,"function.cpp",Type.FILE,null,null));
        }};
        
        List<DirEntryEntity> unsorted = new ArrayList<DirEntryEntity>(){{
            add(new DirEntryEntity(1L,"innerTemp",Type.DIRECTORY,null,null));
            add(new DirEntryEntity(6L,"f4_00127.pdf",Type.FILE,null,null));
            add(new DirEntryEntity(3L,"f.txt",Type.FILE,null,null));
            add(new DirEntryEntity(4L,"F1.txt",Type.FILE,null,null));
            add(new DirEntryEntity(2L,"X-FILES",Type.DIRECTORY,null,null));
            add(new DirEntryEntity(8L,"function.cpp",Type.FILE,null,null));
            add(new DirEntryEntity(5L,"f4_99.JPG",Type.FILE,null,null));
            add(new DirEntryEntity(7L,"f0008.doc",Type.FILE,null,null));
        }};
        
        unsorted.sort(null);
        Assert.assertArrayEquals(expectation.toArray(), unsorted.toArray());
    }
}

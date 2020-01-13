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
package ru.fortochkin.directoriesandfiles.service;

/**
 *
 * @author Fedor Fortochkin f_fortochkin@inbox.ru
 */


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fortochkin.directoriesandfiles.entity.DirEntity;
import ru.fortochkin.directoriesandfiles.entity.DirEntryEntity;
import ru.fortochkin.directoriesandfiles.entity.DirEntryEntity.Type;
import ru.fortochkin.directoriesandfiles.repository.DirEntryRepository;
import ru.fortochkin.directoriesandfiles.repository.DirRepository;

@Service
public class DirService {
    
    @Autowired
    DirRepository dirRepository;
    
    @Autowired
    DirEntryRepository dirEntryRepository;
    
    
    public List<DirEntity> getRootContent(){
        List<DirEntity> list = dirRepository.findAll();
        list.stream().forEach( item ->{
            enrichDirProperties(item);
        });
        list.sort(null);
        return list;
    }
    
    public void enrichDirProperties(DirEntity item){
                    Long sizeOfFiles = item.getEntries().stream()
                    .filter(e -> e.getType() == Type.FILE)
                    .map(a -> a.getSize())
                    .reduce(0L, Long::sum);
            Long filesCount = item.getEntries().stream()
                    .filter(e -> e.getType() == Type.FILE)
                    .count();
            Long dirsCount = item.getEntries().stream()
                    .filter(e -> e.getType() == Type.DIRECTORY)
                    .count();
            item.setDirsCount(dirsCount);
            item.setFilesCount(filesCount);
            item.setContentSize(sizeOfFiles);
    }
    
    public List<DirEntryEntity> getDirectoryContent(Long id){
        DirEntity dir = dirRepository.findById(id);
        List<DirEntryEntity> list = dirEntryRepository.findByDir(dir);
        list.sort(null);
        return list;
    }
    
    @Transactional
    public DirEntity addDirectory(String dir) throws IOException{
        Set<DirEntryEntity> list = new HashSet<>();
        DirEntity entity = new DirEntity();
        Files.newDirectoryStream(Paths.get(dir)).forEach(x -> {
           DirEntryEntity e = new DirEntryEntity();
           e.setName(x.getFileName().toString());
           e.setSize(x.toFile().isDirectory() ? null : x.toFile().length());
           e.setType(x.toFile().isDirectory() ? Type.DIRECTORY : Type.FILE);
           e.setDir(entity);
           list.add(e);
        });
        entity.setBaseDir(dir);
        entity.setContentSize(0L);
        entity.setDirsCount(0L);
        entity.setEntries(list);
        entity.setFilesCount(0L);
        entity.setDate(new Date());
        enrichDirProperties(entity);
        return dirRepository.save(entity);
    } 
}

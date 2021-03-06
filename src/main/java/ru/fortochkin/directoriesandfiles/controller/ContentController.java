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
package ru.fortochkin.directoriesandfiles.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.fortochkin.directoriesandfiles.entity.DirEntity;
import ru.fortochkin.directoriesandfiles.entity.DirEntryEntity;
import ru.fortochkin.directoriesandfiles.service.DirService;

/**
 *
 * @author Fedor Fortochkin f_fortochkin@inbox.ru
 */
@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    DirService dirService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<DirEntity> getRootContent(){
        return dirService.getRootContent();
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public DirEntity getDirectoryContent(@RequestBody String newDir) throws IOException{
        return dirService.addDirectory(newDir);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<DirEntryEntity> getDirectoryContent(@PathVariable Long id){
        return dirService.getDirectoryContent(id);
    }
}

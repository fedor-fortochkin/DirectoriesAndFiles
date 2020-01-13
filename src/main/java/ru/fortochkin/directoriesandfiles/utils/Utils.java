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
package ru.fortochkin.directoriesandfiles.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;
import ru.fortochkin.directoriesandfiles.entity.DirEntryEntity;
import ru.fortochkin.directoriesandfiles.entity.DirEntryEntity.Type;

/**
 *
 * @author Fedor Fortochkin f_fortochkin@inbox.ru
 */

@UtilityClass
public class Utils {
    public static int compare(Type type1, String name1, Type type2, String name2){
        if (type1 == DirEntryEntity.Type.DIRECTORY && type2 == DirEntryEntity.Type.FILE){
            return -1;
        }else if(type1 == DirEntryEntity.Type.FILE && type2 == DirEntryEntity.Type.DIRECTORY){
            return 1;
        }else{
            Pattern pattern = Pattern.compile("\\d{1,18}");
            Matcher thisMatcher = pattern.matcher(name1);
            Matcher oMatcher = pattern.matcher(name2);
            
            while(thisMatcher.find() && oMatcher.find()){
                if (thisMatcher.start() == oMatcher.start()){
                    long thisValue = Long.valueOf(thisMatcher.group());
                    long otherValue = Long.valueOf(oMatcher.group());
                    if (thisValue > otherValue){
                        return 1;
                    }else if (thisValue < otherValue){
                        return -1;
                    }
                }
            }
            return name1.toLowerCase().compareTo(name2.toLowerCase());
        }
    }
}

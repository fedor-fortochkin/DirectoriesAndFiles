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
package ru.fortochkin.directoriesandfiles.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fortochkin.directoriesandfiles.entity.DirEntity;
import ru.fortochkin.directoriesandfiles.entity.DirEntryEntity;

/**
 *
 * @author Fedor Fortochkin f_fortochkin@inbox.ru
 */
@Repository
public interface DirEntryRepository extends JpaRepository<DirEntryEntity, Long> {
    List<DirEntryEntity> findByDir(DirEntity e);
}

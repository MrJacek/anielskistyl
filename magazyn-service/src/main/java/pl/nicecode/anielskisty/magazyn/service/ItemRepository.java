/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.magazyn.service;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.nicecode.anielskisty.magazyn.entity.Item;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author jhojczak
 */
@RepositoryRestResource(collectionResourceRel = "item", path = "item")
public interface ItemRepository extends CrudRepository<Item, Integer> {
    List<Item> findByTitle(@Param("title") String name);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.magazyn.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jhojczak
 */
@Entity
@Table(name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = -3345479494366375876L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private String imgLink;

    private int allegroId;

    public int getAllegroId() {
        return allegroId;
    }

    public void setAllegroId(int allegroIid) {
        this.allegroId = allegroIid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    @Override
    public String toString() {
        return "Item [" + "id=" + id + ", title=" + title + ", imgLink=" + imgLink + ", allegroIid=" + allegroId + ']';
    }

}

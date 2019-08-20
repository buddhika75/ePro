/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ariyaratne_mhb2017
 */
public class ProjectCategory {
    Item category;
    List<ProjectSubCategory> projectSubcategories;

    public Item getCategory() {
        return category;
    }

    public void setCategory(Item category) {
        this.category = category;
    }

    public List<ProjectSubCategory> getProjectSubcategories() {
        if(projectSubcategories==null){
            projectSubcategories = new ArrayList<>();
        }
        return projectSubcategories;
    }

    public void setProjectSubcategories(List<ProjectSubCategory> projectSubcategories) {
        this.projectSubcategories = projectSubcategories;
    }
    
    
}
